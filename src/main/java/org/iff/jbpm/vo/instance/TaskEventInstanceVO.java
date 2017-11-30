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
@XStreamAlias("task-event-instance")
public class TaskEventInstanceVO implements Serializable {

	@XStreamAlias("task-event-id")
	private Long id;

	@XStreamAlias("task-id")
	private Long taskId;

	@XStreamAlias("task-event-type")
	private String type;

	@XStreamAlias("task-event-user")
	private String userId;

	@XStreamAlias("task-event-date")
	private Date logTime;

	@XStreamAlias("task-process-instance-id")
	private Long processInstanceId;

	@XStreamAlias("task-work-item-id")
	private Long workItemId;

	@XStreamAlias("task-event-message")
	private String message;

	public TaskEventInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TaskEventInstance{" + "id=" + id + ", taskId=" + taskId + ", type='" + type + '\'' + ", userId='"
				+ userId + '\'' + ", logTime=" + logTime + ", processInstanceId=" + processInstanceId + ", workItemId="
				+ workItemId + ", message=" + message + '}';
	}
}
