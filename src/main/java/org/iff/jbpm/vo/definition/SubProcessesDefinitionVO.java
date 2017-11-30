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
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("process-subprocesses")
public class SubProcessesDefinitionVO implements Serializable {

	@XStreamAlias("subprocesses")
	private List<String> subProcesses = new ArrayList<String>();

	public SubProcessesDefinitionVO() {
	}

	public SubProcessesDefinitionVO(List<String> subprocesses) {
		this.subProcesses = subprocesses;
	}

	public List<String> getSubProcesses() {
		return subProcesses;
	}

	public void setSubProcesses(List<String> subProcesses) {
		this.subProcesses = subProcesses;
	}

	@Override
	public String toString() {
		return "SubProcessesDefinition{" + "subProcesses=" + subProcesses + '}';
	}
}
