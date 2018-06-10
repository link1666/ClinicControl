package com.cliniccontrol.DAO;

import com.cliniccontrol.entities.Usuarios;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import orclconn.fuentes.OrclConn;

/**
 *
 * @author Oscar Guerrero
 * @fecha 06-09-2018
 * @Hora 04:17:47 PM
 *
 */
public class UsuarioDao extends DaoHelper {

    @Override
    public void create(Object obj, OrclConn orclConn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Object obj, OrclConn orclConn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Object obj, OrclConn orclConn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(Object obj, OrclConn orclConn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuarios find(String usuario, String clave, OrclConn orclConn) throws Exception {
        List<Usuarios> lista;
        String query;
        ResultSet rs;
        try {
            query = "SELECT COUNT(*) EXISTE FROM CLINICCONTROL.USUARIOS WHERE USUARIO = ?";

            rs = orclConn.execQueryPS(query, new Object[]{usuario});

            if (rs.next()) {
                Integer i = rs.getInt("EXISTE");
                if (i > 0) {
                    query = "SELECT USUARIO, PARAM_EMPRESA, ID_ROL\n"
                            + "FROM CLINICCONTROL.USUARIOS A JOIN CLINICCONTROL.EMPRESAS B ON A.ID_EMPRESA = B.ID_EMPRESA\n"
                            + "WHERE USUARIO = ? AND CLAVE = ?";

                    rs = orclConn.execQueryPS(query, new Object[]{usuario, clave});

                    if (rs != null) {
                        if ((lista = parseResultSettoClass(rs)).size() > 0) {
                            return lista.get(0);
                        } else {
                            throw new Exception("La clave ingresada no correspone al usuario");
                        }
                    } else {
                        return null;
                    }
                } else {
                    throw new Exception("El usuario no existe");
                }
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        return null;
    }

    @Override
    public List<Usuarios> parseResultSettoClass(ResultSet rs) throws Exception {
        List<Usuarios> lista = new ArrayList<>();
        while (rs.next()) {
            Usuarios obj = new Usuarios();
            obj.setIdRol(rs.getInt("ID_ROL"));
            obj.setParamEmpresa(rs.getString("PARAM_EMPRESA"));
            obj.setUsuario(rs.getString("USUARIO"));
            lista.add(obj);
        }
        return lista;
    }
}
