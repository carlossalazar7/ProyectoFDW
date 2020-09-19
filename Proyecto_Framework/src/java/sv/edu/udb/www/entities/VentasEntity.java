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
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "VentasEntity.findAll", query = "SELECT v FROM VentasEntity v")
    , @NamedQuery(name = "VentasEntity.findByIdVenta", query = "SELECT v FROM VentasEntity v WHERE v.idVenta = :idVenta")
    , @NamedQuery(name = "VentasEntity.findByIdMusic", query = "SELECT v FROM VentasEntity v WHERE v.idMusic = :idMusic")
    , @NamedQuery(name = "VentasEntity.findByIdUser", query = "SELECT v FROM VentasEntity v WHERE v.idUser = :idUser")
    , @NamedQuery(name = "VentasEntity.findByIdPaquete", query = "SELECT v FROM VentasEntity v WHERE v.idPaquete = :idPaquete")
    , @NamedQuery(name = "VentasEntity.findByFechaVenta", query = "SELECT v FROM VentasEntity v WHERE v.fechaVenta = :fechaVenta")})
public class VentasEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idVenta;
    @Basic(optional = false)
    private Integer idMusic;
    @Basic(optional = false)
    private Integer idUser;
    @Basic(optional = false)
    private Integer idPaquete;
    @Basic(optional = false)
    private String fechaVenta;

    public VentasEntity() {
    }

    public VentasEntity(Integer idVenta, Integer idMusic, Integer idUser, Integer idPaquete, String fechaVenta) {
        this.idVenta = idVenta;
        this.idMusic = idMusic;
        this.idUser = idUser;
        this.idPaquete = idPaquete;
        this.fechaVenta = fechaVenta;

    }

    public VentasEntity(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(Integer idMusic) {
        this.idMusic = idMusic;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasEntity)) {
            return false;
        }
        VentasEntity other = (VentasEntity) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.VentasEntity[ idVenta=" + idVenta + " ]";
    }

}
