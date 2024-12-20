public abstract class Pengguna {
    protected String id;
    protected String nama;

    public Pengguna(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public abstract void tampilkanInfo();

    @Override
    public String toString() {
        return "Pengguna{id='" + id + "', nama='" + nama + "'}";
    }
}