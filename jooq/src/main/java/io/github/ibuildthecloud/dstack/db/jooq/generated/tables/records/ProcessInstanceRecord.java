/**
 * This class is generated by jOOQ
 */
package io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "process_instance", schema = "dstack")
public class ProcessInstanceRecord extends org.jooq.impl.UpdatableRecordImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ProcessInstanceRecord> implements org.jooq.Record13<java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long> {

	private static final long serialVersionUID = -95291659;

	/**
	 * Setter for <code>dstack.process_instance.id</code>. 
	 */
	public ProcessInstanceRecord setId(java.lang.Long value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.id</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 20)
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>dstack.process_instance.start_time</code>. 
	 */
	public ProcessInstanceRecord setStartTime(java.sql.Timestamp value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.start_time</code>. 
	 */
	@javax.persistence.Column(name = "start_time")
	public java.sql.Timestamp getStartTime() {
		return (java.sql.Timestamp) getValue(1);
	}

	/**
	 * Setter for <code>dstack.process_instance.end_time</code>. 
	 */
	public ProcessInstanceRecord setEndTime(java.sql.Timestamp value) {
		setValue(2, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.end_time</code>. 
	 */
	@javax.persistence.Column(name = "end_time")
	public java.sql.Timestamp getEndTime() {
		return (java.sql.Timestamp) getValue(2);
	}

	/**
	 * Setter for <code>dstack.process_instance.log</code>. 
	 */
	public ProcessInstanceRecord setLog(java.lang.String value) {
		setValue(3, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.log</code>. 
	 */
	@javax.persistence.Column(name = "log", length = 16777215)
	public java.lang.String getLog() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>dstack.process_instance.data</code>. 
	 */
	public ProcessInstanceRecord setData(java.lang.String value) {
		setValue(4, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.data</code>. 
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	public java.lang.String getData() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>dstack.process_instance.process_name</code>. 
	 */
	public ProcessInstanceRecord setProcessName(java.lang.String value) {
		setValue(5, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.process_name</code>. 
	 */
	@javax.persistence.Column(name = "process_name", length = 128)
	public java.lang.String getProcessName() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>dstack.process_instance.resource_type</code>. 
	 */
	public ProcessInstanceRecord setResourceType(java.lang.String value) {
		setValue(6, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.resource_type</code>. 
	 */
	@javax.persistence.Column(name = "resource_type", length = 128)
	public java.lang.String getResourceType() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>dstack.process_instance.resource_id</code>. 
	 */
	public ProcessInstanceRecord setResourceId(java.lang.String value) {
		setValue(7, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.resource_id</code>. 
	 */
	@javax.persistence.Column(name = "resource_id", length = 128)
	public java.lang.String getResourceId() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>dstack.process_instance.result</code>. 
	 */
	public ProcessInstanceRecord setResult(java.lang.String value) {
		setValue(8, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.result</code>. 
	 */
	@javax.persistence.Column(name = "result", length = 128)
	public java.lang.String getResult() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>dstack.process_instance.exit_reason</code>. 
	 */
	public ProcessInstanceRecord setExitReason(java.lang.String value) {
		setValue(9, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.exit_reason</code>. 
	 */
	@javax.persistence.Column(name = "exit_reason", length = 128)
	public java.lang.String getExitReason() {
		return (java.lang.String) getValue(9);
	}

	/**
	 * Setter for <code>dstack.process_instance.phase</code>. 
	 */
	public ProcessInstanceRecord setPhase(java.lang.String value) {
		setValue(10, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.phase</code>. 
	 */
	@javax.persistence.Column(name = "phase", length = 128)
	public java.lang.String getPhase() {
		return (java.lang.String) getValue(10);
	}

	/**
	 * Setter for <code>dstack.process_instance.start_process_server_id</code>. 
	 */
	public ProcessInstanceRecord setStartProcessServerId(java.lang.Long value) {
		setValue(11, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.start_process_server_id</code>. 
	 */
	@javax.persistence.Column(name = "start_process_server_id", precision = 20)
	public java.lang.Long getStartProcessServerId() {
		return (java.lang.Long) getValue(11);
	}

	/**
	 * Setter for <code>dstack.process_instance.running_process_server_id</code>. 
	 */
	public ProcessInstanceRecord setRunningProcessServerId(java.lang.Long value) {
		setValue(12, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.process_instance.running_process_server_id</code>. 
	 */
	@javax.persistence.Column(name = "running_process_server_id", precision = 20)
	public java.lang.Long getRunningProcessServerId() {
		return (java.lang.Long) getValue(12);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Long> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long> fieldsRow() {
		return (org.jooq.Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Long, java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long> valuesRow() {
		return (org.jooq.Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field2() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.START_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field3() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.END_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.LOG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.PROCESS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.RESOURCE_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.RESOURCE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.RESULT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.EXIT_REASON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field11() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.PHASE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field12() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.START_PROCESS_SERVER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field13() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE.RUNNING_PROCESS_SERVER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value2() {
		return getStartTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value3() {
		return getEndTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getLog();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getProcessName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getResourceType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getResourceId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getExitReason();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value11() {
		return getPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value12() {
		return getStartProcessServerId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value13() {
		return getRunningProcessServerId();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ProcessInstanceRecord
	 */
	public ProcessInstanceRecord() {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE);
	}

	/**
	 * Create a detached, initialised ProcessInstanceRecord
	 */
	public ProcessInstanceRecord(java.lang.Long id, java.sql.Timestamp startTime, java.sql.Timestamp endTime, java.lang.String log, java.lang.String data, java.lang.String processName, java.lang.String resourceType, java.lang.String resourceId, java.lang.String result, java.lang.String exitReason, java.lang.String phase, java.lang.Long startProcessServerId, java.lang.Long runningProcessServerId) {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ProcessInstance.PROCESS_INSTANCE);

		setValue(0, id);
		setValue(1, startTime);
		setValue(2, endTime);
		setValue(3, log);
		setValue(4, data);
		setValue(5, processName);
		setValue(6, resourceType);
		setValue(7, resourceId);
		setValue(8, result);
		setValue(9, exitReason);
		setValue(10, phase);
		setValue(11, startProcessServerId);
		setValue(12, runningProcessServerId);
	}
}
