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
@XStreamAlias("process-service-tasks")
public class ServiceTasksDefinitionVO implements Serializable {

	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("tasks")
	private Map<String, String> serviceTasks;

	public ServiceTasksDefinitionVO() {
		this(new HashMap<String, String>());
	}

	public ServiceTasksDefinitionVO(Map<String, String> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	public Map<String, String> getServiceTasks() {
		return serviceTasks;
	}

	public void setServiceTasks(Map<String, String> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	@Override
	public String toString() {
		return "ServiceTasksDefinition{" + "serviceTasks=" + serviceTasks + '}';
	}
}
