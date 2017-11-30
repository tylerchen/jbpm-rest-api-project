/*******************************************************************************
 * Copyright (c) Nov 23, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.mybatis.plugin.Page;
import org.iff.jbpm.application.JbpmRestApplication;
import org.iff.jbpm.application.JobServicesApplication;
import org.iff.jbpm.application.ProcessServicesApplication;
import org.iff.jbpm.application.QueryServicesApplication;
import org.iff.jbpm.application.UiServicesApplication;
import org.iff.jbpm.application.UserTaskServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.instance.NodeInstanceVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.TaskInstanceVO;
import org.iff.jbpm.vo.instance.TaskSummaryVO;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 23, 2017
 */
//@Named("jbpmRestApplication")
@SuppressWarnings("rawtypes")
public class JbpmRestApplicationImpl implements JbpmRestApplication {

	//@Inject
	JobServicesApplication jobServicesApplication;
	//@Inject
	ProcessServicesApplication processServicesApplication;
	//@Inject
	QueryServicesApplication queryServicesApplication;
	//@Inject
	UiServicesApplication uiServicesApplication;
	//@Inject
	UserTaskServicesApplication userTaskServicesApplication;

	public Page<?> pageFindProcessDefinition(Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessDefinitionVO> list = queryServicesApplication.findProcesses(page.getCurrentPage() - 1,
				page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public ProcessDefinitionVO getProcessDefinition(String processDefinitionId) {
		List<ProcessDefinitionVO> list = queryServicesApplication.findProcessesById(processDefinitionId);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	public String getProcessDefinitionImageSvg(String containerId, String processDefinitionId) {
		String svg = uiServicesApplication.getProcessImage(containerId, processDefinitionId);
		return svg;
	}

	public byte[] getProcessDefinitionImagePng(String containerId, String processDefinitionId) {
		String svg = uiServicesApplication.getProcessImage(containerId, processDefinitionId);
		return JbpmRestHelper.convertToPng(svg);
	}

	public Page<?> pageFindProcessInstance(Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstances(page.getCurrentPage() - 1,
				page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindProcessInstance(String processDefinitionId, Page<?> page) {
		return this.pageFindProcessInstance(processDefinitionId, null, page);
	}

	public Page<?> pageFindProcessInstance(String processDefinitionId, List<Integer> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstancesByProcessId(processDefinitionId,
				status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindProcessInstanceByName(String processName, List<Integer> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstancesByProcessName(processName, status,
				page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindProcessInstanceByInitiator(String initiator, List<Integer> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstancesByInitiator(initiator, status,
				page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindProcessInstanceByVariable(String variable, List<Integer> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstancesByVariable(variable, status,
				page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindProcessInstanceByVariableAndValue(String variable, String value, List<Integer> status,
			Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<ProcessInstanceVO> list = queryServicesApplication.findProcessInstancesByVariableAndValue(variable, value,
				status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public String getProcessInstanceImageSvg(String containerId, Long processInstanceId) {
		String svg = uiServicesApplication.getProcessInstanceImage(containerId, processInstanceId);
		return svg;
	}

	public byte[] getProcessInstanceImagePng(String containerId, Long processInstanceId) {
		String svg = uiServicesApplication.getProcessInstanceImage(containerId, processInstanceId);
		return JbpmRestHelper.convertToPng(svg);
	}

	public ProcessInstanceVO startProcess(String containerId, String processDefinitionId,
			Map<String, String> parameters) {
		Long id = processServicesApplication.startProcess(containerId, processDefinitionId, parameters);
		return getProcessInstance(containerId, id);
	}

	public ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId) {
		return processServicesApplication.getProcessInstance(containerId, processInstanceId);
	}

	public void signalProcessInstance(String containerId, String type, Object event, long processInstanceId) {
		processServicesApplication.signalProcessInstance(containerId, processInstanceId, type, event);
	}

	public void abortProcessInstance(String containerId, long processInstanceId) {
		processServicesApplication.abortProcessInstance(containerId, processInstanceId);
	}

	public void setProcessVariable(String containerId, Long processInstanceId, String variableId, String value) {
		processServicesApplication.setProcessVariable(containerId, processInstanceId, variableId, value);
	}

	public void setProcessVariables(String containerId, Long processInstanceId, Map<String, String> variables) {
		processServicesApplication.setProcessVariables(containerId, processInstanceId, variables);
	}

	public Object getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName) {
		return processServicesApplication.getProcessInstanceVariable(containerId, processInstanceId, variableName);
	}

	public Map<String, String> getProcessInstanceVariables(String containerId, Long processInstanceId) {
		return processServicesApplication.getProcessInstanceVariables(containerId, processInstanceId);
	}

	public List<String> getAvailableSignals(String containerId, Long processInstanceId) {
		return processServicesApplication.getAvailableSignals(containerId, processInstanceId);
	}

	//	public void completeWorkItem(String containerId, Long processInstanceId, Long id, Map<String, String> results) {
	//		processServicesApplication.completeWorkItem(containerId, processInstanceId, id, results);
	//	}
	//
	//	public void abortWorkItem(String containerId, Long processInstanceId, Long id) {
	//		processServicesApplication.abortWorkItem(containerId, processInstanceId, id);
	//	}
	//
	//	public WorkItemInstanceVO getWorkItem(String containerId, Long processInstanceId, Long id) {
	//		return processServicesApplication.getWorkItem(containerId, processInstanceId, id);
	//	}
	//
	//	public List<WorkItemInstanceVO> getWorkItemByProcessInstance(String containerId, Long processInstanceId) {
	//		return processServicesApplication.getWorkItemByProcessInstance(containerId, processInstanceId);
	//	}

	public Page<?> pageFindTasksAssignedAsPotentialOwner(String userId, String filter, List<String> groups,
			List<String> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}

		if (filter == null) {
			List<TaskSummaryVO> list = userTaskServicesApplication.findTasksAssignedAsPotentialOwner(userId, groups,
					status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
			page.setRows(list);
			page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		} else {
			List<TaskSummaryVO> list = userTaskServicesApplication.findTasksAssignedAsPotentialOwner(userId, filter,
					status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
			page.setRows(list);
			page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		}
		return page;
	}

	public Page<?> pageFindTasksAssignedAsBusinessAdministrator(String userId, List<String> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<TaskSummaryVO> list = userTaskServicesApplication.findTasksAssignedAsBusinessAdministrator(userId, status,
				page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindTasksOwned(String userId, List<String> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<TaskSummaryVO> list = userTaskServicesApplication.findTasksOwned(userId, status, page.getCurrentPage() - 1,
				page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindTasks(String userId, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<TaskSummaryVO> list = userTaskServicesApplication.findTasks(userId, page.getCurrentPage() - 1,
				page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<TaskSummaryVO> list = userTaskServicesApplication.findTasksByStatusByProcessInstanceId(processInstanceId,
				status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Page<?> page) {
		page = Page.notNullPage(page);
		String name = "";
		boolean order = false;
		{
			List<LinkedHashMap> sortList = page.getOrderBy();
			LinkedHashMap orderBy = sortList == null || sortList.isEmpty() ? null : sortList.get(0);
			name = MapUtils.getString(orderBy, "name", "");
			order = StringUtils.equalsIgnoreCase("asc", MapUtils.getString(orderBy, "order", "asc"));
		}
		List<TaskSummaryVO> list = userTaskServicesApplication.findTasksByVariableAndValue(userId, variableName,
				variableValue, status, page.getCurrentPage() - 1, page.getPageSize(), name, order);
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Map<String, String> getTaskInputContentByTaskId(String containerId, Long taskId) {
		return userTaskServicesApplication.getTaskInputContentByTaskId(containerId, taskId);
	}

	public Map<String, String> getTaskOutputContentByTaskId(String containerId, Long taskId) {
		return userTaskServicesApplication.getTaskOutputContentByTaskId(containerId, taskId);
	}

	public TaskInstanceVO getTaskInstance(String containerId, Long taskId, boolean withInputs, boolean withOutputs,
			boolean withAssignments) {
		return userTaskServicesApplication.getTaskInstance(containerId, taskId, withInputs, withOutputs,
				withAssignments);
	}

	public void claimTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.claimTask(containerId, taskId, userId);
	}

	public void startTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.startTask(containerId, taskId, userId);
	}

	public void stopTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.stopTask(containerId, taskId, userId);
	}

	public void releaseTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.releaseTask(containerId, taskId, userId);
	}

	public void suspendTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.suspendTask(containerId, taskId, userId);
	}

	public void resumeTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.resumeTask(containerId, taskId, userId);
	}

	public void skipTask(String containerId, long taskId, String userId) {
		userTaskServicesApplication.skipTask(containerId, taskId, userId);
	}

	public void delegateTask(String containerId, long taskId, String userId, String targetUserId) {
		userTaskServicesApplication.delegateTask(containerId, taskId, userId, targetUserId);
	}

	public void completeTask(String containerId, long taskId, String userId, Map<String, String> results) {
		userTaskServicesApplication.completeTask(containerId, taskId, userId, results);
	}

	//	public void updateTask(String containerId, Long taskId, String userId, TaskInstanceVO updatedTask) {
	//		userTaskServicesApplication.updateTask(containerId, taskId, userId, updatedTask);
	//	}

	public Long saveTaskContent(String containerId, Long taskId, Map<String, String> values) {
		return userTaskServicesApplication.saveTaskContent(containerId, taskId, values);
	}

	public Page<?> pageFindActiveNodeInstances(String containerId, Long processInstanceId, Page<?> page) {
		page = Page.notNullPage(page);
		List<NodeInstanceVO> list = processServicesApplication.findActiveNodeInstances(containerId, processInstanceId,
				page.getCurrentPage() - 1, page.getPageSize());
		Collections.sort(list, new Comparator<NodeInstanceVO>() {
			public int compare(NodeInstanceVO o1, NodeInstanceVO o2) {
				return (o1 == null && o2 == null) ? 0
						: (o1 == null) ? 1//
								: (o2 == null) ? -1//
										: (int) (o2.getDate().getTime() - o1.getDate().getTime());
			}
		});
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindCompletedNodeInstances(String containerId, Long processInstanceId, Page<?> page) {
		page = Page.notNullPage(page);
		List<NodeInstanceVO> list = processServicesApplication.findCompletedNodeInstances(containerId,
				processInstanceId, page.getCurrentPage() - 1, page.getPageSize());
		Collections.sort(list, new Comparator<NodeInstanceVO>() {
			public int compare(NodeInstanceVO o1, NodeInstanceVO o2) {
				return (o1 == null && o2 == null) ? 0
						: (o1 == null) ? 1//
								: (o2 == null) ? -1//
										: (int) (o2.getDate().getTime() - o1.getDate().getTime());
			}
		});
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

	public Page<?> pageFindNodeInstances(String containerId, Long processInstanceId, Page<?> page) {
		page = Page.notNullPage(page);
		List<NodeInstanceVO> list = processServicesApplication.findNodeInstances(containerId, processInstanceId,
				page.getCurrentPage() - 1, page.getPageSize());
		Collections.sort(list, new Comparator<NodeInstanceVO>() {
			public int compare(NodeInstanceVO o1, NodeInstanceVO o2) {
				return (o1 == null && o2 == null) ? 0
						: (o1 == null) ? 1//
								: (o2 == null) ? -1//
										: (int) (o2.getDate().getTime() - o1.getDate().getTime());
			}
		});
		page.setRows(list);
		page.setTotalCount(page.getOffset() + list.size() / page.getPageSize());
		return page;
	}

}
