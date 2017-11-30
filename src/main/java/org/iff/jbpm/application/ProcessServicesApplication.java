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

public interface ProcessServicesApplication {

	// process definition
	ProcessDefinitionVO getProcessDefinition(String containerId, String processId);

	SubProcessesDefinitionVO getReusableSubProcessDefinitions(String containerId, String processId);

	VariablesDefinitionVO getProcessVariableDefinitions(String containerId, String processId);

	ServiceTasksDefinitionVO getServiceTaskDefinitions(String containerId, String processId);

	AssociatedEntitiesDefinitionVO getAssociatedEntityDefinitions(String containerId, String processId);

	List<UserTaskDefinitionVO> getUserTaskDefinitions(String containerId, String processId);

	TaskInputsDefinitionVO getUserTaskInputDefinitions(String containerId, String processId, String taskName);

	TaskOutputsDefinitionVO getUserTaskOutputDefinitions(String containerId, String processId, String taskName);

	// process operations
	Long startProcess(String containerId, String processId);

	Long startProcess(String containerId, String processId, Map<String, String> variables);

	//	Long startProcess(String containerId, String processId, CorrelationKey correlationKey);
	//
	//	Long startProcess(String containerId, String processId, CorrelationKey correlationKey,
	//			Map<String,String> variables);

	void abortProcessInstance(String containerId, Long processInstanceId);

	void abortProcessInstances(String containerId, List<Long> processInstanceIds);

	Object getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName);

	<T> T getProcessInstanceVariable(String containerId, Long processInstanceId, String variableName, Class<T> type);

	Map<String, String> getProcessInstanceVariables(String containerId, Long processInstanceId);

	void signalProcessInstance(String containerId, Long processInstanceId, String signalName, Object event);

	void signalProcessInstances(String containerId, List<Long> processInstanceId, String signalName, Object event);

	void signal(String containerId, String signalName, Object event);

	List<String> getAvailableSignals(String containerId, Long processInstanceId);

	void setProcessVariable(String containerId, Long processInstanceId, String variableId, String value);

	void setProcessVariables(String containerId, Long processInstanceId, Map<String, String> variables);

	ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId);

	ProcessInstanceVO getProcessInstance(String containerId, Long processInstanceId, boolean withVars);

	void completeWorkItem(String containerId, Long processInstanceId, Long id, Map<String, String> results);

	void abortWorkItem(String containerId, Long processInstanceId, Long id);

	WorkItemInstanceVO getWorkItem(String containerId, Long processInstanceId, Long id);

	List<WorkItemInstanceVO> getWorkItemByProcessInstance(String containerId, Long processInstanceId);

	List<NodeInstanceVO> findActiveNodeInstances(String containerId, Long processInstanceId, Integer page,
			Integer pageSize);

	List<NodeInstanceVO> findCompletedNodeInstances(String containerId, Long processInstanceId, Integer page,
			Integer pageSize);

	List<NodeInstanceVO> findNodeInstances(String containerId, Long processInstanceId, Integer page, Integer pageSize);

	List<VariableInstanceVO> findVariablesCurrentState(String containerId, Long processInstanceId);

	List<VariableInstanceVO> findVariableHistory(String containerId, Long processInstanceId, String variableName,
			Integer page, Integer pageSize);

	//void setResponseHandler(ResponseHandler responseHandler);

	List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId, Integer page,
			Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId,
			List<Integer> status, Integer page, Integer pageSize);

	List<ProcessInstanceVO> findProcessInstancesByParent(String containerId, Long parentProcessInstanceId,
			List<Integer> status, Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<ProcessInstanceVO> findProcessInstances(String containerId, Integer page, Integer pageSize);

	List<ProcessInstanceVO> findProcessInstances(String containerId, Integer page, Integer pageSize, String sort,
			boolean sortOrder);
}
