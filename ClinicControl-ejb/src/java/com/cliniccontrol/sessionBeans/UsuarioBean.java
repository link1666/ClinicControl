/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliniccontrol.sessionBeans;

import com.cliniccontrol.DAO.UsuarioDao;
import com.cliniccontrol.entities.Usuarios;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import orclconn.fuentes.OrclConn;

/**
 *
 * @author Oscar Guerrero
 */
@Stateless
@LocalBean
public class UsuarioBean extends BeanHelper {

    public Usuarios findUsuario(String usuario, String clave, String paramName) throws Exception {
        OrclConn conn = new OrclConn();
        Usuarios usuarioEncontrado = new Usuarios();
        UsuarioDao dao = new UsuarioDao();
        try {
            conn.setConnection(getJNDIDs(paramName));
            usuarioEncontrado = dao.find(usuario, clave, conn);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            conn.closeConn();
        }
        return usuarioEncontrado;
    }

}
