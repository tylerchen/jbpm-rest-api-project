/*******************************************************************************
 * Copyright (c) Nov 24, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import static org.iff.jbpm.util.RestURI.*;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.BaseCryptHelper;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.NumberHelper;
import org.iff.jbpm.application.QueryServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.definition.QueryDefinitionVO;
import org.iff.jbpm.vo.definition.QueryFilterSpecVO;
import org.iff.jbpm.vo.instance.NodeInstanceVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.VariableInstanceVO;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 24, 2017
 */
@SuppressWarnings("unchecked")
//@Named("queryServicesApplication")
public class QueryServicesApplicationImpl implements QueryServicesApplication {

	public ProcessDefinitionVO findProcessByContainerIdProcessId(String containerId, String processId) {
		return JbpmRestHelper.xml(QUERY_URI, PROCESS_DEFINITIONS_BY_CONTAINER_ID_DEF_ID_GET_URI, null,
				ProcessDefinitionVO.class, containerId, processId);
	}

	public List<ProcessDefinitionVO> findProcesses(Integer page, Integer pageSize) {
		return findProcesses(page, pageSize, "", true);
	}

	public List<ProcessDefinitionVO> findProcessesById(String processId) {
		return JbpmRestHelper.xmlListFix(QUERY_URI, PROCESS_DEFINITIONS_BY_ID_GET_URI, null, ProcessDefinitionVO.class,
				"process-definitions", RPL_PD, processId);
	}

	public List<ProcessDefinitionVO> findProcesses(String filter, Integer page, Integer pageSize) {
		return findProcesses(filter, page, pageSize, "", true);
	}

	public List<ProcessDefinitionVO> findProcessesByContainerId(String containerId, Integer page, Integer pageSize) {
		return findProcessesByContainerId(containerId, page, pageSize, "", true);
	}

	public List<ProcessDefinitionVO> findProcesses(Integer page, Integer pageSize, String sort, boolean sortOrder) {
		return findProcesses("", page, pageSize, sort, sortOrder);
	}

	public List<ProcessDefinitionVO> findProcesses(String filter, Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"filter", filter, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, PROCESS_DEFINITIONS_BY_ID_GET_URI, conditions,
				ProcessDefinitionVO.class, "process-definitions", "");
	}

	public List<ProcessDefinitionVO> findProcessesByContainerId(String containerId, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, PROCESS_DEFINITIONS_BY_CONTAINER_ID_GET_URI, conditions,
				ProcessDefinitionVO.class, "process-definitions", containerId);
	}

	public List<ProcessInstanceVO> findProcessInstances(Integer page, Integer pageSize) {
		return findProcessInstances(page, pageSize, "", true);
	}

	//	public List<ProcessInstance> findProcessInstancesByCorrelationKey(CorrelationKey correlationKey, Integer page,
	//			Integer pageSize) {
	//
	//		return null;
	//	}

	public List<ProcessInstanceVO> findProcessInstancesByProcessId(String processId, List<Integer> status, Integer page,
			Integer pageSize) {
		return findProcessInstancesByProcessId(processId, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByProcessName(String processName, List<Integer> status,
			Integer page, Integer pageSize) {
		return findProcessInstancesByProcessName(processName, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByContainerId(String containerId, List<Integer> status,
			Integer page, Integer pageSize) {
		return findProcessInstancesByContainerId(containerId, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByStatus(List<Integer> status, Integer page, Integer pageSize) {
		return findProcessInstancesByStatus(status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByInitiator(String initiator, List<Integer> status, Integer page,
			Integer pageSize) {
		return findProcessInstancesByInitiator(initiator, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByVariable(String variableName, List<Integer> status,
			Integer page, Integer pageSize) {
		return findProcessInstancesByVariable(variableName, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByVariableAndValue(String variableName, String variableValue,
			List<Integer> status, Integer page, Integer pageSize) {
		return findProcessInstancesByVariableAndValue(variableName, variableValue, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstances(Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, PROCESS_INSTANCES_GET_URI, conditions, ProcessInstanceVO.class,
				"process-instance-list", "");
	}

	//	public List<ProcessInstance> findProcessInstancesByCorrelationKey(CorrelationKey correlationKey, Integer page,
	//			Integer pageSize, String sort, boolean sortOrder) {
	//
	//		return null;
	//	}

	public List<ProcessInstanceVO> findProcessInstancesByProcessId(String processId, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, PROCESS_INSTANCES_BY_PROCESS_ID_GET_URI, conditions,
				ProcessInstanceVO.class, "process-instance-list", processId);
	}

	public List<ProcessInstanceVO> findProcessInstancesByProcessName(String processName, List<Integer> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		return findProcessInstancesByProcessName(processName, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByContainerId(String containerId, List<Integer> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		return findProcessInstancesByContainerId(containerId, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByStatus(List<Integer> status, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		return findProcessInstancesByStatus(status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByInitiator(String initiator, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder) {
		return findProcessInstancesByInitiator(initiator, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByVariable(String variableName, List<Integer> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		return findProcessInstancesByVariable(variableName, status, page, pageSize, "", true);
	}

	public List<ProcessInstanceVO> findProcessInstancesByVariableAndValue(String variableName, String variableValue,
			List<Integer> status, Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"varValue", BaseCryptHelper.urlEncode(variableValue), //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, PROCESS_INSTANCE_BY_VAR_NAME_GET_URI, conditions,
				ProcessInstanceVO.class, "process-instance-list", variableName);
	}

	public ProcessInstanceVO findProcessInstanceById(Long processInstanceId) {
		return JbpmRestHelper.xml(QUERY_URI, PROCESS_INSTANCE_BY_INSTANCE_ID_GET_URI, null, ProcessInstanceVO.class,
				processInstanceId);
	}

	public ProcessInstanceVO findProcessInstanceById(Long processInstanceId, boolean withVars) {
		Map<String, String> conditions = MapHelper.toMap("withVars", String.valueOf(withVars));
		return JbpmRestHelper.xml(QUERY_URI, PROCESS_INSTANCE_BY_INSTANCE_ID_GET_URI, conditions,
				ProcessInstanceVO.class, processInstanceId);
	}

	//	public ProcessInstance findProcessInstanceByCorrelationKey(CorrelationKey correlationKey) {
	//
	//		return null;
	//	}

	public NodeInstanceVO findNodeInstanceByWorkItemId(Long processInstanceId, Long workItemId) {
		return JbpmRestHelper.xml(QUERY_URI, NODE_INSTANCES_BY_WORK_ITEM_ID_GET_URI, null, NodeInstanceVO.class,
				processInstanceId, workItemId);
	}

	public List<NodeInstanceVO> findActiveNodeInstances(Long processInstanceId, Integer page, Integer pageSize) {
		return findNodeInstancesWithConditions(MapHelper.toMap("activeOnly", "true"), processInstanceId, page,
				pageSize);
	}

	public List<NodeInstanceVO> findCompletedNodeInstances(Long processInstanceId, Integer page, Integer pageSize) {
		return findNodeInstancesWithConditions(MapHelper.toMap("completedOnly", "true"), processInstanceId, page,
				pageSize);
	}

	public List<NodeInstanceVO> findNodeInstances(Long processInstanceId, Integer page, Integer pageSize) {
		return findNodeInstancesWithConditions(null, processInstanceId, page, pageSize);
	}

	List<NodeInstanceVO> findNodeInstancesWithConditions(Map<String, String> conditions, Long processInstanceId,
			Integer page, Integer pageSize) {
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, NODE_INSTANCES_BY_INSTANCE_ID_GET_URI, conditions,
				NodeInstanceVO.class, "node-instance-list", processInstanceId);
	}

	public List<VariableInstanceVO> findVariablesCurrentState(Long processInstanceId) {
		return JbpmRestHelper.xmlList(QUERY_URI, VAR_INSTANCES_BY_INSTANCE_ID_GET_URI, null, VariableInstanceVO.class,
				"variable-instance-list", processInstanceId);
	}

	public List<VariableInstanceVO> findVariableHistory(Long processInstanceId, String variableName, Integer page,
			Integer pageSize) {
		Map<String, String> conditions = MapHelper.toMap(//
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, VAR_INSTANCES_BY_VAR_INSTANCE_ID_GET_URI, conditions,
				VariableInstanceVO.class, "variable-instance-list", processInstanceId, variableName);
	}

	public void registerQuery(QueryDefinitionVO queryDefinition) {
		Exceptions.runtime("NOT SUPPORT!!!");
	}

	public void replaceQuery(QueryDefinitionVO queryDefinition) {
		Exceptions.runtime("NOT SUPPORT!!!");
	}

	public void unregisterQuery(String queryName) {
		Exceptions.runtime("NOT SUPPORT!!!");
	}

	public QueryDefinitionVO getQuery(String queryName) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	public List<QueryDefinitionVO> getQueries(Integer page, Integer pageSize) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	public <T> List<T> query(String queryName, String mapper, Integer page, Integer pageSize, Class<T> resultType) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	public <T> List<T> query(String queryName, String mapper, String orderBy, Integer page, Integer pageSize,
			Class<T> resultType) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	public <T> List<T> query(String queryName, String mapper, QueryFilterSpecVO filterSpec, Integer page,
			Integer pageSize, Class<T> resultType) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	public <T> List<T> query(String queryName, String mapper, String builder, Map<String, Object> parameters,
			Integer page, Integer pageSize, Class<T> resultType) {
		Exceptions.runtime("NOT SUPPORT!!!");
		return null;
	}

	//	public void setResponseHandler(ResponseHandler responseHandler) {
	//
	//	}

}
