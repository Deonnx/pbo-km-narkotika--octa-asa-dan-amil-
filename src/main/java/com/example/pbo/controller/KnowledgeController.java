package com.example.pbo.controller;

import com.example.pbo.model.KnowledgeRepository;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.StatistikPutusan;
import java.util.ArrayList;

public class KnowledgeController {
    private KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
    }

    public boolean tambahPutusan(String[] data) {
        try {
            int umur = Integer.parseInt(data[4]);
            double berat = Double.parseDouble(data[6]);
            int hukuman = Integer.parseInt(data[9]);
            double denda = Double.parseDouble(data[10]);

            Putusan p = new Putusan(data[0], data[1], data[2], data[3], umur, data[5], berat, data[7], data[8], hukuman, denda, data[11]);
            repository.simpan(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Putusan> cariPutusan(String keyword, String mode) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        if (mode.equalsIgnoreCase("nomor")) {
            Putusan p = repository.cariByNomor(keyword);
            if (p != null) {
                hasil.add(p);
            }
        } else if (mode.equalsIgnoreCase("nama")) {
            hasil = repository.cariByNama(keyword);
        }
        return hasil;
    }

    public ArrayList<Putusan> filterPutusan(String kriteria, String nilai) {
        if (kriteria.equalsIgnoreCase("jenis")) {
            return repository.filterByJenis(nilai);
        } else if (kriteria.equalsIgnoreCase("pengadilan")) {
            return repository.filterByPengadilan(nilai);
        }
        return new ArrayList<>();
    }

    public boolean hapusPutusan(String nomor) {
        return repository.hapus(nomor);
    }

    public StatistikPutusan getStatistik() {
        StatistikPutusan stat = new StatistikPutusan(repository.getDaftarSemua());
        stat.hitungSemua();
        return stat;
    }

    public void tampilkanSemua() {
        ArrayList<Putusan> daftar = repository.getDaftarSemua();
        for (Putusan p : daftar) {
            p.tampilkan();
        }
    }
}