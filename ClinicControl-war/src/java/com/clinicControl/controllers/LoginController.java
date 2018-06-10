package com.clinicControl.controllers;

import com.cliniccontrol.entities.Usuarios;
import com.cliniccontrol.sessionBeans.UsuarioBean;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 *
 * @author Oscar Guerrero
 * @fecha 06-09-2018
 * @Hora 03:44:51 PM
 *
 */
@Named
@RequestScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioBean usuarioBean;

    private String usuario;
    private String clave;
    private Usuarios user;
    private String paramName;

    @PostConstruct
    public void init() {
        paramName = "CLINICCONTROL";
    }

    public String login() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (usuario != null && !usuario.isEmpty() && clave != null && !clave.isEmpty()) {

            try {
                user = usuarioBean.findUsuario(usuario, encriptar(clave), paramName);
                fc.getExternalContext().getSessionMap().put("user", user.getUsuario());
                fc.getExternalContext().getSessionMap().put("paramName", user.getParamEmpresa());
                fc.getExternalContext().getSessionMap().put("idRol", user.getIdRol());
                return "index?faces-redirect=true";
            } catch (Exception e) {
                e.printStackTrace();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getCause().getMessage().replace("java.lang.Exception:", ""), null));
            }
        } else {
            String mensaje;
            if (usuario != null || !usuario.isEmpty()) {
                mensaje = "Por favor digite el usuario";
            } else {
                mensaje = "Por favor digite la clave";
            }

            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null));
        }

        return "";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    private String encriptar(String clave) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
        md.update(clave.getBytes());
        byte[] digest = md.digest();
        byte[] encoded = Base64.encodeBase64(digest);
        return new String(encoded);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
