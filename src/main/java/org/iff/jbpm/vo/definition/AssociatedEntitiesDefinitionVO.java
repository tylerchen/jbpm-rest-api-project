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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringsMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("process-associated-entities")
public class AssociatedEntitiesDefinitionVO implements Serializable {

	@XStreamConverter(StringStringsMapConverter.class)
	@XStreamAlias("associated-entities")
	private Map<String, String[]> associatedEntities;

	public AssociatedEntitiesDefinitionVO() {
	}

	public AssociatedEntitiesDefinitionVO(Map<String, String[]> associatedEntities) {
		this.associatedEntities = associatedEntities;
	}

	public static AssociatedEntitiesDefinitionVO from(Map<String, Collection<String>> associatedEntities) {
		Map<String, String[]> data = new HashMap<String, String[]>();

		for (Map.Entry<String, Collection<String>> entry : associatedEntities.entrySet()) {
			data.put(entry.getKey(), entry.getValue().toArray(new String[entry.getValue().size()]));
		}

		return new AssociatedEntitiesDefinitionVO(data);
	}

	public Map<String, String[]> getAssociatedEntities() {
		return associatedEntities;
	}

	public void setAssociatedEntities(Map<String, String[]> associatedEntities) {
		this.associatedEntities = associatedEntities;
	}

	@Override
	public String toString() {
		return "AssociatedEntitiesDefinition{" + "associatedEntities=" + associatedEntities + '}';
	}
}
