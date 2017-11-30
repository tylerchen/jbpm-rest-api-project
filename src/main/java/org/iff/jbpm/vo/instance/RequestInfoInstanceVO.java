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
@XStreamAlias("request-info-instance")
public class RequestInfoInstanceVO implements Serializable {

	@XStreamAlias("request-instance-id")
	private Long id;
	@XStreamAlias("request-status")
	private String status;
	@XStreamAlias("request-business-key")
	private String businessKey;
	@XStreamAlias("request-message")
	private String message;
	@XStreamAlias("request-retries")
	private Integer retries;
	@XStreamAlias("request-executions")
	private Integer executions;
	@XStreamAlias("request-command")
	private String commandName;
	@XStreamAlias("request-scheduled-date")
	private Date scheduledDate;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("request-data")
	private Map<String, String> data;
	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("response-data")
	private Map<String, String> responseData;
	@XStreamAlias("request-errors") //error-info-instance-list
	private List<ErrorInfoInstanceVO> errors;
	@XStreamAlias("request-container-id")
	private String containerId;

	public RequestInfoInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public Integer getExecutions() {
		return executions;
	}

	public void setExecutions(Integer executions) {
		this.executions = executions;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public List<ErrorInfoInstanceVO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorInfoInstanceVO> errors) {
		this.errors = errors;
	}

	public Map<String, String> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, String> responseData) {
		this.responseData = responseData;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	@Override
	public String toString() {
		return "RequestInfoInstance{" + "id=" + id + ", status='" + status + '\'' + ", businessKey='" + businessKey
				+ '\'' + ", retries=" + retries + ", executions=" + executions + ", commandName='" + commandName + '\''
				+ ", scheduledDate=" + scheduledDate + '}';
	}

}
