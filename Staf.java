public class Staf extends Pengguna {
   protected String jadwal;

   public Staf(String id, String nama, String jadwal) {
      super(id, nama);
      this.jadwal = jadwal;
   }

   public String getJadwal() {
      return this.jadwal;
   }

   public void setJadwal(String jadwal) {
      this.jadwal = jadwal;
   }

   public void tampilkanInfo() {
      System.out.println("ID Staf: " + this.id + ", Nama: " + this.nama + ", Jadwal: " + this.jadwal);
   }
}
