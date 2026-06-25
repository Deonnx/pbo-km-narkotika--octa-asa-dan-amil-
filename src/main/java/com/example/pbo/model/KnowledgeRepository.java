package com.example.pbo.model;

import java.util.ArrayList;

public class KnowledgeRepository {
    private ArrayList<Putusan> daftarPutusan = new ArrayList<>();

    public KnowledgeRepository() {
        muatDataAwal();
    }

    public void simpan(Putusan p) { daftarPutusan.add(p); }
    public ArrayList<Putusan> getDaftarSemua() { return daftarPutusan; }

    private void muatDataAwal() {
        simpan(new Putusan("1/Pid/2024", "Andi", "Sabu", 60, 1000000000));
        simpan(new Putusan("2/Pid/2024", "Budi", "Ganja", 12, 0));
        // Tambahkan data lainnya di sini...
    }

    public boolean hapusPutusan(String nomorPerkara) {

        for (Putusan p : daftarPutusan) {

            if (p.getNomorPerkara().equals(nomorPerkara)) {
                daftarPutusan.remove(p);
                return true;
            }
        }

        return false;
    }

    public void updatePutusan(String nomor, String nama, String jenis, int vonis, double denda) {

        for (Putusan p : daftarPutusan) {

            if (p.getNomorPerkara().equals(nomor)) {

                daftarPutusan.remove(p);
                daftarPutusan.add(new Putusan(nomor, nama, jenis, vonis, denda));

                break;
            }
        }
    }
}