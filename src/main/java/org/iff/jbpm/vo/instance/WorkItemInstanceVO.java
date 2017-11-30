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
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("work-item-instance")
public class WorkItemInstanceVO implements Serializable {

	@XStreamAlias("work-item-id")
	private Long id;
	@XStreamAlias("work-item-name")
	private String name;
	@XStreamAlias("work-item-state")
	private Integer state = 0;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("work-item-params")
	private Map<String, String> parameters;
	@XStreamAlias("process-instance-id")
	private Long processInstanceId;
	@XStreamAlias("container-id")
	private String containerId;
	@XStreamAlias("node-instance-id")
	private Long nodeInstanceId;
	@XStreamAlias("node-id")
	private Long nodeId;

	public WorkItemInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public Long getNodeInstanceId() {
		return nodeInstanceId;
	}

	public void setNodeInstanceId(Long nodeInstanceId) {
		this.nodeInstanceId = nodeInstanceId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "WorkItemInstance{" + "id=" + id + ", name='" + name + '\'' + ", state=" + state + ", parameters="
				+ parameters + ", processInstanceId=" + processInstanceId + ", containerId='" + containerId + '\''
				+ ", nodeInstanceId=" + nodeInstanceId + ", nodeId=" + nodeId + '}';
	}
}
