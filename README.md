JBPM 接口封装
=====

### 1. 项目结构说明

1. org.iff.jbpm.application: 所有封装的接口都在这里，关注接口JbpmRestApplication即可。
2. org.iff.jbpm.util: 接口工具类及常量
3. org.iff.jbpm.vo: 接口的VO对象

### 2. 使用说明

#### 2.1. 包名修改

本项目使用org.iff开头，项目组应该对应修改包名，以符合项目规范。

#### 2.2. 官方文档

官方API接口文档： http://kie-server-ip:port/kie-server/docs/，测试环境的：http://121.40.35.24:8080/kie-server/docs/

官方JBPM7使用手册：https://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/

任务状态(重要)：http://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/#_jbpmtasklifecycle

任务执行人权限(重要)：http://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/#_task_permissions_matrix

#### 2.3. JBPM7部署

项目组安排部署JBPM7.

#### 2.4. 本接口使用注意事项

***所有任务变量及数据都采用字符串形式，在流程设计时要注意，设置为String类型，实际类型可以由String自行转换***

***默认采用了单用户登录的方式，不集成业务用户及角色***

***应该采用Spring注解的方式进行注入Bean，Bean的注解已经注释了，请打开***

下面Maven的包版本可以改成项目的版本：

		<dependency>
			<groupId>org.iff</groupId>
			<artifactId>tc-util-project</artifactId>
			<version>1.0.19</version>
		</dependency>

直接把源码复制到项目中使用，***方便调试及改BUG***。

#### 2.4. 接口注解

重要接口：org.iff.jbpm.application.JbpmRestApplication

其他接口无注解，一是因为接口数据太多暂无时间，二是官方接口也无注解，三是从接口名称也可知道具体用途，要参看官方API接口文档。


#### 2.5. 流程知识

***请勿使用admin角色或用户创建流程，否则流程不能正常工作***，这是因为JBPM默认采用容器的用户进行登录，admin角色或用户是容器的管理员，有冲突。

流程关键内容有：流程定义ProcessDefinition，流程实例ProcessInstance，任务描述TaskSummary/任务实例TaskInstance，流程变量Variable，任务变量Variable。

流程过程: 取到流程定义 -> start创建流程实例 -> 取到任务 -> claim获得任务 -> start启动任务 -> 设置任务变量/流程变量 -> 结束任务 -> 流程结束。


#### 2.4. 测试用例

具体测试用例请看：org.iff.jbpm.application.impl.Test

以单用户流程为例说明具体应用。

单用户流程，只采用一个用户登录JBPM（只使用JBPM用户，不集成业务用户及角色），通过变量的方式与系统的中用户权限联系，通过业务系统的对流程变量的控制，以达到业务系统中的不同用户角色来流转流程。实现流程如下：

1. 流程每个任务节点需要设置两个输出（Output）变量用于存储用户及角色，如用户变量：bizUserId, 角色变量：bizRoleId。
2. 每个任务执行完成后，都要start下一个任务，并且设置bizUserId、bizRoleId。
3. 建议使用外置表单，需要使用外置表单变量来存储外置表单的ID，在流程设置任务节点时可以以常量方式配置，如表单ID变量：bizFormId。
4. 业务用户登录时，可以通过pageFindTasksByVariableAndValue，可查询自己的流程，或自己所属角色(或者部门)的流程。
5. 分配的是角色任务时，业务用户需要saveTaskContent，来把自己的用户ID设置进去，才能获得任务，如果要换人，也是调用这个方法。

请看下面例子：

	/**
	 * 单用户流程，只采用一个用户登录JBPM，通过变量的方式与系统的中用户权限联系，通过业务系统的对流程变量的控制，以达到业务系统中的不同用户角色来流转流程。
	 * 这里以performance变量为例
	 * 
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	public static void singleUserTask() {
		JbpmRestHelper.init(JbpmRestConfigVO.Builder.create().user("mary").password("mary").host("121.40.35.24")
				.port(8080).contextUrl("/kie-server/services/rest/server/").connectTimeout(10000).readTimeout(10000)
				.build());

		JbpmRestApplicationImpl jbpm = new JbpmRestApplicationImpl();
		{//实际使用的过程中应该使用Spring Bean的方式
			jbpm.jobServicesApplication = new JobServicesApplicationImpl();
			jbpm.processServicesApplication = new ProcessServicesApplicationImpl();
			jbpm.queryServicesApplication = new QueryServicesApplicationImpl();
			jbpm.uiServicesApplication = new UiServicesApplicationImpl();
			jbpm.userTaskServicesApplication = new UserTaskServicesApplicationImpl();
		}
		{
			/**
			 * <pre>
			 *                                     -> HR Evaluation
			 * evaluation: start-> Self Evaluation ->                -> end
			 *                                     -> PM Evaluation
			 * </pre>
			 * IT: jack
			 * PM: john
			 * HR: mary
			 */
			// 1.拿到一个流程
			ProcessDefinitionVO pd = jbpm.getProcessDefinition("evaluation");
			// 2.启动一个流程(mary)(ActorId=#{employee})
			ProcessInstanceVO pi = jbpm.startProcess(pd.getContainerId(), pd.getId(),
					MapHelper.toMap("employee", "mary"));
			{//打印历史信息
				Page<NodeInstanceVO> his = (Page<NodeInstanceVO>) jbpm.pageFindCompletedNodeInstances(
						pi.getContainerId(), pi.getId(), Page.pageable(100, 0, 0, null));
				System.out.println("====WorkItemInstanceVO===");
				for (NodeInstanceVO vo : his.getRows()) {
					System.out.println(vo);
				}
			}
			{// 3.执行：Self Evaluation(mary)
				Page<TaskSummaryVO> page = (Page<TaskSummaryVO>) jbpm.pageFindTasksAssignedAsPotentialOwner("mary",
						null, null, null, Page.pageable(10, 1, 0, null));
				System.out.println(GsonHelper.toJsonString(page.getRows()));
				for (TaskSummaryVO ts : page.getRows()) {
					// 3.1 启动任务
					jbpm.startTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.2 暂停任务(可选)
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.3 恢复任务(可选)
					jbpm.resumeTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.4 完成任务
					jbpm.completeTask(pi.getContainerId(), ts.getId(), "mary",
							MapHelper.toMap("reason", "我要请假", "performance", "OK", "reason", "tyler"));
					{//3.5 开启新的任务，搞了变量的问题整个过程要开启新的任务才算结束。
						Page<TaskSummaryVO> pageTask = (Page<TaskSummaryVO>) jbpm
								.pageFindTasksByStatusByProcessInstanceId(pi.getId(), null,
										Page.pageable(100, 0, 0, null));
						for (TaskSummaryVO newTs : pageTask.getRows()) {
							Map<String, String> input = jbpm.getTaskInputContentByTaskId(pi.getContainerId(),
									newTs.getId());
							Map<String, String> output = jbpm.getTaskOutputContentByTaskId(pi.getContainerId(),
									newTs.getId());
							// 3.5.1 启动任务
							jbpm.startTask(pi.getContainerId(), newTs.getId(), "mary");
							// 3.5.2 设置任务变量 - 这个可用于业务系统用户的任务查询
							Map<String, String> outputData = new HashMap<String, String>();
							// 3.5.3 设置特定的任务变量【指定用户变量、角色变量名称】作为输出，可以通过getTaskInputContentByTaskId，来获得节点的输入数据，通过业务系统的判断然后设置具体的执行用户的ID
							outputData.put("performance",
									"PM".equals(MapUtils.getString(input, "GroupId")) ? "tylerPM" : "tylerHR");
							jbpm.saveTaskContent(pi.getContainerId(), newTs.getId(), outputData);
						}
					}
				}
				{//打印历史信息
					Page<NodeInstanceVO> his = (Page<NodeInstanceVO>) jbpm.pageFindCompletedNodeInstances(
							pi.getContainerId(), pi.getId(), Page.pageable(100, 0, 0, null));
					System.out.println("====WorkItemInstanceVO===");
					for (NodeInstanceVO vo : his.getRows()) {
						System.out.println(vo);
					}
				}
			}
			{// 4.执行：HR Evaluation(mary)，这里要注意状态，任务还是mary执行，但是业务系统用户是通过查询变量值来查询任务的：performance=tylerHR
				// 这个列表是业务用户tylerHR所看到的列表。
				Page<TaskSummaryVO> page = (Page<TaskSummaryVO>) jbpm.pageFindTasksByVariableAndValue(null,
						"performance", "tylerHR", null, Page.pageable(100, 0, 0, null));
				for (TaskSummaryVO ts : page.getRows()) {
					// 4.1 获得任务
					jbpm.claimTask(pi.getContainerId(), ts.getId(), "mary");
					// 4.2 启动任务
					jbpm.startTask(pi.getContainerId(), ts.getId(), "mary");
					// 4.3 暂停任务
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "mary");
					// 4.4 恢复任务
					jbpm.resumeTask(pi.getContainerId(), ts.getId(), "mary");
					// 4.5 完成任务
					jbpm.completeTask(pi.getContainerId(), ts.getId(), "mary",
							MapHelper.toMap("reason", "批准HR", "performance", "tylerHR"));
				}
				{//打印历史信息
					Page<NodeInstanceVO> his = (Page<NodeInstanceVO>) jbpm.pageFindCompletedNodeInstances(
							pi.getContainerId(), pi.getId(), Page.pageable(100, 0, 0, null));
					System.out.println("====WorkItemInstanceVO===");
					for (NodeInstanceVO vo : his.getRows()) {
						System.out.println(vo);
					}
				}
			}
			{// 5.执行：PM Evaluation(mary)，这里要注意状态，任务还是mary执行，但是业务系统用户是通过查询变量值来查询任务的：performance=tylerPM
				// 这个列表是业务用户tylerPM所看到的列表。
				Page<TaskSummaryVO> page = (Page<TaskSummaryVO>) jbpm.pageFindTasksByVariableAndValue(null,
						"performance", "tylerPM", null, Page.pageable(100, 0, 0, null));
				for (TaskSummaryVO ts : page.getRows()) {
					// 5.1 获得任务
					jbpm.claimTask(pi.getContainerId(), ts.getId(), "mary");
					// 5.2 启动任务
					jbpm.startTask(pi.getContainerId(), ts.getId(), "mary");
					// 5.3 暂停任务
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "mary");
					// 5.4 恢复任务
					jbpm.resumeTask(pi.getContainerId(), ts.getId(), "mary");
					// 5.5 完成任务
					jbpm.completeTask(pi.getContainerId(), ts.getId(), "mary",
							MapHelper.toMap("reason", "批准PM", "performance", "tylerPM"));
				}
				{//打印历史信息
					Page<NodeInstanceVO> his = (Page<NodeInstanceVO>) jbpm.pageFindCompletedNodeInstances(
							pi.getContainerId(), pi.getId(), Page.pageable(100, 0, 0, null));
					System.out.println("====WorkItemInstanceVO===");
					for (NodeInstanceVO vo : his.getRows()) {
						System.out.println(vo);
					}
				}
			}
			//jbpm.abortProcessInstance(pi.getContainerId(), pi.getId());
		}
	}


### 3. 存在问题

接口内容太多，未能全部测试，或进行所有情况测试，难免有BUG。


### 4. 扩展

如果不喜欢这个封装接口，也可以使用官方的包：

		<dependency>
			<groupId>org.kie.server</groupId>
			<artifactId>kie-server-client</artifactId>
			<version>7.4.1.Final</version>
		</dependency>
		org.kie.server.client.KieServicesFactory.newKieServicesRestClient(String, String, String)
		org.kie.server.client.impl.KieServicesClientImpl.getServicesClient(Class<T>)
		org.kie.server.client.*


