/*******************************************************************************
 * Copyright (c) Nov 27, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import static org.iff.jbpm.util.RestURI.CANCEL_JOB_DEL_URI;
import static org.iff.jbpm.util.RestURI.JOB_INSTANCES_BY_CMD_GET_URI;
import static org.iff.jbpm.util.RestURI.JOB_INSTANCES_BY_CONTAINER_GET_URI;
import static org.iff.jbpm.util.RestURI.JOB_INSTANCES_BY_KEY_GET_URI;
import static org.iff.jbpm.util.RestURI.JOB_INSTANCES_BY_PROCESS_INSTANCE_GET_URI;
import static org.iff.jbpm.util.RestURI.JOB_URI;
import static org.iff.jbpm.util.RestURI.REQUEUE_JOB_PUT_URI;
import static org.iff.jbpm.util.RestURI.UPDATE_JOB_DATA_POST_URI;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.NumberHelper;
import org.iff.jbpm.application.JobServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.instance.JobRequestInstanceVO;
import org.iff.jbpm.vo.instance.RequestInfoInstanceVO;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 27, 2017
 */
@SuppressWarnings("unchecked")
//@Named("jobServicesApplication")
public class JobServicesApplicationImpl implements JobServicesApplication {

	public Long scheduleRequest(JobRequestInstanceVO jobRequest) {
		return scheduleRequest("", jobRequest);
	}

	public Long scheduleRequest(String containerId, JobRequestInstanceVO jobRequest) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap("containerId", containerId);
		Long id = JbpmRestHelper.post(JOB_URI, "", null, conditions, jobRequest, Long.class, "");
		return id;
	}

	public void cancelRequest(long requestId) {
		cancelRequest(null, requestId);
	}

	public void cancelRequest(String containerId, long requestId) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap("containerId", containerId);
		JbpmRestHelper.del(JOB_URI, CANCEL_JOB_DEL_URI, null, conditions, null, null, requestId);
	}

	public void updateRequestData(long requestId, String containerId, Map<String, Object> data) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap("containerId", containerId);
		JbpmRestHelper.post(JOB_URI, UPDATE_JOB_DATA_POST_URI, null, conditions, data, null, requestId);
	}

	public void requeueRequest(long requestId) {
		requeueRequest(null, requestId);
	}

	public void requeueRequest(String containerId, long requestId) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap("containerId", containerId);
		JbpmRestHelper.put(JOB_URI, REQUEUE_JOB_PUT_URI, null, conditions, null, null, requestId);
	}

	public List<RequestInfoInstanceVO> getRequestsByStatus(List<String> status, Integer page, Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, "", conditions, RequestInfoInstanceVO.class,
				"request-info-instance-list", "");
	}

	public List<RequestInfoInstanceVO> getRequestsByBusinessKey(String businessKey, Integer page, Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap(//
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_KEY_GET_URI, conditions, RequestInfoInstanceVO.class,
				"request-info-instance-list", businessKey);
	}

	public List<RequestInfoInstanceVO> getRequestsByBusinessKey(String businessKey, List<String> status, Integer page,
			Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_KEY_GET_URI, conditions, RequestInfoInstanceVO.class,
				"request-info-instance-list", businessKey);
	}

	public List<RequestInfoInstanceVO> getRequestsByCommand(String command, Integer page, Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap(//
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_CMD_GET_URI, conditions, RequestInfoInstanceVO.class,
				"request-info-instance-list", command);
	}

	public List<RequestInfoInstanceVO> getRequestsByCommand(String command, List<String> status, Integer page,
			Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_CMD_GET_URI, conditions, RequestInfoInstanceVO.class,
				"request-info-instance-list", command);
	}

	public List<RequestInfoInstanceVO> getRequestsByContainer(String containerId, List<String> status, Integer page,
			Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_CONTAINER_GET_URI, conditions,
				RequestInfoInstanceVO.class, "request-info-instance-list", containerId);
	}

	public List<RequestInfoInstanceVO> getRequestsByProcessInstance(Long processInstanceId, List<String> status,
			Integer page, Integer pageSize) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = null;
		if (!(status == null || status.isEmpty())) {
			conditions = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		conditions = MapHelper.fillMap(//
				conditions, //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(JOB_URI, JOB_INSTANCES_BY_PROCESS_INSTANCE_GET_URI, conditions,
				RequestInfoInstanceVO.class, "request-info-instance-list", processInstanceId);
	}

	public RequestInfoInstanceVO getRequestById(Long requestId, boolean withErrors, boolean withData) {
		return getRequestById(null, requestId, withErrors, withData);
	}

	public RequestInfoInstanceVO getRequestById(String containerId, Long requestId, boolean withErrors,
			boolean withData) {
		Exceptions.runtime("NOT TEST!!!");
		Map<String, String> conditions = MapHelper.toMap(//
				"containerId", containerId, //
				"withData", String.valueOf(withData), //
				"withErrors", String.valueOf(withErrors));
		return JbpmRestHelper.xml(JOB_URI, JOB_INSTANCES_BY_PROCESS_INSTANCE_GET_URI, conditions,
				RequestInfoInstanceVO.class, requestId);
	}

	//	public void setResponseHandler(ResponseHandler responseHandler) {
	//
	//	}

}
