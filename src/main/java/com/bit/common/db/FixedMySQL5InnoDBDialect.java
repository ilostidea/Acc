/**
 * 20160610
 */
package com.bit.common.db;

import java.sql.Types;

import org.hibernate.dialect.InnoDBStorageEngine;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQLStorageEngine;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author Administrator
 * 将Char 取值转换为String
 */
public class FixedMySQL5InnoDBDialect extends MySQL5Dialect {

	protected void registerVarcharTypes() {  
        super.registerVarcharTypes();
        registerColumnType(Types.CHAR, 255, "char($l)");
        //registerHibernateType(Types.LONGVARCHAR, StandardBasicTypes.TEXT.getName());
        registerHibernateType( Types.CHAR, StandardBasicTypes.STRING.getName() );  
    }
	
	/**
	 * Hibernate 5.2.10.Final 中的方法，5.1.0.Final中没有，当把版本降低的时候，需要把该方法删掉
	 * 本平台使用Hibernate 5.2.10.Final生成反向工程时报错，需降低版本。
	 */
	protected MySQLStorageEngine getDefaultMySQLStorageEngine() {
		return InnoDBStorageEngine.INSTANCE;
	}
}
