/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "paquetes")
@NamedQueries({
    @NamedQuery(name = "PaquetesEntity.findAll", query = "SELECT p FROM PaquetesEntity p")
    , @NamedQuery(name = "PaquetesEntity.findByIdPaquete", query = "SELECT p FROM PaquetesEntity p WHERE p.idPaquete = :idPaquete")
    , @NamedQuery(name = "PaquetesEntity.findByNombrePaquete", query = "SELECT p FROM PaquetesEntity p WHERE p.nombrePaquete = :nombrePaquete")
    , @NamedQuery(name = "PaquetesEntity.findByPrecio", query = "SELECT p FROM PaquetesEntity p WHERE p.precio = :precio")
    , @NamedQuery(name = "PaquetesEntity.findByDescripcion", query = "SELECT p FROM PaquetesEntity p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "PaquetesEntity.findByFechaPublicacion", query = "SELECT p FROM PaquetesEntity p WHERE p.fechaPublicacion = :fechaPublicacion")})
public class PaquetesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idPaquete;
    private String nombrePaquete;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float precio;
    private String descripcion;
    private String fechaPublicacion;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    private GenerosEntity id;
    @OneToMany(mappedBy = "idPaquete")
    private List<VentasEntity> ventasEntityList;

    public PaquetesEntity() {
    }

    public PaquetesEntity(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public GenerosEntity getId() {
        return id;
    }

    public void setId(GenerosEntity id) {
        this.id = id;
    }

    public List<VentasEntity> getVentasEntityList() {
        return ventasEntityList;
    }

    public void setVentasEntityList(List<VentasEntity> ventasEntityList) {
        this.ventasEntityList = ventasEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaquete != null ? idPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaquetesEntity)) {
            return false;
        }
        PaquetesEntity other = (PaquetesEntity) object;
        if ((this.idPaquete == null && other.idPaquete != null) || (this.idPaquete != null && !this.idPaquete.equals(other.idPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.PaquetesEntity[ idPaquete=" + idPaquete + " ]";
    }
    
}
