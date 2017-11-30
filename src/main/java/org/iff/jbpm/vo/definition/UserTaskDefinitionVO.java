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

package org.iff.jbpm.vo.definition;

import java.io.Serializable;
import java.util.Map;
import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("user-task-definition")
public class UserTaskDefinitionVO implements Serializable {

	@XStreamAlias("task-name")
	private String name;
	@XStreamAlias("task-priority")
	private Integer priority;
	@XStreamAlias("task-comment")
	private String comment;
	@XStreamAlias("task-created-by")
	private String createdBy;
	@XStreamAlias("task-skippable")
	private boolean skippable;

	@XStreamAlias("associated-entities")
	private String[] associatedEntities;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("task-inputs")
	private Map<String, String> taskInputMappings;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("task-outputs")
	private Map<String, String> taskOutputMappings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isSkippable() {
		return skippable;
	}

	public void setSkippable(boolean skippable) {
		this.skippable = skippable;
	}

	public String[] getAssociatedEntities() {
		return associatedEntities;
	}

	public void setAssociatedEntities(String[] associatedEntities) {
		this.associatedEntities = associatedEntities;
	}

	public Map<String, String> getTaskInputMappings() {
		return taskInputMappings;
	}

	public void setTaskInputMappings(Map<String, String> taskInputMappings) {
		this.taskInputMappings = taskInputMappings;
	}

	public Map<String, String> getTaskOutputMappings() {
		return taskOutputMappings;
	}

	public void setTaskOutputMappings(Map<String, String> taskOutputMappings) {
		this.taskOutputMappings = taskOutputMappings;
	}

	@Override
	public String toString() {
		return "UserTaskDefinition{" + "name='" + name + '\'' + ", priority=" + priority + ", skippable=" + skippable
				+ ", createdBy='" + createdBy + '\'' + ", comment='" + comment + '\'' + '}';
	}
}
