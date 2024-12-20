public class MenuItem {
    private String nama;
    private String id;
    private String kategori;
    private double harga;

    public MenuItem(String nama, String id, String kategori, double harga) {
        this.nama = nama;
        this.id = id;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) { // Setter untuk nama
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) { // Setter untuk kategori
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return nama; // Menampilkan nama menu
    }
}