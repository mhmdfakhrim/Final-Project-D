/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCPWEBSERVICE.UCP.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThinkPad
 */
@Entity
@Table(name = "daftarujian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Daftarujian.findAll", query = "SELECT d FROM Daftarujian d"),
    @NamedQuery(name = "Daftarujian.findById", query = "SELECT d FROM Daftarujian d WHERE d.id = :id"),
    @NamedQuery(name = "Daftarujian.findByNama", query = "SELECT d FROM Daftarujian d WHERE d.nama = :nama"),
    @NamedQuery(name = "Daftarujian.findByJumlah", query = "SELECT d FROM Daftarujian d WHERE d.jumlah = :jumlah")})
public class Daftarujian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Jumlah")
    private Integer jumlah;
    @Lob
    @Column(name = "Gambar")
    private byte[] gambar;

    public Daftarujian() {
    }

    public Daftarujian(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
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
        if (!(object instanceof Daftarujian)) {
            return false;
        }
        Daftarujian other = (Daftarujian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UCPWEBSERVICE.UCP.model.entity.Daftarujian[ id=" + id + " ]";
    }
    
}
