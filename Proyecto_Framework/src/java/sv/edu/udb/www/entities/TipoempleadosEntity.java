/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "tipoempleados")
@NamedQueries({
    @NamedQuery(name = "TipoempleadosEntity.findAll", query = "SELECT t FROM TipoempleadosEntity t")
    , @NamedQuery(name = "TipoempleadosEntity.findByCodigoTipoEmpleado", query = "SELECT t FROM TipoempleadosEntity t WHERE t.codigoTipoEmpleado = :codigoTipoEmpleado")
    , @NamedQuery(name = "TipoempleadosEntity.findByNombreTipoEmpleado", query = "SELECT t FROM TipoempleadosEntity t WHERE t.nombreTipoEmpleado = :nombreTipoEmpleado")})
public class TipoempleadosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer codigoTipoEmpleado;
    @Basic(optional = false)
    private String nombreTipoEmpleado;

    public TipoempleadosEntity() {
    }

    public TipoempleadosEntity(Integer codigoTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
    }

    public TipoempleadosEntity(Integer codigoTipoEmpleado, String nombreTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
        this.nombreTipoEmpleado = nombreTipoEmpleado;
    }

    public Integer getCodigoTipoEmpleado() {
        return codigoTipoEmpleado;
    }

    public void setCodigoTipoEmpleado(Integer codigoTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
    }

    public String getNombreTipoEmpleado() {
        return nombreTipoEmpleado;
    }

    public void setNombreTipoEmpleado(String nombreTipoEmpleado) {
        this.nombreTipoEmpleado = nombreTipoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoEmpleado != null ? codigoTipoEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoempleadosEntity)) {
            return false;
        }
        TipoempleadosEntity other = (TipoempleadosEntity) object;
        if ((this.codigoTipoEmpleado == null && other.codigoTipoEmpleado != null) || (this.codigoTipoEmpleado != null && !this.codigoTipoEmpleado.equals(other.codigoTipoEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.TipoempleadosEntity[ codigoTipoEmpleado=" + codigoTipoEmpleado + " ]";
    }

}
