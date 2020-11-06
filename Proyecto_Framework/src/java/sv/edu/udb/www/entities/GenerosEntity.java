/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "generos")
@NamedQueries({
    @NamedQuery(name = "GenerosEntity.findAll", query = "SELECT g FROM GenerosEntity g")
    , @NamedQuery(name = "GenerosEntity.findById", query = "SELECT g FROM GenerosEntity g WHERE g.id = :id")
    , @NamedQuery(name = "GenerosEntity.findByNombreGenero", query = "SELECT g FROM GenerosEntity g WHERE g.nombreGenero = :nombreGenero")})
public class GenerosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String nombreGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<ArtistaEntity> artistaEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<MusicEntity> musicEntityList;
    @OneToMany(mappedBy = "id")
    private List<PaquetesEntity> paquetesEntityList;

    public GenerosEntity() {
    }

    public GenerosEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public List<ArtistaEntity> getArtistaEntityList() {
        return artistaEntityList;
    }

    public void setArtistaEntityList(List<ArtistaEntity> artistaEntityList) {
        this.artistaEntityList = artistaEntityList;
    }

    public List<MusicEntity> getMusicEntityList() {
        return musicEntityList;
    }

    public void setMusicEntityList(List<MusicEntity> musicEntityList) {
        this.musicEntityList = musicEntityList;
    }

    public List<PaquetesEntity> getPaquetesEntityList() {
        return paquetesEntityList;
    }

    public void setPaquetesEntityList(List<PaquetesEntity> paquetesEntityList) {
        this.paquetesEntityList = paquetesEntityList;
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
        if (!(object instanceof GenerosEntity)) {
            return false;
        }
        GenerosEntity other = (GenerosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.GenerosEntity[ id=" + id + " ]";
    }

}
