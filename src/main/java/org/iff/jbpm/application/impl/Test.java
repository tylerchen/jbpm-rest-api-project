/*******************************************************************************
 * Copyright (c) Nov 28, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.iff.infra.util.GsonHelper;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.mybatis.plugin.Page;
import org.iff.jbpm.application.JbpmRestApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.JbpmRestConfigVO;
import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.definition.UserTaskDefinitionVO;
import org.iff.jbpm.vo.instance.NodeInstanceVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.TaskInstanceVO;
import org.iff.jbpm.vo.instance.TaskSummaryVO;
import org.iff.jbpm.vo.instance.WorkItemInstanceVO;

import com.google.gson.Gson;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 28, 2017
 */
public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		singleUserTask();
	}

	/**
	 * 单用户流程，只采用一个用户登录JBPM，通过变量的方式与系统的中用户权限联系，通过业务系统的对流程变量的控制，以达到业务系统中的不同用户角色来流转流程。
	 * 这里以employee变量为例
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
					// 3.2 暂停任务
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.3 恢复任务
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
				// 这个列表是业务用户所看到的列表。
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
				// 这个列表是业务用户所看到的列表。
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

	/**
	 * 多用户流程，需要切换不用用户进行登录，如果用户体系没有与JBPM用户体系集成的话就会非常麻烦。【而且JbpmRestHelper只支持一个用户登录，不建议使用这个方式】
	 * 
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	public static void multiUserTask() {
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
					// 3.2 暂停任务
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.3 恢复任务
					jbpm.resumeTask(pi.getContainerId(), ts.getId(), "mary");
					// 3.4 完成任务
					jbpm.completeTask(pi.getContainerId(), ts.getId(), "mary",
							MapHelper.toMap("reason", "我要请假", "performance", "OK"));
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
			{// 4.执行：HR Evaluation(mary)
				Page<TaskSummaryVO> page = (Page<TaskSummaryVO>) jbpm.pageFindTasksAssignedAsPotentialOwner("mary",
						null, null, null, Page.pageable(10, 1, 0, null));
				System.out.println(GsonHelper.toJsonString(page.getRows()));
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
							MapHelper.toMap("reason", "批准HR", "performance", "OK"));
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
			if (System.currentTimeMillis() < 1) {// 5.执行：PM Evaluation(john)，需要切换登录，否则看不到数据。
				{
					JbpmRestHelper.init(JbpmRestConfigVO.Builder.create().user("john").password("john")
							.host("121.40.35.24").port(8080).contextUrl("/kie-server/services/rest/server/")
							.connectTimeout(10000).readTimeout(10000).build());
				}
				Page<TaskSummaryVO> page = (Page<TaskSummaryVO>) jbpm.pageFindTasksAssignedAsPotentialOwner("john",
						null, null, null, Page.pageable(10, 1, 0, null));
				System.out.println(GsonHelper.toJsonString(page.getRows()));
				for (TaskSummaryVO ts : page.getRows()) {
					// 4.1 获得任务
					jbpm.claimTask(pi.getContainerId(), ts.getId(), "john");
					// 4.2 启动任务
					jbpm.startTask(pi.getContainerId(), ts.getId(), "john");
					// 4.3 暂停任务
					jbpm.suspendTask(pi.getContainerId(), ts.getId(), "john");
					// 4.4 恢复任务
					jbpm.resumeTask(pi.getContainerId(), ts.getId(), "john");
					// 4.5 完成任务
					jbpm.completeTask(pi.getContainerId(), ts.getId(), "john",
							MapHelper.toMap("reason", "批准PM", "performance", "OK"));
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
}
