package dao;

import java.sql.Connection;

public interface DAO {

	Connection connect = null;

	void insert();

	void delete();

	/**
	 * 
	 * @param colName
	 * @param value
	 */
	void selectByColName(String colName, String value);

	void getAll();

	/**
	 * 
	 * @param id
	 */
	void selectById(int id);

}