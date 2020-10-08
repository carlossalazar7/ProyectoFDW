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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "nombreplaylist")
@NamedQueries({
    @NamedQuery(name = "NombreplaylistEntity.findAll", query = "SELECT n FROM NombreplaylistEntity n")
    , @NamedQuery(name = "NombreplaylistEntity.findByIdNombrePlayList", query = "SELECT n FROM NombreplaylistEntity n WHERE n.idNombrePlayList = :idNombrePlayList")
    , @NamedQuery(name = "NombreplaylistEntity.findByNombrePlayList", query = "SELECT n FROM NombreplaylistEntity n WHERE n.nombrePlayList = :nombrePlayList")})
public class NombreplaylistEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idNombrePlayList;
    private String nombrePlayList;
    @OneToMany(mappedBy = "idNombrePlayList")
    private List<PlaylistEntity> playlistEntityList;

    public NombreplaylistEntity() {
    }

    public NombreplaylistEntity(Integer idNombrePlayList) {
        this.idNombrePlayList = idNombrePlayList;
    }

    public Integer getIdNombrePlayList() {
        return idNombrePlayList;
    }

    public void setIdNombrePlayList(Integer idNombrePlayList) {
        this.idNombrePlayList = idNombrePlayList;
    }

    public String getNombrePlayList() {
        return nombrePlayList;
    }

    public void setNombrePlayList(String nombrePlayList) {
        this.nombrePlayList = nombrePlayList;
    }

    public List<PlaylistEntity> getPlaylistEntityList() {
        return playlistEntityList;
    }

    public void setPlaylistEntityList(List<PlaylistEntity> playlistEntityList) {
        this.playlistEntityList = playlistEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNombrePlayList != null ? idNombrePlayList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NombreplaylistEntity)) {
            return false;
        }
        NombreplaylistEntity other = (NombreplaylistEntity) object;
        if ((this.idNombrePlayList == null && other.idNombrePlayList != null) || (this.idNombrePlayList != null && !this.idNombrePlayList.equals(other.idNombrePlayList))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.NombreplaylistEntity[ idNombrePlayList=" + idNombrePlayList + " ]";
    }
    
}
