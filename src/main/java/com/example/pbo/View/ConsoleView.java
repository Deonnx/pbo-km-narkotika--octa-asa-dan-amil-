package com.example.pbo.View;

import com.example.pbo.controller.KnowledgeController;
import com.example.pbo.model.Putusan;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {

    private KnowledgeController controller;
    private Scanner scanner;

    public ConsoleView(KnowledgeController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public int tampilkanMenu() {

        System.out.println("\n===== KMS PUTUSAN NARKOTIKA =====");
        System.out.println("1. Tambah Putusan");
        System.out.println("2. Tampilkan Semua");
        System.out.println("3. Statistik");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu : ");

        return Integer.parseInt(scanner.nextLine());
    }

    public void menuTambahPutusan() {

        System.out.println("\n=== Tambah Putusan ===");

        System.out.print("Nomor Perkara : ");
        String nomor = scanner.nextLine();

        System.out.print("Nama Terdakwa : ");
        String nama = scanner.nextLine();

        System.out.print("Jenis Narkotika : ");
        String jenis = scanner.nextLine();

        System.out.print("Vonis Hukuman : ");
        String vonis = scanner.nextLine();

        System.out.print("Vonis Denda : ");
        String denda = scanner.nextLine();

        try {
            controller.tambahPutusan(nomor, nama, jenis, vonis, denda);
            tampilkanPesan("Data berhasil ditambahkan!");

        } catch (Exception e) {
            tampilkanPesan("Gagal menambahkan data: " + e.getMessage());
        }
    }

    public void tampilkanDaftarPutusan() {

        ArrayList<Putusan> daftar = controller.getDaftarSemua();

        System.out.println("\n=== DAFTAR PUTUSAN ===");
        System.out.printf(
                "%-15s %-20s %-15s %-10s %-15s%n",
                "Nomor", "Nama", "Jenis", "Vonis", "Denda"
        );

        for (Putusan p : daftar) {

            System.out.printf("%-15s %-20s %-15s %-10d %-15.0f%n",
                    p.getNomorPerkara(), p.getNamaTerdakwa(), p.getJenisNarkotika(), p.getVonisHukuman(), p.getVonisDenda()
            );
        }
    }

    public void tampilkanStatistik() {

        double rataRata = controller.getRataRataVonis();

        System.out.println("\n=== STATISTIK ===");
        System.out.println("Rata-rata Vonis : "+ rataRata + " bulan");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    ConsoleView view = new ConsoleView(controller);
    int pilihan;}