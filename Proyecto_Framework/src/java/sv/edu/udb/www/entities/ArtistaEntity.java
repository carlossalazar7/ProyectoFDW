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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "artista")
@NamedQueries({
    @NamedQuery(name = "ArtistaEntity.findAll", query = "SELECT a FROM ArtistaEntity a")
    , @NamedQuery(name = "ArtistaEntity.findByIdArtista", query = "SELECT a FROM ArtistaEntity a WHERE a.idArtista = :idArtista")
    , @NamedQuery(name = "ArtistaEntity.findByNombreArtista", query = "SELECT a FROM ArtistaEntity a WHERE a.nombreArtista = :nombreArtista")
    , @NamedQuery(name = "ArtistaEntity.findByNacimiento", query = "SELECT a FROM ArtistaEntity a WHERE a.nacimiento = :nacimiento")
    , @NamedQuery(name = "ArtistaEntity.findByDescripcion", query = "SELECT a FROM ArtistaEntity a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "ArtistaEntity.findByNombreArtistaPublic", query = "SELECT a FROM ArtistaEntity a WHERE a.nombreArtistaPublic = :nombreArtistaPublic")})
public class ArtistaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idArtista;
    private String nombreArtista;
    private String nacimiento;
    private String descripcion;
    @Lob
    private String nombreArtistaPublic;
    

    public ArtistaEntity() {
    }

    public ArtistaEntity(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArtistaPublic() {
        return nombreArtistaPublic;
    }

    public void setNombreArtistaPublic(String nombreArtistaPublic) {
        this.nombreArtistaPublic = nombreArtistaPublic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtista != null ? idArtista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtistaEntity)) {
            return false;
        }
        ArtistaEntity other = (ArtistaEntity) object;
        if ((this.idArtista == null && other.idArtista != null) || (this.idArtista != null && !this.idArtista.equals(other.idArtista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.ArtistaEntity[ idArtista=" + idArtista + " ]";
    }
    
}
