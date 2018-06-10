package com.cliniccontrol.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Oscar Guerrero
 * @fecha 06-09-2018
 * @Hora 07:34:07 PM
 *
 */

@Entity
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String paramEmpresa;
    private String usuario;
    private String clave;
    private Integer idRol;

    public Usuarios() {
    }

    public Usuarios(Long id, String paramEmpresa, String usuario, String clave, Integer idRol) {
        this.id = id;
        this.paramEmpresa = paramEmpresa;
        this.usuario = usuario;
        this.clave = clave;
        this.idRol = idRol;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamEmpresa() {
        return paramEmpresa;
    }

    public void setParamEmpresa(String paramEmpresa) {
        this.paramEmpresa = paramEmpresa;
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

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cliniccontrol.entities.Usuarios[ id=" + id + " ]";
    }

}
