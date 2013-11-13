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
@javax.persistence.Table(name = "image_storage_pool_map", schema = "dstack")
public class ImageStoragePoolMapRecord extends org.jooq.impl.UpdatableRecordImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ImageStoragePoolMapRecord> implements org.jooq.Record13<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean> {

	private static final long serialVersionUID = -1442678436;

	/**
	 * Setter for <code>dstack.image_storage_pool_map.id</code>. 
	 */
	public ImageStoragePoolMapRecord setId(java.lang.Long value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.id</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "id", unique = true, nullable = false, precision = 19)
	public java.lang.Long getId() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.template_id</code>. 
	 */
	public ImageStoragePoolMapRecord setTemplateId(java.lang.Long value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.template_id</code>. 
	 */
	@javax.persistence.Column(name = "template_id", nullable = false, precision = 19)
	public java.lang.Long getTemplateId() {
		return (java.lang.Long) getValue(1);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.storage_pool_id</code>. 
	 */
	public ImageStoragePoolMapRecord setStoragePoolId(java.lang.Long value) {
		setValue(2, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.storage_pool_id</code>. 
	 */
	@javax.persistence.Column(name = "storage_pool_id", nullable = false, precision = 19)
	public java.lang.Long getStoragePoolId() {
		return (java.lang.Long) getValue(2);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.progress</code>. 
	 */
	public ImageStoragePoolMapRecord setProgress(java.lang.String value) {
		setValue(3, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.progress</code>. 
	 */
	@javax.persistence.Column(name = "progress", length = 255)
	public java.lang.String getProgress() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.resource_state</code>. 
	 */
	public ImageStoragePoolMapRecord setResourceState(java.lang.String value) {
		setValue(4, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.resource_state</code>. 
	 */
	@javax.persistence.Column(name = "resource_state", nullable = false, length = 255)
	public java.lang.String getResourceState() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.location</code>. 
	 */
	public ImageStoragePoolMapRecord setLocation(java.lang.String value) {
		setValue(5, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.location</code>. 
	 */
	@javax.persistence.Column(name = "location", length = 255)
	public java.lang.String getLocation() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.created</code>. 
	 */
	public ImageStoragePoolMapRecord setCreated(java.sql.Timestamp value) {
		setValue(6, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.created</code>. 
	 */
	@javax.persistence.Column(name = "created")
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(6);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.billing_start</code>. 
	 */
	public ImageStoragePoolMapRecord setBillingStart(java.sql.Timestamp value) {
		setValue(7, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.billing_start</code>. 
	 */
	@javax.persistence.Column(name = "billing_start")
	public java.sql.Timestamp getBillingStart() {
		return (java.sql.Timestamp) getValue(7);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.billing_end</code>. 
	 */
	public ImageStoragePoolMapRecord setBillingEnd(java.sql.Timestamp value) {
		setValue(8, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.billing_end</code>. 
	 */
	@javax.persistence.Column(name = "billing_end")
	public java.sql.Timestamp getBillingEnd() {
		return (java.sql.Timestamp) getValue(8);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.removed</code>. 
	 */
	public ImageStoragePoolMapRecord setRemoved(java.sql.Timestamp value) {
		setValue(9, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.removed</code>. 
	 */
	@javax.persistence.Column(name = "removed")
	public java.sql.Timestamp getRemoved() {
		return (java.sql.Timestamp) getValue(9);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.resource_remove_schedule</code>. 
	 */
	public ImageStoragePoolMapRecord setResourceRemoveSchedule(java.sql.Timestamp value) {
		setValue(10, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.resource_remove_schedule</code>. 
	 */
	@javax.persistence.Column(name = "resource_remove_schedule")
	public java.sql.Timestamp getResourceRemoveSchedule() {
		return (java.sql.Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.resource_removed</code>. 
	 */
	public ImageStoragePoolMapRecord setResourceRemoved(java.sql.Timestamp value) {
		setValue(11, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.resource_removed</code>. 
	 */
	@javax.persistence.Column(name = "resource_removed")
	public java.sql.Timestamp getResourceRemoved() {
		return (java.sql.Timestamp) getValue(11);
	}

	/**
	 * Setter for <code>dstack.image_storage_pool_map.remove_locked</code>. 
	 */
	public ImageStoragePoolMapRecord setRemoveLocked(java.lang.Boolean value) {
		setValue(12, value);
		return this;
	}

	/**
	 * Getter for <code>dstack.image_storage_pool_map.remove_locked</code>. 
	 */
	@javax.persistence.Column(name = "remove_locked", nullable = false, precision = 1)
	public java.lang.Boolean getRemoveLocked() {
		return (java.lang.Boolean) getValue(12);
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
	public org.jooq.Row13<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean> fieldsRow() {
		return (org.jooq.Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row13<java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.sql.Timestamp, java.lang.Boolean> valuesRow() {
		return (org.jooq.Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field2() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.TEMPLATE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field3() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.STORAGE_POOL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.PROGRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.RESOURCE_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.LOCATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field7() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field8() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.BILLING_START;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field9() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.BILLING_END;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field10() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field11() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.RESOURCE_REMOVE_SCHEDULE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field12() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.RESOURCE_REMOVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field13() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP.REMOVE_LOCKED;
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
	public java.lang.Long value2() {
		return getTemplateId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value3() {
		return getStoragePoolId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getProgress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getResourceState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getLocation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value7() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value8() {
		return getBillingStart();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value9() {
		return getBillingEnd();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value10() {
		return getRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value11() {
		return getResourceRemoveSchedule();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value12() {
		return getResourceRemoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value13() {
		return getRemoveLocked();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ImageStoragePoolMapRecord
	 */
	public ImageStoragePoolMapRecord() {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP);
	}

	/**
	 * Create a detached, initialised ImageStoragePoolMapRecord
	 */
	public ImageStoragePoolMapRecord(java.lang.Long id, java.lang.Long templateId, java.lang.Long storagePoolId, java.lang.String progress, java.lang.String resourceState, java.lang.String location, java.sql.Timestamp created, java.sql.Timestamp billingStart, java.sql.Timestamp billingEnd, java.sql.Timestamp removed, java.sql.Timestamp resourceRemoveSchedule, java.sql.Timestamp resourceRemoved, java.lang.Boolean removeLocked) {
		super(io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ImageStoragePoolMap.IMAGE_STORAGE_POOL_MAP);

		setValue(0, id);
		setValue(1, templateId);
		setValue(2, storagePoolId);
		setValue(3, progress);
		setValue(4, resourceState);
		setValue(5, location);
		setValue(6, created);
		setValue(7, billingStart);
		setValue(8, billingEnd);
		setValue(9, removed);
		setValue(10, resourceRemoveSchedule);
		setValue(11, resourceRemoved);
		setValue(12, removeLocked);
	}
}
