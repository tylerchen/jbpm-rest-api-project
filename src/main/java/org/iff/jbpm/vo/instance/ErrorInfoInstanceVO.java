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
@XStreamAlias("error-info-instance")
public class ErrorInfoInstanceVO implements Serializable {

	@XStreamAlias("error-instance-id")
	private Long id;

	@XStreamAlias("request-instance-id")
	private Long requestInfoId;

	@XStreamAlias("error-message")
	private String message;

	@XStreamAlias("error-stacktrace")
	private String stacktrace;

	@XStreamAlias("error-date")
	private Date errorDate;

	public ErrorInfoInstanceVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRequestInfoId() {
		return requestInfoId;
	}

	public void setRequestInfoId(Long requestInfoId) {
		this.requestInfoId = requestInfoId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	@Override
	public String toString() {
		return "ErrorInfoInstance{" + "id=" + id + ", requestInfoId=" + requestInfoId + ", message='" + message + '\''
				+ ", errorDate=" + errorDate + '}';
	}
}
