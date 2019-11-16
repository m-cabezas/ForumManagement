package dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface DAO<Table> {

	Connection connect = null;

	/**
	 *
	 * @param t
	 */
	void insert(Table t);

	/**
	 *
	 * @param t
	 */
	void delete(Table t);

	/**
	 * 
	 * @param colName
	 * @param value
	 */
	ArrayList<Table> selectByColName(String colName, String value);

	ArrayList<Table> getAll();

	/**
	 * 
	 * @param id
	 */
	Table selectById(int id);

}