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
import java.util.HashMap;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("process-task-inputs")
public class TaskInputsDefinitionVO implements Serializable {

	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("inputs")
	private Map<String, String> taskInputs;

	public TaskInputsDefinitionVO() {
		this(new HashMap<String, String>());
	}

	public TaskInputsDefinitionVO(Map<String, String> taskInputs) {
		this.taskInputs = taskInputs;
	}

	public Map<String, String> getTaskInputs() {
		return taskInputs;
	}

	public void setTaskInputs(Map<String, String> taskInputs) {
		this.taskInputs = taskInputs;
	}

	@Override
	public String toString() {
		return "TaskInputsDefinition{" + "taskInputs=" + taskInputs + '}';
	}
}
