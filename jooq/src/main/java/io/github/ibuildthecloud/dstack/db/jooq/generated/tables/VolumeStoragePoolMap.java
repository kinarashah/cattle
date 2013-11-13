/**
 * This class is generated by jOOQ
 */
package io.github.ibuildthecloud.dstack.db.jooq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VolumeStoragePoolMap extends org.jooq.impl.TableImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord> {

	private static final long serialVersionUID = 145839619;

	/**
	 * The singleton instance of <code>dstack.volume_storage_pool_map</code>
	 */
	public static final io.github.ibuildthecloud.dstack.db.jooq.generated.tables.VolumeStoragePoolMap VOLUME_STORAGE_POOL_MAP = new io.github.ibuildthecloud.dstack.db.jooq.generated.tables.VolumeStoragePoolMap();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord> getRecordType() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord.class;
	}

	/**
	 * The column <code>dstack.volume_storage_pool_map.id</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>dstack.volume_storage_pool_map.volume_id</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.Long> VOLUME_ID = createField("volume_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>dstack.volume_storage_pool_map.storage_pool_id</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.Long> STORAGE_POOL_ID = createField("storage_pool_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>dstack.volume_storage_pool_map.storage_pool_location</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.Long> STORAGE_POOL_LOCATION = createField("storage_pool_location", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * The column <code>dstack.volume_storage_pool_map.state</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this);

	/**
	 * The column <code>dstack.volume_storage_pool_map.removed</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.sql.Timestamp> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * Create a <code>dstack.volume_storage_pool_map</code> table reference
	 */
	public VolumeStoragePoolMap() {
		super("volume_storage_pool_map", io.github.ibuildthecloud.dstack.db.jooq.generated.Dstack.DSTACK);
	}

	/**
	 * Create an aliased <code>dstack.volume_storage_pool_map</code> table reference
	 */
	public VolumeStoragePoolMap(java.lang.String alias) {
		super(alias, io.github.ibuildthecloud.dstack.db.jooq.generated.Dstack.DSTACK, io.github.ibuildthecloud.dstack.db.jooq.generated.tables.VolumeStoragePoolMap.VOLUME_STORAGE_POOL_MAP);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, java.lang.Long> getIdentity() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.IDENTITY_VOLUME_STORAGE_POOL_MAP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord> getPrimaryKey() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.KEY_VOLUME_STORAGE_POOL_MAP_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord>>asList(io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.KEY_VOLUME_STORAGE_POOL_MAP_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.VolumeStoragePoolMapRecord, ?>>asList(io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.VOLUME_STORAGE_POOL_MAP_IBFK_1, io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.VOLUME_STORAGE_POOL_MAP_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.github.ibuildthecloud.dstack.db.jooq.generated.tables.VolumeStoragePoolMap as(java.lang.String alias) {
		return new io.github.ibuildthecloud.dstack.db.jooq.generated.tables.VolumeStoragePoolMap(alias);
	}
}
