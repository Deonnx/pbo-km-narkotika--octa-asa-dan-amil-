package com.example.pbo.controller;

import com.example.pbo.model.KnowledgeRepository;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.StatistikPutusan;
import com.example.pbo.util.InputHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public int getTotalPutusan() {
        return repository.getDaftarSemua().size();
    }

    public double getTotalDenda() {

        double total = 0;

        for (Putusan p : repository.getDaftarSemua()) {
            total += p.getVonisDenda();
        }

        return total;
    }

    public double getRataRataVonis() {
        return statistik.hitungRataRataVonis(repository.getDaftarSemua());
    }

    public boolean hapusPutusan(String nomorPerkara) {
        return repository.hapusPutusan(nomorPerkara);
    }

    public void updatePutusan(String nomor, String nama, String jenis, String vonis, String denda) {
        repository.updatePutusan(nomor, nama, jenis, Integer.parseInt(vonis), Double.parseDouble(denda));
    }

    public ArrayList<Putusan> cariPutusan(String keyword) {
        return repository.cariPutusan(keyword);
    }

    public String getJenisTerbanyak() {

        Map<String, Integer> jumlahJenis = new HashMap<>();
        for (Putusan p : repository.getDaftarSemua()) {

            jumlahJenis.merge(
                    p.getJenisNarkotika(),
                    1,
                    Integer::sum
            );
        }

        if (jumlahJenis.isEmpty()) {
            return "-";
        }

        String jenisTerbanyak = "";
        int maksimum = 0;

        for (Map.Entry<String, Integer> entry : jumlahJenis.entrySet()) {
            if (entry.getValue() > maksimum) {

                maksimum = entry.getValue();
                jenisTerbanyak = entry.getKey();

            }
        }

        return jenisTerbanyak;
    }
}