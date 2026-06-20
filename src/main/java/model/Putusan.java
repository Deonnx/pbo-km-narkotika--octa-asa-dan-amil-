package model;

public class Putusan implements Comparable<Putusan> {
    private String nomorPerkara;
    private String namaTerdakwa;
    private String jenisNarkotika;
    private int vonisHukuman;
    private double vonisDenda;

    public Putusan(String nomorPerkara, String namaTerdakwa, String jenisNarkotika, int vonisHukuman, double vonisDenda) {
        this.nomorPerkara = nomorPerkara;
        this.namaTerdakwa = namaTerdakwa;
        this.jenisNarkotika = jenisNarkotika;
        this.vonisHukuman = vonisHukuman;
        this.vonisDenda = vonisDenda;
    }

    // Getters
    public String getNomorPerkara() { return nomorPerkara; }
    public String getNamaTerdakwa() { return namaTerdakwa; }
    public String getJenisNarkotika() { return jenisNarkotika; }
    public int getVonisHukuman() { return vonisHukuman; }
    public double getVonisDenda() { return vonisDenda; }

    @Override
    public int compareTo(Putusan other) {
        return Integer.compare(this.vonisHukuman, other.vonisHukuman);
    }
}