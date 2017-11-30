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
import java.util.HashMap;
import java.util.Map;

import org.iff.jbpm.util.JbpmRestHelper.StringStringMapConverter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
@XStreamAlias("job-request-instance")
public class JobRequestInstanceVO implements Serializable {

	@XStreamAlias("job-command")
	private String command;

	@XStreamAlias("scheduled-date")
	private Date scheduledDate;

	@XStreamConverter(StringStringMapConverter.class)
	@XStreamAlias("request-data")
	private Map<String, String> data = new HashMap<String, String>();

	public JobRequestInstanceVO() {
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
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

	@Override
	public String toString() {
		return "JobRequestInstance{" + "command='" + command + '\'' + ", scheduledDate=" + scheduledDate + ", data="
				+ data + '}';
	}

}
