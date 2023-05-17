package abstractDAO;


import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DataAccess;

/**
 * @Author: Paul Petrut
 * @Since: 13 May 2023
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public Connection connect() {
        Connection connection=null;
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/proiectpt?sslMode=VERIFY_IDENTITY",
                    "dz02h8gj2rwrr5hjw1xq",
                    "pscale_pw_qXt8GLS5u7q3Tv7M14dhbFc5sKQnvPDMZihZbjNdkYm");


            return connection;
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException connection: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String createSelectQuery(String field) {
        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE " + field + " =?";
    }

    public List<T> findAll() {
        // TODO:


        return null;
    }
    /**
     * gets id as param, and it makes an SQL quarry to search for it abd return it
     * @return an object of the calling class with the data from the table
     */

    public T findById(int id) {
        Connection connection = connect();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            System.out.println(connection);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            DataAccess.close(resultSet);
            DataAccess.close(statement);
            DataAccess.close(connection);
        }
        return null;
    }
    /**
     * create an object of the type of the calling class and return it
     * @param resultSet result from a database SQL statement
     *
     * @return a list with
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * gets t and write each field beside id and add it to the database
     * @param t any of the 3 classes Product,Client,Purchase
     * @return t but it gives t with the correct id;
     */
    public boolean insert(T t) {
        // Check if t is null
        if (t == null) {
            System.out.print("T is null");
            return false;
        }
        DataAccess dataAccess= new DataAccess();
        Connection connection = dataAccess.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            statement = connection.prepareStatement(query);
            Field[] fields = type.getDeclaredFields();
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i, fields[i].get(t));
            }
            // Execute the query and return the result
            statement.executeUpdate();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            DataAccess.close(resultSet);
            DataAccess.close(statement);
        }

        return false;
    }

    // Abstract methods to be implemented by subclasses
    protected String createInsertQuery() {
        return null;
    }

    public T update(T t) {
        // TODO:
        return t;
    }
}
