package com.cesgroup.core.utils;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.MappingException;
import org.hibernate.dialect.ColumnAliasExtractor;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.lock.LockingStrategy;
import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.hibernate.dialect.lock.OptimisticLockingStrategy;
import org.hibernate.dialect.lock.PessimisticForceIncrementLockingStrategy;
import org.hibernate.dialect.lock.PessimisticReadSelectLockingStrategy;
import org.hibernate.dialect.lock.PessimisticWriteSelectLockingStrategy;
import org.hibernate.dialect.lock.SelectLockingStrategy;
import org.hibernate.exception.internal.SQLStateConverter;
import org.hibernate.exception.spi.SQLExceptionConverter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.persister.entity.Lockable;
import org.hibernate.type.StandardBasicTypes;

/**
 * 支持hibernate4 的达梦方言
 * 
 * @author 国栋
 *
 */
@SuppressWarnings("deprecation")
public class DmDialect extends Dialect
{
	public DmDialect()
	{
		super();
		registerColumnType(Types.CHAR, "CHAR($l)");
		registerColumnType(Types.VARCHAR, "VARCHAR($l)");
		registerColumnType(Types.LONGVARCHAR, "TEXT");
		registerColumnType(Types.CLOB, "TEXT");
		registerColumnType(Types.BIT, "BIT");
		registerColumnType(Types.BOOLEAN, "BIT");
		registerColumnType(Types.TINYINT, "TINYINT");
		registerColumnType(Types.SMALLINT, "SMALLINT");
		registerColumnType(Types.INTEGER, "INTEGER");
		registerColumnType(Types.BIGINT, "BIGINT");
		registerColumnType(Types.REAL, "FLOAT");
		registerColumnType(Types.FLOAT, "FLOAT");
		registerColumnType(Types.DOUBLE, "DOUBLE");
		registerColumnType(Types.DECIMAL, "DECIMAL");
		registerColumnType(Types.NUMERIC, "DECIMAL");
		registerColumnType(Types.BINARY, "BINARY");
		registerColumnType(Types.VARBINARY, "BLOB");
		registerColumnType(Types.LONGVARBINARY, "BLOB");
		registerColumnType(Types.BLOB, "BLOB");
		registerColumnType(Types.DATE, "DATE");
		registerColumnType(Types.TIME, "TIME");
		registerColumnType(Types.TIMESTAMP, "DATETIME");

		registerKeyword("last");
		registerKeyword("size");
		registerHibernateType(Types.SMALLINT, StandardBasicTypes.SHORT.getName());

		// ��ֵ����
		registerFunction("abs", new StandardSQLFunction("abs")); //
		registerFunction("asin", new StandardSQLFunction("asin", StandardBasicTypes.DOUBLE));
		registerFunction("acos", new StandardSQLFunction("acos", StandardBasicTypes.DOUBLE));
		registerFunction("atan", new StandardSQLFunction("atan", StandardBasicTypes.DOUBLE));
		registerFunction("atan2", new StandardSQLFunction("atan2", StandardBasicTypes.DOUBLE));
		registerFunction("ceil", new StandardSQLFunction("ceil", StandardBasicTypes.INTEGER));
		registerFunction("ceiling", new StandardSQLFunction("ceiling", StandardBasicTypes.INTEGER));
		registerFunction("cos", new StandardSQLFunction("cos", StandardBasicTypes.DOUBLE));
		registerFunction("cot", new StandardSQLFunction("cot", StandardBasicTypes.DOUBLE));
		registerFunction("cosh", new StandardSQLFunction("cosh", StandardBasicTypes.DOUBLE));
		registerFunction("degrees", new StandardSQLFunction("degrees")); //
		registerFunction("exp", new StandardSQLFunction("exp", StandardBasicTypes.DOUBLE));
		registerFunction("GREATEST", new StandardSQLFunction("GREATEST", StandardBasicTypes.DOUBLE));
		registerFunction("floor", new StandardSQLFunction("floor", StandardBasicTypes.INTEGER));
		registerFunction("ln", new StandardSQLFunction("ln", StandardBasicTypes.DOUBLE));
		registerFunction("log", new StandardSQLFunction("log", StandardBasicTypes.DOUBLE));
		registerFunction("log10", new StandardSQLFunction("log10", StandardBasicTypes.DOUBLE));
		registerFunction("mod", new StandardSQLFunction("mod", StandardBasicTypes.INTEGER)); //
		registerFunction("pi", new NoArgSQLFunction("pi", StandardBasicTypes.DOUBLE));
		registerFunction("power", new StandardSQLFunction("power", StandardBasicTypes.DOUBLE));
		registerFunction("radians", new StandardSQLFunction("radians")); //
		registerFunction("rand", new NoArgSQLFunction("rand", StandardBasicTypes.DOUBLE)); //
		registerFunction("round", new StandardSQLFunction("round"));
		registerFunction("sign", new StandardSQLFunction("sign", StandardBasicTypes.INTEGER));
		registerFunction("sin", new StandardSQLFunction("sin", StandardBasicTypes.DOUBLE));
		registerFunction("sinh", new StandardSQLFunction("sinh", StandardBasicTypes.DOUBLE));
		registerFunction("sqrt", new StandardSQLFunction("sqrt", StandardBasicTypes.DOUBLE));
		registerFunction("tan", new StandardSQLFunction("tan", StandardBasicTypes.DOUBLE));
		registerFunction("tanh", new StandardSQLFunction("tanh", StandardBasicTypes.DOUBLE));
		registerFunction("trunc", new StandardSQLFunction("trunc")); //
		registerFunction("truncate", new StandardSQLFunction("truncate"));

		registerFunction("stddev", new StandardSQLFunction("stddev", StandardBasicTypes.DOUBLE));
		registerFunction("variance", new StandardSQLFunction("variance", StandardBasicTypes.DOUBLE));
		
		registerFunction("concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", ""));
		registerFunction("ascii", new StandardSQLFunction("ascii", StandardBasicTypes.INTEGER));
		registerFunction("bit_length", new StandardSQLFunction("bit_length", StandardBasicTypes.LONG));
		registerFunction("char", new StandardSQLFunction("char", StandardBasicTypes.CHARACTER)); //
		registerFunction("difference", new StandardSQLFunction("difference", StandardBasicTypes.INTEGER));

		registerFunction("LENGTH", new StandardSQLFunction("LENGTH", StandardBasicTypes.INTEGER));
		registerFunction("char_length", new StandardSQLFunction("char_length", StandardBasicTypes.LONG));
		registerFunction("character_length", new StandardSQLFunction("character_length", StandardBasicTypes.LONG));
		registerFunction("chr", new StandardSQLFunction("chr", StandardBasicTypes.CHARACTER));
		registerFunction("initcap", new StandardSQLFunction("initcap", StandardBasicTypes.STRING));
		registerFunction("insert", new StandardSQLFunction("insert", StandardBasicTypes.STRING));
		registerFunction("insstr", new StandardSQLFunction("insstr", StandardBasicTypes.STRING));
		registerFunction("instr", new StandardSQLFunction("instr", StandardBasicTypes.LONG));
		registerFunction("SUBSTRING", new StandardSQLFunction("SUBSTRING", StandardBasicTypes.STRING));
		registerFunction("instrb", new StandardSQLFunction("instrb", StandardBasicTypes.LONG));
		registerFunction("lcase", new StandardSQLFunction("lcase", StandardBasicTypes.STRING));
		registerFunction("left", new StandardSQLFunction("left", StandardBasicTypes.STRING));
		registerFunction("leftstr", new StandardSQLFunction("leftstr", StandardBasicTypes.STRING));
		registerFunction("len", new StandardSQLFunction("len", StandardBasicTypes.INTEGER));
		registerFunction("LENGTHB", new StandardSQLFunction("LENGTHB", StandardBasicTypes.INTEGER));
		registerFunction("octet_length", new StandardSQLFunction("octet_length", StandardBasicTypes.LONG));
		registerFunction("locate", new StandardSQLFunction("locate", StandardBasicTypes.LONG));
		registerFunction("lower", new StandardSQLFunction("lower", StandardBasicTypes.STRING));
		registerFunction("lpad", new StandardSQLFunction("lpad", StandardBasicTypes.STRING));
		registerFunction("ltrim", new StandardSQLFunction("ltrim", StandardBasicTypes.STRING));
		registerFunction("position", new StandardSQLFunction("position", StandardBasicTypes.INTEGER));
		registerFunction("INS", new StandardSQLFunction("INS", StandardBasicTypes.STRING));
		registerFunction("repeat", new StandardSQLFunction("repeat", StandardBasicTypes.STRING));
		registerFunction("REPLICATE", new StandardSQLFunction("REPLICATE", StandardBasicTypes.STRING));
		registerFunction("STUFF", new StandardSQLFunction("STUFF", StandardBasicTypes.STRING));
		registerFunction("repeatstr", new StandardSQLFunction("repeatstr", StandardBasicTypes.STRING));
		registerFunction("replace", new StandardSQLFunction("replace", StandardBasicTypes.STRING));
		registerFunction("reverse", new StandardSQLFunction("reverse", StandardBasicTypes.STRING));
		registerFunction("right", new StandardSQLFunction("right", StandardBasicTypes.STRING));
		registerFunction("rightstr", new StandardSQLFunction("rightstr", StandardBasicTypes.STRING));
		registerFunction("rpad", new StandardSQLFunction("rpad", StandardBasicTypes.STRING));
		registerFunction("TO_NUMBER", new StandardSQLFunction("TO_NUMBER"));
		registerFunction("rtrim", new StandardSQLFunction("rtrim", StandardBasicTypes.STRING));
		registerFunction("soundex", new StandardSQLFunction("soundex", StandardBasicTypes.STRING));
		registerFunction("space", new StandardSQLFunction("space", StandardBasicTypes.STRING));
		registerFunction("substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
		registerFunction("substrb", new StandardSQLFunction("substrb", StandardBasicTypes.STRING));
		registerFunction("to_char", new StandardSQLFunction("to_char", StandardBasicTypes.STRING));
		registerFunction("STRPOSDEC", new StandardSQLFunction("STRPOSDEC", StandardBasicTypes.STRING));
		registerFunction("STRPOSINC", new StandardSQLFunction("STRPOSINC", StandardBasicTypes.STRING));
		registerFunction("VSIZE", new StandardSQLFunction("VSIZE", StandardBasicTypes.INTEGER));
		registerFunction("translate", new StandardSQLFunction("translate", StandardBasicTypes.STRING));
		registerFunction("trim", new StandardSQLFunction("trim", StandardBasicTypes.STRING));
		registerFunction("ucase", new StandardSQLFunction("ucase", StandardBasicTypes.STRING));
		registerFunction("upper", new StandardSQLFunction("upper", StandardBasicTypes.STRING));

		registerFunction("OVERLAPS", new StandardSQLFunction("OVERLAPS"));
		registerFunction("DATEPART", new StandardSQLFunction("DATEPART"));
		registerFunction("DATE_PART", new StandardSQLFunction("DATE_PART")); //
		registerFunction("add_days", new StandardSQLFunction("add_days")); //
		registerFunction("add_months", new StandardSQLFunction("add_months")); //
		registerFunction("add_weeks", new StandardSQLFunction("add_weeks")); //
		registerFunction("curdate", new NoArgSQLFunction("curdate", StandardBasicTypes.DATE));
		registerFunction("curtime", new NoArgSQLFunction("curtime", StandardBasicTypes.TIME));
		registerFunction("current_date", new NoArgSQLFunction("current_date", StandardBasicTypes.DATE));
		registerFunction("current_time", new NoArgSQLFunction("current_time", StandardBasicTypes.TIME));
		registerFunction("current_timestamp", new NoArgSQLFunction("current_timestamp", StandardBasicTypes.TIMESTAMP));
		registerFunction("dateadd", new StandardSQLFunction("dateadd", StandardBasicTypes.TIMESTAMP));
		registerFunction("CUR_TICK_TIME", new StandardSQLFunction("CUR_TICK_TIME"));
		registerFunction("datediff", new StandardSQLFunction("datediff", StandardBasicTypes.INTEGER));
		registerFunction("datepart", new StandardSQLFunction("datepart", StandardBasicTypes.INTEGER));
		registerFunction("dayname", new StandardSQLFunction("dayname", StandardBasicTypes.STRING));
		registerFunction("dayofmonth", new StandardSQLFunction("dayofmonth", StandardBasicTypes.INTEGER));
		registerFunction("dayofweek", new StandardSQLFunction("dayofweek", StandardBasicTypes.INTEGER));
		registerFunction("dayofyear", new StandardSQLFunction("dayofyear", StandardBasicTypes.INTEGER));
		registerFunction("days_between", new StandardSQLFunction("days_between", StandardBasicTypes.INTEGER));
		registerFunction("extract", new StandardSQLFunction("extract")); //
		registerFunction("getdate", new StandardSQLFunction("getdate", StandardBasicTypes.TIMESTAMP));
		registerFunction("hour", new StandardSQLFunction("hour", StandardBasicTypes.INTEGER));
		registerFunction("LOCALTIMESTAMP", new StandardSQLFunction("LOCALTIMESTAMP"));
		registerFunction("NOW", new StandardSQLFunction("NOW"));
		registerFunction("last_day", new StandardSQLFunction("last_day")); //
		registerFunction("minute", new StandardSQLFunction("minute", StandardBasicTypes.INTEGER));
		registerFunction("month", new StandardSQLFunction("month", StandardBasicTypes.INTEGER));
		registerFunction("monthname", new StandardSQLFunction("monthname", StandardBasicTypes.STRING));
		registerFunction("months_between", new StandardSQLFunction("months_between"));
		registerFunction("GREATEST", new StandardSQLFunction("GREATEST", StandardBasicTypes.DATE));
		registerFunction("TO_DATETIME", new StandardSQLFunction("TO_DATETIME"));
		registerFunction("next_day", new StandardSQLFunction("next_day"));
		registerFunction("quarter", new StandardSQLFunction("quarter", StandardBasicTypes.INTEGER));
		registerFunction("second", new StandardSQLFunction("second", StandardBasicTypes.INTEGER));
		registerFunction("round", new StandardSQLFunction("round")); //
		registerFunction("timestampadd", new StandardSQLFunction("timestampadd", StandardBasicTypes.TIMESTAMP));
		registerFunction("timestampdiff", new StandardSQLFunction("timestampdiff", StandardBasicTypes.INTEGER));
		registerFunction("BIGDATEDIFF", new StandardSQLFunction("BIGDATEDIFF", StandardBasicTypes.BIG_INTEGER));
		registerFunction("sysdate", new StandardSQLFunction("sysdate", StandardBasicTypes.TIME));
		registerFunction("LEAST", new StandardSQLFunction("LEAST"));
		registerFunction("trunc", new StandardSQLFunction("trunc")); //
		registerFunction("week", new StandardSQLFunction("week", StandardBasicTypes.INTEGER));
		registerFunction("weekday", new StandardSQLFunction("weekday", StandardBasicTypes.INTEGER));
		registerFunction("weeks_between", new StandardSQLFunction("weeks_between", StandardBasicTypes.INTEGER));
		registerFunction("year", new StandardSQLFunction("year", StandardBasicTypes.INTEGER));
		registerFunction("years_between", new StandardSQLFunction("years_between", StandardBasicTypes.INTEGER));
		registerFunction("to_date", new StandardSQLFunction("to_date", StandardBasicTypes.TIMESTAMP));
		registerFunction("systimestamp", new NoArgSQLFunction("systimestamp", StandardBasicTypes.TIMESTAMP));

		registerFunction("coalesce", new StandardSQLFunction("coalesce")); //
		registerFunction("ifnull", new StandardSQLFunction("ifnull")); //
		registerFunction("isnull", new StandardSQLFunction("isnull")); //
		registerFunction("nullif", new StandardSQLFunction("nullif")); //
		registerFunction("nvl", new StandardSQLFunction("nvl")); //

		registerFunction("str", new StandardSQLFunction("to_char", StandardBasicTypes.STRING));
		registerFunction("decode", new StandardSQLFunction("decode")); //

		// registerFunction("cur_database", new
		// StandardSQLFunction("cur_database", Hibernate.STRING));
		registerFunction("page", new StandardSQLFunction("page", StandardBasicTypes.INTEGER));
		registerFunction("sessid", new StandardSQLFunction("sessid", StandardBasicTypes.LONG)); //
		registerFunction("uid", new StandardSQLFunction("uid", StandardBasicTypes.LONG)); //
		registerFunction("user", new StandardSQLFunction("user", StandardBasicTypes.STRING));

		registerFunction("vsize", new StandardSQLFunction("vsize", StandardBasicTypes.INTEGER)); //
		registerFunction("tabledef", new StandardSQLFunction("tabledef", StandardBasicTypes.STRING));

		getDefaultProperties().setProperty("hibernate.use_outer_join", "true");
		getDefaultProperties().setProperty("hibernate.jdbc.batch_size", "0");
	}

	/**
	 * Does this dialect support identity column key generation?
	 *
	 * @return True if IDENTITY columns are supported; false otherwise.
	 */
	@Override
	public boolean supportsIdentityColumns()
	{
		return true;
	}

	/**
	 * Does the dialect support some form of inserting and selecting
	 * the generated IDENTITY value all in the same statement.
	 *
	 * @return True if the dialect supports selecting the just
	 *         generated IDENTITY in the insert statement.
	 */
	@Override
	public boolean supportsInsertSelectIdentity()
	{
		return true;
	}

	/**
	 * Whether this dialect have an Identity clause added to the data type or a
	 * completely seperate identity data type
	 *
	 * @return boolean
	 */
	@Override
	public boolean hasDataTypeInIdentityColumn()
	{
		return true;
	}

	/**
	 * Get the select command to use to retrieve the last generated IDENTITY
	 * value.
	 *
	 * @return The appropriate select command
	 * @throws org.hibernate.MappingException
	 *             If IDENTITY generation is not supported.
	 */
	@Override
	public String getIdentitySelectString() throws MappingException
	{
		return "select scope_identity()";
	}

	/**
	 * Provided we {@link #supportsInsertSelectIdentity}, then attach the
	 * "select identity" clause to the insert statement.
	 * <p/>
	 * Note, if {@link #supportsInsertSelectIdentity} == false then
	 * the insert-string should be returned without modification.
	 *
	 * @param insertString
	 *            The insert command
	 * @return The insert command with any necessary identity select
	 *         clause attached.
	 */
	@Override
	public String appendIdentitySelectToInsert(String insertString)
	{
		return insertString + " select scope_identity()";
	}

	/**
	 * The syntax used during DDL to define a column as being an IDENTITY.
	 *
	 * @return The appropriate DDL fragment.
	 * @throws org.hibernate.MappingException
	 *             If IDENTITY generation is not supported.
	 */
	@Override
	protected String getIdentityColumnString() throws MappingException
	{
		return "identity not null";
	}

	/**
	 * // SEQUENCE support
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * 
	 * /**
	 * Does this dialect support sequences?
	 *
	 * @return True if sequences supported; false otherwise.
	 */
	public boolean supportsSequences()
	{
		return true;
	}

	/**
	 * Does this dialect support "pooled" sequences. Not aware of a better
	 * name for this. Essentially can we specify the initial and increment
	 * values?
	 *
	 * @return True if such "pooled" sequences are supported; false otherwise.
	 * @see #getCreateSequenceStrings(String, int, int)
	 * @see #getCreateSequenceString(String, int, int)
	 */
	public boolean supportsPooledSequences()
	{
		return true;
	}

	/**
	 * Generate the appropriate select statement to to retrieve the next value
	 * of a sequence.
	 * <p/>
	 * This should be a "stand alone" select statement.
	 *
	 * @param sequenceName
	 *            the name of the sequence
	 * @return String The "nextval" select string.
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String getSequenceNextValString(String sequenceName) throws MappingException
	{
		return "select " + getSelectSequenceNextValString(sequenceName);
	}

	/**
	 * Generate the select expression fragment that will retrieve the next
	 * value of a sequence as part of another (typically DML) statement.
	 * <p/>
	 * This differs from {@link #getSequenceNextValString(String)} in that this
	 * should return an expression usable within another statement.
	 *
	 * @param sequenceName
	 *            the name of the sequence
	 * @return The "nextval" fragment.
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String getSelectSequenceNextValString(String sequenceName) throws MappingException
	{
		return sequenceName + ".nextval";
	}

	/**
	 * The multiline script used to create a sequence.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence creation commands
	 * @throws MappingException
	 *             If sequences are not supported.
	 * @deprecated Use {@link #getCreateSequenceString(String, int, int)}
	 *             instead
	 */
	public String[] getCreateSequenceStrings(String sequenceName) throws MappingException
	{
		return new String[] { getCreateSequenceString(sequenceName) };
	}

	/**
	 * An optional multi-line form for databases which
	 * {@link #supportsPooledSequences()}.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @param initialValue
	 *            The initial value to apply to 'create sequence' statement
	 * @param incrementSize
	 *            The increment value to apply to 'create sequence' statement
	 * @return The sequence creation commands
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String[] getCreateSequenceStrings(String sequenceName, int initialValue, int incrementSize) throws MappingException
	{
		return new String[] { getCreateSequenceString(sequenceName, initialValue, incrementSize) };
	}

	/**
	 * Typically dialects which support sequences can create a sequence
	 * with a single command. This is convenience form of
	 * {@link #getCreateSequenceStrings} to help facilitate that.
	 * <p/>
	 * Dialects which support sequences and can create a sequence in a
	 * single command need *only* override this method. Dialects
	 * which support sequences but require multiple commands to create
	 * a sequence should instead override {@link #getCreateSequenceStrings}.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence creation command
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	protected String getCreateSequenceString(String sequenceName) throws MappingException
	{
		return "create sequence " + sequenceName;
	}

	/**
	 * Overloaded form of {@link #getCreateSequenceString(String)}, additionally
	 * taking the initial value and increment size to be applied to the sequence
	 * definition.
	 * </p>
	 * The default definition is to suffix
	 * {@link #getCreateSequenceString(String)}
	 * with the string:
	 * " start with {initialValue} increment by {incrementSize}" where
	 * {initialValue} and {incrementSize} are replacement placeholders.
	 * Generally
	 * dialects should only need to override this method if different key
	 * phrases
	 * are used to apply the allocation information.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @param initialValue
	 *            The initial value to apply to 'create sequence' statement
	 * @param incrementSize
	 *            The increment value to apply to 'create sequence' statement
	 * @return The sequence creation command
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	protected String getCreateSequenceString(String sequenceName, int initialValue, int incrementSize) throws MappingException
	{
		if (supportsPooledSequences())
		{
			return getCreateSequenceString(sequenceName) + " increment by " + incrementSize + " start with " + initialValue;
		}
		throw new MappingException(getClass().getName() + " does not support pooled sequences");
	}

	/**
	 * The multiline script used to drop a sequence.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence drop commands
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String[] getDropSequenceStrings(String sequenceName) throws MappingException
	{
		return new String[] { getDropSequenceString(sequenceName) };
	}

	/**
	 * Typically dialects which support sequences can drop a sequence
	 * with a single command. This is convenience form of
	 * {@link #getDropSequenceStrings} to help facilitate that.
	 * <p/>
	 * Dialects which support sequences and can drop a sequence in a
	 * single command need *only* override this method. Dialects
	 * which support sequences but require multiple commands to drop
	 * a sequence should instead override {@link #getDropSequenceStrings}.
	 *
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence drop commands
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	@Override
	protected String getDropSequenceString(String sequenceName) throws MappingException
	{
		return "drop sequence " + sequenceName;
	}

	/**
	 * Get the select command used retrieve the names of all sequences.
	 *
	 * @return The select command; or null if sequences are not supported.
	 * @see org.hibernate.tool.hbm2ddl.SchemaUpdate
	 */
	public String getQuerySequencesString()
	{
		return "select name from sysobjects where type$ = 'SCHOBJ' and subtype$ = 'SEQ';";
	}
	// GUID support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Get the command used to select a GUID from the underlying database.
	 * <p/>
	 * Optional operation.
	 *
	 * @return The appropriate command.
	 */
	@Override
	public String getSelectGUIDString()
	{
		return "select GUID()";
	}

	// limit/offset support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// DM5 don't support limit/offset

	/**
	 * Does this dialect support some form of limiting query results via a SQL
	 * clause?
	 *
	 * @return True if this dialect supports some form of LIMIT.
	 */
	@Override
	public boolean supportsLimit()
	{
		return true;
	}

	/**
	 * Does this dialect's LIMIT support (if any) additionally support
	 * specifying an offset?
	 *
	 * @return True if the dialect supports an offset within the limit support.
	 */
	@Override
	public boolean supportsLimitOffset()
	{
		return supportsLimit();
	}

	/**
	 * Does this dialect support bind variables (i.e., prepared statememnt
	 * parameters) for its limit/offset?
	 *
	 * @return True if bind variables can be used; false otherwise.
	 */
	@Override
	public boolean supportsVariableLimit()
	{
		return supportsLimit();
	}

	/**
	 * ANSI SQL defines the LIMIT clause to be in the form LIMIT offset, limit.
	 * Does this dialect require us to bind the parameters in reverse order?
	 *
	 * @return true if the correct order is limit, offset
	 */
	@Override
	public boolean bindLimitParametersInReverseOrder()
	{
		return true;
	}

	/**
	 * Does the <tt>LIMIT</tt> clause come at the start of the <tt>SELECT</tt>
	 * statement, rather than at the end?
	 *
	 * @return true if limit parameters should come before other parameters
	 */
	@Override
	public boolean bindLimitParametersFirst()
	{
		return false;
	}

	/**
	 * Does the <tt>LIMIT</tt> clause take a "maximum" row number instead
	 * of a total number of returned rows?
	 * <p/>
	 * This is easiest understood via an example. Consider you have a table
	 * with 20 rows, but you only want to retrieve rows number 11 through 20.
	 * Generally, a limit with offset would say that the offset = 11 and the
	 * limit = 10 (we only want 10 rows at a time); this is specifying the
	 * total number of returned rows. Some dialects require that we instead
	 * specify offset = 11 and limit = 20, where 20 is the "last" row we want
	 * relative to offset (i.e. total number of rows = 20 - 11 = 9)
	 * <p/>
	 * So essentially, is limit relative from offset? Or is limit absolute?
	 *
	 * @return True if limit is relative from offset; false otherwise.
	 */
	public boolean useMaxForLimit()
	{
		return true;
	}

	/**
	 * Generally, if there is no limit applied to a Hibernate query we do not
	 * apply any limits
	 * to the SQL query. This option forces that the limit be written to the SQL
	 * query.
	 *
	 * @return True to force limit into SQL query even if none specified in
	 *         Hibernate query; false otherwise.
	 */
	public boolean forceLimitUsage()
	{
		return false;
	}

	static int getAfterSelectInsertPoint(String sql)
	{
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex != selectIndex ? 6 : 15);
	}

	/**
	 * Given a limit and an offset, apply the limit clause to the query.
	 *
	 * @param query
	 *            The query to which to apply the limit.
	 * @param offset
	 *            The offset of the limit
	 * @param limit
	 *            The limit of the limit ;)
	 * @return The modified query statement with the limit applied.
	 */
	@Override
	public String getLimitString(String query, int offset, int limit)
	{
		return getLimitString(query, (offset > 0));
	}

	/**
	 * Apply s limit clause to the query.
	 * <p/>
	 * Typically dialects utilize
	 * {@link #supportsVariableLimit() variable} limit caluses when they support
	 * limits. Thus, when building the select command we do not actually need to
	 * know the limit or the offest since we will just be using placeholders.
	 * <p/>
	 * Here we do still pass along whether or not an offset was specified
	 * so that dialects not supporting offsets can generate proper exceptions.
	 * In general, dialects will override one or the other of this method and
	 * {@link #getLimitString(String, int, int)}.
	 *
	 * @param query
	 *            The query to which to apply the limit.
	 * @param hasOffset
	 *            Is the query requesting an offset?
	 * @return the modified SQL
	 */
	@Override
	protected String getLimitString(String query, boolean hasOffset)
	{
		query = query.trim();
		boolean isForUpdate = false;
		if (query.toLowerCase().endsWith(" for update"))
		{
			query = query.substring(0, query.length() - 11);
			isForUpdate = true;
		}

		StringBuilder pagingSelect = new StringBuilder(query.length() + 100);
		if (hasOffset)
		{
			pagingSelect.append(query).append(" limit ? offset ? ");
		}
		else
			pagingSelect.append(query).append(" limit ? ");

		if (isForUpdate)
		{
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	/**
	 * Hibernate APIs explicitly state that setFirstResult() should be a
	 * zero-based offset. Here we allow the
	 * Dialect a chance to convert that value based on what the underlying conf or
	 * driver will expect.
	 * <p/>
	 * NOTE: what gets passed into {@link #getLimitString(String,int,int)} is
	 * the zero-based offset. Dialects which
	 * do not {@link #supportsVariableLimit} should take care to perform any
	 * needed {@link #convertToFirstRowValue}
	 * calls prior to injecting the limit values into the SQL string.
	 *
	 * @param zeroBasedFirstResult
	 *            The user-supplied, zero-based first row offset.
	 *
	 * @return The corresponding conf/dialect specific offset.
	 *
	 * @see org.hibernate.Query#setFirstResult
	 * @see org.hibernate.Criteria#setFirstResult
	 */
	public int convertToFirstRowValue(int zeroBasedFirstResult)
	{
		return zeroBasedFirstResult;
	}

	// lock acquisition support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Informational metadata about whether this dialect is known to support
	 * specifying timeouts for requested lock acquisitions.
	 *
	 * @return True is this dialect supports specifying lock timeouts.
	 */
	public boolean supportsLockTimeouts()
	{
		return true;

	}

	/**
	 * If this dialect supports specifying lock timeouts, are those timeouts
	 * rendered into the <tt>SQL</tt> string as parameters. The implication
	 * is that Hibernate will need to bind the timeout value as a parameter
	 * in the {@link java.sql.PreparedStatement}. If true, the param position
	 * is always handled as the last parameter; if the dialect specifies the
	 * lock timeout elsewhere in the <tt>SQL</tt> statement then the timeout
	 * value should be directly rendered into the statement and this method
	 * should return false.
	 *
	 * @return True if the lock timeout is rendered into the <tt>SQL</tt>
	 *         string as a parameter; false otherwise.
	 */
	public boolean isLockTimeoutParameterized()
	{
		return false;
	}

	/**
	 * Get a strategy instance which knows how to acquire a database-level lock
	 * of the specified mode for this dialect.
	 *
	 * @param lockable
	 *            The persister for the entity to be locked.
	 * @param lockMode
	 *            The type of lock to be acquired.
	 * @return The appropriate locking strategy.
	 * @since 3.2
	 */
	public LockingStrategy getLockingStrategy(Lockable lockable, LockMode lockMode)
	{
		if (lockMode == LockMode.PESSIMISTIC_FORCE_INCREMENT)
		{
			return new PessimisticForceIncrementLockingStrategy(lockable, lockMode);
		}
		else if (lockMode == LockMode.PESSIMISTIC_WRITE)
		{
			return new PessimisticWriteSelectLockingStrategy(lockable, lockMode);
		}
		else if (lockMode == LockMode.PESSIMISTIC_READ)
		{
			return new PessimisticReadSelectLockingStrategy(lockable, lockMode);
		}
		else if (lockMode == LockMode.OPTIMISTIC)
		{
			return new OptimisticLockingStrategy(lockable, lockMode);
		}
		else if (lockMode == LockMode.OPTIMISTIC_FORCE_INCREMENT)
		{
			return new OptimisticForceIncrementLockingStrategy(lockable, lockMode);
		}
		return new SelectLockingStrategy(lockable, lockMode);
	}

	/**
	 * Given LockOptions (lockMode, timeout), determine the appropriate for
	 * update fragment to use.
	 *
	 * @param lockOptions
	 *            contains the lock mode to apply.
	 * @return The appropriate for update fragment.
	 */
	public String getForUpdateString(LockOptions lockOptions)
	{
		LockMode lockMode = lockOptions.getLockMode();
		if (lockMode == LockMode.PESSIMISTIC_READ)
		{
			return getReadLockString(lockOptions.getTimeOut());
		}
		else if (lockMode == LockMode.PESSIMISTIC_WRITE)
		{
			return getWriteLockString(lockOptions.getTimeOut());
		}
		else if (lockMode == LockMode.UPGRADE_NOWAIT)
		{
			return getForUpdateNowaitString();
		}
		else if (lockMode == LockMode.PESSIMISTIC_FORCE_INCREMENT)
		{
			return getForUpdateNowaitString();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Given a lock mode, determine the appropriate for update fragment to use.
	 *
	 * @param lockMode
	 *            The lock mode to apply.
	 * @return The appropriate for update fragment.
	 */
	public String getForUpdateString(LockMode lockMode)
	{
		if (lockMode == LockMode.PESSIMISTIC_READ)
		{
			return getReadLockString(LockOptions.WAIT_FOREVER);
		}
		else if (lockMode == LockMode.PESSIMISTIC_WRITE)
		{
			return getWriteLockString(LockOptions.WAIT_FOREVER);
		}
		else if (lockMode == LockMode.UPGRADE_NOWAIT)
		{
			return getForUpdateNowaitString();
		}
		else if (lockMode == LockMode.PESSIMISTIC_FORCE_INCREMENT)
		{
			return getForUpdateNowaitString();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Get the string to append to SELECT statements to acquire locks
	 * for this dialect.
	 *
	 * @return The appropriate <tt>FOR UPDATE</tt> clause string.
	 */
	public String getForUpdateString()
	{
		return "";
	}

	/**
	 * Get the string to append to SELECT statements to acquire WRITE locks
	 * for this dialect. Location of the of the returned string is treated
	 * the same as getForUpdateString.
	 *
	 * @param timeout
	 *            in milliseconds, -1 for indefinite wait and 0 for no wait.
	 * @return The appropriate <tt>LOCK</tt> clause string.
	 */
	public String getWriteLockString(int timeout)
	{
		return getForUpdateString();
	}

	/**
	 * Get the string to append to SELECT statements to acquire WRITE locks
	 * for this dialect. Location of the of the returned string is treated
	 * the same as getForUpdateString.
	 *
	 * @param timeout
	 *            in milliseconds, -1 for indefinite wait and 0 for no wait.
	 * @return The appropriate <tt>LOCK</tt> clause string.
	 */
	public String getReadLockString(int timeout)
	{
		return getForUpdateString();
	}

	/**
	 * Is <tt>FOR UPDATE OF</tt> syntax supported?
	 *
	 * @return True if the database supports <tt>FOR UPDATE OF</tt> syntax;
	 *         false otherwise.
	 */
	@Override
	public boolean forUpdateOfColumns()
	{
		// by default we report no support
		return false;
	}

	/**
	 * Does this dialect support <tt>FOR UPDATE</tt> in conjunction with outer
	 * joined rows?
	 *
	 * @return True if outer joined rows can be locked via <tt>FOR UPDATE</tt>.
	 */
	@Override
	public boolean supportsOuterJoinForUpdate()
	{
		return false;
	}

	/**
	 * Get the <tt>FOR UPDATE OF column_list</tt> fragment appropriate for
	 * this dialect given the aliases of the columns to be write locked.
	 *
	 * @param aliases
	 *            The columns to be write locked.
	 * @return The appropriate <tt>FOR UPDATE OF column_list</tt> clause
	 *         string.
	 */
	public String getForUpdateString(String aliases)
	{
		// by default we simply return the getForUpdateString() result since
		// the default is to say no support for "FOR UPDATE OF ..."
		return getForUpdateString();
	}

	/**
	 * Get the <tt>FOR UPDATE OF column_list</tt> fragment appropriate for this
	 * dialect given the aliases of the columns to be write locked.
	 *
	 * @param aliases
	 *            The columns to be write locked.
	 * @param lockOptions
	 * @return The appropriate <tt>FOR UPDATE OF column_list</tt> clause string.
	 */
	public String getForUpdateString(String aliases, LockOptions lockOptions)
	{
		// by default we simply return the getForUpdateString() result since
		// the default is to say no support for "FOR UPDATE OF ..."
		return getForUpdateString(lockOptions);
	}

	/**
	 * Retrieves the <tt>FOR UPDATE NOWAIT</tt> syntax specific to this dialect.
	 *
	 * @return The appropriate <tt>FOR UPDATE NOWAIT</tt> clause string.
	 */
	public String getForUpdateNowaitString()
	{
		// by default we report no support for NOWAIT lock semantics
		return getForUpdateString();
	}

	/**
	 * Get the <tt>FOR UPDATE OF column_list NOWAIT</tt> fragment appropriate
	 * for this dialect given the aliases of the columns to be write locked.
	 *
	 * @param aliases
	 *            The columns to be write locked.
	 * @return The appropriate <tt>FOR UPDATE colunm_list NOWAIT</tt> clause
	 *         string.
	 */
	public String getForUpdateNowaitString(String aliases)
	{
		return getForUpdateString(aliases);
	}

	/**
	 * Some dialects support an alternative means to <tt>SELECT FOR UPDATE</tt>,
	 * whereby a "lock hint" is appends to the table name in the from clause.
	 * <p/>
	 * contributed by <a href="http://sourceforge.net/users/heschulz">Helge
	 * Schulz</a>
	 *
	 * @param mode
	 *            The lock mode to apply
	 * @param tableName
	 *            The name of the table to which to apply the lock hint.
	 * @return The table with any required lock hints.
	 */
	@Override
	public String appendLockHint(LockMode mode, String tableName)
	{
		return tableName;
	}

	// table support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Command used to create a table.
	 *
	 * @return The command used to create a table.
	 */
	public String getCreateTableString()
	{
		return "create table ";
	}

	/**
	 * Slight variation on {@link #getCreateTableString}. Here, we have the
	 * command used to create a table when there is no primary key and
	 * duplicate rows are expected.
	 * <p/>
	 * Most databases do not care about the distinction; originally added for
	 * Teradata support which does care.
	 *
	 * @return The command used to create a multiset table.
	 */
	public String getCreateMultisetTableString()
	{
		return getCreateTableString();
	}

	/**
	 * Does this dialect support temporary tables?
	 *
	 * @return True if temp tables are supported; false otherwise.
	 */
	@Override
	public boolean supportsTemporaryTables()
	{
		return true;
	}

	/**
	 * Generate a temporary table name given the bas table.
	 *
	 * @param baseTableName
	 *            The table name from which to base the temp table
	 *            name.
	 * @return The generated temp table name.
	 */
	@Override
	public String generateTemporaryTableName(String baseTableName)
	{
		return "##" + baseTableName;
	}

	/**
	 * Command used to create a temporary table.
	 *
	 * @return The command used to create a temporary table.
	 */
	@Override
	public String getCreateTemporaryTableString()
	{
		return "create global temporary table";
	}

	/**
	 * Get any fragments needing to be postfixed to the command for
	 * temporary table creation.
	 *
	 * @return Any required postfix.
	 */
	public String getCreateTemporaryTablePostfix()
	{
		return "on commit delete rows";
	}

	/**
	 * Command used to drop a temporary table.
	 *
	 * @return The command used to drop a temporary table.
	 */
	public String getDropTemporaryTableString()
	{
		return "drop table ";
	}

	/**
	 * Does the dialect require that temporary table DDL statements occur in
	 * isolation from other statements? This would be the case if the creation
	 * would cause any current transaction to get committed implicitly.
	 * <p/>
	 * JDBC defines a standard way to query for this information via the
	 * {@link java.sql.DatabaseMetaData#dataDefinitionCausesTransactionCommit()}
	 * method. However, that does not distinguish between temporary table
	 * DDL and other forms of DDL; MySQL, for example, reports DDL causing a
	 * transaction commit via its driver, even though that is not the case for
	 * temporary table DDL.
	 * <p/>
	 * Possible return values and their meanings:
	 * <ul>
	 * <li>{@link Boolean#TRUE} - Unequivocally, perform the temporary table DDL
	 * in isolation.</li>
	 * <li>{@link Boolean#FALSE} - Unequivocally, do <b>not</b> perform the
	 * temporary table DDL in isolation.</li>
	 * <li><i>null</i> - defer to the JDBC driver response in regards to
	 * {@link java.sql.DatabaseMetaData#dataDefinitionCausesTransactionCommit()}
	 * </li>
	 * </ul>
	 * 
	 * @return see the result matrix above.
	 */
	@Override
	public Boolean performTemporaryTableDDLInIsolation()
	{
		return true;
	}

	/**
	 * Do we need to drop the temporary table after use?
	 *
	 * @return True if the table should be dropped.
	 */
	@Override
	public boolean dropTemporaryTableAfterUse()
	{
		return false;
	}

	// callable statement support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Registers an OUT parameter which will be returning a
	 * {@link java.sql.ResultSet}. How this is accomplished varies greatly
	 * from DB to DB, hence its inclusion (along with {@link #getResultSet})
	 * here.
	 *
	 * @param statement
	 *            The callable statement.
	 * @param position
	 *            The bind position at which to register the OUT param.
	 * @return The number of (contiguous) bind positions used.
	 * @throws SQLException
	 *             Indicates problems registering the OUT param.
	 */
	public int registerResultSetOutParameter(CallableStatement statement, int position) throws SQLException
	{
		throw new UnsupportedOperationException(getClass().getName() + " does not support resultsets via stored procedures");
	}

	/**
	 * Given a callable statement previously processed by
	 * {@link #registerResultSetOutParameter},
	 * extract the {@link java.sql.ResultSet} from the OUT parameter.
	 *
	 * @param statement
	 *            The callable statement.
	 * @return The extracted result set.
	 * @throws SQLException
	 *             Indicates problems extracting the result set.
	 */
	public ResultSet getResultSet(CallableStatement statement) throws SQLException
	{
		for (boolean flag = statement.execute(); !flag && statement.getUpdateCount() != -1; flag = statement.getMoreResults())
			;
		return statement.getResultSet();
	}

	// current timestamp support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Does this dialect support a way to retrieve the database's current
	 * timestamp value?
	 *
	 * @return True if the current timestamp can be retrieved; false otherwise.
	 */
	@Override
	public boolean supportsCurrentTimestampSelection()
	{
		return true;
	}

	/**
	 * Should the value returned by {@link #getCurrentTimestampSelectString}
	 * be treated as callable. Typically this indicates that JDBC escape
	 * syntax is being used...
	 *
	 * @return True if the {@link #getCurrentTimestampSelectString} return
	 *         is callable; false otherwise.
	 */
	public boolean isCurrentTimestampSelectStringCallable()
	{
		return false;
	}

	/**
	 * Retrieve the command used to retrieve the current timestamp from the
	 * database.
	 *
	 * @return The command.
	 */
	@Override
	public String getCurrentTimestampSelectString()
	{
		return "select current_timestamp()";
	}

	/**
	 * The name of the database-specific SQL function for retrieving the
	 * current timestamp.
	 *
	 * @return The function name.
	 */
	public String getCurrentTimestampSQLFunctionName()
	{
		// the standard SQL function name is current_timestamp...
		return "current_timestamp";
	}

	// SQLException support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Build an instance of the SQLExceptionConverter preferred by this dialect
	 * for
	 * converting SQLExceptions into Hibernate's JDBCException hierarchy. The
	 * default
	 * Dialect implementation simply returns a converter based on X/Open
	 * SQLState codes.
	 * <p/>
	 * It is strongly recommended that specific Dialect implementations override
	 * this
	 * method, since interpretation of a SQL error is much more accurate when
	 * based on
	 * the ErrorCode rather than the SQLState. Unfortunately, the ErrorCode is a
	 * vendor-
	 * specific approach.
	 *
	 * @return The Dialect's preferred SQLExceptionConverter.
	 */
	public SQLExceptionConverter buildSQLExceptionConverter()
	{
		// The default SQLExceptionConverter for all dialects is based on
		// SQLState
		// since SQLErrorCode is extremely vendor-specific. Specific Dialects
		// may override to return whatever is most appropriate for that vendor.
		return new SQLStateConverter(getViolatedConstraintNameExtracter());
	}

	private static final ViolatedConstraintNameExtracter EXTRACTER = new ViolatedConstraintNameExtracter()
	{
		public String extractConstraintName(SQLException sqle)
		{
			return null;
		}
	};

	public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter()
	{
		return EXTRACTER;
	}

	// union subclass support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Given a {@link java.sql.Types} type code, determine an appropriate
	 * null value to use in a select clause.
	 * <p/>
	 * One thing to consider here is that certain databases might
	 * require proper casting for the nulls here since the select here
	 * will be part of a UNION/UNION ALL.
	 *
	 * @param sqlType
	 *            The {@link java.sql.Types} type code.
	 * @return The appropriate select clause value fragment.
	 */
	public String getSelectClauseNullString(int sqlType)
	{
		return "null";
	}

	/**
	 * Does this dialect support UNION ALL, which is generally a faster
	 * variant of UNION?
	 *
	 * @return True if UNION ALL is supported; false otherwise.
	 */
	public boolean supportsUnionAll()
	{
		return true;
	}

	// miscellaneous support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * The name of the SQL function that transforms a string to
	 * lowercase
	 *
	 * @return The dialect-specific lowercase function.
	 */
	public String getLowercaseFunction()
	{
		return "lower";
	}

	/**
	 * Meant as a means for end users to affect the select strings being sent
	 * to the database and perhaps manipulate them in some fashion.
	 * <p/>
	 * The recommend approach is to instead use
	 * {@link org.hibernate.Interceptor#onPrepareStatement(String)}.
	 *
	 * @param select
	 *            The select command
	 * @return The mutated select command, or the same as was passed in.
	 */
	public String transformSelectString(String select)
	{
		return select;
	}

	/**
	 * The SQL literal value to which this database maps boolean values.
	 *
	 * @param bool
	 *            The boolean value
	 * @return The appropriate SQL literal.
	 */
	@Override
	public String toBooleanValueString(boolean bool)
	{
		return bool ? "1" : "0";
	}

	// identifier quoting support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * The character specific to this dialect used to begin a quoted identifier.
	 *
	 * @return The dialect's specific open quote character.
	 */
	@Override
	public char openQuote()
	{
		return '"';
	}

	/**
	 * The character specific to this dialect used to close a quoted identifier.
	 *
	 * @return The dialect's specific close quote character.
	 */
	@Override
	public char closeQuote()
	{
		return '"';
	}

	// DDL support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Does this dialect support the <tt>ALTER TABLE</tt> syntax?
	 *
	 * @return True if we support altering of tables; false otherwise.
	 */
	public boolean hasAlterTable()
	{
		return true;
	}

	/**
	 * Do we need to drop constraints before dropping tables in this dialect?
	 *
	 * @return True if constraints must be dropped prior to dropping
	 *         the table; false otherwise.
	 */
	public boolean dropConstraints()
	{
		return false;
	}

	/**
	 * Do we need to qualify index names with the schema name?
	 *
	 * @return boolean
	 */
	public boolean qualifyIndexName()
	{
		return true;
	}

	/**
	 * Does this dialect support the <tt>UNIQUE</tt> column syntax?
	 *
	 * @return boolean
	 */
	public boolean supportsUnique()
	{
		return true;
	}

	/**
	 * Does this dialect support adding Unique constraints via create and alter
	 * table ?
	 * 
	 * @return boolean
	 */
	public boolean supportsUniqueConstraintInCreateAlterTable()
	{
		return true;
	}

	/**
	 */
	public String getAddColumnString()
	{
		return " add column ";
	}

	public String getDropForeignKeyString()
	{
		return " drop constraint ";
	}

	public String getTableTypeString()
	{
		// grrr... for differentiation of mysql storage engines
		return "";
	}

	/**
	 * The syntax used to add a foreign key constraint to a table.
	 *
	 * @param constraintName
	 *            The FK constraint name.
	 * @param foreignKey
	 *            The names of the columns comprising the FK
	 * @param referencedTable
	 *            The table referenced by the FK
	 * @param primaryKey
	 *            The explicit columns in the referencedTable referenced
	 *            by this FK.
	 * @param referencesPrimaryKey
	 *            if false, constraint should be
	 *            explicit about which column names the constraint refers to
	 *
	 * @return the "add FK" fragment
	 */
	public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey)
	{
		StringBuffer res = new StringBuffer(30);

		res.append(" add constraint ").append(constraintName).append(" foreign key (").append(StringHelper.join(", ", foreignKey)).append(") references ").append(referencedTable);

		if (!referencesPrimaryKey)
		{
			res.append(" (").append(StringHelper.join(", ", primaryKey)).append(')');
		}

		return res.toString();
	}

	/**
	 * The syntax used to add a primary key constraint to a table.
	 *
	 * @param constraintName
	 *            The name of the PK constraint.
	 * @return The "add PK" fragment
	 */
	public String getAddPrimaryKeyConstraintString(String constraintName)
	{
		return " add constraint " + constraintName + " primary key ";
	}

	public boolean hasSelfReferentialForeignKeyBug()
	{
		return false;
	}

	/**
	 * The keyword used to specify a nullable column.
	 *
	 * @return String
	 */
	public String getNullColumnString()
	{
		return "";
	}

	public boolean supportsCommentOn()
	{
		return false;
	}

	public String getTableComment(String comment)
	{
		return "";
	}

	public String getColumnComment(String comment)
	{
		return "";
	}

	public boolean supportsIfExistsBeforeTableName()
	{
		return false;
	}

	@Override
	public boolean supportsIfExistsAfterTableName()
	{
		return false;
	}

	/**
	 * Does this dialect support column-level check constraints?
	 *
	 * @return True if column-level CHECK constraints are supported; false
	 *         otherwise.
	 */
	@Override
	public boolean supportsColumnCheck()
	{
		return true;
	}

	/**
	 * Does this dialect support table-level check constraints?
	 *
	 * @return True if table-level CHECK constraints are supported; false
	 *         otherwise.
	 */
	@Override
	public boolean supportsTableCheck()
	{
		return true;
	}

	@Override
	public boolean supportsCascadeDelete()
	{
		return true;
	}

	@Override
	public boolean supportsNotNullUnique()
	{
		return true;
	}

	/**
	 * Completely optional cascading drop clause
	 *
	 * @return String
	 */
	public String getCascadeConstraintsString()
	{
		return " cascade ";
	}

	/**
	 * Get the separator to use for defining cross joins when translating HQL
	 * queries.
	 * <p/>
	 * Typically this will be either [<tt> cross join </tt>] or [<tt>, </tt>]
	 * <p/>
	 * Note that the spaces are important!
	 *
	 * @return
	 */
	public String getCrossJoinSeparator()
	{
		return " cross join ";
	}

	public ColumnAliasExtractor getColumnAliasExtractor()
	{
		return ColumnAliasExtractor.COLUMN_LABEL_EXTRACTOR;
	}
	// Informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Does this dialect support empty IN lists?
	 * <p/>
	 * For example, is [where XYZ in ()] a supported construct?
	 *
	 * @return True if empty in lists are supported; false otherwise.
	 * @since 3.2
	 */
	@Override
	public boolean supportsEmptyInList()
	{
		return false;
	}

	/**
	 * Are string comparisons implicitly case insensitive.
	 * <p/>
	 * In other words,
	 * does [where 'XYZ' = 'xyz'] resolve to true?
	 *
	 * @return True if comparisons are case insensitive.
	 * @since 3.2
	 */
	@Override
	public boolean areStringComparisonsCaseInsensitive()
	{
		return true;
	}

	/**
	 * Is this dialect known to support what ANSI-SQL terms "row value
	 * constructor" syntax; sometimes called tuple syntax.
	 * <p/>
	 * Basically, does it support syntax like
	 * "... where (FIRST_NAME, LAST_NAME) = ('Steve', 'Ebersole') ...".
	 *
	 * @return True if this SQL dialect is known to support "row value
	 *         constructor" syntax; false otherwise.
	 * @since 3.2
	 */
	public boolean supportsRowValueConstructorSyntax()
	{
		// return false here, as most databases do not properly support this
		// construct...
		return false;
	}

	/**
	 * If the dialect supports {@link #supportsRowValueConstructorSyntax() row
	 * values},
	 * does it offer such support in IN lists as well?
	 * <p/>
	 * For example,
	 * "... where (FIRST_NAME, LAST_NAME) IN ( (?, ?), (?, ?) ) ..."
	 *
	 * @return True if this SQL dialect is known to support "row value
	 *         constructor" syntax in the IN list; false otherwise.
	 * @since 3.2
	 */
	public boolean supportsRowValueConstructorSyntaxInInList()
	{
		return false;
	}

	/**
	 * Should LOBs (both BLOB and CLOB) be bound using stream operations (i.e.
	 * {@link java.sql.PreparedStatement#setBinaryStream}).
	 *
	 * @return True if BLOBs and CLOBs should be bound using stream operations.
	 * @since 3.2
	 */
	public boolean useInputStreamToInsertBlob()
	{
		return true;
	}

	/**
	 * Does this dialect support parameters within the <tt>SELECT</tt> clause of
	 * <tt>INSERT ... SELECT ...</tt> statements?
	 *
	 * @return True if this is supported; false otherwise.
	 * @since 3.2
	 */
	public boolean supportsParametersInInsertSelect()
	{
		return false;
	}

	/**
	 * Does this dialect require that references to result variables
	 * (i.e, select expresssion aliases) in an ORDER BY clause be
	 * replaced by column positions (1-origin) as defined
	 * by the select clause?
	 * 
	 * @return true if result variable references in the ORDER BY
	 *         clause should be replaced by column positions;
	 *         false otherwise.
	 */
	public boolean replaceResultVariableInOrderByClauseWithPosition()
	{
		return false;
	}

	/**
	 * Does this dialect require that parameters appearing in the
	 * <tt>SELECT</tt> clause be wrapped in <tt>cast()</tt>
	 * calls to tell the conf parser the expected type.
	 *
	 * @return True if select clause parameter must be cast()ed
	 * @since 3.2
	 */
	public boolean requiresCastingOfParametersInSelectClause()
	{
		return false;
	}

	/**
	 * Does this dialect support asking the result set its positioning
	 * information on forward only cursors. Specifically, in the case of
	 * scrolling fetches, Hibernate needs to use
	 * {@link java.sql.ResultSet#isAfterLast} and
	 * {@link java.sql.ResultSet#isBeforeFirst}. Certain drivers do not
	 * allow access to these methods for forward only cursors.
	 * <p/>
	 * NOTE : this is highly driver dependent!
	 *
	 * @return True if methods like {@link java.sql.ResultSet#isAfterLast} and
	 *         {@link java.sql.ResultSet#isBeforeFirst} are supported for
	 *         forward
	 *         only cursors; false otherwise.
	 * @since 3.2
	 */
	@Override
	public boolean supportsResultSetPositionQueryMethodsOnForwardOnlyCursor()
	{
		return true;
	}

	/**
	 * Does this dialect support definition of cascade delete constraints which
	 * can cause circular chains?
	 *
	 * @return True if circular cascade delete constraints are supported; false
	 *         otherwise.
	 * @since 3.2
	 */
	@Override
	public boolean supportsCircularCascadeDeleteConstraints()
	{
		return false;
	}

	/**
	 * Are subselects supported as the left-hand-side (LHS) of
	 * IN-predicates.
	 * <p/>
	 * In other words, is syntax like "... <subquery> IN (1, 2, 3) ..."
	 * supported?
	 *
	 * @return True if subselects can appear as the LHS of an in-predicate;
	 *         false otherwise.
	 * @since 3.2
	 */
	public boolean supportsSubselectAsInPredicateLHS()
	{
		return true;
	}

	/**
	 * Expected LOB usage pattern is such that I can perform an insert
	 * via prepared statement with a parameter binding for a LOB value
	 * without crazy casting to JDBC driver implementation-specific classes...
	 * <p/>
	 * Part of the trickiness here is the fact that this is largely
	 * driver dependent. For example, Oracle (which is notoriously bad with
	 * LOB support in their drivers historically) actually does a pretty good
	 * job with LOB support as of the 10.2.x versions of their drivers...
	 *
	 * @return True if normal LOB usage patterns can be used with this driver;
	 *         false if driver-specific hookiness needs to be applied.
	 * @since 3.2
	 */
	public boolean supportsExpectedLobUsagePattern()
	{
		return true;
	}

	/**
	 * Does the dialect support propagating changes to LOB
	 * values back to the database? Talking about mutating the
	 * internal value of the locator as opposed to supplying a new
	 * locator instance...
	 * <p/>
	 * For BLOBs, the internal value might be changed by:
	 * {@link java.sql.Blob#setBinaryStream},
	 * {@link java.sql.Blob#setBytes(long, byte[])},
	 * {@link java.sql.Blob#setBytes(long, byte[], int, int)},
	 * or {@link java.sql.Blob#truncate(long)}.
	 * <p/>
	 * For CLOBs, the internal value might be changed by:
	 * {@link java.sql.Clob#setAsciiStream(long)},
	 * {@link java.sql.Clob#setCharacterStream(long)},
	 * {@link java.sql.Clob#setString(long, String)},
	 * {@link java.sql.Clob#setString(long, String, int, int)},
	 * or {@link java.sql.Clob#truncate(long)}.
	 * <p/>
	 * NOTE : I do not know the correct answer currently for
	 * databases which (1) are not part of the cruise control process
	 * or (2) do not {@link #supportsExpectedLobUsagePattern}.
	 *
	 * @return True if the changes are propagated back to the
	 *         database; false otherwise.
	 * @since 3.2
	 */
	@Override
	public boolean supportsLobValueChangePropogation()
	{
		return false;
	}

	/**
	 * Is it supported to materialize a LOB locator outside the transaction in
	 * which it was created?
	 * <p/>
	 * Again, part of the trickiness here is the fact that this is largely
	 * driver dependent.
	 * <p/>
	 * NOTE: all database I have tested which
	 * {@link #supportsExpectedLobUsagePattern()}
	 * also support the ability to materialize a LOB outside the owning
	 * transaction...
	 *
	 * @return True if unbounded materialization is supported; false otherwise.
	 * @since 3.2
	 */
	public boolean supportsUnboundedLobLocatorMaterialization()
	{
		return false;
	}

	/**
	 * Does this dialect support referencing the table being mutated in
	 * a subquery. The "table being mutated" is the table referenced in
	 * an UPDATE or a DELETE query. And so can that table then be
	 * referenced in a subquery of said UPDATE/DELETE query.
	 * <p/>
	 * For example, would the following two syntaxes be supported:
	 * <ul>
	 * <li>delete from TABLE_A where ID not in ( select ID from TABLE_A )</li>
	 * <li>update TABLE_A set NON_ID = 'something' where ID in ( select ID from
	 * TABLE_A)</li>
	 * </ul>
	 *
	 * @return True if this dialect allows references the mutating table from
	 *         a subquery.
	 */
	public boolean supportsSubqueryOnMutatingTable()
	{
		return true;
	}

	/**
	 * Does the dialect support an exists statement in the select clause?
	 *
	 * @return True if exists checks are allowed in the select clause; false
	 *         otherwise.
	 */
	public boolean supportsExistsInSelect()
	{
		return false;
	}

	/**
	 * For the underlying database, is READ_COMMITTED isolation implemented by
	 * forcing readers to wait for write locks to be released?
	 *
	 * @return True if writers block readers to achieve READ_COMMITTED; false
	 *         otherwise.
	 */
	@Override
	public boolean doesReadCommittedCauseWritersToBlockReaders()
	{
		return false;
	}

	/**
	 * For the underlying database, is REPEATABLE_READ isolation implemented by
	 * forcing writers to wait for read locks to be released?
	 *
	 * @return True if readers block writers to achieve REPEATABLE_READ; false
	 *         otherwise.
	 */
	@Override
	public boolean doesRepeatableReadCauseReadersToBlockWriters()
	{
		return false;
	}

	/**
	 * Does this dialect support using a JDBC bind parameter as an argument
	 * to a function or procedure call?
	 *
	 * @return True if the database supports accepting bind params as args;
	 *         false otherwise.
	 */
	public boolean supportsBindAsCallableArgument()
	{
		return true;
	}

	/**
	 * Does this dialect support `count(a,b)`?
	 *
	 * @return True if the database supports counting tuples; false otherwise.
	 */
	public boolean supportsTupleCounts()
	{
		return false;
	}

	/**
	 * Does this dialect support `count(distinct a,b)`?
	 *
	 * @return True if the database supports counting disintct tuples; false
	 *         otherwise.
	 */
	public boolean supportsTupleDistinctCounts()
	{
		// oddly most database in fact seem to, so true is the default.
		return false;
	}
}
