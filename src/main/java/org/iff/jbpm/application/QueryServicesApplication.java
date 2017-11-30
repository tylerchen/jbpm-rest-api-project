/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.iff.jbpm.application;

import java.util.List;
import java.util.Map;

import org.iff.jbpm.vo.definition.ProcessDefinitionVO;
import org.iff.jbpm.vo.definition.QueryDefinitionVO;
import org.iff.jbpm.vo.definition.QueryFilterSpecVO;
import org.iff.jbpm.vo.instance.NodeInstanceVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.VariableInstanceVO;

public interface QueryServicesApplication {

	// runtime data searches
	ProcessDefinitionVO findProcessByContainerIdProcessId(String containerId, String processId);

	List<ProcessDefinitionVO> findProcessesById(String processId);

	List<ProcessDefinitionVO> findProcesses(Integer page, Integer pageSize);

	List<ProcessDefinitionVO> findProcesses(String filter, Integer page, Integer pageSize);

	List<ProcessDefinitionVO> findProcessesByContainerId(String containerId, Integer page, Integer pageSize);

	List<ProcessDefinitionVO> findProcesses(Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<ProcessDefinitionVO> findProcesses(String filter, Integer page, Integer pageSize, String sort,
			boolean sortOrder);

	List<ProcessDefinitionVO> findProcessesByContainerId(String containerId, Integer page, Integer pageSize,
			String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstances(Integer page, Integer pageSize);

	//List<ProcessInstance> findProcessInstancesByCorrelationKey(CorrelationKey correlationKey, Integer page,Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByProcessId(String processId, List<Integer> status, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByProcessName(String processName, List<Integer> status, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByContainerId(String containerId, List<Integer> status, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByStatus(List<Integer> status, Integer page, Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByInitiator(String initiator, List<Integer> status, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByVariable(String variableName, List<Integer> status, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByVariableAndValue(String variableName, String variableValue,
			List<Integer> status, Integer page, Integer pageSize);

	List<ProcessInstanceVO> findProcessInstances(Integer page, Integer pageSize, String sort, boolean sortOrder);

	//List<ProcessInstance> findProcessInstancesByCorrelationKey(CorrelationKey correlationKey, Integer page,Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByProcessId(String processId, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByProcessName(String processName, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByContainerId(String containerId, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByStatus(List<Integer> status, Integer page, Integer pageSize,
			String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByInitiator(String initiator, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByVariable(String variableName, List<Integer> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstancesByVariableAndValue(String variableName, String variableValue,
			List<Integer> status, Integer page, Integer pageSize, String sort, boolean sortOrder);

	ProcessInstanceVO findProcessInstanceById(Long processInstanceId);

	ProcessInstanceVO findProcessInstanceById(Long processInstanceId, boolean withVars);

	//ProcessInstance findProcessInstanceByCorrelationKey(CorrelationKey correlationKey);

	NodeInstanceVO findNodeInstanceByWorkItemId(Long processInstanceId, Long workItemId);

	List<NodeInstanceVO> findActiveNodeInstances(Long processInstanceId, Integer page, Integer pageSize);

	List<NodeInstanceVO> findCompletedNodeInstances(Long processInstanceId, Integer page, Integer pageSize);

	List<NodeInstanceVO> findNodeInstances(Long processInstanceId, Integer page, Integer pageSize);

	List<VariableInstanceVO> findVariablesCurrentState(Long processInstanceId);

	List<VariableInstanceVO> findVariableHistory(Long processInstanceId, String variableName, Integer page,
			Integer pageSize);

	// QueryDataService related
	void registerQuery(QueryDefinitionVO queryDefinition);

	void replaceQuery(QueryDefinitionVO queryDefinition);

	void unregisterQuery(String queryName);

	QueryDefinitionVO getQuery(String queryName);

	List<QueryDefinitionVO> getQueries(Integer page, Integer pageSize);

	<T> List<T> query(String queryName, String mapper, Integer page, Integer pageSize, Class<T> resultType);

	<T> List<T> query(String queryName, String mapper, String orderBy, Integer page, Integer pageSize,
			Class<T> resultType);

	<T> List<T> query(String queryName, String mapper, QueryFilterSpecVO filterSpec, Integer page, Integer pageSize,
			Class<T> resultType);

	<T> List<T> query(String queryName, String mapper, String builder, Map<String, Object> parameters, Integer page,
			Integer pageSize, Class<T> resultType);

	//void setResponseHandler(ResponseHandler responseHandler);
}
