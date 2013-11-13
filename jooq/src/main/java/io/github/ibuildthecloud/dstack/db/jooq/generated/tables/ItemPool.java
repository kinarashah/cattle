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
public class ItemPool extends org.jooq.impl.TableImpl<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord> {

	private static final long serialVersionUID = 1473987746;

	/**
	 * The singleton instance of <code>dstack.item_pool</code>
	 */
	public static final io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ItemPool ITEM_POOL = new io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ItemPool();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord> getRecordType() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord.class;
	}

	/**
	 * The column <code>dstack.item_pool.id</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.name</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.kind</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.state</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.qualifier</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> QUALIFIER = createField("qualifier", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.item_pool_generator_id</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.Long> ITEM_POOL_GENERATOR_ID = createField("item_pool_generator_id", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * The column <code>dstack.item_pool.segment</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> SEGMENT = createField("segment", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>dstack.item_pool.description</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>dstack.item_pool.uuid</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this);

	/**
	 * The column <code>dstack.item_pool.data</code>. 
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.String> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(16777215), this);

	/**
	 * Create a <code>dstack.item_pool</code> table reference
	 */
	public ItemPool() {
		super("item_pool", io.github.ibuildthecloud.dstack.db.jooq.generated.Dstack.DSTACK);
	}

	/**
	 * Create an aliased <code>dstack.item_pool</code> table reference
	 */
	public ItemPool(java.lang.String alias) {
		super(alias, io.github.ibuildthecloud.dstack.db.jooq.generated.Dstack.DSTACK, io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ItemPool.ITEM_POOL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, java.lang.Long> getIdentity() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.IDENTITY_ITEM_POOL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord> getPrimaryKey() {
		return io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.KEY_ITEM_POOL_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord>>asList(io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.KEY_ITEM_POOL_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.db.jooq.generated.tables.records.ItemPoolRecord, ?>>asList(io.github.ibuildthecloud.dstack.db.jooq.generated.Keys.ITEM_POOL_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ItemPool as(java.lang.String alias) {
		return new io.github.ibuildthecloud.dstack.db.jooq.generated.tables.ItemPool(alias);
	}
}
