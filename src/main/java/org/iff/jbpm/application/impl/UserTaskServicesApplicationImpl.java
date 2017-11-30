/*******************************************************************************
 * Copyright (c) Nov 27, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.application.impl;

import static org.iff.jbpm.util.RestURI.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.iff.infra.util.BaseCryptHelper;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.NumberHelper;
import org.iff.jbpm.application.UserTaskServicesApplication;
import org.iff.jbpm.util.JbpmRestHelper;
import org.iff.jbpm.vo.instance.TaskAttachmentVO;
import org.iff.jbpm.vo.instance.TaskCommentVO;
import org.iff.jbpm.vo.instance.TaskEventInstanceVO;
import org.iff.jbpm.vo.instance.TaskInstanceVO;
import org.iff.jbpm.vo.instance.TaskSummaryVO;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 27, 2017
 */
@SuppressWarnings("unchecked")
//@Named("userTaskServicesApplication")
public class UserTaskServicesApplicationImpl implements UserTaskServicesApplication {

	public void activateTask(String containerId, Long taskId, String userId) {

		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_ACTIVATE_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void claimTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_CLAIM_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void completeTask(String containerId, Long taskId, String userId, Map<String, String> params) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_COMPLETE_PUT_URI, null, conditions, params, null, containerId,
				taskId);
	}

	public void completeAutoProgress(String containerId, Long taskId, String userId, Map<String, String> params) {

		Map<String, String> conditions = MapHelper.toMap("user", userId, "auto-progress", "true");
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_COMPLETE_PUT_URI, null, conditions, params, null, containerId,
				taskId);
	}

	public void delegateTask(String containerId, Long taskId, String userId, String targetUserId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId, "targetUser", targetUserId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_DELEGATE_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void exitTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_EXIT_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void failTask(String containerId, Long taskId, String userId, Map<String, String> params) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_FAIL_PUT_URI, null, conditions, params, null, containerId, taskId);
	}

	public void forwardTask(String containerId, Long taskId, String userId, String targetEntityId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId, "targetUser", targetEntityId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_FORWARD_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void releaseTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_RELEASE_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void resumeTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_RESUME_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void skipTask(String containerId, Long taskId, String userId) {

		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_SKIP_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void startTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_START_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void stopTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_STOP_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void suspendTask(String containerId, Long taskId, String userId) {
		Map<String, String> conditions = MapHelper.toMap("user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_SUSPEND_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void nominateTask(String containerId, Long taskId, String userId, List<String> potentialOwners) {
		Map<String, String> map = null;
		if (!(potentialOwners == null || potentialOwners.isEmpty())) {
			map = MapHelper.toMap("potOwner", StringUtils.join(potentialOwners, "&potOwner="));
		}

		Map<String, String> conditions = MapHelper.fillMap(map, "user", userId);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_NOMINATE_PUT_URI, null, conditions, null, null, containerId, taskId);
	}

	public void setTaskPriority(String containerId, Long taskId, int priority) {

		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_PRIORITY_PUT_URI, null, null, priority, null, containerId, taskId);
	}

	public void setTaskExpirationDate(String containerId, Long taskId, Date date) {

		String dateStr = date == null ? null : new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(date);
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_EXPIRATION_DATE_PUT_URI, null, null, dateStr, null, containerId,
				taskId);
	}

	public void setTaskSkipable(String containerId, Long taskId, boolean skipable) {

		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_SKIPABLE_PUT_URI, null, null, skipable, null, containerId, taskId);
	}

	public void setTaskName(String containerId, Long taskId, String name) {

		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_NAME_PUT_URI, null, null, name, null, containerId, taskId);
	}

	public void setTaskDescription(String containerId, Long taskId, String description) {

		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_DESCRIPTION_PUT_URI, null, null, description, null, containerId,
				taskId);
	}

	public void updateTask(String containerId, Long taskId, String userId, TaskInstanceVO updatedTask) {
		JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_PUT_URI, null, null, updatedTask, null, containerId, taskId);
	}

	public Long saveTaskContent(String containerId, Long taskId, Map<String, String> values) {
		Long id = JbpmRestHelper.put(TASK_URI, TASK_INSTANCE_OUTPUT_DATA_PUT_URI, null, null, values, Long.class,
				containerId, taskId);
		return id;
	}

	public Map<String, String> getTaskOutputContentByTaskId(String containerId, Long taskId) {

		Map<String, String> bean = JbpmRestHelper.json(TASK_URI, TASK_INSTANCE_OUTPUT_DATA_GET_URI, null, Map.class,
				containerId, taskId);
		return bean;
	}

	public Map<String, String> getTaskInputContentByTaskId(String containerId, Long taskId) {
		Map<String, String> bean = JbpmRestHelper.json(TASK_URI, TASK_INSTANCE_INPUT_DATA_GET_URI, null, Map.class,
				containerId, taskId);
		return bean;
	}

	public void deleteTaskContent(String containerId, Long taskId, Long contentId) {

		JbpmRestHelper.del(TASK_URI, TASK_INSTANCE_CONTENT_DATA_DELETE_URI, null, null, null, null, containerId, taskId,
				contentId);
	}

	public Long addTaskComment(String containerId, Long taskId, String text, String addedBy, Date addedOn) {

		Map<String, String> headers = MapHelper.toMap("X-KIE-ClassType",
				"org.kie.server.api.model.instance.TaskComment");
		String dateStr = addedOn == null ? null : new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(addedOn);
		Map<String, String> value = MapHelper.toMap("text", text, "addedBy", addedBy, "addedOn", dateStr);
		Long id = JbpmRestHelper.post(TASK_URI, TASK_INSTANCE_COMMENT_ADD_POST_URI, headers, null, value, Long.class,
				containerId, taskId);
		return id;
	}

	public void deleteTaskComment(String containerId, Long taskId, Long commentId) {

		JbpmRestHelper.del(TASK_URI, TASK_INSTANCE_COMMENT_DELETE_URI, null, null, null, null, containerId, taskId,
				commentId);
	}

	public List<TaskCommentVO> getTaskCommentsByTaskId(String containerId, Long taskId) {

		return JbpmRestHelper.xmlList(TASK_URI, TASK_INSTANCE_COMMENTS_GET_URI, null, TaskCommentVO.class,
				"task-comment-list", containerId, taskId);
	}

	public TaskCommentVO getTaskCommentById(String containerId, Long taskId, Long commentId) {

		return JbpmRestHelper.xml(TASK_URI, TASK_INSTANCE_COMMENT_GET_URI, null, TaskCommentVO.class, containerId,
				taskId, commentId);
	}

	public Long addTaskAttachment(String containerId, Long taskId, String userId, String name, Object attachment) {

		Map<String, String> conditions = MapHelper.toMap("user", userId, "name", name);
		Long id = JbpmRestHelper.post(TASK_URI, TASK_INSTANCE_ATTACHMENT_ADD_POST_URI, null, conditions, attachment,
				Long.class, containerId, taskId);
		return id;
	}

	public void deleteTaskAttachment(String containerId, Long taskId, Long attachmentId) {

		JbpmRestHelper.del(TASK_URI, TASK_INSTANCE_ATTACHMENT_DELETE_URI, null, null, null, null, containerId, taskId,
				attachmentId);
	}

	public TaskAttachmentVO getTaskAttachmentById(String containerId, Long taskId, Long attachmentId) {

		return JbpmRestHelper.xml(TASK_URI, TASK_INSTANCE_ATTACHMENT_GET_URI, null, TaskAttachmentVO.class, containerId,
				taskId, attachmentId);
	}

	public Object getTaskAttachmentContentById(String containerId, Long taskId, Long attachmentId) {

		Object bean = JbpmRestHelper.json(TASK_URI, TASK_INSTANCE_ATTACHMENT_CONTENT_GET_URI, null, Object.class,
				containerId, taskId, attachmentId);
		return bean;
	}

	public List<TaskAttachmentVO> getTaskAttachmentsByTaskId(String containerId, Long taskId) {

		return JbpmRestHelper.xmlList(TASK_URI, TASK_INSTANCE_ATTACHMENTS_GET_URI, null, TaskAttachmentVO.class,
				"task-attachment-list", containerId, taskId);
	}

	public TaskInstanceVO getTaskInstance(String containerId, Long taskId) {
		return JbpmRestHelper.xml(TASK_URI, TASK_INSTANCE_GET_URI, null, TaskInstanceVO.class, containerId, taskId);
	}

	public TaskInstanceVO getTaskInstance(String containerId, Long taskId, boolean withInputs, boolean withOutputs,
			boolean withAssignments) {
		Map<String, String> conditions = MapHelper.toMap(//
				"withInputData", String.valueOf(withInputs), //
				"withOutputData", String.valueOf(withOutputs), //
				"withAssignments", String.valueOf(withAssignments));
		return JbpmRestHelper.xmlFix(TASK_URI, TASK_INSTANCE_GET_URI, conditions, TaskInstanceVO.class, RPL_TI,
				containerId, taskId);
	}

	public List<TaskEventInstanceVO> findTaskEvents(String containerId, Long taskId, Integer page, Integer pageSize) {
		return findTaskEvents(taskId, page, pageSize, "", true);
	}

	public List<TaskEventInstanceVO> findTaskEvents(String containerId, Long taskId, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(TASK_URI, TASKS_EVENTS_GET_URI, conditions, TaskEventInstanceVO.class,
				"task-event-instance-list", containerId, taskId);
	}

	public TaskInstanceVO findTaskByWorkItemId(Long workItemId) {
		return JbpmRestHelper.xml(QUERY_URI, TASK_BY_WORK_ITEM_ID_GET_URI, null, TaskInstanceVO.class, workItemId);
	}

	public TaskInstanceVO findTaskById(Long taskId) {
		return JbpmRestHelper.xml(QUERY_URI, TASK_GET_URI, null, TaskInstanceVO.class, taskId);
	}

	public List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, Integer page, Integer pageSize) {
		return findTasksAssignedAsBusinessAdministrator(userId, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, List<String> status,
			Integer page, Integer pageSize) {
		return findTasksAssignedAsBusinessAdministrator(userId, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, Integer page, Integer pageSize) {
		return findTasksAssignedAsPotentialOwner(userId, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> status, Integer page,
			Integer pageSize) {
		return findTasksAssignedAsPotentialOwner(userId, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, String filter, List<String> status,
			Integer page, Integer pageSize) {
		return findTasksAssignedAsPotentialOwner(userId, filter, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> groups,
			List<String> status, Integer page, Integer pageSize) {
		return findTasksAssignedAsPotentialOwner(userId, groups, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksOwned(String userId, Integer page, Integer pageSize) {
		return findTasksOwned(userId, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize) {
		return findTasksOwned(userId, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status,
			Integer page, Integer pageSize) {
		return findTasksByStatusByProcessInstanceId(processInstanceId, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasks(String userId, Integer page, Integer pageSize) {
		return findTasks(userId, page, pageSize, "", true);
	}

	public List<TaskEventInstanceVO> findTaskEvents(Long taskId, Integer page, Integer pageSize) {
		return findTaskEvents(taskId, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksByVariable(String userId, String variableName, List<String> status,
			Integer page, Integer pageSize) {
		return findTasksByVariable(userId, variableName, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Integer page, Integer pageSize) {
		return findTasksByVariableAndValue(userId, variableName, variableValue, status, page, pageSize, "", true);
	}

	public List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		return findTasksAssignedAsBusinessAdministrator(userId, null, page, pageSize, sort, sortOrder);
	}

	public List<TaskSummaryVO> findTasksAssignedAsBusinessAdministrator(String userId, List<String> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"user", userId, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_ASSIGN_BUSINESS_ADMINS_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", "");
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		return this.findTasksAssignedAsPotentialOwner(userId, null, page, pageSize, sort, sortOrder);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> status, Integer page,
			Integer pageSize, String sort, boolean sortOrder) {
		return this.findTasksAssignedAsPotentialOwner(userId, "", status, page, pageSize);
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, String filter, List<String> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"user", userId, //
				"filter", filter, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_ASSIGN_POT_OWNERS_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", "");
	}

	public List<TaskSummaryVO> findTasksAssignedAsPotentialOwner(String userId, List<String> groups,
			List<String> status, Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		if (!(groups == null || groups.isEmpty())) {
			map = MapHelper.fillMap(map, "groups", StringUtils.join(groups, "&groups="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"user", userId, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_ASSIGN_POT_OWNERS_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", "");
	}

	public List<TaskSummaryVO> findTasksOwned(String userId, Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		return this.findTasksOwned(userId, null, page, pageSize);
	}

	public List<TaskSummaryVO> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize,
			String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"user", userId, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_OWNED_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", "");
	}

	public List<TaskSummaryVO> findTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASK_BY_PROCESS_INST_ID_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", processInstanceId);
	}

	public List<TaskSummaryVO> findTasks(String userId, Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"user", userId, //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_GET_URI, conditions, TaskSummaryVO.class, "task-summary-list",
				"");
	}

	public List<TaskEventInstanceVO> findTaskEvents(Long taskId, Integer page, Integer pageSize, String sort,
			boolean sortOrder) {
		Map<String, String> conditions = MapHelper.toMap(//
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_EVENTS_GET_URI, conditions, TaskEventInstanceVO.class,
				"task-event-instance-list", taskId);
	}

	public List<TaskSummaryVO> findTasksByVariable(String userId, String variableName, List<String> status,
			Integer page, Integer pageSize, String sort, boolean sortOrder) {
		return findTasksByVariableAndValue(userId, variableName, null, status, page, pageSize, sort, sortOrder);
	}

	public List<TaskSummaryVO> findTasksByVariableAndValue(String userId, String variableName, String variableValue,
			List<String> status, Integer page, Integer pageSize, String sort, boolean sortOrder) {
		Map<String, String> map = null;
		if (!(status == null || status.isEmpty())) {
			map = MapHelper.toMap("status", StringUtils.join(status, "&status="));
		}
		Map<String, String> conditions = MapHelper.fillMap(//
				map, //
				"user", userId, //
				"varValue", BaseCryptHelper.urlEncode(variableValue), //
				"sort", sort, //
				"sortOrder", String.valueOf(sortOrder), //
				"page", String.valueOf(NumberHelper.getInt(page, 0)), //
				"pageSize", String.valueOf(NumberHelper.getInt(pageSize, 10)));
		return JbpmRestHelper.xmlList(QUERY_URI, TASKS_BY_VAR_NAME_GET_URI, conditions, TaskSummaryVO.class,
				"task-summary-list", variableName);
	}

	//	public void setResponseHandler(ResponseHandler responseHandler) {
	//
	//	}

}
