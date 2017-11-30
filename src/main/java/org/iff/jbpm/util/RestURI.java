/*******************************************************************************
 * Copyright (c) Nov 23, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.util;

import java.util.Collections;
import java.util.Map;

import org.iff.infra.util.MapHelper;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 23, 2017
 */
@SuppressWarnings("unchecked")
public class RestURI {

	public static final Map<String, String> RPL_PD = Collections
			.unmodifiableMap(MapHelper.toMap("processes>", "process-definition>", "item>", "string>"));
	public static final Map<String, String> RPL_TI = Collections
			.unmodifiableMap(MapHelper.toMap("task-pot-owners>", "string>", "task-business-admins>", "string>"));
	//Application中使用的变量
	public static final String CONST_FORM_MODELLER_TYPE = "FORM";
	public static final String CONST_FORM_TYPE = "FRM";
	public static final String CONST_FREE_MARKER_TYPE = "FTL";
	public static final String CONST_ANY_FORM = "ANY";

	public static final String CONST_SORT_BY_NAME = "ProcessName";
	public static final String CONST_SORT_BY_VERSION = "ProcessVersion";
	public static final String CONST_SORT_BY_PROJECT = "Project";
	public static final int CONST_STATE_PENDING = 0;
	public static final int CONST_STATE_ACTIVE = 1;
	public static final int CONST_STATE_COMPLETED = 2;
	public static final int CONST_STATE_ABORTED = 3;
	public static final int CONST_STATE_SUSPENDED = 4;
	
	public static enum Status {
	    Created, Ready, Reserved, InProgress, Suspended, Completed, Failed, Error, Exited, Obselete;
	}
	//Created, Ready, Reserved, InProgress, Suspended, Completed, Failed, Error, Exited, Obselete
	public static final int CONST_TASK_CREATED = Status.Created.ordinal();
	public static final int CONST_TASK_READY = Status.Ready.ordinal();
	public static final int CONST_TASK_RESERVED =Status.Reserved.ordinal();
	public static final int CONST_TASK_INPROGRESS =Status.InProgress.ordinal();
	public static final int CONST_TASK_SUSPENDED =Status.Suspended.ordinal();
	public static final int CONST_TASK_COMPLETED =Status.Completed.ordinal();
	public static final int CONST_TASK_FAILED =Status.Failed.ordinal();
	public static final int CONST_TASK_ERROR =Status.Error.ordinal();
	public static final int CONST_TASK_EXITED=Status.Exited.ordinal();
	public static final int CONST_TASK_OBSELETE=Status.Obselete.ordinal();

	//parameters
	public static final String CONTAINER_ID = "id";
	public static final String PROCESS_ID = "pId";
	public static final String PROCESS_INST_ID = "pInstanceId";
	public static final String SIGNAL_NAME = "sName";
	public static final String VAR_NAME = "varName";
	public static final String TASK_NAME = "taskName";
	public static final String TASK_INSTANCE_ID = "tInstanceId";
	public static final String CONTENT_ID = "contentId";
	public static final String COMMENT_ID = "commentId";
	public static final String ATTACHMENT_ID = "attachmentId";
	public static final String CORRELATION_KEY = "correlationKey";
	public static final String WORK_ITEM_ID = "workItemId";
	public static final String JOB_ID = "jobId";
	public static final String JOB_CMD_NAME = "cmd";
	public static final String JOB_KEY = "key";
	public static final String QUERY_NAME = "queryName";
	public static final String SOLVER_ID = "solverId";
	public static final String DOCUMENT_ID = "documentId";
	public static final String CASE_ID = "caseId";
	public static final String CASE_DEF_ID = "caseDefId";
	public static final String CASE_FILE_ITEM = "dataId";
	public static final String CASE_STAGE_ID = "caseStageId";
	public static final String CASE_NODE_NAME = "nodeName";
	public static final String CASE_COMMENT_ID = "caseCommentId";
	public static final String CASE_ROLE_NAME = "caseRoleName";
	public static final String CASE_OWNER = "caseOwner";
	public static final String NODE_INSTANCE_ID = "nodeInstanceId";
	public static final String TIMER_INSTANCE_ID = "timerId";
	public static final String NODE_ID = "nodeId";
	public static final String ENTITY_ID = "entityId";
	public static final String REASSIGNMENT_ID = "reassignmentId";
	public static final String NOTIFICATION_ID = "notificationId";
	public static final String ERROR_ID = "errorId";

	public static final String PROCESS_URI = "containers/{" + CONTAINER_ID + "}/processes";
	public static final String PROCESS_DEF_URI = "containers/{" + CONTAINER_ID + "}/processes/definitions";
	public static final String JOB_URI = "jobs";
	public static final String TASK_URI = "containers/{" + CONTAINER_ID + "}/tasks";
	public static final String QUERY_URI = "queries";
	public static final String FORM_URI = "containers/{" + CONTAINER_ID + "}/forms";
	public static final String IMAGE_URI = "containers/{" + CONTAINER_ID + "}/images";
	public static final String QUERY_DEF_URI = "queries/definitions";
	public static final String DOCUMENT_URI = "documents";
	public static final String CASE_URI = "containers/{" + CONTAINER_ID + "}/cases";
	public static final String CASE_QUERY_URI = "queries/cases";

	// admin URIs
	public static final String ADMIN_PROCESS_URI = "admin/containers/{" + CONTAINER_ID + "}/processes";
	public static final String ADMIN_TASK_URI = "admin/containers/{" + CONTAINER_ID + "}/tasks";
	public static final String ADMIN_CASE_URI = "admin/cases";

	// uris
	// process related prefixed by PROCESS_URI
	public static final String START_PROCESS_POST_URI = "{" + PROCESS_ID + "}/instances";
	public static final String START_PROCESS_WITH_CORRELATION_KEY_POST_URI = "{" + PROCESS_ID
			+ "}/instances/correlation/{" + CORRELATION_KEY + "}";
	public static final String ABORT_PROCESS_INST_DEL_URI = "instances/{" + PROCESS_INST_ID + "}";
	public static final String ABORT_PROCESS_INSTANCES_DEL_URI = "instances";
	public static final String SIGNAL_PROCESS_INST_POST_URI = "instances/{" + PROCESS_INST_ID + "}/signal/{"
			+ SIGNAL_NAME + "}";
	public static final String SIGNAL_PROCESS_INSTANCES_PORT_URI = "instances/signal/{" + SIGNAL_NAME + "}";
	public static final String PROCESS_INSTANCE_GET_URI = "instances/{" + PROCESS_INST_ID + "}";
	public static final String PROCESS_INSTANCE_VAR_PUT_URI = "instances/{" + PROCESS_INST_ID + "}/variable/{"
			+ VAR_NAME + "}";
	public static final String PROCESS_INSTANCE_VARS_POST_URI = "instances/{" + PROCESS_INST_ID + "}/variables";
	public static final String PROCESS_INSTANCE_VAR_GET_URI = "instances/{" + PROCESS_INST_ID + "}/variable/{"
			+ VAR_NAME + "}";
	public static final String PROCESS_INSTANCE_VARS_GET_URI = "instances/{" + PROCESS_INST_ID + "}/variables";
	public static final String PROCESS_INSTANCE_VAR_INSTANCES_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/variables/instances";
	public static final String PROCESS_INSTANCE_VAR_INSTANCE_BY_VAR_NAME_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/variables/instances/{" + VAR_NAME + "}";
	public static final String PROCESS_INSTANCE_SIGNALS_GET_URI = "instances/{" + PROCESS_INST_ID + "}/signals";
	public static final String PROCESS_INSTANCES_BY_CONTAINER_GET_URI = "instances";
	public static final String PROCESS_INSTANCES_BY_PARENT_GET_URI = "instances/{" + PROCESS_INST_ID + "}/processes";

	public static final String PROCESS_INSTANCES_NODE_INSTANCES_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/nodes/instances";
	public static final String PROCESS_INSTANCE_WORK_ITEM_COMPLETE_PUT_URI = "instances/{" + PROCESS_INST_ID
			+ "}/workitems/{" + WORK_ITEM_ID + "}/completed";
	public static final String PROCESS_INSTANCE_WORK_ITEM_ABORT_PUT_URI = "instances/{" + PROCESS_INST_ID
			+ "}/workitems/{" + WORK_ITEM_ID + "}/aborted";
	public static final String PROCESS_INSTANCE_WORK_ITEM_BY_ID_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/workitems/{" + WORK_ITEM_ID + "}";
	public static final String PROCESS_INSTANCE_WORK_ITEMS_BY_PROC_INST_ID_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/workitems";

	// process definition related prefixed by PROCESS_DEF_URI
	public static final String PROCESS_DEF_GET_URI = "{" + PROCESS_ID + "}";
	public static final String PROCESS_DEF_SUBPROCESS_GET_URI = "{" + PROCESS_ID + "}/subprocesses";
	public static final String PROCESS_DEF_VARIABLES_GET_URI = "{" + PROCESS_ID + "}/variables";
	public static final String PROCESS_DEF_SERVICE_TASKS_GET_URI = "{" + PROCESS_ID + "}/tasks/service";
	public static final String PROCESS_DEF_ASSOCIATED_ENTITIES_GET_URI = "{" + PROCESS_ID + "}/entities";
	public static final String PROCESS_DEF_USER_TASKS_GET_URI = "{" + PROCESS_ID + "}/tasks/users";
	public static final String PROCESS_DEF_USER_TASK_INPUT_GET_URI = "{" + PROCESS_ID + "}/tasks/users/{" + TASK_NAME
			+ "}/inputs";
	public static final String PROCESS_DEF_USER_TASK_OUTPUT_GET_URI = "{" + PROCESS_ID + "}/tasks/users/{" + TASK_NAME
			+ "}/outputs";

	// runtime data related prefixed by QUERY_URI
	public static final String PROCESS_INSTANCES_GET_URI = "processes/instances";
	public static final String PROCESS_INSTANCES_GET_FILTERED_URI = "processes/instances/filtered-data";
	public static final String PROCESS_INSTANCES_BY_PROCESS_ID_GET_URI = "processes/{" + PROCESS_ID + "}/instances";
	public static final String PROCESS_INSTANCES_BY_CONTAINER_ID_GET_URI = "containers/{" + CONTAINER_ID
			+ "}/process/instances";
	public static final String PROCESS_INSTANCE_BY_CORRELATION_KEY_GET_URI = "processes/instance/correlation/{"
			+ CORRELATION_KEY + "}";
	public static final String PROCESS_INSTANCES_BY_CORRELATION_KEY_GET_URI = "processes/instances/correlation/{"
			+ CORRELATION_KEY + "}";
	public static final String PROCESS_INSTANCE_BY_INSTANCE_ID_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}";
	public static final String PROCESS_INSTANCE_BY_VAR_NAME_GET_URI = "processes/instances/variables/{" + VAR_NAME
			+ "}";

	public static final String PROCESS_DEFINITIONS_BY_CONTAINER_ID_GET_URI = "containers/{" + CONTAINER_ID
			+ "}/processes/definitions";
	public static final String PROCESS_DEFINITIONS_GET_URI = "processes/definitions";
	public static final String PROCESS_DEFINITIONS_BY_CONTAINER_ID_DEF_ID_GET_URI = "containers/{" + CONTAINER_ID
			+ "}/processes/definitions/{" + PROCESS_ID + "}";
	public static final String PROCESS_DEFINITIONS_BY_ID_GET_URI = "processes/definitions/{" + PROCESS_ID + "}";

	public static final String NODE_INSTANCES_BY_INSTANCE_ID_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}/nodes/instances";
	public static final String NODE_INSTANCES_BY_WORK_ITEM_ID_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}/wi-nodes/instances/{" + WORK_ITEM_ID + "}";

	public static final String VAR_INSTANCES_BY_INSTANCE_ID_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}/variables/instances";
	public static final String VAR_INSTANCES_BY_VAR_INSTANCE_ID_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}/variables/instances/{" + VAR_NAME + "}";

	// task search related prefixed by QUERY_URI
	public static final String TASKS_ASSIGN_POT_OWNERS_GET_URI = "tasks/instances/pot-owners";
	public static final String TASKS_ASSIGN_BUSINESS_ADMINS_GET_URI = "tasks/instances/admins";
	public static final String TASKS_OWNED_GET_URI = "tasks/instances/owners";
	public static final String TASKS_GET_URI = "tasks/instances";
	public static final String TASKS_GET_FILTERED_URI = "tasks/instances/filtered-data";
	public static final String TASKS_EVENTS_GET_URI = "tasks/instances/{" + TASK_INSTANCE_ID + "}/events";
	public static final String TASK_GET_URI = "tasks/instances/{" + TASK_INSTANCE_ID + "}";
	public static final String TASK_BY_WORK_ITEM_ID_GET_URI = "tasks/instances/workitem/{" + WORK_ITEM_ID + "}";
	public static final String TASK_BY_PROCESS_INST_ID_GET_URI = "tasks/instances/process/{" + PROCESS_INST_ID + "}";
	public static final String TASKS_BY_VAR_NAME_GET_URI = "tasks/instances/variables/{" + VAR_NAME + "}";

	// task related prefixed by TASK_URI
	public static final String TASK_INSTANCE_ACTIVATE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/activated";
	public static final String TASK_INSTANCE_CLAIM_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/claimed";
	public static final String TASK_INSTANCE_START_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/started";
	public static final String TASK_INSTANCE_STOP_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/stopped";
	public static final String TASK_INSTANCE_COMPLETE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/completed";
	public static final String TASK_INSTANCE_DELEGATE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/delegated";
	public static final String TASK_INSTANCE_EXIT_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/exited";
	public static final String TASK_INSTANCE_FAIL_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/failed";
	public static final String TASK_INSTANCE_FORWARD_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/forwarded";
	public static final String TASK_INSTANCE_RELEASE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/released";
	public static final String TASK_INSTANCE_RESUME_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/resumed";
	public static final String TASK_INSTANCE_SKIP_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/skipped";
	public static final String TASK_INSTANCE_SUSPEND_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/suspended";
	public static final String TASK_INSTANCE_NOMINATE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/states/nominated";

	public static final String TASK_INSTANCE_PRIORITY_PUT_URI = "{" + TASK_INSTANCE_ID + "}/priority";
	public static final String TASK_INSTANCE_DESCRIPTION_PUT_URI = "{" + TASK_INSTANCE_ID + "}/description";
	public static final String TASK_INSTANCE_NAME_PUT_URI = "{" + TASK_INSTANCE_ID + "}/name";
	public static final String TASK_INSTANCE_EXPIRATION_DATE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/expiration";
	public static final String TASK_INSTANCE_SKIPABLE_PUT_URI = "{" + TASK_INSTANCE_ID + "}/skipable";

	public static final String TASK_INSTANCE_OUTPUT_DATA_PUT_URI = "{" + TASK_INSTANCE_ID + "}/contents/output";
	public static final String TASK_INSTANCE_OUTPUT_DATA_GET_URI = "{" + TASK_INSTANCE_ID + "}/contents/output";
	public static final String TASK_INSTANCE_INPUT_DATA_GET_URI = "{" + TASK_INSTANCE_ID + "}/contents/input";

	public static final String TASK_INSTANCE_CONTENT_DATA_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/contents/{"
			+ CONTENT_ID + "}";

	public static final String TASK_INSTANCE_COMMENT_ADD_POST_URI = "{" + TASK_INSTANCE_ID + "}/comments";
	public static final String TASK_INSTANCE_COMMENTS_GET_URI = "{" + TASK_INSTANCE_ID + "}/comments";
	public static final String TASK_INSTANCE_COMMENT_GET_URI = "{" + TASK_INSTANCE_ID + "}/comments/{" + COMMENT_ID
			+ "}";
	public static final String TASK_INSTANCE_COMMENT_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/comments/{" + COMMENT_ID
			+ "}";

	public static final String TASK_INSTANCE_ATTACHMENT_ADD_POST_URI = "{" + TASK_INSTANCE_ID + "}/attachments";
	public static final String TASK_INSTANCE_ATTACHMENTS_GET_URI = "{" + TASK_INSTANCE_ID + "}/attachments";
	public static final String TASK_INSTANCE_ATTACHMENT_GET_URI = "{" + TASK_INSTANCE_ID + "}/attachments/{"
			+ ATTACHMENT_ID + "}";
	public static final String TASK_INSTANCE_ATTACHMENT_CONTENT_GET_URI = "{" + TASK_INSTANCE_ID + "}/attachments/{"
			+ ATTACHMENT_ID + "}/content";
	public static final String TASK_INSTANCE_ATTACHMENT_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/attachments/{"
			+ ATTACHMENT_ID + "}";

	public static final String TASK_INSTANCE_GET_URI = "{" + TASK_INSTANCE_ID + "}";
	public static final String TASK_INSTANCE_PUT_URI = "{" + TASK_INSTANCE_ID + "}";

	public static final String TASK_INSTANCE_EVENTS_GET_URI = "{" + TASK_INSTANCE_ID + "}/events";

	// job executor prefixed by JOB_URI
	public static final String CANCEL_JOB_DEL_URI = "{" + JOB_ID + "}";
	public static final String REQUEUE_JOB_PUT_URI = "{" + JOB_ID + "}";
	public static final String UPDATE_JOB_DATA_POST_URI = "{" + JOB_ID + "}/data";
	public static final String JOB_INSTANCES_BY_KEY_GET_URI = "keys/{" + JOB_KEY + "}";
	public static final String JOB_INSTANCES_BY_CMD_GET_URI = "commands/{" + JOB_CMD_NAME + "}";
	public static final String JOB_INSTANCES_BY_CONTAINER_GET_URI = "containers/{" + CONTAINER_ID + "}";
	public static final String JOB_INSTANCES_BY_PROCESS_INSTANCE_GET_URI = "processes/instances/{" + PROCESS_INST_ID
			+ "}";
	public static final String JOB_INSTANCE_GET_URI = "{" + JOB_ID + "}";

	// form prefixed by FORM_URI
	public static final String PROCESS_FORM_GET_URI = "processes/{" + PROCESS_ID + "}";
	public static final String TASK_FORM_GET_URI = "tasks/{" + TASK_INSTANCE_ID + "}";

	// image prefixed by IMAGE_URI
	public static final String PROCESS_IMG_GET_URI = "processes/{" + PROCESS_ID + "}";
	public static final String PROCESS_INST_IMG_GET_URI = "processes/instances/{" + PROCESS_INST_ID + "}";

	// query definition related
	public static final String CREATE_QUERY_DEF_POST_URI = "{" + QUERY_NAME + "}";
	public static final String REPLACE_QUERY_DEF_PUT_URI = "{" + QUERY_NAME + "}";
	public static final String DROP_QUERY_DEF_DELETE_URI = "{" + QUERY_NAME + "}";
	public static final String QUERY_DEF_GET_URI = "{" + QUERY_NAME + "}";
	public static final String RUN_QUERY_DEF_GET_URI = "{" + QUERY_NAME + "}/data";
	public static final String RUN_FILTERED_QUERY_DEF_POST_URI = "{" + QUERY_NAME + "}/filtered-data";

	// optaplanner URI
	public static final String SOLVER_URI = "containers/{" + CONTAINER_ID + "}/solvers";
	public static final String SOLVER_ID_URI = "{" + SOLVER_ID + "}";
	public static final String SOLVER_BEST_SOLUTION = "bestsolution";
	public static final String SOLVER_PROBLEM_FACT_CHANGES = "problemfactchanges";
	public static final String SOLVER_PROBLEM_FACTS_CHANGES_PROCESSED = SOLVER_PROBLEM_FACT_CHANGES + "/processed";
	public static final String SOLVER_STATE_RUNNING = "state/solving";
	public static final String SOLVER_STATE_TERMINATING = "state/terminating-early";

	// DMN URI
	public static final String DMN_URI = "containers/{" + CONTAINER_ID + "}/dmn";

	// document related
	public static final String DOCUMENT_INSTANCE_GET_URI = "{" + DOCUMENT_ID + "}";
	public static final String DOCUMENT_INSTANCE_CONTENT_GET_URI = "{" + DOCUMENT_ID + "}/content";
	public static final String DOCUMENT_INSTANCE_PUT_URI = "{" + DOCUMENT_ID + "}";
	public static final String DOCUMENT_INSTANCE_DELETE_URI = "{" + DOCUMENT_ID + "}";

	// admin process related
	public static final String MIGRATE_PROCESS_INST_PUT_URI = "instances/{" + PROCESS_INST_ID + "}";
	public static final String MIGRATE_PROCESS_INSTANCES_PUT_URI = "instances";
	public static final String CANCEL_NODE_INST_PROCESS_INST_DELETE_URI = "instances/{" + PROCESS_INST_ID
			+ "}/nodeinstances/{" + NODE_INSTANCE_ID + "}";
	public static final String RETRIGGER_NODE_INST_PROCESS_INST_PUT_URI = "instances/{" + PROCESS_INST_ID
			+ "}/nodeinstances/{" + NODE_INSTANCE_ID + "}";
	public static final String UPDATE_TIMER_PROCESS_INST_PUT_URI = "instances/{" + PROCESS_INST_ID + "}/timers/{"
			+ TIMER_INSTANCE_ID + "}";
	public static final String TRIGGER_NODE_PROCESS_INST_POST_URI = "instances/{" + PROCESS_INST_ID + "}/nodes/{"
			+ NODE_ID + "}";
	public static final String NODES_PROCESS_INST_GET_URI = "instances/{" + PROCESS_INST_ID + "}/nodes";
	public static final String TIMERS_PROCESS_INST_GET_URI = "instances/{" + PROCESS_INST_ID + "}/timers";
	public static final String NODE_INSTANCES_PROCESS_INST_GET_URI = "instances/{" + PROCESS_INST_ID
			+ "}/nodeinstances";

	public static final String TASK_INSTANCE_POT_OWNERS_USERS_URI = "{" + TASK_INSTANCE_ID + "}/pot-owners";
	public static final String TASK_INSTANCE_EXL_OWNERS_USERS_URI = "{" + TASK_INSTANCE_ID + "}/exl-owners";
	public static final String TASK_INSTANCE_ADMINS_USERS_URI = "{" + TASK_INSTANCE_ID + "}/admins";
	public static final String TASK_INSTANCE_INPUTS_URI = "{" + TASK_INSTANCE_ID + "}/contents/input";
	public static final String TASK_INSTANCE_OUTPUTS_URI = "{" + TASK_INSTANCE_ID + "}/contents/output";
	public static final String TASK_INSTANCE_REASSIGNMENTS_URI = "{" + TASK_INSTANCE_ID + "}/reassignments";
	public static final String TASK_INSTANCE_NOTIFICATIONS_URI = "{" + TASK_INSTANCE_ID + "}/notifications";
	public static final String TASK_INSTANCE_REASSIGNMENT_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/reassignments/{"
			+ REASSIGNMENT_ID + "}";
	public static final String TASK_INSTANCE_NOTIFICATION_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/notifications/{"
			+ NOTIFICATION_ID + "}";

	public static final String TASK_INSTANCE_POT_OWNERS_USERS_DELETE_URI = "{" + TASK_INSTANCE_ID
			+ "}/pot-owners/users/{" + ENTITY_ID + "}";
	public static final String TASK_INSTANCE_POT_OWNERS_GROUPS_DELETE_URI = "{" + TASK_INSTANCE_ID
			+ "}/pot-owners/groups/{" + ENTITY_ID + "}";
	public static final String TASK_INSTANCE_EXL_OWNERS_USERS_DELETE_URI = "{" + TASK_INSTANCE_ID
			+ "}/exl-owners/users/{" + ENTITY_ID + "}";
	public static final String TASK_INSTANCE_EXL_OWNERS_GROUPS_DELETE_URI = "{" + TASK_INSTANCE_ID
			+ "}/exl-owners/groups/{" + ENTITY_ID + "}";
	public static final String TASK_INSTANCE_ADMINS_USERS_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/admins/users/{"
			+ ENTITY_ID + "}";
	public static final String TASK_INSTANCE_ADMINS_GROUPS_DELETE_URI = "{" + TASK_INSTANCE_ID + "}/admins/groups/{"
			+ ENTITY_ID + "}";

	// error handling related
	public static final String ERRORS_BY_PROCESS_INST_GET_URI = "instances/{" + PROCESS_INST_ID + "}/errors";
	public static final String ERROR_GET_URI = "errors/{" + ERROR_ID + "}";
	public static final String ACK_ERROR_PUT_URI = "errors/{" + ERROR_ID + "}";
	public static final String ACK_ERRORS_PUT_URI = "errors";

	public static final String ERRORS_BY_TASK_ID_GET_URI = "{" + TASK_INSTANCE_ID + "}/errors";
	public static final String ERRORS_GET_URI = "errors";

	// case management related
	public static final String START_CASE_POST_URI = "{" + CASE_DEF_ID + "}/instances";
	public static final String REOPEN_CASE_PUT_URI = "{" + CASE_DEF_ID + "}/instances/{" + CASE_ID + "}";
	public static final String CASE_GET_URI = "{" + CASE_DEF_ID + "}";
	public static final String CASE_INSTANCES_GET_URI = "instances";
	public static final String CASE_INSTANCES_BY_DEF_GET_URI = "{" + CASE_DEF_ID + "}/instances";
	public static final String CASE_INSTANCE_GET_URI = "instances/{" + CASE_ID + "}";
	public static final String CASE_INSTANCE_DELETE_URI = "instances/{" + CASE_ID + "}";
	public static final String CASE_MILESTONES_GET_URI = "instances/{" + CASE_ID + "}/milestones";
	public static final String CASE_STAGES_GET_URI = "instances/{" + CASE_ID + "}/stages";
	public static final String CASE_AD_HOC_FRAGMENTS_GET_URI = "instances/{" + CASE_ID + "}/adhocfragments";
	public static final String CASE_ROLES_GET_URI = "instances/{" + CASE_ID + "}/roles";
	public static final String CASE_ROLES_PUT_URI = "instances/{" + CASE_ID + "}/roles/{" + CASE_ROLE_NAME + "}";
	public static final String CASE_ROLES_DELETE_URI = "instances/{" + CASE_ID + "}/roles/{" + CASE_ROLE_NAME + "}";
	public static final String CASE_NODE_INSTANCES_GET_URI = "instances/{" + CASE_ID + "}/nodes/instances";
	public static final String CASE_PROCESS_INSTANCES_GET_URI = "instances/{" + CASE_ID + "}/processes/instances";
	public static final String CASE_COMMENTS_GET_URI = "instances/{" + CASE_ID + "}/comments";
	public static final String CASE_COMMENTS_POST_URI = "instances/{" + CASE_ID + "}/comments";
	public static final String CASE_COMMENTS_PUT_URI = "instances/{" + CASE_ID + "}/comments/{" + CASE_COMMENT_ID + "}";
	public static final String CASE_COMMENTS_DELETE_URI = "instances/{" + CASE_ID + "}/comments/{" + CASE_COMMENT_ID
			+ "}";
	public static final String CASE_FILE_GET_URI = "instances/{" + CASE_ID + "}/caseFile";
	public static final String CASE_FILE_POST_URI = "instances/{" + CASE_ID + "}/caseFile";
	public static final String CASE_FILE_DELETE_URI = "instances/{" + CASE_ID + "}/caseFile";
	public static final String CASE_FILE_BY_NAME_GET_URI = "instances/{" + CASE_ID + "}/caseFile/{" + CASE_FILE_ITEM
			+ "}";
	public static final String CASE_FILE_BY_NAME_POST_URI = "instances/{" + CASE_ID + "}/caseFile/{" + CASE_FILE_ITEM
			+ "}";
	public static final String CASE_DYNAMIC_TASK_POST_URI = "instances/{" + CASE_ID + "}/tasks";
	public static final String CASE_DYNAMIC_TASK_IN_STAGE_POST_URI = "instances/{" + CASE_ID + "}/stages/{"
			+ CASE_STAGE_ID + "}/tasks";
	public static final String CASE_DYNAMIC_PROCESS_POST_URI = "instances/{" + CASE_ID + "}/processes/{" + PROCESS_ID
			+ "}";
	public static final String CASE_DYNAMIC_PROCESS_IN_STAGE_POST_URI = "instances/{" + CASE_ID + "}/stages/{"
			+ CASE_STAGE_ID + "}/processes/{" + PROCESS_ID + "}";
	public static final String CASE_DYNAMIC_TASK_PUT_URI = "instances/{" + CASE_ID + "}/tasks/{" + CASE_NODE_NAME + "}";
	public static final String CASE_DYNAMIC_TASK_IN_STAGE_PUT_URI = "instances/{" + CASE_ID + "}/stages/{"
			+ CASE_STAGE_ID + "}/tasks/{" + CASE_NODE_NAME + "}";

	// case queries
	public static final String CASE_ALL_INSTANCES_GET_URI = "instances";
	public static final String CASE_ALL_DEFINITIONS_GET_URI = "definitions";
	public static final String CASE_DEFINITIONS_BY_ID_GET_URI = "definitions/{" + CASE_DEF_ID + "}";
	public static final String CASE_TASKS_AS_POT_OWNER_GET_URI = "instances/{" + CASE_ID
			+ "}/tasks/instances/pot-owners";
	public static final String CASE_TASKS_AS_ADMIN_GET_URI = "instances/{" + CASE_ID + "}/tasks/instances/admins";
	public static final String CASE_TASKS_AS_STAKEHOLDER_GET_URI = "instances/{" + CASE_ID
			+ "}/tasks/instances/stakeholders";
	public static final String CASE_INSTANCES_BY_ROLE_GET_URI = "{" + CASE_ROLE_NAME + "}/instances";
	public static final String CASE_ALL_PROCESSES_INSTANCES_GET_URI = "processes";
	public static final String CASE_PROCESSES_BY_CONTAINER_INSTANCES_GET_URI = "{" + CONTAINER_ID + "}/processes";

}