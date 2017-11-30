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
@XStreamAlias("task-instance")
public class TaskInstanceVO implements Serializable {

	@XStreamAlias("task-id")
	private Long id;
	@XStreamAlias("task-priority")
	private Integer priority;
	@XStreamAlias("task-name")
	private String name;
	@XStreamAlias("task-subject")
	private String subject;
	@XStreamAlias("task-description")
	private String description;
	@XStreamAlias("task-type")
	private String taskType;
	@XStreamAlias("task-form")
	private String formName;
	@XStreamAlias("task-status")
	private String status;
	@XStreamAlias("task-actual-owner")
	private String actualOwner;
	@XStreamAlias("task-created-by")
	private String createdBy;
	@XStreamAlias("task-created-on")
	private Date createdOn;
	@XStreamAlias("task-activation-time")
	private Date activationTime;
	@XStreamAlias("task-expiration-time")
	private Date expirationDate;
	@XStreamAlias("task-skippable")
	private Boolean skipable;
	@XStreamAlias("task-workitem-id")
	private Long workItemId;
	@XStreamAlias("task-process-instance-id")
	private Long processInstanceId;
	@XStreamAlias("task-parent-id")
	private Long parentId;
	@XStreamAlias("task-process-id")
	private String processId;
	@XStreamAlias("task-container-id")
	private String containerId;

	@XStreamAlias("potential-owners")
	// @XStreamAlias("task-pot-owners")
	private List<String> potentialOwners;

	@XStreamAlias("excluded-owners")
	// @XStreamAlias("task-excl-owners")
	private List<String> excludedOwners;

	@XStreamAlias("business-admins")
	// @XStreamAlias("task-business-admins")
	private List<String> businessAdmins;

	//@XStreamAlias("task-input-data")
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("inputData")
	private Map<String, String> inputData;

	//@XStreamAlias("task-output-data")
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("outputData")
	private Map<String, String> outputData;

	public TaskInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getSkipable() {
		return skipable;
	}

	public void setSkipable(Boolean skipable) {
		this.skipable = skipable;
	}

	public Long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(Long workItemId) {
		this.workItemId = workItemId;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public List<String> getPotentialOwners() {
		return potentialOwners;
	}

	public void setPotentialOwners(List<String> potentialOwners) {
		this.potentialOwners = potentialOwners;
	}

	public List<String> getExcludedOwners() {
		return excludedOwners;
	}

	public void setExcludedOwners(List<String> excludedOwners) {
		this.excludedOwners = excludedOwners;
	}

	public List<String> getBusinessAdmins() {
		return businessAdmins;
	}

	public void setBusinessAdmins(List<String> businessAdmins) {
		this.businessAdmins = businessAdmins;
	}

	public Map<String, String> getInputData() {
		return inputData;
	}

	public void setInputData(Map<String, String> inputData) {
		this.inputData = inputData;
	}

	public Map<String, String> getOutputData() {
		return outputData;
	}

	public void setOutputData(Map<String, String> outputData) {
		this.outputData = outputData;
	}

	@Override
	public String toString() {
		return "TaskInstance{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", status='" + status + '\'' + ", actualOwner='" + actualOwner + '\'' + ", processInstanceId="
				+ processInstanceId + ", processId='" + processId + '\'' + ", containerId='" + containerId + '\'' + '}';
	}
}
