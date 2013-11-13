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
@javax.persistence.Table(name = "volume", schema = "dstack")
public class VolumeRecord extends org.jooq.impl.UpdatableRecordImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeRecord> implements org.jooq.Record20<java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Boolean, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String> {

	private static final long serialVersionUID = 1258582816;

	/**
	 * Setter for <code>dstack.volume.id</code>. 
	 */
	public VolumeRecord setId(java.lang.Long value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.id</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>dstack.volume.name</code>. 
	 */
	public VolumeRecord setName(java.lang.String value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.name</code>. 
	 */
	@javax.persistence.Column(name = "name", length = 255)
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>dstack.volume.description</code>. 
	 */
	public VolumeRecord setDescription(java.lang.String value) {
		setValue(2, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.description</code>. 
	 */
	@javax.persistence.Column(name = "description", length = 1024)
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>dstack.volume.account_id</code>. 
	 */
	public VolumeRecord setAccountId(java.lang.Long value) {
		setValue(3, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.account_id</code>. 
	 */
	@javax.persistence.Column(name = "account_id", nullable = false, precision = 19)
	public java.lang.Long getAccountId() {
		return (java.lang.Long) getValue(3);
	}

	/**
	 * Setter for <code>dstack.volume.uuid</code>. 
	 */
	public VolumeRecord setUuid(java.lang.String value) {
		setValue(4, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.uuid</code>. 
	 */
	@javax.persistence.Column(name = "uuid", nullable = false, length = 128)
	public java.lang.String getUuid() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>dstack.volume.state</code>. 
	 */
	public VolumeRecord setState(java.lang.String value) {
		setValue(5, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.state</code>. 
	 */
	@javax.persistence.Column(name = "state", nullable = false, length = 255)
	public java.lang.String getState() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>dstack.volume.physical_size_bytes</code>. 
	 */
	public VolumeRecord setPhysicalSizeBytes(java.lang.Long value) {
		setValue(6, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.physical_size_bytes</code>. 
	 */
	@javax.persistence.Column(name = "physical_size_bytes", precision = 19)
	public java.lang.Long getPhysicalSizeBytes() {
		return (java.lang.Long) getValue(6);
	}

	/**
	 * Setter for <code>dstack.volume.virtual_size_bytes</code>. 
	 */
	public VolumeRecord setVirtualSizeBytes(java.lang.Long value) {
		setValue(7, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.virtual_size_bytes</code>. 
	 */
	@javax.persistence.Column(name = "virtual_size_bytes", nullable = false, precision = 19)
	public java.lang.Long getVirtualSizeBytes() {
		return (java.lang.Long) getValue(7);
	}

	/**
	 * Setter for <code>dstack.volume.format</code>. 
	 */
	public VolumeRecord setFormat(java.lang.String value) {
		setValue(8, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.format</code>. 
	 */
	@javax.persistence.Column(name = "format", length = 255)
	public java.lang.String getFormat() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>dstack.volume.image_id</code>. 
	 */
	public VolumeRecord setImageId(java.lang.Long value) {
		setValue(9, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.image_id</code>. 
	 */
	@javax.persistence.Column(name = "image_id", precision = 19)
	public java.lang.Long getImageId() {
		return (java.lang.Long) getValue(9);
	}

	/**
	 * Setter for <code>dstack.volume.offering_id</code>. 
	 */
	public VolumeRecord setOfferingId(java.lang.Long value) {
		setValue(10, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.offering_id</code>. 
	 */
	@javax.persistence.Column(name = "offering_id", precision = 19)
	public java.lang.Long getOfferingId() {
		return (java.lang.Long) getValue(10);
	}

	/**
	 * Setter for <code>dstack.volume.device_number</code>. 
	 */
	public VolumeRecord setDeviceNumber(java.lang.Integer value) {
		setValue(11, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.device_number</code>. 
	 */
	@javax.persistence.Column(name = "device_number", precision = 10)
	public java.lang.Integer getDeviceNumber() {
		return (java.lang.Integer) getValue(11);
	}

	/**
	 * Setter for <code>dstack.volume.instance_id</code>. 
	 */
	public VolumeRecord setInstanceId(java.lang.Long value) {
		setValue(12, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.instance_id</code>. 
	 */
	@javax.persistence.Column(name = "instance_id", precision = 19)
	public java.lang.Long getInstanceId() {
		return (java.lang.Long) getValue(12);
	}

	/**
	 * Setter for <code>dstack.volume.recreatable</code>. 
	 */
	public VolumeRecord setRecreatable(java.lang.Boolean value) {
		setValue(13, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.recreatable</code>. 
	 */
	@javax.persistence.Column(name = "recreatable", nullable = false, precision = 1)
	public java.lang.Boolean getRecreatable() {
		return (java.lang.Boolean) getValue(13);
	}

	/**
	 * Setter for <code>dstack.volume.attached_state</code>. 
	 */
	public VolumeRecord setAttachedState(java.lang.String value) {
		setValue(14, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.attached_state</code>. 
	 */
	@javax.persistence.Column(name = "attached_state", nullable = false, length = 255)
	public java.lang.String getAttachedState() {
		return (java.lang.String) getValue(14);
	}

	/**
	 * Setter for <code>dstack.volume.allocation_state</code>. 
	 */
	public VolumeRecord setAllocationState(java.lang.String value) {
		setValue(15, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.allocation_state</code>. 
	 */
	@javax.persistence.Column(name = "allocation_state", nullable = false, length = 255)
	public java.lang.String getAllocationState() {
		return (java.lang.String) getValue(15);
	}

	/**
	 * Setter for <code>dstack.volume.created</code>. 
	 */
	public VolumeRecord setCreated(java.sql.Timestamp value) {
		setValue(16, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.created</code>. 
	 */
	@javax.persistence.Column(name = "created")
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(16);
	}

	/**
	 * Setter for <code>dstack.volume.removed</code>. 
	 */
	public VolumeRecord setRemoved(java.sql.Timestamp value) {
		setValue(17, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.removed</code>. 
	 */
	@javax.persistence.Column(name = "removed")
	public java.sql.Timestamp getRemoved() {
		return (java.sql.Timestamp) getValue(17);
	}

	/**
	 * Setter for <code>dstack.volume.remove_time</code>. 
	 */
	public VolumeRecord setRemoveTime(java.sql.Timestamp value) {
		setValue(18, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.remove_time</code>. 
	 */
	@javax.persistence.Column(name = "remove_time")
	public java.sql.Timestamp getRemoveTime() {
		return (java.sql.Timestamp) getValue(18);
	}

	/**
	 * Setter for <code>dstack.volume.data</code>. 
	 */
	public VolumeRecord setData(java.lang.String value) {
		setValue(19, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.volume.data</code>. 
	 */
	@javax.persistence.Column(name = "data", length = 16777215)
	public java.lang.String getData() {
		return (java.lang.String) getValue(19);
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
	// Record20 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row20<java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Boolean, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String> fieldsRow() {
		return (org.jooq.Row20) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row20<java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Boolean, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.String> valuesRow() {
		return (org.jooq.Row20) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field4() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.ACCOUNT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.UUID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field7() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.PHYSICAL_SIZE_BYTES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field8() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.VIRTUAL_SIZE_BYTES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.FORMAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field10() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.IMAGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field11() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.OFFERING_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field12() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.DEVICE_NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field13() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.INSTANCE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field14() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.RECREATABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field15() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.ATTACHED_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field16() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.ALLOCATION_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field17() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field18() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field19() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.REMOVE_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field20() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME.DATA;
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
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value4() {
		return getAccountId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getUuid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value7() {
		return getPhysicalSizeBytes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value8() {
		return getVirtualSizeBytes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getFormat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value10() {
		return getImageId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value11() {
		return getOfferingId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value12() {
		return getDeviceNumber();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value13() {
		return getInstanceId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value14() {
		return getRecreatable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value15() {
		return getAttachedState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value16() {
		return getAllocationState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value17() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value18() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value19() {
		return getRemoveTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value20() {
		return getData();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VolumeRecord
	 */
	public VolumeRecord() {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME);
	}

	/**
	 * Create a detached, initialised VolumeRecord
	 */
	public VolumeRecord(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.Long accountId, java.lang.String uuid, java.lang.String state, java.lang.Long physicalSizeBytes, java.lang.Long virtualSizeBytes, java.lang.String format, java.lang.Long imageId, java.lang.Long offeringId, java.lang.Integer deviceNumber, java.lang.Long instanceId, java.lang.Boolean recreatable, java.lang.String attachedState, java.lang.String allocationState, java.sql.Timestamp created, java.sql.Timestamp removed, java.sql.Timestamp removeTime, java.lang.String data) {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.Volume.VOLUME);

		setValue(0, id);
		setValue(1, name);
		setValue(2, description);
		setValue(3, accountId);
		setValue(4, uuid);
		setValue(5, state);
		setValue(6, physicalSizeBytes);
		setValue(7, virtualSizeBytes);
		setValue(8, format);
		setValue(9, imageId);
		setValue(10, offeringId);
		setValue(11, deviceNumber);
		setValue(12, instanceId);
		setValue(13, recreatable);
		setValue(14, attachedState);
		setValue(15, allocationState);
		setValue(16, created);
		setValue(17, removed);
		setValue(18, removeTime);
		setValue(19, data);
	}
}
