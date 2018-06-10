package com.cliniccontrol.DAO;

import java.sql.ResultSet;
import orclconn.fuentes.OrclConn;

/**
 *
 * @author Oscar Guerrero
 * @fecha 06-09-2018
 * @Hora 03:58:27 PM
 *
 */
public abstract class DaoHelper {

    public abstract void create(Object obj, OrclConn orclConn)
            throws Exception;

    public abstract void remove(Object obj, OrclConn orclConn)
            throws Exception;

    public abstract void edit(Object obj, OrclConn orclConn)
            throws Exception;

    public abstract Object find(Object obj, OrclConn orclConn)
            throws Exception;

    public abstract Object parseResultSettoClass(ResultSet rs)
            throws Exception;
}
