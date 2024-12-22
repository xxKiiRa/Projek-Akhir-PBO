package SmartRestaurantManagement.gui;

import SmartRestaurantManagement.models.*;
import SmartRestaurantManagement.models.MenuItem;
import SmartRestaurantManagement.services.*;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntarmukaGUI {
    private Pesanan pesanan;
    private DefaultListModel<MenuItem> menuListModel;
    private JList<MenuItem> menuJList;
    private DefaultListModel<String> inventarisListModel;
    private JList<String> inventarisJList;
    private Map<String, Integer> stokBarang;
    private List<String> daftarPelanggan;
    private PembuatLaporan pembuatLaporan;
    private Inventaris inventaris;

    public AntarmukaGUI() {
        pesanan = new Pesanan();
        menuListModel = new DefaultListModel<>();
        inventarisListModel = new DefaultListModel<>();
        stokBarang = new HashMap<>();
        daftarPelanggan = new ArrayList<>();
        pembuatLaporan = new PembuatLaporan();
        inventaris = new Inventaris();
    }

    public void mulai() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistem Manajemen Restoran Pintar");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            frame.add(panel);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            tempatkanKomponen(panel, gbc);

            frame.setVisible(true);
        });
    }

    private void tempatkanKomponen(JPanel panel, GridBagConstraints gbc) {
        JLabel labelSambutan = new JLabel("Selamat Datang di Sistem Manajemen Restoran Pintar");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(labelSambutan, gbc);

        JButton tombolMenu = new JButton("Kelola Menu");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(tombolMenu, gbc);

        JButton tombolPesanan = new JButton("Kelola Pesanan");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(tombolPesanan, gbc);

        JButton tombolStaf = new JButton("Kelola Staf");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(tombolStaf, gbc);

        JButton tombolInventaris = new JButton("Kelola Inventaris");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(tombolInventaris, gbc);

        JButton tombolLaporan = new JButton("Buat Laporan");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(tombolLaporan, gbc);

        JButton tombolTambahPelanggan = new JButton("Tambah Pelanggan");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(tombolTambahPelanggan, gbc);

        JButton tombolStafDapur = new JButton("Kelola Staf Dapur");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(tombolStafDapur, gbc);

        tombolMenu.addActionListener(e -> kelolaMenu());
        tombolPesanan.addActionListener(e -> kelolaPesanan());
        tombolStaf.addActionListener(e -> kelolaStaf());
        tombolInventaris.addActionListener(e -> kelolaInventaris());
        tombolLaporan.addActionListener(e -> buatLaporan());
        tombolTambahPelanggan.addActionListener(e -> tambahPelanggan());
        tombolStafDapur.addActionListener(e -> kelolaStafDapur());
    }

    private void tambahPelanggan() {
        String idBaru = JOptionPane.showInputDialog("Masukkan ID Pelanggan:");
        String namaBaru = JOptionPane.showInputDialog("Masukkan Nama Pelanggan:");
        String kontakBaru = JOptionPane.showInputDialog("Masukkan Kontak Pelanggan:");

        if (idBaru != null && !idBaru.isEmpty() && namaBaru != null && !namaBaru.isEmpty()) {
            daftarPelanggan.add(idBaru + " - " + namaBaru);
            JOptionPane.showMessageDialog(null, "Pelanggan baru berhasil ditambahkan.");
        } else {
            JOptionPane.showMessageDialog(null, "ID dan Nama Pelanggan tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kelolaMenu() {
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        menuJList = new JList<>(menuListModel);
        JScrollPane scrollPane = new JScrollPane(menuJList);
        panelMenu.add(scrollPane);

        JButton tambahMenuButton = new JButton("Tambah Menu");
        tambahMenuButton.addActionListener(e -> {
            try {
                String namaMenu = JOptionPane.showInputDialog("Masukkan Nama Menu:");
                if (namaMenu == null || namaMenu.isEmpty()) {
                    throw new IllegalArgumentException("Nama menu tidak boleh kosong");
                }

                String kategori = JOptionPane.showInputDialog("Masukkan Kategori:");
                double harga = Double.parseDouble(JOptionPane.showInputDialog("Masukkan Harga:"));
                if (harga <= 0) {
                    throw new IllegalArgumentException("Harga harus lebih besar dari 0");
                }

                MenuItem menuItem = new MenuItem(namaMenu, "ID" + (menuListModel.size() + 1), kategori, harga);
                menuListModel.addElement(menuItem);
                menuJList.revalidate();
                menuJList.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelMenu.add(tambahMenuButton);

        JButton hapusMenuButton = new JButton("Hapus Menu");
        hapusMenuButton.addActionListener(e -> {
            int selectedMenuIndex = menuJList.getSelectedIndex();
            if (selectedMenuIndex != -1) {
                menuListModel.remove(selectedMenuIndex);
                menuJList.revalidate();
                menuJList.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Pilih menu terlebih dahulu.");
            }
        });
        panelMenu.add(hapusMenuButton);

        JButton ubahMenuButton = new JButton("Ubah Menu");
        ubahMenuButton.addActionListener(e -> {
            int selectedMenuIndex = menuJList.getSelectedIndex();
            if (selectedMenuIndex != -1) {
                MenuItem selectedItem = menuListModel.get(selectedMenuIndex);
                String namaMenu = JOptionPane.showInputDialog("Masukkan Nama Menu:", selectedItem.getNama());
                String kategori = JOptionPane.showInputDialog("Masukkan Kategori:", selectedItem.getKategori());
                double harga = Double.parseDouble(JOptionPane.showInputDialog("Masukkan Harga:", selectedItem.getHarga()));
                selectedItem.setNama(namaMenu);
                selectedItem.setKategori(kategori);
                selectedItem.setHarga(harga);
                menuJList.revalidate();
                menuJList.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Pilih menu terlebih dahulu.");
            }
        });
        panelMenu.add(ubahMenuButton);

        JOptionPane.showMessageDialog(null, panelMenu, "Manajemen Menu", JOptionPane.PLAIN_MESSAGE);
    }

    private void kelolaPesanan() {
        JPanel panelPesanan = new JPanel();
        panelPesanan.setLayout(new BoxLayout(panelPesanan, BoxLayout.Y_AXIS));

        JComboBox<String> comboPelanggan = new JComboBox<>(daftarPelanggan.toArray(new String[0]));
        panelPesanan.add(new JLabel("Pilih Pelanggan:"));
        panelPesanan.add(comboPelanggan);

        // Tambahkan JComboBox untuk memilih staf
        JComboBox<String> comboStaf = new JComboBox<>(new String[] {
                "S001 - Luthfi - 08:00-12:00",
                "S002 - Pael - 12:00-14:00",
                "S003 - Saif - 14:00-18:00",
                "S004 - Dava - 18:00-20:00"
        });
        panelPesanan.add(new JLabel("Pilih Staf:"));
        panelPesanan.add(comboStaf);

        JButton pilihPelangganButton = new JButton("Konfirmasi Pelanggan");
        pilihPelangganButton.addActionListener(e -> {
            String selectedPelanggan = (String) comboPelanggan.getSelectedItem();
            if (selectedPelanggan != null && !selectedPelanggan.isEmpty()) {
                String[] pelangganData = selectedPelanggan.split(" - ");
                pesanan.setIdPelanggan(pelangganData[0]);
                pesanan.setNamaPelanggan(pelangganData[1]);
                JOptionPane.showMessageDialog(null, "Pelanggan Terpilih: " + pelangganData[1]);
            } else {
                JOptionPane.showMessageDialog(null, "Pilih pelanggan terlebih dahulu.");
            }
        });
        panelPesanan.add(pilihPelangganButton);

        JButton tambahItemButton = new JButton("Tambah Item ke Pesanan");
        tambahItemButton.addActionListener(e -> {
            try {
                MenuItem selectedMenuItem = menuJList.getSelectedValue();
                if (selectedMenuItem != null) {
                    pesanan.tambahItem(selectedMenuItem);
                    JOptionPane.showMessageDialog(null, "Item berhasil ditambahkan ke pesanan.");
                    System.out.println("Total setelah menambah item: Rp" + pesanan.hitungTotal()); // Debug
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan pilih menu terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelPesanan.add(tambahItemButton);

        JButton konfirmasiPesananButton = new JButton("Konfirmasi Pesanan");
        konfirmasiPesananButton.addActionListener(e -> {
            if (!pesanan.getItemPesanan().isEmpty() && !pesanan.getIdPelanggan().isEmpty()) {
                String selectedStaf = (String) comboStaf.getSelectedItem();
                if (selectedStaf != null && !selectedStaf.isEmpty()) {
                    String[] stafData = selectedStaf.split(" - "); // Memisahkan ID, Nama, dan Jadwal
                    pesanan.setIdStaf(stafData[0]); // Simpan ID staf
                    pesanan.setNamaStaf(stafData[1]); // Simpan Nama staf
                    JOptionPane.showMessageDialog(null, "Pesanan akan dilayani oleh: " + stafData[1]);
                    pembuatLaporan.tambahPesanan(pesanan);
                    JOptionPane.showMessageDialog(null, "Pesanan berhasil ditambahkan.");
                    System.out.println("Total Pesanan: Rp" + pesanan.hitungTotal()); // Debug
                    pesanan = new Pesanan(); // Reset pesanan setelah konfirmasi
                } else {
                    JOptionPane.showMessageDialog(null, "Staf harus dipilih.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pesanan tidak boleh kosong dan pelanggan harus dipilih.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelPesanan.add(konfirmasiPesananButton);
        JOptionPane.showMessageDialog(null, panelPesanan, "Manajemen Pesanan", JOptionPane.PLAIN_MESSAGE);
    }

    private void kelolaStaf() {
        JPanel panelStaf = new JPanel();
        panelStaf.setLayout(new BoxLayout(panelStaf, BoxLayout.Y_AXIS));

        DefaultListModel<String> stafListModel = new DefaultListModel<>();
        stafListModel.addElement("S001 - Luthfi - 08:00-12:00");
        stafListModel.addElement("S002 - Pael - 12:00-14:00");
        stafListModel.addElement("S003 - Saif - 14:00-18:00");
        stafListModel.addElement("S004 - Dava - 18:00-20:00");
        JList<String> stafJList = new JList<>(stafListModel);
        JScrollPane scrollPaneStaf = new JScrollPane(stafJList);
        panelStaf.add(scrollPaneStaf);

        JButton tombolTambahStaf = new JButton("Tambah Staf");
        tombolTambahStaf.addActionListener(e -> {
            String idStaf = JOptionPane.showInputDialog("Masukkan ID Staf:");
            String namaStaf = JOptionPane.showInputDialog("Masukkan Nama Staf:");
            String jadwalStaf = JOptionPane.showInputDialog("Masukkan Jadwal Staf:");

            if (idStaf != null && !idStaf.isEmpty() && namaStaf != null && !namaStaf.isEmpty() && jadwalStaf != null && !jadwalStaf.isEmpty()) {
                stafListModel.addElement(idStaf + " - " + namaStaf + " - " + jadwalStaf);
                JOptionPane.showMessageDialog(null, "Staf baru berhasil ditambahkan.");
            } else {
                JOptionPane.showMessageDialog(null, "ID, Nama, dan Jadwal Staf tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelStaf.add(tombolTambahStaf);

        JOptionPane.showMessageDialog(null, panelStaf, "Manajemen Staf", JOptionPane.PLAIN_MESSAGE);
    }

    private void kelolaInventaris() {
        JPanel panelInventaris = new JPanel();
        panelInventaris.setLayout(new BoxLayout(panelInventaris, BoxLayout.Y_AXIS));

        inventarisJList = new JList<>(inventarisListModel);
        JScrollPane scrollPaneInventaris = new JScrollPane(inventarisJList);
        panelInventaris.add(scrollPaneInventaris);

        JButton tombolTambahInventaris = new JButton("Tambah Inventaris");
        tombolTambahInventaris.addActionListener(e -> {
            String namaItem = JOptionPane.showInputDialog("Masukkan Nama Item:");
            try {
                int jumlahItem = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Jumlah Item:"));
                if (jumlahItem <= 0) {
                    throw new IllegalArgumentException("Jumlah harus lebih besar dari 0");
                }

                // Memanggil metode tambahStok
                inventaris.tambahStok(namaItem, jumlahItem);
                inventarisListModel.addElement(namaItem + ": " + inventaris.getStok(namaItem));
                JOptionPane.showMessageDialog(null, "Item inventaris berhasil ditambahkan.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelInventaris.add(tombolTambahInventaris);

        JButton tombolGunakanInventaris = new JButton("Gunakan Inventaris");
        tombolGunakanInventaris.addActionListener(e -> {
            String namaItem = JOptionPane.showInputDialog("Masukkan Nama Item untuk Digunakan:");
            try {
                int jumlahItem = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Jumlah Item:"));
                if (jumlahItem <= 0) {
                    throw new IllegalArgumentException("Jumlah harus lebih besar dari 0");
                }

                // Memanggil metode gunakanStok
                inventaris.gunakanStok(namaItem, jumlahItem);
                inventarisListModel.addElement(namaItem + ": " + inventaris.getStok(namaItem));
                JOptionPane.showMessageDialog(null, "Item inventaris berhasil digunakan.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelInventaris.add(tombolGunakanInventaris);

        // Tambahkan tombol untuk menampilkan stok
        JButton tombolTampilkanStok = new JButton("Tampilkan Stok");
        tombolTampilkanStok.addActionListener(e -> {
            inventaris.tampilkanStok(); // Panggil metode tampilkanStok
        });
        panelInventaris.add(tombolTampilkanStok);

        JOptionPane.showMessageDialog(null, panelInventaris, "Kelola Inventaris", JOptionPane.PLAIN_MESSAGE);
    }

    private void kelolaStafDapur() {
        JPanel panelStafDapur = new JPanel();
        panelStafDapur.setLayout(new BoxLayout(panelStafDapur, BoxLayout.Y_AXIS));

        DefaultListModel<StafDapur> stafDapurListModel = new DefaultListModel<>();
        JList<StafDapur> stafDapurJList = new JList<>(stafDapurListModel);
        JScrollPane scrollPane = new JScrollPane(stafDapurJList);
        panelStafDapur.add(scrollPane);

        JButton tambahStafDapurButton = new JButton("Tambah Staf Dapur");
        tambahStafDapurButton.addActionListener(e -> {
            String idStaf = JOptionPane.showInputDialog("Masukkan ID Staf Dapur:");
            String namaStaf = JOptionPane.showInputDialog("Masukkan Nama Staf Dapur:");
            String jadwalStaf = JOptionPane.showInputDialog("Masukkan Jadwal Staf Dapur:");

            if (idStaf != null && !idStaf.isEmpty() && namaStaf != null && !namaStaf.isEmpty() && jadwalStaf != null && !jadwalStaf.isEmpty()) {
                StafDapur stafDapur = new StafDapur(idStaf, namaStaf, jadwalStaf);
                stafDapurListModel.addElement(stafDapur);
                JOptionPane.showMessageDialog(null, "Staf Dapur baru berhasil ditambahkan.");
            } else {
                JOptionPane.showMessageDialog(null, "ID, Nama, dan Jadwal Staf Dapur tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelStafDapur.add(tambahStafDapurButton);

        JButton hapusStafDapurButton = new JButton("Hapus Staf Dapur");
        hapusStafDapurButton.addActionListener(e -> {
            StafDapur selectedStafDapur = stafDapurJList.getSelectedValue();
            if (selectedStafDapur != null) {
                stafDapurListModel.removeElement(selectedStafDapur);
                JOptionPane.showMessageDialog(null, "Staf Dapur berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(null, "Silakan pilih staf dapur terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelStafDapur.add(hapusStafDapurButton);

        JButton ubahStafDapurButton = new JButton("Ubah Staf Dapur");
        ubahStafDapurButton.addActionListener(e -> {
            StafDapur selectedStafDapur = stafDapurJList.getSelectedValue();
            if (selectedStafDapur != null) {
                String idStaf = JOptionPane.showInputDialog("Masukkan ID Staf Dapur:", selectedStafDapur.getId());
                String namaStaf = JOptionPane.showInputDialog("Masukkan Nama Staf Dapur:", selectedStafDapur.getNama());
                String jadwalStaf = JOptionPane.showInputDialog("Masukkan Jadwal Staf Dapur:", selectedStafDapur.getJadwal());

                if (idStaf != null && !idStaf.isEmpty() && namaStaf != null && !namaStaf.isEmpty() && jadwalStaf != null && !jadwalStaf.isEmpty()) {
                    selectedStafDapur.setId(idStaf);
                    selectedStafDapur.setNama(namaStaf);
                    selectedStafDapur.setJadwal(jadwalStaf);
                    JOptionPane.showMessageDialog(null, "Staf Dapur berhasil diubah.");
                } else {
                    JOptionPane.showMessageDialog(null, "ID, Nama, dan Jadwal Staf Dapur tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Silakan pilih staf dapur terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelStafDapur.add(ubahStafDapurButton);

        JOptionPane.showMessageDialog(null, panelStafDapur, "Manajemen Staf Dapur", JOptionPane.PLAIN_MESSAGE);
    }

    private void buatLaporan() {
        pembuatLaporan.buatLaporanHarian();
        pembuatLaporan.buatLaporanBulanan();
        JOptionPane.showMessageDialog(null, "Laporan telah dibuat dan ditampilkan di konsol.", "Laporan", JOptionPane.INFORMATION_MESSAGE);
    }
}