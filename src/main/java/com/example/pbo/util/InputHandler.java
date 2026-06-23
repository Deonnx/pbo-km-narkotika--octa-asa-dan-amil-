package com.example.pbo.util;

public class InputHandler {

    public static int validasiInt(String input, String fieldName) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        try {
            int value = Integer.parseInt(input.trim());
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " tidak boleh bernilai negatif.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " harus berupa angka bulat.");
        }
    }

    public static double validasiDouble(String input, String fieldName) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        try {
            double value = Double.parseDouble(input.trim());
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " tidak boleh bernilai negatif.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " harus berupa angka desimal.");
        }
    }

    public static String validasiString(String input, String fieldName) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return input.trim();
    }
}