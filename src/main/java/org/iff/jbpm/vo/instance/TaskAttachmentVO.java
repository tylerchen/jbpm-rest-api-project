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
@XStreamAlias("task-attachment")
public class TaskAttachmentVO implements Serializable {

	@XStreamAlias("attachment-id")
	private Long id;

	@XStreamAlias("attachment-name")
	private String name;

	@XStreamAlias("attachment-added-by")
	private String addedBy;

	@XStreamAlias("attachment-added-at")
	private Date addedAt;

	@XStreamAlias("attachment-type")
	private String contentType;

	@XStreamAlias("attachment-size")
	private Integer size;

	@XStreamAlias("attachment-content-id")
	private Long attachmentContentId;

	public TaskAttachmentVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getAttachmentContentId() {
		return attachmentContentId;
	}

	public void setAttachmentContentId(Long attachmentContentId) {
		this.attachmentContentId = attachmentContentId;
	}

	@Override
	public String toString() {
		return "TaskAttachment{" + "id=" + id + ", name='" + name + '\'' + ", addedBy='" + addedBy + '\'' + ", addedAt="
				+ addedAt + ", contentType='" + contentType + '\'' + ", size=" + size + ", attachmentContentId="
				+ attachmentContentId + '}';
	}
}
