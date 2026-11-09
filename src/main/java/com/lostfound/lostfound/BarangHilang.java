
package com.lostfound.lostfound;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "barang_hilang")
public class BarangHilang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_barang", nullable = false, length = 100)
    private String namaBarang;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "lokasi", nullable = false, length = 150)
    private String lokasi;

    @Column(name = "tanggal", nullable = false)
    private LocalDate tanggal;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "HILANG";

    // DATA PENGAMBILAN BARANG

    @Column(name = "metode_pengambilan", length = 30)
    private String metodePengambilan;

    @Column(name = "nama_penerima", length = 100)
    private String namaPenerima;

    @Column(name = "nomor_telepon", length = 20)
    private String nomorTelepon;

    @Column(name = "alamat_pengantaran")
    private String alamatPengantaran;

    @Column(name = "status_pengantaran", length = 30)
    private String statusPengantaran;

    // CONSTRUCTOR

    public BarangHilang() {
    }

    // GETTER DAN SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodePengambilan() {
        return metodePengambilan;
    }

    public void setMetodePengambilan(String metodePengambilan) {
        this.metodePengambilan = metodePengambilan;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getAlamatPengantaran() {
        return alamatPengantaran;
    }

    public void setAlamatPengantaran(String alamatPengantaran) {
        this.alamatPengantaran = alamatPengantaran;
    }

    public String getStatusPengantaran() {
        return statusPengantaran;
    }

    public void setStatusPengantaran(String statusPengantaran) {
        this.statusPengantaran = statusPengantaran;
    }
}
