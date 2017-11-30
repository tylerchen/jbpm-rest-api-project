/*******************************************************************************
 * Copyright (c) Nov 27, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import static org.iff.jbpm.util.RestURI.*;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.MapHelper;
import org.iff.jbpm.application.UiServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 27, 2017
 */
@SuppressWarnings("unchecked")
//@Named("uIServicesApplication")
public class UiServicesApplicationImpl implements UiServicesApplication {

	String getProcessFormByType(String containerId, String processId, String language, String formType,
			boolean marshallContent) {
		Map<String, String> map = null;
		if (StringUtils.isNotBlank(language)) {
			map = MapHelper.toMap("lang", language, "filter", "true");
		} else {
			map = MapHelper.toMap("filter", "false");
		}

		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"type", formType, //
				"marshallContent", String.valueOf(marshallContent));
		String xml = JbpmRestHelper.json(FORM_URI, PROCESS_FORM_GET_URI, conditions, null, containerId, processId);

		return xml;
	}

	public String getProcessForm(String containerId, String processId, String language) {
		return getProcessFormByType(containerId, processId, language, CONST_ANY_FORM);
	}

	public String getProcessFormByType(String containerId, String processId, String language, String formType) {
		return getProcessFormByType(containerId, processId, language, formType, true);
	}

	public String getProcessRawForm(String containerId, String processId) {
		return getProcessFormByType(containerId, processId, null, CONST_ANY_FORM, false);
	}

	public String getProcessForm(String containerId, String processId) {
		return getProcessFormByType(containerId, processId, CONST_ANY_FORM);
	}

	public String getProcessFormByType(String containerId, String processId, String formType) {
		return getProcessFormByType(containerId, processId, null, formType, true);
	}

	String getTaskFormByType(String containerId, Long taskId, String language, String formType,
			boolean marshallContent) {
		Map<String, String> map = null;
		if (StringUtils.isNotBlank(language)) {
			map = MapHelper.toMap("lang", language, "filter", "true");
		} else {
			map = MapHelper.toMap("filter", "false");
		}

		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"type", formType, //
				"marshallContent", String.valueOf(marshallContent));
		String xml = JbpmRestHelper.json(FORM_URI, TASK_FORM_GET_URI, conditions, null, containerId, taskId);
		return xml;
	}

	public String getTaskForm(String containerId, Long taskId, String language) {
		return getTaskFormByType(containerId, taskId, language, CONST_ANY_FORM);
	}

	public String getTaskFormByType(String containerId, Long taskId, String language, String formType) {
		return getTaskFormByType(containerId, taskId, language, formType, true);
	}

	public String getTaskForm(String containerId, Long taskId) {
		return getTaskFormByType(containerId, taskId, CONST_ANY_FORM);
	}

	public String getTaskRawForm(String containerId, Long taskId) {
		return getTaskFormByType(containerId, taskId, CONST_ANY_FORM);
	}

	public String getTaskFormByType(String containerId, Long taskId, String formType) {
		return getTaskFormByType(containerId, taskId, null, CONST_ANY_FORM, false);
	}

	public String getProcessImage(String containerId, String processId) {
		String xml = JbpmRestHelper.json(IMAGE_URI, PROCESS_IMG_GET_URI, null, null, containerId, processId);
		return xml;
	}

	public String getProcessInstanceImage(String containerId, Long processInstanceId) {
		String xml = JbpmRestHelper.json(IMAGE_URI, PROCESS_INST_IMG_GET_URI, null, null, containerId,
				processInstanceId);
		return xml;
	}

}
