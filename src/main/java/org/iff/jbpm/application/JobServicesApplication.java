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

package org.iff.jbpm.application;

import java.util.List;
import java.util.Map;

import org.iff.jbpm.vo.instance.JobRequestInstanceVO;
import org.iff.jbpm.vo.instance.RequestInfoInstanceVO;

public interface JobServicesApplication {

	Long scheduleRequest(JobRequestInstanceVO jobRequest);

	Long scheduleRequest(String containerId, JobRequestInstanceVO jobRequest);

	void cancelRequest(long requestId);

	void cancelRequest(String containerId, long requestId);

	void updateRequestData(long requestId, String containerId, Map<String, Object> data);

	void requeueRequest(long requestId);

	void requeueRequest(String containerId, long requestId);

	List<RequestInfoInstanceVO> getRequestsByStatus(List<String> statuses, Integer page, Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByBusinessKey(String businessKey, Integer page, Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByBusinessKey(String businessKey, List<String> statuses, Integer page,
			Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByCommand(String command, Integer page, Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByCommand(String command, List<String> statuses, Integer page,
			Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByContainer(String containerId, List<String> statuses, Integer page,
			Integer pageSize);

	List<RequestInfoInstanceVO> getRequestsByProcessInstance(Long processInstanceId, List<String> statuses, Integer page,
			Integer pageSize);

	RequestInfoInstanceVO getRequestById(Long requestId, boolean withErrors, boolean withData);

	RequestInfoInstanceVO getRequestById(String containerId, Long requestId, boolean withErrors, boolean withData);

	//void setResponseHandler(ResponseHandler responseHandler);
}
