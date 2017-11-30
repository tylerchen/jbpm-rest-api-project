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

package org.iff.jbpm.vo.instance;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("process-instance")
public class ProcessInstanceVO implements Serializable {

	@XStreamAlias("process-instance-id")
	private Long id;
	@XStreamAlias("process-id")
	private String processId;
	@XStreamAlias("process-name")
	private String processName;
	@XStreamAlias("process-version")
	private String processVersion;
	@XStreamAlias("process-instance-state")
	private Integer state;
	@XStreamAlias("container-id")
	private String containerId;
	@XStreamAlias("initiator")
	private String initiator;
	@XStreamAlias("start-date")
	private Date date;
	@XStreamAlias("process-instance-desc")
	private String processInstanceDescription;
	@XStreamAlias("correlation-key")
	private String correlationKey;
	@XStreamAlias("parent-instance-id")
	private Long parentId;

	@XStreamAlias("active-user-tasks")
	private List<TaskSummaryVO> activeUserTasks;

	//@XStreamAlias("process-instance-variables")
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("variables")
	private Map<String, String> variables;

	public ProcessInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessVersion() {
		return processVersion;
	}

	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProcessInstanceDescription() {
		return processInstanceDescription;
	}

	public void setProcessInstanceDescription(String processInstanceDescription) {
		this.processInstanceDescription = processInstanceDescription;
	}

	public String getCorrelationKey() {
		return correlationKey;
	}

	public void setCorrelationKey(String correlationKey) {
		this.correlationKey = correlationKey;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public List<TaskSummaryVO> getActiveUserTasks() {
		return activeUserTasks;
	}

	public void setActiveUserTasks(List<TaskSummaryVO> activeUserTasks) {
		this.activeUserTasks = activeUserTasks;
	}

	@Override
	public String toString() {
		return "ProcessInstance{" + "id=" + id + ", processId='" + processId + '\'' + ", processName='" + processName
				+ '\'' + ", state=" + state + ", containerId='" + containerId + '\'' + ", correlationKey='"
				+ correlationKey + '\'' + '}';
	}
}
