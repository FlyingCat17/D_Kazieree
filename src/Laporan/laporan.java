/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laporan;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import db.konekdb;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fathan
 */
public class laporan extends javax.swing.JInternalFrame {
DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
DateFormat harii = new SimpleDateFormat("dd-MM-yyyy");
String a = hari.format(Calendar.getInstance().getTime());
String aa = harii.format(Calendar.getInstance().getTime());
DateFormat Bulanan = new SimpleDateFormat("yyyyMM");
String AmbilBulanSekarang = Bulanan.format(Calendar.getInstance().getTime());

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
        
    }
//    private void loadTable(){
//        DefaultTableModel table2 = new DefaultTableModel();
//        table2.addColumn("Tanggal Transaksi");
//        table2.addColumn("Jumlah Laba");
//        try {
//            String sql = "SELECT tb_transjual.tgl_transaksi,tb_detail_transjual.id_barang, SUM((tb_barang.harga_jual - tb_barang.harga_dasar) * tb_detail_transjual.jumlah) AS laba\n"
//                    + "FROM tb_detail_transjual\n"
//                    + "JOIN tb_transjual\n"
//                    + "ON tb_transjual.id_transjual = tb_detail_transjual.id_transjual\n"
//                    + "JOIN tb_barang\n"
//                    + "ON tb_barang.id_barang = tb_detail_transjual.id_barang\n"
//                    + "GROUP BY tb_transjual.tgl_transaksi;";
//            java.sql.Connection conn = (Connection)konekdb.GetConnection();
//            java.sql.Statement stm = conn.createStatement();
//            java.sql.ResultSet rs = stm.executeQuery(sql);
//            while (rs.next()) {                
//                table2.addRow(new Object[]{
//                    rs.getString("tgl_transaksi"),
//                    rs.getString("laba")
//                });
//                
//            jTable1.setModel(table2);
//            }
//        } catch (Exception e) {
//        }
//    }
    public void loadDataHariIni(){
        DefaultTableModel transjual = new DefaultTableModel();
        transjual.addColumn("ID Transaksi");
        transjual.addColumn("Diskon");
        transjual.addColumn("Total Harga");
        transjual.addColumn("Kasir/Pengguna");
        DefaultTableModel transbeli = new DefaultTableModel();
        transbeli.addColumn("ID Transaksi");
        transbeli.addColumn("ID Pemasok");
        transbeli.addColumn("Total Harga");
        
        txt_TanggalHariIni.setText(aa);
        try {
            String loadPendapatandanLaba = "SELECT SUM(tb_jual.total_harga) AS Pendapatan, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk = tb_produk.id_produk\n"
                    + "WHERE tb_jual.tgl_transaksi = '"+a+"'\n"
                    + "GROUP BY tb_jual.id_transaksi";
            String loadTransaksiJualHariIni = "SELECT tb_jual.id_transaksi, tb_jual.total_diskon, "
                    + "tb_jual.total_harga, tb_jual.id_pengguna\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '"+a+"'";
            String loadPendapatanHarian = "SELECT SUM(tb_jual.total_harga) AS PENDAPATAN\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '"+a+"'";
            String loadTransaksiBeliHariIni = "SELECT tb_beli.id_transaksi, tb_beli.id_pemasok, tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE tb_beli.tgl_transaksi = '"+a+"'";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.Statement st2 = con.createStatement();
            java.sql.Statement st3 = con.createStatement();
            java.sql.ResultSet res = st.executeQuery(loadPendapatandanLaba);
            java.sql.ResultSet res1 = st1.executeQuery(loadTransaksiJualHariIni);
            java.sql.ResultSet res2 = st2.executeQuery(loadPendapatanHarian);
            java.sql.ResultSet res3 = st3.executeQuery(loadTransaksiBeliHariIni);
            if (res.next()) {
                txt_labaHarian.setText("Rp"+res.getString("Laba"));
            }
            while (res2.next()) {                
                txt_PendapatanHarian.setText("Rp"+res2.getString("PENDAPATAN").toString());
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
    public void loadDataperTanggal(){
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
                    + "WHERE tb_jual.tgl_transaksi = '"+date+"'\n"
                    + "GROUP BY tb_jual.id_transaksi";
            String loadTransaksiJualHariIni = "SELECT tb_jual.id_transaksi, tb_jual.total_diskon, "
                    + "tb_jual.total_harga, tb_jual.id_pengguna\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '"+date+"'";
            String loadPendapatanHarian = "SELECT SUM(tb_jual.total_harga) AS PENDAPATAN\n"
                    + "FROM tb_jual\n"
                    + "WHERE tb_jual.tgl_transaksi = '"+date+"'";
            String loadTransaksiBeliHariIni = "SELECT tb_beli.id_transaksi, tb_beli.id_pemasok, tb_beli.total_harga\n"
                    + "FROM tb_beli\n"
                    + "WHERE tb_beli.tgl_transaksi = '"+date+"'";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.Statement st2 = con.createStatement();
            java.sql.Statement st3 = con.createStatement();
            java.sql.ResultSet res = st.executeQuery(loadPendapatandanLaba);
            java.sql.ResultSet res1 = st1.executeQuery(loadTransaksiJualHariIni);
            java.sql.ResultSet res2 = st2.executeQuery(loadPendapatanHarian);
            java.sql.ResultSet res3 = st3.executeQuery(loadTransaksiBeliHariIni);
            if (res.next()) {
                txt_labaHarian.setText("Rp"+res.getString("Laba"));
            }
            while (res2.next()) {                
                txt_PendapatanHarian.setText("Rp"+res2.getString("PENDAPATAN").toString());
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
            txt_pemasukanLainLainperTanggal.setText("Rp0");
            txt_pengeluaranmLainLainperTanggal.setText("Rp0");
            tabel_TransaksiPembelianHarian.setModel(transbeli);
            tabel_TransaksiPenjualanHarian.setModel(transjual); 
        }
    }
    public void loadDataBulainIni(){
        txt_BulanHariIni1.setText(AmbilBulanSekarang);
        try {
            String PendapatanBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM(tb_jual.total_harga) AS Pendapatan\n"
                    + "FROM tb_jual\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '"+AmbilBulanSekarang+"'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            String LabaBulanIni = "SELECT EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) AS year_and_month, SUM((tb_produk.harga_jual - tb_produk.harga_beli)*tb_detailjual.jumlah_produk) AS Laba\n"
                    + "FROM tb_jual\n"
                    + "JOIN tb_detailjual\n"
                    + "ON tb_jual.id_transaksi = tb_detailjual.id_transaksi\n"
                    + "JOIN tb_produk\n"
                    + "ON tb_detailjual.id_produk=tb_produk.id_produk\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi) = '"+AmbilBulanSekarang+"'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_jual.tgl_transaksi)";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.Statement st1 = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(PendapatanBulanIni);
            java.sql.ResultSet rs1 = st1.executeQuery(LabaBulanIni);
            if (rs.next()) {
                txt_PendapatanBulanan.setText("Rp"+rs.getString(2));
            }
            if (rs1.next()) {
                txt_labaHarian1.setText("Rp"+rs1.getString(2));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        panel_pendapatanHarian12 = new Swing.PanelRound();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabel_TransaksiPembelianHarian2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel27 = new javax.swing.JLabel();
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
        panel_pendapatanHarian10 = new Swing.PanelRound();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_TransaksiPenjualanHarian1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        panel_pendapatanHarian11 = new Swing.PanelRound();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabel_TransaksiPembelianHarian1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel26 = new javax.swing.JLabel();
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
        panel_pendapatanHarian3 = new Swing.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        txt_pengeluaranmLainLainperTanggal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panel_pendapatanHarian2 = new Swing.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        txt_pemasukanLainLainperTanggal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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

        jMonthChooser1.setBackground(new java.awt.Color(253, 144, 39));
        jMonthChooser1.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        panel_Bulanan.add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, -1, 30));

        panel_pendapatanHarian12.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian12.setRoundBottomLeft(10);
        panel_pendapatanHarian12.setRoundBottomRight(10);
        panel_pendapatanHarian12.setRoundTopLeft(10);
        panel_pendapatanHarian12.setRoundTopRight(10);
        panel_pendapatanHarian12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_TransaksiPembelianHarian.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPembelianHarian.getTableHeader().setOpaque(false);
        tabel_TransaksiPembelianHarian.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPembelianHarian.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPembelianHarian2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_TransaksiPembelianHarian2.setRowHeight(30);
        tabel_TransaksiPembelianHarian2.setRowSelectionAllowed(false);
        tabel_TransaksiPembelianHarian2.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPembelianHarian2.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(tabel_TransaksiPembelianHarian2);
        if (tabel_TransaksiPembelianHarian2.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPembelianHarian2.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPembelianHarian2.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPembelianHarian2.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPembelianHarian2.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_pendapatanHarian12.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel27.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel27.setText("Produk Paling Banyak Dibeli");
        panel_pendapatanHarian12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(panel_pendapatanHarian12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1100, 720, 290));

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

        panel_pendapatanHarian10.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian10.setRoundBottomLeft(10);
        panel_pendapatanHarian10.setRoundBottomRight(10);
        panel_pendapatanHarian10.setRoundTopLeft(10);
        panel_pendapatanHarian10.setRoundTopRight(10);
        panel_pendapatanHarian10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel25.setText("Catatan Transaksi Penjualan");
        panel_pendapatanHarian10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        tabel_TransaksiPenjualanHarian.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPenjualanHarian.getTableHeader().setOpaque(false);
        tabel_TransaksiPenjualanHarian.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPenjualanHarian.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPenjualanHarian1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_TransaksiPenjualanHarian1.setRowHeight(30);
        tabel_TransaksiPenjualanHarian1.setRowSelectionAllowed(false);
        tabel_TransaksiPenjualanHarian1.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPenjualanHarian1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabel_TransaksiPenjualanHarian1);
        if (tabel_TransaksiPenjualanHarian1.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPenjualanHarian1.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPenjualanHarian1.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPenjualanHarian1.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPenjualanHarian1.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_pendapatanHarian10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        panel_Bulanan.add(panel_pendapatanHarian10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 720, 290));

        panel_pendapatanHarian11.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(253, 144, 39), new java.awt.Color(253, 144, 39), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian11.setRoundBottomLeft(10);
        panel_pendapatanHarian11.setRoundBottomRight(10);
        panel_pendapatanHarian11.setRoundTopLeft(10);
        panel_pendapatanHarian11.setRoundTopRight(10);
        panel_pendapatanHarian11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_TransaksiPembelianHarian.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        tabel_TransaksiPembelianHarian.getTableHeader().setOpaque(false);
        tabel_TransaksiPembelianHarian.getTableHeader().setBackground(new Color(255,144,39));
        tabel_TransaksiPembelianHarian.getTableHeader().setForeground(new Color(255,255,255));
        tabel_TransaksiPembelianHarian1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_TransaksiPembelianHarian1.setRowHeight(30);
        tabel_TransaksiPembelianHarian1.setRowSelectionAllowed(false);
        tabel_TransaksiPembelianHarian1.setSelectionBackground(new java.awt.Color(253, 144, 39));
        tabel_TransaksiPembelianHarian1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(tabel_TransaksiPembelianHarian1);
        if (tabel_TransaksiPembelianHarian1.getColumnModel().getColumnCount() > 0) {
            tabel_TransaksiPembelianHarian1.getColumnModel().getColumn(0).setResizable(false);
            tabel_TransaksiPembelianHarian1.getColumnModel().getColumn(1).setResizable(false);
            tabel_TransaksiPembelianHarian1.getColumnModel().getColumn(2).setResizable(false);
            tabel_TransaksiPembelianHarian1.getColumnModel().getColumn(3).setResizable(false);
        }

        panel_pendapatanHarian11.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 200));

        jLabel26.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel26.setText("Catatan Transaksi Pembelian");
        panel_pendapatanHarian11.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, 30));

        panel_Bulanan.add(panel_pendapatanHarian11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 770, 720, 290));

        jPanel3.add(panel_Bulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 862, 1460));

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

        panel_Harian.add(panel_pendapatanHarian5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 770, 720, 290));

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

        panel_Harian.add(panel_pendapatanHarian4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 720, 290));

        panel_pendapatanHarian3.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 63, 63), new java.awt.Color(255, 63, 63), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian3.setRoundBottomLeft(10);
        panel_pendapatanHarian3.setRoundBottomRight(10);
        panel_pendapatanHarian3.setRoundTopLeft(10);
        panel_pendapatanHarian3.setRoundTopRight(10);
        panel_pendapatanHarian3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneyred.png"))); // NOI18N
        panel_pendapatanHarian3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_pengeluaranmLainLainperTanggal.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_pengeluaranmLainLainperTanggal.setText("Rp0");
        panel_pendapatanHarian3.add(txt_pengeluaranmLainLainperTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel17.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel17.setText("Pengeluaran");
        panel_pendapatanHarian3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 30));

        panel_Harian.add(panel_pendapatanHarian3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 340, 110));

        panel_pendapatanHarian2.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(54, 255, 74), new java.awt.Color(54, 255, 74), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian2.setRoundBottomLeft(10);
        panel_pendapatanHarian2.setRoundBottomRight(10);
        panel_pendapatanHarian2.setRoundTopLeft(10);
        panel_pendapatanHarian2.setRoundTopRight(10);
        panel_pendapatanHarian2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoneygreen.png"))); // NOI18N
        panel_pendapatanHarian2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_pemasukanLainLainperTanggal.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_pemasukanLainLainperTanggal.setText("Rp0");
        panel_pendapatanHarian2.add(txt_pemasukanLainLainperTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel14.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel14.setText("Pemasukan Lain-Lain");
        panel_pendapatanHarian2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 30));

        panel_Harian.add(panel_pendapatanHarian2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 340, 110));

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
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        loadDataperTanggal();   
        
    }//GEN-LAST:event_button3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg_harianBulanan;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel panel_Bulanan;
    private javax.swing.JPanel panel_Harian;
    private Swing.PanelRound panel_pendapatanHarian;
    private Swing.PanelRound panel_pendapatanHarian1;
    private Swing.PanelRound panel_pendapatanHarian10;
    private Swing.PanelRound panel_pendapatanHarian11;
    private Swing.PanelRound panel_pendapatanHarian12;
    private Swing.PanelRound panel_pendapatanHarian2;
    private Swing.PanelRound panel_pendapatanHarian3;
    private Swing.PanelRound panel_pendapatanHarian4;
    private Swing.PanelRound panel_pendapatanHarian5;
    private Swing.PanelRound panel_pendapatanHarian6;
    private Swing.PanelRound panel_pendapatanHarian7;
    private Swing.PanelRound panel_pendapatanHarian8;
    private Swing.PanelRound panel_pendapatanHarian9;
    private javax.swing.JTable tabel_TransaksiPembelianHarian;
    private javax.swing.JTable tabel_TransaksiPembelianHarian1;
    private javax.swing.JTable tabel_TransaksiPembelianHarian2;
    private javax.swing.JTable tabel_TransaksiPenjualanHarian;
    private javax.swing.JTable tabel_TransaksiPenjualanHarian1;
    private javax.swing.JLabel txt_BulanHariIni1;
    private javax.swing.JLabel txt_PendapatanBulanan;
    private javax.swing.JLabel txt_PendapatanHarian;
    private javax.swing.JLabel txt_TanggalHariIni;
    private javax.swing.JLabel txt_labaHarian;
    private javax.swing.JLabel txt_labaHarian1;
    private javax.swing.JLabel txt_pemasukanLainLainperTanggal;
    private javax.swing.JLabel txt_pemasukanLainLainperTanggal1;
    private javax.swing.JLabel txt_pengeluaranmLainLainperTanggal;
    private javax.swing.JLabel txt_pengeluaranmLainLainperTanggal1;
    // End of variables declaration//GEN-END:variables
}
