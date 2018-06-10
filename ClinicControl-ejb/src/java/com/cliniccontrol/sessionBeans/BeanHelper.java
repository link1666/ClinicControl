/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliniccontrol.sessionBeans;

import com.cliniccontrol.utils.params.ParamHelper;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author zeped
 */
public class BeanHelper {
    private String jndiDs;
    
    protected Connection getJNDIDs(String paramName){
        return getConn(ParamHelper.getParametro(paramName));
    }
    
    private Connection getConn(String jndiName) {
        
        Connection conn = null;
        try {
            Context c = new InitialContext();
            
            DataSource datasource = (DataSource) c.lookup(jndiName);
            if (datasource != null) {
                conn = datasource.getConnection();
                conn.setAutoCommit(false);
            } else {
                System.out.println("Failed to lookup datasource.");
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }

        return conn;
    }
}
