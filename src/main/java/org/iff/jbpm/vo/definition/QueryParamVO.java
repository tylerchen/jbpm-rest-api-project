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
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("query-param")
public class QueryParamVO implements Serializable {

	@XStreamAlias("cond-column")
	private String column;
	@XStreamAlias("cond-operator")
	private String operator;
	@XStreamAlias("cond-values")
	private List<?> value;

	public QueryParamVO() {

	}

	public QueryParamVO(String column, String operator, List<?> value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<?> getValue() {
		return value;
	}

	public void setValue(List<?> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QueryParam{" + "column='" + column + '\'' + ", operator='" + operator + '\'' + ", value=" + value + '}';
	}
}
