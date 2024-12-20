import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pesanan {
   private String idPelanggan = "";
   private String namaPelanggan = "";
   private List<MenuItem> itemPesanan = new ArrayList();
   private String idStaf = "";
   private String namaStaf = "";

   public Pesanan() {
   }

   public void tambahItem(MenuItem menuItem) {
      if (menuItem != null) {
         this.itemPesanan.add(menuItem);
      } else {
         System.out.println("Item tidak valid!");
      }

   }

   public void tampilkanDetailPesanan() {
      System.out.println("ID Pelanggan: " + this.idPelanggan);
      System.out.println("Nama Pelanggan: " + this.namaPelanggan);
      System.out.println("Staf yang Melayani: " + this.namaStaf);
      System.out.println("Item Pesanan:");
      Iterator var1 = this.itemPesanan.iterator();

      while(var1.hasNext()) {
         MenuItem item = (MenuItem)var1.next();
         PrintStream var10000 = System.out;
         String var10001 = item.getNama();
         var10000.println("- " + var10001 + " (Rp" + item.getHarga() + ")");
      }

   }

   public double hitungTotal() {
      return this.itemPesanan.stream().mapToDouble(MenuItem::getHarga).sum();
   }

   public String getIdPelanggan() {
      return this.idPelanggan;
   }

   public void setIdPelanggan(String idPelanggan) {
      this.idPelanggan = idPelanggan;
   }

   public String getNamaPelanggan() {
      return this.namaPelanggan;
   }

   public void setNamaPelanggan(String namaPelanggan) {
      this.namaPelanggan = namaPelanggan;
   }

   public List<MenuItem> getItemPesanan() {
      return this.itemPesanan;
   }

   public String getIdStaf() {
      return this.idStaf;
   }

   public void setIdStaf(String idStaf) {
      this.idStaf = idStaf;
   }

   public String getNamaStaf() {
      return this.namaStaf;
   }

   public void setNamaStaf(String namaStaf) {
      this.namaStaf = namaStaf;
   }
}
