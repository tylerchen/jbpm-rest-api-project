/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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
import java.util.Arrays;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("query-filter-spec")
public class QueryFilterSpecVO implements Serializable {

	@XStreamAlias("order-by")
	private String orderBy;
	@XStreamAlias("order-asc")
	private boolean ascending;
	@XStreamAlias("query-params")
	private QueryParamVO[] parameters;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("result-column-mapping")
	private Map<String, String> columnMapping;
	@XStreamAlias("order-by-clause")
	private String orderByClause;

	public QueryFilterSpecVO() {
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public QueryParamVO[] getParameters() {
		return parameters;
	}

	public void setParameters(QueryParamVO[] parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getColumnMapping() {
		return columnMapping;
	}

	public void setColumnMapping(Map<String, String> columnMapping) {
		this.columnMapping = columnMapping;
	}

	public String getOrderByClause() {
		return this.orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	@Override
	public String toString() {
		return "QueryFilterSpec{" + "orderBy='" + orderBy + '\'' + ", ascending=" + ascending + ", parameters="
				+ Arrays.toString(parameters) + '}';
	}

}
