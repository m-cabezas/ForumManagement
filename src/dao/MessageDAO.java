package dao;

import dao.DAO.*;
import model.Message;

import java.util.ArrayList;

public class MessageDAO implements DAO<Message> {
    @Override
    public void insert(Message message) {

    }

    @Override
    public void delete(Message message) {

    }

    /**
     *
     * @param colName
     * @param value
     */
    @Override
    public void selectByColName(String colName, String value) {

    }

    @Override
    public ArrayList<Message> getAll() {
        return null;
    }

    @Override
    public void selectById(int id) {

    }
}