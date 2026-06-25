package com.example.pbo.controller;

import com.example.pbo.model.KnowledgeRepository;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.StatistikPutusan;
import com.example.pbo.util.InputHandler;
import java.util.ArrayList;

public class KnowledgeController {
    private KnowledgeRepository repository;
    private StatistikPutusan statistik;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
        this.statistik = new StatistikPutusan();
    }

    public void tambahPutusan(String nomor, String nama, String jenis, String vonisStr, String dendaStr) throws IllegalArgumentException {
        String nomorValid = InputHandler.validasiString(nomor, "Nomor Perkara");
        String namaValid = InputHandler.validasiString(nama, "Nama Terdakwa");
        String jenisValid = InputHandler.validasiString(jenis, "Jenis Narkotika");
        int vonisValid = InputHandler.validasiInt(vonisStr, "Vonis Hukuman");
        double dendaValid = InputHandler.validasiDouble(dendaStr, "Vonis Denda");

        Putusan p = new Putusan(nomorValid, namaValid, jenisValid, vonisValid, dendaValid);
        repository.simpan(p);
    }

    public ArrayList<Putusan> getDaftarSemua() {
        return repository.getDaftarSemua();
    }

    public double getRataRataVonis() {
        return statistik.hitungRataRataVonis(repository.getDaftarSemua());
    }

    public boolean hapusPutusan(String nomorPerkara) {
        return repository.hapusPutusan(nomorPerkara);
    }
}