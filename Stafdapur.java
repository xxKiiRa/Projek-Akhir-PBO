import java.io.PrintStream;

public class StafDapur extends Staf {
   public StafDapur(String id, String nama, String jadwal) {
      super(id, nama, jadwal);
   }

   public void tampilkanInfo() {
      PrintStream var10000 = System.out;
      String var10001 = this.getId();
      var10000.println("ID Staf Dapur: " + var10001 + ", Nama: " + this.getNama() + ", Jadwal: " + this.getJadwal());
   }

   public String toString() {
      String var10000 = this.getId();
      return var10000 + " - " + this.getNama() + " - " + this.getJadwal();
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setNama(String nama) {
      this.nama = nama;
   }

   public void setJadwal(String jadwal) {
      this.jadwal = jadwal;
   }
}
