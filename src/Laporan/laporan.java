package Laporan;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import db.konekdb;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fathan
 */
public class laporan extends javax.swing.JInternalFrame {
Connection connn;
public Connection connnn(){
    try {
        connn = (Connection) konekdb.GetConnection();
        return connn;
    } catch (Exception e) {
    }
    return connn;
}
    DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat harii = new SimpleDateFormat("dd-MM-yyyy");
    String a = hari.format(Calendar.getInstance().getTime());
    String aa = harii.format(Calendar.getInstance().getTime());
    DateFormat Bulanan = new SimpleDateFormat("yyyyMM");
    String AmbilBulanSekarang = Bulanan.format(Calendar.getInstance().getTime());
    DateFormat Bulann = new SimpleDateFormat("yyyyMM");

// bulanan
    DateFormat formatBulan = new SimpleDateFormat("MM");
    String formatBulan1 = formatBulan.format(Calendar.getInstance().getTime());
    DateFormat formatTahun = new SimpleDateFormat("Y");
    String formatTahun1 = formatTahun.format(Calendar.getInstance().getTime());

    /**
     * Creates new form
     */
    public laporan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        ImageIcon iic = new ImageIcon("src/Laporan/bg_laporanHarian.png");
        bg_harianBulanan.setIcon(iic);
        panel_Harian.setVisible(true);
        panel_Bulanan.setVisible(false);
        loadDataHariIni();
        System.out.println(formatTahun1 + formatBulan1);
    }
//    

    public void loadDataHariIni() {
        DefaultTableModel transjual = new DefaultTableModel();
        transjual.addColumn("ID Transaksi");
        transjual.addColumn("Diskon");
        transjual.addColumn("Total Harga");
        transjual.addColumn("Kasir/Pengguna");
        DefaultTableModel transbeli = new DefaultTableModel();
        transbeli.addColumn("ID Transaksi");
        transbeli.addColumn("ID Pemasok");
        transbeli.addColumn("Total Harga");
        System.out.println(a);
        txt_TanggalHariIni.setText(aa);

        try {
            String loadPendapatandanLaba = "SELECT SUM(tb_jual.total_harga) AS Pendapatan, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + a + "'\n"
                    + "GROUP BY tb_jual.id_transaksi";
            String loadTransaksiJualHariIni = "SELECT tb_jual.id_transaksi, tb_jual.total_diskon, "
                    + "tb_jual.total_harga, tb_jual.id_pengguna\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + a + "'";
            String loadPendapatanHarian = "SELECT SUM(tb_jual.total_harga) AS PENDAPATAN\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + a + "'";
            String loadTransaksiBeliHariIni = "SELECT tb_beli.id_transaksi, tb_beli.id_pemasok, tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE tb_beli.tgl_transaksi = '" + a + "'";
            String loadPemasukanHariIni = "SELECT tb_pemasukan.tgl_pemasukan, SUM(tb_pemasukan.jumlah_pemasukan) AS PEMASUKAN FROM tb_pemasukan WHERE tb_pemasukan.tgl_pemasukan = '" + a + "'";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Connection con1 = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.Statement st2 = con.createStatement();
            java.sql.Statement st3 = con.createStatement();
            java.sql.Statement st4 = con1.createStatement();
            java.sql.ResultSet res = st.executeQuery(loadPendapatandanLaba);
            java.sql.ResultSet res1 = st1.executeQuery(loadTransaksiJualHariIni);
            java.sql.ResultSet res2 = st2.executeQuery(loadPendapatanHarian);
            java.sql.ResultSet res3 = st3.executeQuery(loadTransaksiBeliHariIni);
            java.sql.ResultSet res4 = st4.executeQuery(loadPemasukanHariIni);
            if (res.next()) {
                txt_labaHarian.setText("Rp" + res.getString("Laba"));
            }
            while (res2.next()) {
                txt_PendapatanHarian.setText("Rp" + res2.getString("PENDAPATAN").toString());
            }
            while (res1.next()) {
                transjual.addRow(new Object[]{
                    res1.getString(1),
                    res1.getString(2),
                    res1.getString(3),
                    res1.getString(4)
                });
                tabel_TransaksiPenjualanHarian.setModel(transjual);
            }
            while (res3.next()) {
                transbeli.addRow(new Object[]{
                    res3.getString(1),
                    res3.getString(2),
                    res3.getString(3)
                });
                tabel_TransaksiPembelianHarian.setModel(transbeli);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadDataperTanggal() {

        DefaultTableModel transjual = new DefaultTableModel();
        transjual.addColumn("ID Transaksi");
        transjual.addColumn("Diskon");
        transjual.addColumn("Total Harga");
        transjual.addColumn("Kasir/Pengguna");
        DefaultTableModel transbeli = new DefaultTableModel();
        transbeli.addColumn("ID Transaksi");
        transbeli.addColumn("ID Pemasok");
        transbeli.addColumn("Total Harga");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());
            SimpleDateFormat sdff = new SimpleDateFormat("dd-MM-yyyy");
            String datee = sdff.format(jDateChooser1.getDate());

            txt_TanggalHariIni.setText(datee);
            String loadPendapatandanLaba = "SELECT SUM(tb_jual.total_harga) AS Pendapatan, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + date + "'\n"
                    + "GROUP BY tb_jual.id_transaksi";
            String loadTransaksiJualHariIni = "SELECT tb_jual.id_transaksi, tb_jual.total_diskon, "
                    + "tb_jual.total_harga, tb_jual.id_pengguna\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + date + "'";
            String loadPendapatanHarian = "SELECT SUM(tb_jual.total_harga) AS PENDAPATAN\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '" + date + "'";
            String loadTransaksiBeliHariIni = "SELECT tb_beli.id_transaksi, tb_beli.id_pemasok, tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE tb_beli.tgl_transaksi = '" + date + "'";
            String loadPemasukanHariIni = "SELECT tb_pemasukan.tgl_pemasukan, SUM(tb_pemasukan.jumlah_pemasukan) AS PEMASUKAN FROM tb_pemasukan WHERE tb_pemasukan.tgl_pemasukan = '" + date + "'";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement s = con.createStatement();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.Statement st2 = con.createStatement();
            java.sql.Statement st3 = con.createStatement();
            java.sql.ResultSet r = s.executeQuery(loadPemasukanHariIni);
            java.sql.ResultSet res = st.executeQuery(loadPendapatandanLaba);
            java.sql.ResultSet res1 = st1.executeQuery(loadTransaksiJualHariIni);
            java.sql.ResultSet res2 = st2.executeQuery(loadPendapatanHarian);
            java.sql.ResultSet res3 = st3.executeQuery(loadTransaksiBeliHariIni);
            if (res.next()) {
                txt_labaHarian.setText("Rp" + res.getString("Laba"));
            }
            while (res2.next()) {
                txt_PendapatanHarian.setText("Rp" + res2.getString("PENDAPATAN").toString());
            }
            while (res1.next()) {
                transjual.addRow(new Object[]{
                    res1.getString(1),
                    res1.getString(2),
                    res1.getString(3),
                    res1.getString(4)
                });
                tabel_TransaksiPenjualanHarian.setModel(transjual);
            }
            while (res3.next()) {
                transbeli.addRow(new Object[]{
                    res3.getString(1),
                    res3.getString(2),
                    res3.getString(3)
                });
                tabel_TransaksiPembelianHarian.setModel(transbeli);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            txt_PendapatanHarian.setText("Rp0");
            txt_labaHarian.setText("Rp0");
            tabel_TransaksiPembelianHarian.setModel(transbeli);
            tabel_TransaksiPenjualanHarian.setModel(transjual);
        }
    }

    public void loadDataBulainIni() {
        DefaultTableModel TBPeringkatProduk = new DefaultTableModel();
        TBPeringkatProduk.addColumn("ID Produk");
        TBPeringkatProduk.addColumn("Nama Produk");
        TBPeringkatProduk.addColumn("Total Terjual");
        DefaultTableModel TBPeringkatPemasok = new DefaultTableModel();
        TBPeringkatPemasok.addColumn("ID Pemasok");
        TBPeringkatPemasok.addColumn("Nama Pemasok");
        DefaultTableModel transbeli = new DefaultTableModel();
        transbeli.addColumn("ID Transaksi");
        transbeli.addColumn("Total Harga");
        DefaultTableModel transjual = new DefaultTableModel();
        transjual.addColumn("tgl Transaksi");
        transjual.addColumn("Total Harga");
        transjual.addColumn("Laba");
        txt_BulanHariIni1.setText(AmbilBulanSekarang);
        try {
            String PendapatanBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM(tb_jual.total_harga) AS Pendapatan\n"
                    + "FROM tb_jual\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            String LabaBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk=tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            String PemasukanBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) AS year_and_month, SUM(tb_pemasukan.jumlah_pemasukan) AS PEMASUKAN_BULAN_INI\n"
                    + "FROM tb_pemasukan\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan);";
            String PengeluaranBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran) AS year_and_month, SUM(tb_pengeluaran.jumlah_pengeluaran) AS PENGELUARAN_BULAN_INI\n"
                    + "FROM tb_pengeluaran\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran);";
            String PeringkatProduk = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS TANGGAL, tb_detailjual.id_produk, tb_produk.nama_produk, COUNT(tb_detailjual.jumlah_produk) AS Total\n"
                    + "FROM tb_detailjual\n"
                    + "JOIN tb_jual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY tb_detailjual.id_produk\n"
                    + "ORDER BY COUNT(tb_detailjual.jumlah_produk) DESC";
            String PeringkatPemasok = "SELECT EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) AS TANGGAL, tb_beli.id_pemasok, tb_pemasok.nama_pemasok, COUNT(tb_beli.id_pemasok) AS TOTAL\n"
                    + "FROM tb_beli\n"
                    + "JOIN tb_pemasok\n"
                    + "ON tb_beli.id_pemasok = tb_pemasok.id_pemasok\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY tb_beli.id_pemasok\n"
                    + "ORDER BY COUNT(tb_beli.id_pemasok) DESC;";
            String CatatanPenjualanBulanan = "SELECT  DATE_FORMAT(tb_jual.tgl_transaksi,'%d/%m/%Y'),tb_jual.total_harga, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual ON tb_detailjual.id_transaksi = tb_jual.id_transaksi\n"
                    + "JOIN tb_produk ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + AmbilBulanSekarang + "'\n"
                    + "GROUP BY tb_jual.tgl_transaksi";
            String CatatanPembelianBulanan = "SELECT DATE_FORMAT(tb_beli.tgl_transaksi, '%d/%m/%Y'), tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) = '" + formatTahun1 + formatBulan1 + "'";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.Statement st2 = con.createStatement();
            java.sql.Statement st3 = con.createStatement();
            java.sql.Statement st4 = con.createStatement();
            java.sql.Statement st5 = con.createStatement();
            java.sql.Statement st6 = con.createStatement();
            java.sql.Statement st7 = con.createStatement();

            java.sql.ResultSet rs = st.executeQuery(PendapatanBulanIni);
            java.sql.ResultSet rs1 = st1.executeQuery(LabaBulanIni);
            java.sql.ResultSet rs2 = st2.executeQuery(PemasukanBulanIni);
            java.sql.ResultSet rs3 = st3.executeQuery(PengeluaranBulanIni);
            java.sql.ResultSet rs4 = st4.executeQuery(PeringkatProduk);
            java.sql.ResultSet rs5 = st5.executeQuery(PeringkatPemasok);
            java.sql.ResultSet rs6 = st6.executeQuery(CatatanPenjualanBulanan);
            java.sql.ResultSet rs7 = st7.executeQuery(CatatanPembelianBulanan);

            if (rs.next()) {
                txt_PendapatanBulanan.setText("Rp" + rs.getString(2));
            }
            if (rs1.next()) {
                txt_labaHarian1.setText("Rp" + rs1.getString(2));
            }
            if (rs2.next()) {
                txt_pemasukanLainLainperTanggal1.setText("Rp" + rs2.getString(2));
            }
            if (rs3.next()) {
                txt_pengeluaranmLainLainperTanggal1.setText("Rp" + rs3.getString(2));
            }
            while (rs4.next()) {
                TBPeringkatProduk.addRow(new Object[]{
                    rs4.getString(2),
                    rs4.getString(3),
                    rs4.getString(4)
                });
                tabel_PeringkatProduk.setModel(TBPeringkatProduk);
            }
            while (rs5.next()) {
                TBPeringkatPemasok.addRow(new Object[]{
                    rs5.getString(2),
                    rs5.getString(3)
                });
                tabel_PeringkatPemasok.setModel(TBPeringkatPemasok);
            }
            while (rs6.next()) {
                transjual.addRow(new Object[]{
                    rs6.getString(1),
                    rs6.getString(2),
                    rs6.getString(3)
                });
                tabel_TransaksiPenjualanBulanan.setModel(transjual);
            }
            while (rs7.next()) {
                transbeli.addRow(new Object[]{
                    rs7.getString(1),
                    rs7.getString(2)
                });
                tabel_TransaksiPembelianBulanan.setModel(transbeli);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadDataPerBulan() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
        Date dateMonthChoose = jDateChooser2.getDate();
        String dateMonth = sdf.format(dateMonthChoose);
        DefaultTableModel TBPeringkatProduk = new DefaultTableModel();
        TBPeringkatProduk.addColumn("ID Produk");
        TBPeringkatProduk.addColumn("Nama Produk");
        TBPeringkatProduk.addColumn("Total Terjual");
        DefaultTableModel TBPeringkatPemasok = new DefaultTableModel();
        TBPeringkatPemasok.addColumn("ID Pemasok");
        TBPeringkatPemasok.addColumn("Nama Pemasok");
        DefaultTableModel transbeli = new DefaultTableModel();
        transbeli.addColumn("ID Transaksi");
        transbeli.addColumn("Total Harga");
        DefaultTableModel transjual = new DefaultTableModel();
        transjual.addColumn("tgl Transaksi");
        transjual.addColumn("Total Harga");
        transjual.addColumn("Laba");
        txt_BulanHariIni1.setText(dateMonth);
        try {
            System.out.println(dateMonth);
            String PendapatanBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM(tb_jual.total_harga) AS Pendapatan\n"
                    + "FROM tb_jual\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + dateMonth
                    + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st7 = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(PendapatanBulanIni);
            

            if (rs.next()) {
                txt_PendapatanBulanan.setText("Rp" + rs.getString(2));
            }
            
            
        } catch (Exception e) {
            System.err.println("ERROR"+e.getMessage());
        }
        // LabaBulanChoose
        try {
            String LabaBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk=tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + dateMonth + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            connn = (Connection)konekdb.GetConnection();
            java.sql.Statement st1 = connn.createStatement();
            java.sql.ResultSet rs1 = st1.executeQuery(LabaBulanIni);
            if (rs1.next()) {
                txt_labaHarian1.setText("Rp" + rs1.getString(2));
            }
        } catch (Exception e) {
            System.err.println("ERRORLABA BULAN INI + "+e.getMessage());
            txt_labaHarian1.setText("Rp0");
        }
        // PemasukanBulanChoose
        try {
             String PemasukanBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) AS year_and_month, SUM(tb_pemasukan.jumlah_pemasukan) AS PEMASUKAN_BULAN_INI\n"
                    + "FROM tb_pemasukan\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) = '" + dateMonth + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan);";
             
             java.sql.Statement st2 = connn.createStatement();
             java.sql.ResultSet rs2 = st2.executeQuery(PemasukanBulanIni);
             if (rs2.next()) {
                txt_pemasukanLainLainperTanggal1.setText("Rp" + rs2.getString(2));
            }
        } catch (Exception e) {
            System.err.println("ERRORPEMASUKAN"+e.getMessage());
            txt_pemasukanLainLainperTanggal1.setText("Rp0");
        }
        //PengeluaranBulanChoose
        try {
            String PengeluaranBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran) AS year_and_month, SUM(tb_pengeluaran.jumlah_pengeluaran) AS PENGELUARAN_BULAN_INI\n"
                    + "FROM tb_pengeluaran\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran) = '" + dateMonth + "'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_pengeluaran.tgl_pengeluaran);";
            java.sql.Statement st3 = connn.createStatement();
            java.sql.ResultSet res3 = st3.executeQuery(PengeluaranBulanIni);
            if (res3.next()) {
                txt_pengeluaranmLainLainperTanggal1.setText("Rp" + res3.getString(2));
            }
        } catch (Exception e) {
            System.err.println("ERRORPENGELUARANBULANTERPILIH"+e.getMessage());
            txt_pengeluaranmLainLainperTanggal1.setText("Rp0");
        }
        //PeringkatBulanaProduk
        try {
            String PeringkatProduk = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS TANGGAL, tb_detailjual.id_produk, tb_produk.nama_produk, COUNT(tb_detailjual.jumlah_produk) AS Total\n"
                    + "FROM tb_detailjual\n"
                    + "JOIN tb_jual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + dateMonth + "'\n"
                    + "GROUP BY tb_detailjual.id_produk\n"
                    + "ORDER BY COUNT(tb_detailjual.jumlah_produk) DESC";
            java.sql.Statement st4 = connn.createStatement();
            java.sql.ResultSet rs4 = st4.executeQuery(PeringkatProduk);
            while (rs4.next()) {
                TBPeringkatProduk.addRow(new Object[]{
                    rs4.getString(2),
                    rs4.getString(3),
                    rs4.getString(4)
                });
                tabel_PeringkatProduk.setModel(TBPeringkatProduk);
            }
        } catch (Exception e) {
            System.err.println("ERRORPERINGKATPRODUKBULANTERPILIH"+e.getMessage());
        }
        //PeringkatPemasokBulanChoose
        try {
            String PeringkatPemasok = "SELECT EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) AS TANGGAL, tb_beli.id_pemasok, tb_pemasok.nama_pemasok, COUNT(tb_beli.id_pemasok) AS TOTAL\n"
                    + "FROM tb_beli\n"
                    + "JOIN tb_pemasok\n"
                    + "ON tb_beli.id_pemasok = tb_pemasok.id_pemasok\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) = '" + dateMonth + "'\n"
                    + "GROUP BY tb_beli.id_pemasok\n"
                    + "ORDER BY COUNT(tb_beli.id_pemasok) DESC;";
            java.sql.Statement st5 = connn.createStatement();
            java.sql.ResultSet rs5 = st5.executeQuery(PeringkatPemasok);
            while (rs5.next()) {
                TBPeringkatPemasok.addRow(new Object[]{
                    rs5.getString(2),
                    rs5.getString(3)
                });
                tabel_PeringkatPemasok.setModel(TBPeringkatPemasok);
            }
        } catch (Exception e) {
            System.err.println("ERRORPERINGKATPEMASOKBULANTERPILIH"+e.getMessage());
        }
        //CatatanPEnjualanBulanTerepilih
        try {
            String CatatanPenjualanBulanan = "SELECT  DATE_FORMAT(tb_jual.tgl_transaksi,'%d/%m/%Y'),tb_jual.total_harga, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual ON tb_detailjual.id_transaksi = tb_jual.id_transaksi\n"
                    + "JOIN tb_produk ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '" + dateMonth+ "'\n"
                    + "GROUP BY tb_jual.tgl_transaksi";
            java.sql.Statement st6 = connn.createStatement();
            java.sql.ResultSet rs6 = st6.executeQuery(CatatanPenjualanBulanan);
            while (rs6.next()) {
                transjual.addRow(new Object[]{
                    rs6.getString(1),
                    rs6.getString(2),
                    rs6.getString(3)
                });
                tabel_TransaksiPenjualanBulanan.setModel(transjual);
            }
        } catch (Exception e) {
            System.err.println("ERRORPENJUALANBULANTERPILIH"+e.getMessage());
        }
        //CatatanPembelianBulanChoose
        try {
            String CatatanPembelianBulanan = "SELECT DATE_FORMAT(tb_beli.tgl_transaksi, '%d/%m/%Y'), tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_beli.tgl_transaksi) = '" + dateMonth + "'";
            java.sql.Statement st7= connn.createStatement();
            java.sql.ResultSet rs7 = st7.executeQuery(CatatanPembelianBulanan);
            while (rs7.next()) {
                transbeli.addRow(new Object[]{
                    rs7.getString(1),
                    rs7.getString(2)
                });
                tabel_TransaksiPembelianBulanan.setModel(transbeli);
            }
        } catch (Exception e) {
            System.err.println("ERRORPENJUALANBULANTERPILIH"+e.getMessage());
        }
    }

    /**
     *
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        button2 = new Swing.Button();
        button1 = new Swing.Button();
        panel_Bulanan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_BulanHariIni1 = new javax.swing.JLabel();
        panel_PeringkatPemasokTerlaris = new Swing.PanelRound();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabel_PeringkatPemasok = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel29 = new javax.swing.JLabel();
        tabel_PeringkatProdukTerlaris = new Swing.PanelRound();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabel_PeringkatProduk = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel27 = new javax.swing.JLabel();
        panel_catatanPembelianBulanan = new Swing.PanelRound();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabel_TransaksiPembelianBulanan = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel30 = new javax.swing.JLabel();
        panel_catatanPenjualanaBulanan = new Swing.PanelRound();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabel_TransaksiPenjualanBulanan = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel28 = new javax.swing.JLabel();
        panel_pendapatanHarian6 = new Swing.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        txt_PendapatanBulanan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panel_pendapatanHarian7 = new Swing.PanelRound();
        jLabel13 = new javax.swing.JLabel();
        txt_labaHarian1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        panel_pendapatanHarian8 = new Swing.PanelRound();
        jLabel20 = new javax.swing.JLabel();
        txt_pemasukanLainLainperTanggal1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panel_pendapatanHarian9 = new Swing.PanelRound();
        jLabel23 = new javax.swing.JLabel();
        txt_pengeluaranmLainLainperTanggal1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        button4 = new Swing.Button();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        panel_Harian = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_TanggalHariIni = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        button3 = new Swing.Button();
        panel_pendapatanHarian5 = new Swing.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_TransaksiPembelianHarian = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel19 = new javax.swing.JLabel();
        panel_pendapatanHarian4 = new Swing.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_TransaksiPenjualanHarian = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        panel_pendapatanHarian1 = new Swing.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        txt_labaHarian = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panel_pendapatanHarian = new Swing.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        txt_PendapatanHarian = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bg_harianBulanan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(960, 710));
        setPreferredSize(new java.awt.Dimension(960, 710));
        setVisible(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBackground(new java.awt.Color(238, 238, 238));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button2.setBackground(new java.awt.Color(253, 144, 39));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("BULANAN");
        button2.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel3.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 120, 100, 30));

        button1.setBackground(new java.awt.Color(253, 144, 39));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("HARIAN");
        button1.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel3.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 100, 30));

        panel_Bulanan.setBackground(new java.awt.Color(255, 255, 255));
        panel_Bulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(253, 144, 39));
        jLabel4.setText("LAPORAN BULANAN");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_Bulanan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 230, 50));

        txt_BulanHariIni1.setFont(new java.awt.Font("Quicksand", 1, 36)); // NOI18N
        txt_BulanHariIni1.setForeground(new java.awt.Color(102, 102, 102));
        txt_BulanHariIni1.setText("--/----");
        txt_BulanHariIni1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Bulanan.add(txt_BulanHariIni1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 320, 50));

        panel_PeringkatPemasokTerlaris.setBackground(new java.awt.Color(255, 255, 255));
        panel_PeringkatPemasokTerlaris.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_PeringkatPemasokTerlaris.setRoundBottomLeft(10);
        panel_PeringkatPemasokTerlaris.setRoundBottomRight(10);
        panel_PeringkatPemasokTerlaris.setRoundTopLeft(10);
        panel_PeringkatPemasokTerlaris.setRoundTopRight(10);
        panel_PeringkatPemasokTerlaris.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_PeringkatPemasok.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_PeringkatPemasok.getTableHeader().setOpaque(false);
        tabel_PeringkatPemasok.getTableHeader().setBackground(new Color(255,144,39));
        tabel_PeringkatPemasok.getTableHeader().setForeground(new Color(255,255,255));
        tabel_PeringkatPemasok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pemasok", "ID Pengguna", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_PeringkatPemasok.setRowHeight(30);
        tabel_PeringkatPemasok.setRowSelectionAllowed(false);
        tabel_PeringkatPemasok.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_PeringkatPemasok.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(tabel_PeringkatPemasok);
        if (tabel_PeringkatPemasok.getColumnModel().getColumnCount() > 0) {
            tabel_PeringkatPemasok.getColumnModel().getColumn(0).setResizable(false);
            tabel_PeringkatPemasok.getColumnModel().getColumn(1).setResizable(false);
            tabel_PeringkatPemasok.getColumnModel().getColumn(2).setResizable(false);
            tabel_PeringkatPemasok.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_PeringkatPemasokTerlaris.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel29.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel29.setText("Peringkat Pemasok");
        panel_PeringkatPemasokTerlaris.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(panel_PeringkatPemasokTerlaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1450, 720, 290));

        tabel_PeringkatProdukTerlaris.setBackground(new java.awt.Color(255, 255, 255));
        tabel_PeringkatProdukTerlaris.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        tabel_PeringkatProdukTerlaris.setRoundBottomLeft(10);
        tabel_PeringkatProdukTerlaris.setRoundBottomRight(10);
        tabel_PeringkatProdukTerlaris.setRoundTopLeft(10);
        tabel_PeringkatProdukTerlaris.setRoundTopRight(10);
        tabel_PeringkatProdukTerlaris.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_PeringkatProduk.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_PeringkatProduk.getTableHeader().setOpaque(false);
        tabel_PeringkatProduk.getTableHeader().setBackground(new Color(255,144,39));
        tabel_PeringkatProduk.getTableHeader().setForeground(new Color(255,255,255));
        tabel_PeringkatProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pemasok", "ID Pengguna", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_PeringkatProduk.setRowHeight(30);
        tabel_PeringkatProduk.setRowSelectionAllowed(false);
        tabel_PeringkatProduk.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_PeringkatProduk.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(tabel_PeringkatProduk);
        if (tabel_PeringkatProduk.getColumnModel().getColumnCount() > 0) {
            tabel_PeringkatProduk.getColumnModel().getColumn(0).setResizable(false);
            tabel_PeringkatProduk.getColumnModel().getColumn(1).setResizable(false);
            tabel_PeringkatProduk.getColumnModel().getColumn(2).setResizable(false);
            tabel_PeringkatProduk.getColumnModel().getColumn(3).setResizable(false);
        }

        tabel_PeringkatProdukTerlaris.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel27.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel27.setText("Peringkat Produk Terlaris");
        tabel_PeringkatProdukTerlaris.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(tabel_PeringkatProdukTerlaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1110, 720, 290));

        panel_catatanPembelianBulanan.setBackground(new java.awt.Color(255, 255, 255));
        panel_catatanPembelianBulanan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_catatanPembelianBulanan.setRoundBottomLeft(10);
        panel_catatanPembelianBulanan.setRoundBottomRight(10);
        panel_catatanPembelianBulanan.setRoundTopLeft(10);
        panel_catatanPembelianBulanan.setRoundTopRight(10);
        panel_catatanPembelianBulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_TransaksiPembelianBulanan.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPembelianBulanan.getTableHeader().setOpaque(false);
        tabel_TransaksiPembelianBulanan.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPembelianBulanan.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPembelianBulanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pemasok", "ID Pengguna", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_TransaksiPembelianBulanan.setRowHeight(30);
        tabel_TransaksiPembelianBulanan.setRowSelectionAllowed(false);
        tabel_TransaksiPembelianBulanan.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPembelianBulanan.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane9.setViewportView(tabel_TransaksiPembelianBulanan);
        if (tabel_TransaksiPembelianBulanan.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPembelianBulanan.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPembelianBulanan.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPembelianBulanan.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPembelianBulanan.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_catatanPembelianBulanan.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel30.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel30.setText("Catatan Pembelian Bulanan");
        panel_catatanPembelianBulanan.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(panel_catatanPembelianBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 770, 720, 290));

        panel_catatanPenjualanaBulanan.setBackground(new java.awt.Color(255, 255, 255));
        panel_catatanPenjualanaBulanan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_catatanPenjualanaBulanan.setRoundBottomLeft(10);
        panel_catatanPenjualanaBulanan.setRoundBottomRight(10);
        panel_catatanPenjualanaBulanan.setRoundTopLeft(10);
        panel_catatanPenjualanaBulanan.setRoundTopRight(10);
        panel_catatanPenjualanaBulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_TransaksiPenjualanBulanan.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPenjualanBulanan.getTableHeader().setOpaque(false);
        tabel_TransaksiPenjualanBulanan.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPenjualanBulanan.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPenjualanBulanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pemasok", "ID Pengguna", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_TransaksiPenjualanBulanan.setRowHeight(30);
        tabel_TransaksiPenjualanBulanan.setRowSelectionAllowed(false);
        tabel_TransaksiPenjualanBulanan.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPenjualanBulanan.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane7.setViewportView(tabel_TransaksiPenjualanBulanan);
        if (tabel_TransaksiPenjualanBulanan.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPenjualanBulanan.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPenjualanBulanan.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPenjualanBulanan.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPenjualanBulanan.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_catatanPenjualanaBulanan.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel28.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel28.setText("Catatan Penjualan Bulanan");
        panel_catatanPenjualanaBulanan.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(panel_catatanPenjualanaBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 720, 290));

        panel_pendapatanHarian6.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 140, 29), new java.awt.Color(255, 140, 29), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian6.setRoundBottomLeft(10);
        panel_pendapatanHarian6.setRoundBottomRight(10);
        panel_pendapatanHarian6.setRoundTopLeft(10);
        panel_pendapatanHarian6.setRoundTopRight(10);
        panel_pendapatanHarian6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoney.png"))); // NOI18N
        panel_pendapatanHarian6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_PendapatanBulanan.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_PendapatanBulanan.setText("Rp0");
        panel_pendapatanHarian6.add(txt_PendapatanBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel10.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel10.setText("Pendapatan Bulanan");
        panel_pendapatanHarian6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 30));

        panel_Bulanan.add(panel_pendapatanHarian6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 340, 110));

        panel_pendapatanHarian7.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(105, 68, 255), new java.awt.Color(105, 68, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian7.setRoundBottomLeft(10);
        panel_pendapatanHarian7.setRoundBottomRight(10);
        panel_pendapatanHarian7.setRoundTopLeft(10);
        panel_pendapatanHarian7.setRoundTopRight(10);
        panel_pendapatanHarian7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneypurple.png"))); // NOI18N
        panel_pendapatanHarian7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_labaHarian1.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_labaHarian1.setText("Rp0");
        panel_pendapatanHarian7.add(txt_labaHarian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel16.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel16.setText("Laba Bulanan");
        panel_pendapatanHarian7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, 30));

        panel_Bulanan.add(panel_pendapatanHarian7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 340, 110));

        panel_pendapatanHarian8.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(54, 255, 74), new java.awt.Color(54, 255, 74), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian8.setRoundBottomLeft(10);
        panel_pendapatanHarian8.setRoundBottomRight(10);
        panel_pendapatanHarian8.setRoundTopLeft(10);
        panel_pendapatanHarian8.setRoundTopRight(10);
        panel_pendapatanHarian8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneygreen.png"))); // NOI18N
        panel_pendapatanHarian8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_pemasukanLainLainperTanggal1.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_pemasukanLainLainperTanggal1.setText("Rp0");
        panel_pendapatanHarian8.add(txt_pemasukanLainLainperTanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel22.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel22.setText("Pemasukan Lain-Lain");
        panel_pendapatanHarian8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 30));

        panel_Bulanan.add(panel_pendapatanHarian8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 340, 110));

        panel_pendapatanHarian9.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 63, 63), new java.awt.Color(255, 63, 63), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian9.setRoundBottomLeft(10);
        panel_pendapatanHarian9.setRoundBottomRight(10);
        panel_pendapatanHarian9.setRoundTopLeft(10);
        panel_pendapatanHarian9.setRoundTopRight(10);
        panel_pendapatanHarian9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneyred.png"))); // NOI18N
        panel_pendapatanHarian9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_pengeluaranmLainLainperTanggal1.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_pengeluaranmLainLainperTanggal1.setText("Rp0");
        panel_pendapatanHarian9.add(txt_pengeluaranmLainLainperTanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel24.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel24.setText("Pengeluaran");
        panel_pendapatanHarian9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 30));

        panel_Bulanan.add(panel_pendapatanHarian9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 340, 110));

        button4.setText("Cari");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        panel_Bulanan.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 50, 20));
        panel_Bulanan.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 110, 30));

        jPanel3.add(panel_Bulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 862, 1800));

        panel_Harian.setBackground(new java.awt.Color(255, 255, 255));
        panel_Harian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(253, 144, 39));
        jLabel5.setText("Pilih Tanggal");
        panel_Harian.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 100, 30));

        txt_TanggalHariIni.setFont(new java.awt.Font("Quicksand", 1, 36)); // NOI18N
        txt_TanggalHariIni.setForeground(new java.awt.Color(102, 102, 102));
        txt_TanggalHariIni.setText("--/--/----");
        txt_TanggalHariIni.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Harian.add(txt_TanggalHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 320, 50));

        jLabel2.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(253, 144, 39));
        jLabel2.setText("LAPORAN HARIAN");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_Harian.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 210, 50));
        panel_Harian.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 120, 30));

        button3.setText("Cari");
        button3.setEffectColor(new java.awt.Color(253, 144, 39));
        button3.setFont(new java.awt.Font("Quicksand", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        panel_Harian.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 50, 30));

        panel_pendapatanHarian5.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian5.setRoundBottomLeft(10);
        panel_pendapatanHarian5.setRoundBottomRight(10);
        panel_pendapatanHarian5.setRoundTopLeft(10);
        panel_pendapatanHarian5.setRoundTopRight(10);
        panel_pendapatanHarian5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_TransaksiPembelianHarian.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPembelianHarian.getTableHeader().setOpaque(false);
        tabel_TransaksiPembelianHarian.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPembelianHarian.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPembelianHarian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pemasok", "ID Pengguna", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_TransaksiPembelianHarian.setRowHeight(30);
        tabel_TransaksiPembelianHarian.setRowSelectionAllowed(false);
        tabel_TransaksiPembelianHarian.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPembelianHarian.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tabel_TransaksiPembelianHarian);
        if (tabel_TransaksiPembelianHarian.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPembelianHarian.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPembelianHarian.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPembelianHarian.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPembelianHarian.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_pendapatanHarian5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel19.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel19.setText("Catatan Transaksi Pembelian");
        panel_pendapatanHarian5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Harian.add(panel_pendapatanHarian5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 720, 290));

        panel_pendapatanHarian4.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian4.setRoundBottomLeft(10);
        panel_pendapatanHarian4.setRoundBottomRight(10);
        panel_pendapatanHarian4.setRoundTopLeft(10);
        panel_pendapatanHarian4.setRoundTopRight(10);
        panel_pendapatanHarian4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel18.setText("Catatan Transaksi Penjualan");
        panel_pendapatanHarian4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        tabel_TransaksiPenjualanHarian.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPenjualanHarian.getTableHeader().setOpaque(false);
        tabel_TransaksiPenjualanHarian.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPenjualanHarian.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPenjualanHarian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Diskon", "Total Harga", "ID Pengguna"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_TransaksiPenjualanHarian.setRowHeight(30);
        tabel_TransaksiPenjualanHarian.setRowSelectionAllowed(false);
        tabel_TransaksiPenjualanHarian.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPenjualanHarian.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tabel_TransaksiPenjualanHarian);
        if (tabel_TransaksiPenjualanHarian.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPenjualanHarian.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPenjualanHarian.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPenjualanHarian.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPenjualanHarian.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_pendapatanHarian4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        panel_Harian.add(panel_pendapatanHarian4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 720, 290));

        panel_pendapatanHarian1.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(105, 68, 255), new java.awt.Color(105, 68, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian1.setRoundBottomLeft(10);
        panel_pendapatanHarian1.setRoundBottomRight(10);
        panel_pendapatanHarian1.setRoundTopLeft(10);
        panel_pendapatanHarian1.setRoundTopRight(10);
        panel_pendapatanHarian1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneypurple.png"))); // NOI18N
        panel_pendapatanHarian1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_labaHarian.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_labaHarian.setText("Rp0");
        panel_pendapatanHarian1.add(txt_labaHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel11.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel11.setText("Laba per Hari");
        panel_pendapatanHarian1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, 30));

        panel_Harian.add(panel_pendapatanHarian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 340, 110));

        panel_pendapatanHarian.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 140, 29), new java.awt.Color(255, 140, 29), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian.setRoundBottomLeft(10);
        panel_pendapatanHarian.setRoundBottomRight(10);
        panel_pendapatanHarian.setRoundTopLeft(10);
        panel_pendapatanHarian.setRoundTopRight(10);
        panel_pendapatanHarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoney.png"))); // NOI18N
        panel_pendapatanHarian.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_PendapatanHarian.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_PendapatanHarian.setText("Rp0");
        panel_pendapatanHarian.add(txt_PendapatanHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel8.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel8.setText("Pendapatan");
        panel_pendapatanHarian.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, 30));

        panel_Harian.add(panel_pendapatanHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 340, 110));

        jPanel3.add(panel_Harian, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 862, 1190));

        bg_harianBulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/bg_laporanHarian.png"))); // NOI18N
        jPanel3.add(bg_harianBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 890, 420));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 990, 960, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/Group 97.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 958, 700));
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1400, 870, 30));

        jScrollPane1.setViewportView(jPanel3);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        ImageIcon ic = new ImageIcon("src/Laporan/bg_laporanBulanan.png");
        bg_harianBulanan.setIcon(ic);
        panel_Bulanan.setVisible(true);
        panel_Harian.setVisible(false);
        loadDataBulainIni();

    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        ImageIcon iic = new ImageIcon("src/Laporan/bg_laporanHarian.png");
        bg_harianBulanan.setIcon(iic);
        panel_Harian.setVisible(true);
        panel_Bulanan.setVisible(false);
        loadDataHariIni();
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        loadDataperTanggal();

    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        
        loadDataPerBulan();
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg_harianBulanan;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel panel_Bulanan;
    private javax.swing.JPanel panel_Harian;
    private Swing.PanelRound panel_PeringkatPemasokTerlaris;
    private Swing.PanelRound panel_catatanPembelianBulanan;
    private Swing.PanelRound panel_catatanPenjualanaBulanan;
    private Swing.PanelRound panel_pendapatanHarian;
    private Swing.PanelRound panel_pendapatanHarian1;
    private Swing.PanelRound panel_pendapatanHarian4;
    private Swing.PanelRound panel_pendapatanHarian5;
    private Swing.PanelRound panel_pendapatanHarian6;
    private Swing.PanelRound panel_pendapatanHarian7;
    private Swing.PanelRound panel_pendapatanHarian8;
    private Swing.PanelRound panel_pendapatanHarian9;
    private javax.swing.JTable tabel_PeringkatPemasok;
    private javax.swing.JTable tabel_PeringkatProduk;
    private Swing.PanelRound tabel_PeringkatProdukTerlaris;
    private javax.swing.JTable tabel_TransaksiPembelianBulanan;
    private javax.swing.JTable tabel_TransaksiPembelianHarian;
    private javax.swing.JTable tabel_TransaksiPenjualanBulanan;
    private javax.swing.JTable tabel_TransaksiPenjualanHarian;
    private javax.swing.JLabel txt_BulanHariIni1;
    private javax.swing.JLabel txt_PendapatanBulanan;
    private javax.swing.JLabel txt_PendapatanHarian;
    private javax.swing.JLabel txt_TanggalHariIni;
    private javax.swing.JLabel txt_labaHarian;
    private javax.swing.JLabel txt_labaHarian1;
    private javax.swing.JLabel txt_pemasukanLainLainperTanggal1;
    private javax.swing.JLabel txt_pengeluaranmLainLainperTanggal1;
    // End of variables declaration//GEN-END:variables
}
