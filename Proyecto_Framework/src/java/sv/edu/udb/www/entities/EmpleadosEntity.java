/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "empleados")
@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})
@NamedQueries({
    @NamedQuery(name = "EmpleadosEntity.findAll", query = "SELECT e FROM EmpleadosEntity e")
    , @NamedQuery(name = "EmpleadosEntity.findByCodigoEmpleado", query = "SELECT e FROM EmpleadosEntity e WHERE e.codigoEmpleado = :codigoEmpleado")
    , @NamedQuery(name = "EmpleadosEntity.findByNombreEmpleado", query = "SELECT e FROM EmpleadosEntity e WHERE e.nombreEmpleado = :nombreEmpleado")
    , @NamedQuery(name = "EmpleadosEntity.findByUsuarioEmpleado", query = "SELECT e FROM EmpleadosEntity e WHERE e.usuarioEmpleado = :usuarioEmpleado")
    , @NamedQuery(name = "EmpleadosEntity.findByContrasena", query = "SELECT e FROM EmpleadosEntity e WHERE e.contrasena = :contrasena")
    , @NamedQuery(name = "EmpleadosEntity.findByCorreo", query = "SELECT e FROM EmpleadosEntity e WHERE e.correo = :correo")})
public class EmpleadosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer codigoEmpleado;
    @Basic(optional = false)
    private String nombreEmpleado;
    @Basic(optional = false)
    private String usuarioEmpleado;
    @Basic(optional = false)
    private String contrasena;
    private String correo;
    @OneToMany(mappedBy = "idUser")
    private List<PlaylistEntity> playlistEntityList;
    @OneToMany(mappedBy = "idUser")
    private List<VentasEntity> ventasEntityList;
    @JoinColumn(name = "codigoTipoEmpleado", referencedColumnName = "codigoTipoEmpleado")
    @ManyToOne(optional = false)
    private TipoempleadosEntity codigoTipoEmpleado;

    public EmpleadosEntity() {
    }

    public EmpleadosEntity(Integer codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public EmpleadosEntity(Integer codigoEmpleado, String nombreEmpleado, String usuarioEmpleado, String contrasena) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.usuarioEmpleado = usuarioEmpleado;
        this.contrasena = contrasena;
    }

    public Integer getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(Integer codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getUsuarioEmpleado() {
        return usuarioEmpleado;
    }

    public void setUsuarioEmpleado(String usuarioEmpleado) {
        this.usuarioEmpleado = usuarioEmpleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<PlaylistEntity> getPlaylistEntityList() {
        return playlistEntityList;
    }

    public void setPlaylistEntityList(List<PlaylistEntity> playlistEntityList) {
        this.playlistEntityList = playlistEntityList;
    }

    public List<VentasEntity> getVentasEntityList() {
        return ventasEntityList;
    }

    public void setVentasEntityList(List<VentasEntity> ventasEntityList) {
        this.ventasEntityList = ventasEntityList;
    }

    public TipoempleadosEntity getCodigoTipoEmpleado() {
        return codigoTipoEmpleado;
    }

    public void setCodigoTipoEmpleado(TipoempleadosEntity codigoTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEmpleado != null ? codigoEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadosEntity)) {
            return false;
        }
        EmpleadosEntity other = (EmpleadosEntity) object;
        if ((this.codigoEmpleado == null && other.codigoEmpleado != null) || (this.codigoEmpleado != null && !this.codigoEmpleado.equals(other.codigoEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EmpleadosEntity[ codigoEmpleado=" + codigoEmpleado + " ]";
    }

    public void setCodigoEmpleado(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
