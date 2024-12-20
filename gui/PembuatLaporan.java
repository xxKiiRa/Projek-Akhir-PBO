
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PembuatLaporan implements Laporan {
    private List<MenuItem> menuItems = new ArrayList();
    private List<Pesanan> pesananList = new ArrayList();

    public PembuatLaporan() {
    }

    public void tambahMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void tambahPesanan(Pesanan pesanan) {
        this.pesananList.add(pesanan);
    }

    public void buatLaporanHarian() {
        StringBuilder laporan = new StringBuilder();
        laporan.append("Laporan Harian:\n");
        double totalPendapatan = 0.0;

        double totalPesanan;
        for(Iterator var4 = this.pesananList.iterator(); var4.hasNext(); totalPendapatan += totalPesanan) {
            Pesanan pesanan = (Pesanan)var4.next();
            laporan.append("ID Pelanggan: ").append(pesanan.getIdPelanggan()).append("\n");
            laporan.append("Nama Pelanggan: ").append(pesanan.getNamaPelanggan()).append("\n");
            laporan.append("Staf yang Melayani: ").append(pesanan.getIdStaf()).append("\n");
            laporan.append("Item Pesanan:\n");
            Iterator var6 = pesanan.getItemPesanan().iterator();

            while(var6.hasNext()) {
                MenuItem item = (MenuItem)var6.next();
                laporan.append("- ").append(item.getNama()).append(" (Rp").append(item.getHarga()).append(")\n");
            }

            totalPesanan = pesanan.hitungTotal();
            laporan.append("Total Pesanan: Rp").append(totalPesanan).append("\n\n");
        }

        laporan.append("Total Pendapatan Hari Ini: Rp").append(totalPendapatan).append("\n");
        System.out.println(laporan.toString());
        this.simpanLaporanKeFile(laporan.toString());
    }

    public void buatLaporanBulanan() {
    }

    private void simpanLaporanKeFile(String laporan) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("laporan_harian.txt"));

            try {
                writer.write(laporan);
                System.out.println("Laporan berhasil disimpan ke laporan_harian.txt");
            } catch (Throwable var6) {
                try {
                    writer.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            writer.close();
        } catch (IOException var7) {
            IOException e = var7;
            System.err.println("Terjadi kesalahan saat menyimpan laporan: " + e.getMessage());
        }

    }
}
