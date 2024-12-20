
import java.util.HashMap;
import java.util.Map;

public class Inventaris {
    private Map<String, Integer> stok = new HashMap();

    public Inventaris() {
    }

    public void tambahStok(String item, int jumlah) {
        this.stok.put(item, (Integer)this.stok.getOrDefault(item, 0) + jumlah);
    }

    public void gunakanStok(String item, int jumlah) {
        if (this.stok.containsKey(item) && (Integer)this.stok.get(item) >= jumlah) {
            this.stok.put(item, (Integer)this.stok.get(item) - jumlah);
        } else {
            System.out.println("Stok tidak mencukupi untuk: " + item);
        }

    }

    public void tampilkanStok() {
        System.out.println("Stok Saat Ini:");
        this.stok.forEach((item, jumlah) -> {
            System.out.println(item + ": " + jumlah);
        });
    }

    public int getStok(String item) {
        return (Integer)this.stok.getOrDefault(item, 0);
    }
}
