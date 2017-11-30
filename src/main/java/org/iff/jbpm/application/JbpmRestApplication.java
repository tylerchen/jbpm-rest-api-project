/*******************************************************************************
 * Copyright (c) Nov 23, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application;

import java.util.List;
import java.util.Map;

import org.iff.infra.util.mybatis.plugin.Page;
import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.TaskInstanceVO;

/**
 * @see http://kie-server-ip:port/kie-server/docs/
 * @see https://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/
 * @see http://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/#_jbpmtasklifecycle
 * @see http://docs.jboss.org/jbpm/release/7.4.1.Final/jbpm-docs/html_single/#_task_permissions_matrix
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 23, 2017
 */
public interface JbpmRestApplication {

	/**
	 * 查询所有流程。
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessDefinition(Page<?> page);

	/**
	 * 拿到指定流程。
	 * @param processDefinitionId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	ProcessDefinitionVO getProcessDefinition(String processDefinitionId);

	/**
	 * 拿到流程的SVG图片。
	 * @param containerId
	 * @param processDefinitionId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	String getProcessDefinitionImageSvg(String containerId, String processDefinitionId);

	/**
	 * 拿到流程的PNG图片，建议还是使用SVG，因为使用程序转SVG-PNG时间长。
	 * @param containerId
	 * @param processDefinitionId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	byte[] getProcessDefinitionImagePng(String containerId, String processDefinitionId);

	/**
	 * 查询所有流程实例（所有状态的）。
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstance(Page<?> page);

	/**
	 * 查询指定流程的流程实例。
	 * @param processDefinitionId
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstance(String processDefinitionId, Page<?> page);

	/**
	 * 查询指定ID、状态的流程实例。
	 * @param processDefinitionId
	 * @param status org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstance(String processDefinitionId, List<Integer> status, Page<?> page);

	/**
	 * 查询指定状态、名称的流程实例。
	 * @param processName
	 * @param status org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstanceByName(String processName, List<Integer> status, Page<?> page);

	/**
	 * 查询指定发起人、状态的流程实例。
	 * @see org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @see org.iff.jbpm.util.RestURI.CONST_SORT_BY*
	 * @param initiator 
	 * @param status org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstanceByInitiator(String initiator, List<Integer> status, Page<?> page);

	/**
	 * 查询指定变量名称、状态的流程实例。
	 * @see org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @see org.iff.jbpm.util.RestURI.CONST_SORT_BY*
	 * @param variable
	 * @param status org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstanceByVariable(String variable, List<Integer> status, Page<?> page);

	/**
	 * 查询指定变量名称、变量值、状态的流程实例。
	 * @see org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @see org.iff.jbpm.util.RestURI.CONST_SORT_BY*
	 * @param variable
	 * @param value
	 * @param status org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindProcessInstanceByVariableAndValue(String variable, String value, List<Integer> status,
			Page<?> page);

	/**
	 * 拿到流程实例的SVG图片。
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	String getProcessInstanceImageSvg(String containerId, Long processInstanceId);

	/**
	 * 拿到流程的PNG图片，建议还是使用SVG，因为使用程序转SVG-PNG时间长。
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	byte[] getProcessInstanceImagePng(String containerId, Long processInstanceId);

	/**
	 * 启动指定流程。
	 * Start a new process instance.  The process (definition) that should
	 * be used is referenced by the given process id.  Parameters can be passed
	 * to the process instance (as name-value pairs), and these will be set
	 * as variables of the process instance.
	 * @param processDefinitionId
	 * @param parameters 最好传入Map<String,String>，因为数据返回时程序分不清楚是什么对象，拿到字符串后自行转换。
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	ProcessInstanceVO startProcess(String containerId, String processDefinitionId, Map<String, String> parameters);

	/**
	 * 取得指定流程。
	 * @param containerId
	 * @param processInstanceId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId);

	/**
	 * Signals the process instance that an event has occurred. The type parameter defines
	 * which type of event and the event parameter can contain additional information
	 * related to the event.  All node instances inside the given process instance that
	 * are listening to this type of (internal) event will be notified.  Note that the event
	 * will only be processed inside the given process instance.  All other process instances
	 * waiting for this type of event will not be notified.
	 * @param type org.iff.jbpm.util.RestURI.CONST_STATE_*
	 * @param event
	 * @param processInstanceId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void signalProcessInstance(String containerId, String type, Object event, long processInstanceId);

	/**
	 * 取消流程实例，删除流程实例。
	 * Aborts the process instance with the given id.  If the process instance has been completed
	 * (or aborted), or the process instance cannot be found, this method will throw an
	 * IllegalArgumentException.
	 *
	 * @param id the id of the process instance
	 */
	void abortProcessInstance(String containerId, long processInstanceId);

	void setProcessVariable(String containerId, Long processInstanceId, String variableId, String value);

	void setProcessVariables(String containerId, Long processInstanceId, Map<String, String> variables);

	Object getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName);

	Map<String, String> getProcessInstanceVariables(String containerId, Long processInstanceId);

	List<String> getAvailableSignals(String containerId, Long processInstanceId);

	//	void completeWorkItem(String containerId, Long processInstanceId, Long id, Map<String, String> results);
	//
	//	void abortWorkItem(String containerId, Long processInstanceId, Long id);
	//
	//	WorkItemInstanceVO getWorkItem(String containerId, Long processInstanceId, Long id);
	//
	//	List<WorkItemInstanceVO> getWorkItemByProcessInstance(String containerId, Long processInstanceId);

	/**
	 * 查询可以分配给指定用户的任务。
	 * @param userId
	 * @param filter
	 * @param groups
	 * @param status
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasksAssignedAsPotentialOwner(String userId, String filter, List<String> groups,
			List<String> status, Page<?> page);

	/**
	 * 查询分配给业务管理员的任务。
	 * @param userId
	 * @param status
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasksAssignedAsBusinessAdministrator(String userId, List<String> status, Page<?> page);

	/**
	 * 查询所有自己的任务。
	 * @param userId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param sortOrder
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasksOwned(String userId, List<String> status, Page<?> page);

	/**
	 * 查询所有的任务，管理用。
	 * @param userId
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasks(String userId, Page<?> page);

	/**
	 * 查询流程实例的任务。
	 * @param processInstanceId
	 * @param status
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status, Page<?> page);

	/**
	 * 查询流程实例的任务，通过变量查询。
	 * @param userId
	 * @param variableName
	 * @param variableValue
	 * @param status
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Page<?> page);

	/**
	 * 获得任务输入。
	 * @param containerId
	 * @param taskId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Map<String, String> getTaskInputContentByTaskId(String containerId, Long taskId);

	/**
	 * 获得任务输出。
	 * @param containerId
	 * @param taskId
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Map<String, String> getTaskOutputContentByTaskId(String containerId, Long taskId);

	/**
	 * 查询任务实例。
	 * @param containerId
	 * @param taskId
	 * @param withInputs
	 * @param withOutputs
	 * @param withAssignments
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	TaskInstanceVO getTaskInstance(String containerId, Long taskId, boolean withInputs, boolean withOutputs,
			boolean withAssignments);

	/**
	 * 获得任务处理权限，如果任务是以Group方式指派人员，那么在Group中的人需要获得Task才能处理。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void claimTask(String containerId, long taskId, String userId);

	/**
	 * 启动任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void startTask(String containerId, long taskId, String userId);

	/**
	 * 停止任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void stopTask(String containerId, long taskId, String userId);

	/**
	 * 释放任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void releaseTask(String containerId, long taskId, String userId);

	/**
	 * 暂停任务，挂起任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void suspendTask(String containerId, long taskId, String userId);

	/**
	 * 继续执行任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void resumeTask(String containerId, long taskId, String userId);

	/**
	 * 跳过任务，不执行当前任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void skipTask(String containerId, long taskId, String userId);

	/**
	 * 代理任务，指定任务代理人。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @param targetUserId
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void delegateTask(String containerId, long taskId, String userId, String targetUserId);

	/**
	 * 完成任务，结束任务。
	 * @param containerId
	 * @param taskId
	 * @param userId
	 * @param results
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	void completeTask(String containerId, long taskId, String userId, Map<String, String> results);

	/**
	 * 更新任务数据。
	 * @param containerId
	 * @param taskId
	 * @param values
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	//void updateTask(String containerId, Long taskId, String userId, TaskInstanceVO updatedTask);
	Long saveTaskContent(String containerId, Long taskId, Map<String, String> values);

	/**
	 * 查询所有活动节点的实例，可用于历史。
	 * @param containerId
	 * @param processInstanceId
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindActiveNodeInstances(String containerId, Long processInstanceId, Page<?> page);

	/**
	 * 查询所有已完成节点的实例，可用于历史。
	 * @param containerId
	 * @param processInstanceId
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindCompletedNodeInstances(String containerId, Long processInstanceId, Page<?> page);

	/**
	 * 查询所有节点的实例，可用于历史。
	 * @param containerId
	 * @param processInstanceId
	 * @param page
	 * @return
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since Nov 29, 2017
	 */
	Page<?> pageFindNodeInstances(String containerId, Long processInstanceId, Page<?> page);
}
