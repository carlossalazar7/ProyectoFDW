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
@Table(name = "playlist")
@NamedQueries({
    @NamedQuery(name = "PlaylistEntity.findAll", query = "SELECT p FROM PlaylistEntity p")
    , @NamedQuery(name = "PlaylistEntity.findByIdPlayList", query = "SELECT p FROM PlaylistEntity p WHERE p.idPlayList = :idPlayList")
    , @NamedQuery(name = "PlaylistEntity.findByIdMusic", query = "SELECT p FROM PlaylistEntity p WHERE p.idMusic = :idMusic")
    , @NamedQuery(name = "PlaylistEntity.findByIdUser", query = "SELECT p FROM PlaylistEntity p WHERE p.idUser = :idUser")
    , @NamedQuery(name = "PlaylistEntity.findByIdNombrePlayList", query = "SELECT p FROM PlaylistEntity p WHERE p.idNombrePlayList = :idNombrePlayList")
    , @NamedQuery(name = "PlaylistEntity.findByEstado", query = "SELECT p FROM PlaylistEntity p WHERE p.estado = :estado")})
public class PlaylistEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idPlayList;
    private Integer idMusic;
    private Integer idUser;
    private Integer idNombrePlayList;
    private String estado;

    public PlaylistEntity() {
    }

    public PlaylistEntity(Integer idPlayList) {
        this.idPlayList = idPlayList;
    }

    public Integer getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(Integer idPlayList) {
        this.idPlayList = idPlayList;
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

    public Integer getIdNombrePlayList() {
        return idNombrePlayList;
    }

    public void setIdNombrePlayList(Integer idNombrePlayList) {
        this.idNombrePlayList = idNombrePlayList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlayList != null ? idPlayList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaylistEntity)) {
            return false;
        }
        PlaylistEntity other = (PlaylistEntity) object;
        if ((this.idPlayList == null && other.idPlayList != null) || (this.idPlayList != null && !this.idPlayList.equals(other.idPlayList))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.PlaylistEntity[ idPlayList=" + idPlayList + " ]";
    }
    
}
