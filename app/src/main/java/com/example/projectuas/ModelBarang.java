package com.example.projectuas;

public class ModelBarang {
    private String barang;
    private String jenis;
    private String berat;
    private String merk;
    private String harga;
    private String key;

    public ModelBarang(){

    }

    public ModelBarang(String barang, String jenis, String berat, String merk, String harga) {
        this.barang = barang;
        this.jenis = jenis;
        this.berat = berat;
        this.merk = merk;
        this.harga = harga;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
