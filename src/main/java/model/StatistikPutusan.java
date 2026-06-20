package model;

import java.util.ArrayList;

public class StatistikPutusan {
    public double hitungRataRataVonis(ArrayList<Putusan> daftar) {
        if (daftar.isEmpty()) return 0;
        double total = 0;
        for (Putusan p : daftar) total += p.getVonisHukuman();
        return total / daftar.size();
    }
}