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
@XStreamAlias("task-summary")
public class TaskSummaryVO implements Serializable {

	@XStreamAlias("task-id")
	private Long id;
	@XStreamAlias("task-name")
	private String name;
	@XStreamAlias("task-subject")
	private String subject;
	@XStreamAlias("task-description")
	private String description;
	@XStreamAlias("task-status")
	private String status;
	@XStreamAlias("task-priority")
	private Integer priority;
	@XStreamAlias("task-is-skipable")
	private Boolean skipable;
	@XStreamAlias("task-actual-owner")
	private String actualOwner;
	@XStreamAlias("task-created-by")
	private String createdBy;
	@XStreamAlias("task-created-on")
	private Date createdOn;
	@XStreamAlias("task-activation-time")
	private Date activationTime;
	@XStreamAlias("task-expiration-time")
	private Date expirationTime;
	@XStreamAlias("task-proc-inst-id")
	private Long processInstanceId;
	@XStreamAlias("task-proc-def-id")
	private String processId;
	@XStreamAlias("task-container-id")
	private String containerId;
	@XStreamAlias("task-parent-id")
	private Long parentId;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getSkipable() {
		return skipable;
	}

	public void setSkipable(Boolean skipable) {
		this.skipable = skipable;
	}

	public String getActualOwner() {
		return actualOwner;
	}

	public void setActualOwner(String actualOwner) {
		this.actualOwner = actualOwner;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "TaskSummary{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", status='" + status + '\'' + ", actualOwner='" + actualOwner + '\'' + ", createdBy='" + createdBy
				+ '\'' + ", createdOn=" + createdOn + ", processInstanceId=" + processInstanceId + ", processId='"
				+ processId + '\'' + ", containerId='" + containerId + '\'' + '}';
	}
}
