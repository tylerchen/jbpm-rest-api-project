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
import java.util.List;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;
import org.iff.jbpm.util.JbpmRestHelper.StringStringsMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("process-definition")
public class ProcessDefinitionVO implements Serializable {

	@XStreamAlias("process-id")
	private String id;

	@XStreamAlias("process-name")
	private String name;

	@XStreamAlias("process-version")
	private String version;

	@XStreamAlias("package")
	private String packageName;

	@XStreamAlias("container-id")
	private String containerId;

	@XStreamAlias("dynamic")
	private boolean dynamic;

	@XStreamConverter(StringStringsMapConverter.class)
	@XStreamAlias("associated-entities")
	private Map<String, String[]> associatedEntities;

	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("service-tasks")
	private Map<String, String> serviceTasks;

	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("process-variables")
	private Map<String, String> processVariables;

	@XStreamAlias("process-subprocesses")
	private List<String> reusableSubProcesses;

	public ProcessDefinitionVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public Map<String, String[]> getAssociatedEntities() {
		return associatedEntities;
	}

	public void setAssociatedEntities(Map<String, String[]> associatedEntities) {
		this.associatedEntities = associatedEntities;
	}

	public Map<String, String> getServiceTasks() {
		return serviceTasks;
	}

	public void setServiceTasks(Map<String, String> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	public Map<String, String> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, String> processVariables) {
		this.processVariables = processVariables;
	}

	public List<String> getReusableSubProcesses() {
		return reusableSubProcesses;
	}

	public void setReusableSubProcesses(List<String> reusableSubProcesses) {
		this.reusableSubProcesses = reusableSubProcesses;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	@Override
	public String toString() {
		return "ProcessDefinition{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", version='" + version + '\''
				+ ", containerId='" + containerId + '\'' + ", dynamic='" + dynamic + '\'' + '}';
	}
}
