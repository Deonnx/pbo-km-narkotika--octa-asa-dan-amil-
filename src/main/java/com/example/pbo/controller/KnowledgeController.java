package com.example.pbo.controller;

import com.example.pbo.model.KnowledgeRepository;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.StatistikPutusan;
import java.util.ArrayList;

public class KnowledgeController {
    private KnowledgeRepository repository;
    private StatistikPutusan statistik;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
        this.statistik = new StatistikPutusan();
    }

    public void tambahPutusan(String nomor, String nama, String jenis, int vonis, double denda) {
        Putusan p = new Putusan(nomor, nama, jenis, vonis, denda);
        repository.simpan(p);
        System.out.println("[SUKSES] Data putusan atas nama " + nama + " berhasil ditambahkan!");
    }

    public void tampilkanSemua() {
        ArrayList<Putusan> daftar = repository.getDaftarSemua();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        System.out.println("\n--- DAFTAR PUTUSAN PENGADILAN ---");
        for (Putusan p : daftar) {
            System.out.println("No: " + p.getNomorPerkara() +
                    " | Terdakwa: " + p.getNamaTerdakwa() +
                    " | Jenis: " + p.getJenisNarkotika() +
                    " | Vonis: " + p.getVonisHukuman() + " bulan" +
                    " | Denda: Rp" + p.getVonisDenda());
        }
    }

    public void tampilkanStatistik() {
        ArrayList<Putusan> daftar = repository.getDaftarSemua();
        double rataRata = statistik.hitungRataRataVonis(daftar);

        System.out.println("\n--- STATISTIK RINGKAS ---");
        System.out.println("Total Data Putusan  : " + daftar.size());
        System.out.println("Rata-rata Hukuman   : " + String.format("%.2f", rataRata) + " bulan");
    }
}