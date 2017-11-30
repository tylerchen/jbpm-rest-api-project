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
import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("node-instance")
public class NodeInstanceVO implements Serializable {

	@XStreamAlias("node-instance-id")
	private Long id;
	@XStreamAlias("node-name")
	private String name;
	@XStreamAlias("process-instance-id")
	private Long processInstanceId;
	@XStreamAlias("work-item-id")
	private Long workItemId;
	@XStreamAlias("container-id")
	private String containerId;
	@XStreamAlias("start-date")
	private Date date;
	@XStreamAlias("node-id")
	private String nodeId;
	@XStreamAlias("node-type")
	private String nodeType;
	@XStreamAlias("node-connection")
	private String connection;
	@XStreamAlias("node-completed")
	private Boolean completed;
	@XStreamAlias("reference-id")
	private Long referenceId;

	public NodeInstanceVO() {
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

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(Long workItemId) {
		this.workItemId = workItemId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	@Override
	public String toString() {
		return "NodeInstance{" + "id=" + id + ", name='" + name + '\'' + ", processInstanceId=" + processInstanceId
				+ ", workItemId=" + workItemId + ", containerId='" + containerId + '\'' + ", date=" + date
				+ ", nodeId='" + nodeId + '\'' + ", nodeType='" + nodeType + '\'' + ", completed=" + completed + '}';
	}
}
