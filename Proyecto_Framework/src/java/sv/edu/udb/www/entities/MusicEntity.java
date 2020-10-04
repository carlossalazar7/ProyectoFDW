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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "music")
@NamedQueries({
    @NamedQuery(name = "MusicEntity.findAll", query = "SELECT m FROM MusicEntity m")
    , @NamedQuery(name = "MusicEntity.findByIdMusic", query = "SELECT m FROM MusicEntity m WHERE m.idMusic = :idMusic")
    , @NamedQuery(name = "MusicEntity.findByNombreCancion", query = "SELECT m FROM MusicEntity m WHERE m.nombreCancion = :nombreCancion")
    , @NamedQuery(name = "MusicEntity.findById", query = "SELECT m FROM MusicEntity m WHERE m.id = :id")
    , @NamedQuery(name = "MusicEntity.findByPrecio", query = "SELECT m FROM MusicEntity m WHERE m.precio = :precio")
    , @NamedQuery(name = "MusicEntity.findByLikes", query = "SELECT m FROM MusicEntity m WHERE m.likes = :likes")
    , @NamedQuery(name = "MusicEntity.findByLyrics", query = "SELECT m FROM MusicEntity m WHERE m.lyrics = :lyrics")})
public class MusicEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMusic;
    @Basic(optional = false)
    private String nombreCancion;
    @Basic(optional = false)
    private Integer id;
    @Lob
    private byte[] audio;
    @Lob
    private String imagen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float precio;
    private Integer likes;
    private String lyrics;

    public MusicEntity() {
    }

    public MusicEntity(Integer idMusic, String nombreCancion,Integer id,byte[] audio,String imagen,Float precio,Integer likes,
    String lyrics) {
        this.idMusic = idMusic;
        this.nombreCancion = nombreCancion;
        this.id = id;
        this.audio = audio;
        this.imagen = imagen;
        this.precio = precio;
        this.likes = likes;
        this.lyrics = lyrics;       
    }

    public MusicEntity(Integer idMusic) {
        this.idMusic = idMusic;
    }

    public Integer getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(Integer idMusic) {
        this.idMusic = idMusic;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMusic != null ? idMusic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicEntity)) {
            return false;
        }
        MusicEntity other = (MusicEntity) object;
        if ((this.idMusic == null && other.idMusic != null) || (this.idMusic != null && !this.idMusic.equals(other.idMusic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.MusicEntity[ idMusic=" + idMusic + " ]";
    }

}
