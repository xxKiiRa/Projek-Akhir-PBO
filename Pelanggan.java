public class Pelanggan extends Pengguna {
    private String kontak;

    public Pelanggan(String id, String nama, String kontak) {
        super(id, nama);
        this.kontak = kontak;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("ID Pelanggan: " + id + ", Nama: " + nama + ", Kontak: " + kontak);
    }
}