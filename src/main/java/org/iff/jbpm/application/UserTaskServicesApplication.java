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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.iff.jbpm.vo.instance.TaskAttachmentVO;
import org.iff.jbpm.vo.instance.TaskCommentVO;
import org.iff.jbpm.vo.instance.TaskEventInstanceVO;
import org.iff.jbpm.vo.instance.TaskInstanceVO;
import org.iff.jbpm.vo.instance.TaskSummaryVO;

public interface UserTaskServicesApplication {

	// task operations
	void activateTask(String containerId, Long taskId, String userId);

	void claimTask(String containerId, Long taskId, String userId);

	void completeTask(String containerId, Long taskId, String userId, Map<String, String> params);

	void completeAutoProgress(String containerId, Long taskId, String userId, Map<String, String> params);

	void delegateTask(String containerId, Long taskId, String userId, String targetUserId);

	void exitTask(String containerId, Long taskId, String userId);

	void failTask(String containerId, Long taskId, String userId, Map<String, String> params);

	void forwardTask(String containerId, Long taskId, String userId, String targetEntityId);

	void releaseTask(String containerId, Long taskId, String userId);

	void resumeTask(String containerId, Long taskId, String userId);

	void skipTask(String containerId, Long taskId, String userId);

	void startTask(String containerId, Long taskId, String userId);

	void stopTask(String containerId, Long taskId, String userId);

	void suspendTask(String containerId, Long taskId, String userId);

	void nominateTask(String containerId, Long taskId, String userId, List<String> potentialOwners);

	void setTaskPriority(String containerId, Long taskId, int priority);

	void setTaskExpirationDate(String containerId, Long taskId, Date date);

	void setTaskSkipable(String containerId, Long taskId, boolean skipable);

	void setTaskName(String containerId, Long taskId, String name);

	void setTaskDescription(String containerId, Long taskId, String description);

	void updateTask(String containerId, Long taskId, String userId, TaskInstanceVO updatedTask);

	Long saveTaskContent(String containerId, Long taskId, Map<String, String> values);

	Map<String, String> getTaskOutputContentByTaskId(String containerId, Long taskId);

	Map<String, String> getTaskInputContentByTaskId(String containerId, Long taskId);

	void deleteTaskContent(String containerId, Long taskId, Long contentId);

	Long addTaskComment(String containerId, Long taskId, String text, String addedBy, Date addedOn);

	void deleteTaskComment(String containerId, Long taskId, Long commentId);

	List<TaskCommentVO> getTaskCommentsByTaskId(String containerId, Long taskId);

	TaskCommentVO getTaskCommentById(String containerId, Long taskId, Long commentId);

	Long addTaskAttachment(String containerId, Long taskId, String userId, String name, Object attachment);

	void deleteTaskAttachment(String containerId, Long taskId, Long attachmentId);

	TaskAttachmentVO getTaskAttachmentById(String containerId, Long taskId, Long attachmentId);

	Object getTaskAttachmentContentById(String containerId, Long taskId, Long attachmentId);

	List<TaskAttachmentVO> getTaskAttachmentsByTaskId(String containerId, Long taskId);

	TaskInstanceVO getTaskInstance(String containerId, Long taskId);

	TaskInstanceVO getTaskInstance(String containerId, Long taskId, boolean withInputs, boolean withOutputs,
			boolean withAssignments);

	List<TaskEventInstanceVO> findTaskEvents(String containerId, Long taskId, Integer page, Integer pageSize);

	List<TaskEventInstanceVO> findTaskEvents(String containerId, Long taskId, Integer page, Integer pageSize, String sort,
			boolean sortOrder);

	// task searches
	TaskInstanceVO findTaskByWorkItemId(Long workItemId);

	TaskInstanceVO findTaskById(Long taskId);

	List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, List<String> status, Integer page,
			Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> status, Integer page,
			Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, String filter, List<String> status, Integer page,
			Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> groups, List<String> status,
			Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksOwned(String userId, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status, Integer page,
			Integer pageSize);

	List<TaskSummaryVO> findTasks(String userId, Integer page, Integer pageSize);

	List<TaskEventInstanceVO> findTaskEvents(Long taskId, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksByVariable(String userId, String variableName, List<String> status, Integer page,
			Integer pageSize);

	List<TaskSummaryVO> findTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Integer page, Integer pageSize);

	List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, Integer page, Integer pageSize,
			String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, Integer page, Integer pageSize, String sort,
			boolean sortOrder);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, String filter, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> groups, List<String> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksOwned(String userId, Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize, String sort,
			boolean sortOrder);

	List<TaskSummaryVO> findTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasks(String userId, Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<TaskEventInstanceVO> findTaskEvents(Long taskId, Integer page, Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksByVariable(String userId, String variableName, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder);

	List<TaskSummaryVO> findTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Integer page, Integer pageSize, String sort, boolean sortOrder);

	//void setResponseHandler(ResponseHandler responseHandler);
}
