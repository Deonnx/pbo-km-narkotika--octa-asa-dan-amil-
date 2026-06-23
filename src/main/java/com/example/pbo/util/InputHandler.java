package com.example.pbo.util;

import java.util.Scanner;

public class InputHandler {

    public static int validasiInt(String prompt, Scanner sc) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka bulat. Silakan coba lagi.");
            }
        }
    }

    public static double validasiDouble(String prompt, Scanner sc) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka desimal/rupiah. Silakan coba lagi.");
            }
        }
    }

    public static String validasiString(String prompt, Scanner sc) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Input tidak boleh kosong.");
        }
    }
}