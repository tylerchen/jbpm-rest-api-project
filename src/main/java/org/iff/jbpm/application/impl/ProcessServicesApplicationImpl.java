/*******************************************************************************
 * Copyright (c) Nov 27, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import static org.iff.jbpm.util.RestURI.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.BaseCryptHelper;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.NumberHelper;
import org.iff.jbpm.application.ProcessServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.definition.AssociatedEntitiesDefinitionVO;
import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.definition.ServiceTasksDefinitionVO;
import org.iff.jbpm.vo.definition.SubProcessesDefinitionVO;
import org.iff.jbpm.vo.definition.TaskInputsDefinitionVO;
import org.iff.jbpm.vo.definition.TaskOutputsDefinitionVO;
import org.iff.jbpm.vo.definition.UserTaskDefinitionVO;
import org.iff.jbpm.vo.definition.VariablesDefinitionVO;
import org.iff.jbpm.vo.instance.NodeInstanceVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.VariableInstanceVO;
import org.iff.jbpm.vo.instance.WorkItemInstanceVO;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 27, 2017
 */
@SuppressWarnings({ "unchecked" })
//@Named("processServicesApplication")
public class ProcessServicesApplicationImpl implements ProcessServicesApplication {

	public ProcessDefinitionVO getProcessDefinition(String containerId, String processId) {
		ProcessDefinitionVO bean = JbpmRestHelper.xmlFix(PROCESS_DEF_URI, PROCESS_DEF_GET_URI, null,
				ProcessDefinitionVO.class, RPL_PD, containerId, processId);
		return bean;
	}

	public SubProcessesDefinitionVO getReusableSubProcessDefinitions(String containerId, String processId) {
		SubProcessesDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI, PROCESS_DEF_SUBPROCESS_GET_URI, null,
				SubProcessesDefinitionVO.class, containerId, processId);
		return bean;
	}

	public VariablesDefinitionVO getProcessVariableDefinitions(String containerId, String processId) {
		VariablesDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI, PROCESS_DEF_VARIABLES_GET_URI, null,
				VariablesDefinitionVO.class, containerId, processId);
		return bean;
	}

	public ServiceTasksDefinitionVO getServiceTaskDefinitions(String containerId, String processId) {
		ServiceTasksDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI, PROCESS_DEF_SERVICE_TASKS_GET_URI, null,
				ServiceTasksDefinitionVO.class, containerId, processId);
		return bean;
	}

	public AssociatedEntitiesDefinitionVO getAssociatedEntityDefinitions(String containerId, String processId) {
		AssociatedEntitiesDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI,
				PROCESS_DEF_ASSOCIATED_ENTITIES_GET_URI, null, AssociatedEntitiesDefinitionVO.class, containerId,
				processId);
		return bean;
	}

	public List<UserTaskDefinitionVO> getUserTaskDefinitions(String containerId, String processId) {
		Map<String, String> replace = MapHelper.toMap("task>", "user-task-definition>", "associatedEntities>",
				"string>");
		return JbpmRestHelper.xmlListFix(PROCESS_DEF_URI, PROCESS_DEF_USER_TASKS_GET_URI, null,
				UserTaskDefinitionVO.class, "user-task-definitions", replace, containerId, processId);
	}

	public TaskInputsDefinitionVO getUserTaskInputDefinitions(String containerId, String processId, String taskName) {
		TaskInputsDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI, PROCESS_DEF_USER_TASK_INPUT_GET_URI, null,
				TaskInputsDefinitionVO.class, containerId, processId, BaseCryptHelper.urlEncode(taskName));
		return bean;
	}

	public TaskOutputsDefinitionVO getUserTaskOutputDefinitions(String containerId, String processId, String taskName) {
		TaskOutputsDefinitionVO bean = JbpmRestHelper.xml(PROCESS_DEF_URI, PROCESS_DEF_USER_TASK_OUTPUT_GET_URI, null,
				TaskOutputsDefinitionVO.class, containerId, processId, BaseCryptHelper.urlEncode(taskName));
		return bean;
	}

	public Long startProcess(String containerId, String processId) {
		return startProcess(containerId, processId, new HashMap<String, String>());
	}

	public Long startProcess(String containerId, String processId, Map<String, String> variables) {
		Long id = JbpmRestHelper.post(PROCESS_URI, START_PROCESS_POST_URI, null, null, variables, Long.class,
				containerId, processId);
		return id;
	}

	public void abortProcessInstance(String containerId, Long processInstanceId) {
		String xml = JbpmRestHelper.del(PROCESS_URI, ABORT_PROCESS_INST_DEL_URI, null, null, null, null, containerId,
				processInstanceId);
		System.out.println(xml);
		if (StringUtils.contains(xml, "error")) {
			Exceptions.runtime(xml);
		}
	}

	public void abortProcessInstances(String containerId, List<Long> processInstanceIds) {
		Map<String, String> map = null;
		if (!(processInstanceIds == null || processInstanceIds.isEmpty())) {
			map = MapHelper.toMap("instanceId", StringUtils.join(processInstanceIds, "&instanceId="));
		}
		String xml = JbpmRestHelper.del(PROCESS_URI, ABORT_PROCESS_INSTANCES_DEL_URI, null, map, null, null,
				containerId);
		System.out.println(xml);
		if (StringUtils.contains(xml, "error")) {
			Exceptions.runtime(xml);
		}
	}

	public Object getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName) {
		return getProcessInstanceVariable(containerId, processInstanceId, variableName, Object.class);
	}

	public <T> T getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName,
			Class<T> type) {
		T bean = JbpmRestHelper.json(PROCESS_URI, PROCESS_INSTANCE_VAR_GET_URI, null, type, containerId,
				processInstanceId, variableName);
		return bean;
	}

	public Map<String, String> getProcessInstanceVariables(String containerId, Long processInstanceId) {
		Map<String, String> bean = JbpmRestHelper.json(PROCESS_URI, PROCESS_INSTANCE_VARS_GET_URI, null, Map.class,
				containerId, processInstanceId);
		return bean;
	}

	public void signalProcessInstance(String containerId, Long processInstanceId, String signalName, Object event) {
		Exceptions.runtime("NOT TEST!!!");
		String xml = JbpmRestHelper.post(PROCESS_URI, SIGNAL_PROCESS_INST_POST_URI, null, null, event, null,
				containerId, processInstanceId, signalName);
		System.out.println(xml);
	}

	public void signalProcessInstances(String containerId, List<Long> processInstanceId, String signalName,
			Object event) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> map = null;
		if (!(processInstanceId == null || processInstanceId.isEmpty())) {
			map = MapHelper.toMap("instanceId", StringUtils.join(processInstanceId, "&instanceId="));
		}
		String xml = JbpmRestHelper.post(PROCESS_URI, SIGNAL_PROCESS_INSTANCES_PORT_URI, null, map, event, null,
				containerId, signalName);
		System.out.println(xml);
	}

	public void signal(String containerId, String signalName, Object event) {
		Exceptions.runtime("NOT TEST!!!");
		String xml = JbpmRestHelper.post(PROCESS_URI, SIGNAL_PROCESS_INSTANCES_PORT_URI, null, null, event, null,
				containerId, signalName);
		System.out.println(xml);
	}

	public List<String> getAvailableSignals(String containerId, Long processInstanceId) {
		Exceptions.runtime("NOT TEST!!!");
		List<String> bean = JbpmRestHelper.json(PROCESS_URI, PROCESS_INSTANCE_SIGNALS_GET_URI, null, List.class,
				containerId, processInstanceId);
		return bean;
	}

	public void setProcessVariable(String containerId, Long processInstanceId, String variableId, String value) {
		String xml = JbpmRestHelper.put(PROCESS_URI, PROCESS_INSTANCE_VAR_PUT_URI, null, null, value, null, containerId,
				processInstanceId, variableId);
		System.out.println(xml);
	}

	public void setProcessVariables(String containerId, Long processInstanceId, Map<String, String> variables) {
		String xml = JbpmRestHelper.post(PROCESS_URI, PROCESS_INSTANCE_VARS_POST_URI, null, null, variables, null,
				containerId, processInstanceId);
		System.out.println(xml);
	}

	public ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId) {
		return JbpmRestHelper.xml(PROCESS_URI, PROCESS_INSTANCE_GET_URI, null, ProcessInstanceVO.class, containerId,
				processInstanceId);
	}

	public ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId, boolean withVars) {
		Map<String, String> conditions = MapHelper.toMap("withVars", String.valueOf(withVars));
		return JbpmRestHelper.xml(PROCESS_URI, PROCESS_INSTANCE_GET_URI, conditions, ProcessInstanceVO.class,
				containerId, processInstanceId);
	}

	public void completeWorkItem(String containerId, Long processInstanceId, Long id, Map<String, String> results) {
		Exceptions.runtime("NOT TEST!!!");
		String xml = JbpmRestHelper.put(PROCESS_URI, PROCESS_INSTANCE_WORK_ITEM_COMPLETE_PUT_URI, null, null, results,
				null, containerId, processInstanceId, id);
		System.out.println(xml);
	}

	public void abortWorkItem(String containerId, Long processInstanceId, Long id) {
		Exceptions.runtime("NOT TEST!!!");
		String xml = JbpmRestHelper.put(PROCESS_URI, PROCESS_INSTANCE_WORK_ITEM_ABORT_PUT_URI, null, null, null, null,
				containerId, processInstanceId, id);
		System.out.println(xml);
	}

	public WorkItemInstanceVO getWorkItem(String containerId, Long processInstanceId, Long id) {
		return JbpmRestHelper.xml(PROCESS_URI, PROCESS_INSTANCE_WORK_ITEM_BY_ID_GET_URI, null, WorkItemInstanceVO.class,
				containerId, processInstanceId, id);
	}

	public List<WorkItemInstanceVO> getWorkItemByProcessInstance(String containerId, Long processInstanceId) {
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCE_WORK_ITEMS_BY_PROC_INST_ID_GET_URI, null,
				WorkItemInstanceVO.class, "work-item-instance-list", containerId, processInstanceId);
	}

	public List<NodeInstanceVO> findActiveNodeInstances(String containerId, Long processInstanceId, Integer page,
			Integer pageSize) {
		Map<String, String> conditions = MapHelper.toMap(//
				"activeOnly", "true", //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCES_NODE_INSTANCES_GET_URI, conditions,
				NodeInstanceVO.class, "node-instance-list", containerId, processInstanceId);
	}

	public List<NodeInstanceVO> findCompletedNodeInstances(String containerId, Long processInstanceId, Integer page,
			Integer pageSize) {
		Map<String, String> conditions = MapHelper.toMap(//
				"completedOnly", "true", //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCES_NODE_INSTANCES_GET_URI, conditions,
				NodeInstanceVO.class, "node-instance-list", containerId, processInstanceId);
	}

	public List<NodeInstanceVO> findNodeInstances(String containerId, Long processInstanceId, Integer page,
			Integer pageSize) {
		Map<String, String> conditions = MapHelper.toMap(//
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCES_NODE_INSTANCES_GET_URI, conditions,
				NodeInstanceVO.class, "node-instance-list", containerId, processInstanceId);
	}

	public List<VariableInstanceVO> findVariablesCurrentState(String containerId, Long processInstanceId) {
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCE_VAR_INSTANCES_GET_URI, null,
				VariableInstanceVO.class, "variable-instance-list", containerId, processInstanceId);
	}

	public List<VariableInstanceVO> findVariableHistory(String containerId, Long processInstanceId, String variableName,
			Integer page, Integer pageSize) {
		Map<String, String> conditions = MapHelper.toMap(//
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCE_VAR_INSTANCE_BY_VAR_NAME_GET_URI, conditions,
				VariableInstanceVO.class, "variable-instance-list", containerId, processInstanceId, variableName);
	}

	public List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId,
			Integer page, Integer pageSize) {
		return findProcessInstancesByParent(containerId, parentProcessInstanceId, null, page, pageSize);
	}

	public List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId,
			List<Integer> status, Integer page, Integer pageSize) {
		return findProcessInstancesByParent(containerId, parentProcessInstanceId, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId,
			List<Integer> status, Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCES_BY_PARENT_GET_URI, conditions,
				ProcessInstanceVO.class, "process-instance-list", containerId, parentProcessInstanceId);
	}

	public List<ProcessInstanceVO> findProcessInstances(String containerId, Integer page, Integer pageSize) {
		return findProcessInstances(containerId, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstances(String containerId, Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(PROCESS_URI, PROCESS_INSTANCES_BY_CONTAINER_GET_URI, conditions,
				ProcessInstanceVO.class, "process-instance-list", containerId);
	}

}
