package PengeluaranPemasukanAdmin;

import Main.user;
import db.konekdb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LenataHoma
 */
public class form_DataPemasukanAdmin extends javax.swing.JInternalFrame {

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String ad = sdf.format(Calendar.getInstance().getTime());
    DateFormat sdff = new SimpleDateFormat("YYYYMM");
    String ads = sdff.format(Calendar.getInstance().getTime());
    
    String IDPemasukan = null;
    String IDPemasukan2 = null;
    String IDPengguna = null;
    user usr = new user();

    /**
     * Creates new form Beranda
     */
    public form_DataPemasukanAdmin() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        System.out.println(IDPemasukan);
        generateIDPemasukan();
        loadTablePemasukan();
        loadPemasukanHariIni();
        loadPemasukanBulanIni();
//        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
//        jDateChooser1.setDate(cal.getTime());
        IDPengguna = usr.getId_pengguna();
        System.out.println("ID PENGGUNA : " + IDPengguna);
    }

    public void loadTablePemasukan() {
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("ID Pemasukan");
        mdl.addColumn("Tanggal");
        mdl.addColumn("ID Pengguna");
        mdl.addColumn("Keterangan");
        mdl.addColumn("Jumlah");
        try {
            String sql = "SELECT * FROM tb_pemasukan";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mdl.addRow(new Object[]{
                    rs.getString("id_pemasukan"),
                    rs.getString("tgl_pemasukan"),
                    rs.getString("id_pengguna"),
                    rs.getString("keterangan"),
                    rs.getString("jumlah_pemasukan")
                });
                tabel_pemaukan.setModel(mdl);
            }
        } catch (Exception e) {
            System.err.println("ERROR LOAD DATA " + e.getMessage());
        }

    }

    public void generateIDPemasukan() {
        DateFormat vblnth = new SimpleDateFormat("yyyyMMdd");
        String blnth = vblnth.format(Calendar.getInstance().getTime());

        DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
        String a = hari.format(Calendar.getInstance().getTime());
        try {
            String sql = "SELECT MAX(RIGHT(id_pemasukan,6)) AS kode_unik\n"
                    + "FROM tb_pemasukan WHERE tgl_pemasukan LIKE '" + a + "';";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.last()) {
                    int autoId = rs.getInt(1) + 1;
                    String no = String.valueOf(autoId);
                    int noLong = no.length();
                    for (int i = 0; i < 6 - noLong; i++) {
                        no = "0" + no;
                    }
                    IDPemasukan = "IN/" + blnth + "/" + no;
                    System.out.println("ID PEMASUKAN : IN/" + blnth + "/" + no);
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void loadPemasukanHariIni(){
        try {
            String sql = "SELECT SUM(tb_pemasukan.jumlah_pemasukan) AS Jumlah FROM tb_pemasukan\n"
                    + "WHERE tb_pemasukan.tgl_pemasukan = '"+ad+"'\n"
                    + "GROUP BY tb_pemasukan.tgl_pemasukan";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {                
                txt_PemasukanHariIni.setText("Rp"+res.getString("Jumlah"));
            }
        } catch (Exception e) {
            System.err.println("ERROR : "+ e.getMessage());
        }
    }
    
    public void loadPemasukanBulanIni(){
        try {
            String sql = "SELECT SUM(tb_pemasukan.jumlah_pemasukan) AS Jumlah FROM tb_pemasukan\n"
                    + "WHERE EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) = '"+ads+"'\n"
                    + "GROUP BY EXTRACT(YEAR_MONTH FROM tb_pemasukan.tgl_pemasukan) = '"+ads+"'";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                txt_PemasukanHariIni1.setText("Rp"+rs.getString("Jumlah"));
            }
        } catch (Exception e) {
            System.err.println("ERROR "+ e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button4 = new Swing.Button();
        button3 = new Swing.Button();
        button2 = new Swing.Button();
        panel_pendapatanHarian1 = new Swing.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        txt_PemasukanHariIni1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel_pendapatanHarian = new Swing.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        txt_PemasukanHariIni = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tb_produk = new javax.swing.JScrollPane();
        tabel_pemaukan = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }

        };
        button1 = new Swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(185, 185, 185));
        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
        setOpaque(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(185, 185, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button4.setBackground(new java.awt.Color(255, 51, 51));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Hapus");
        button4.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 285, 110, 30));

        button3.setBackground(new java.awt.Color(51, 255, 51));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Segarkan");
        button3.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel1.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, 110, 30));

        button2.setBackground(new java.awt.Color(51, 255, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Tambah");
        button2.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 285, 110, 30));

        panel_pendapatanHarian1.setBackground(new java.awt.Color(255, 255, 255));
        panel_pendapatanHarian1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 140, 29), new java.awt.Color(255, 140, 29), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        panel_pendapatanHarian1.setRoundBottomLeft(10);
        panel_pendapatanHarian1.setRoundBottomRight(10);
        panel_pendapatanHarian1.setRoundTopLeft(10);
        panel_pendapatanHarian1.setRoundTopRight(10);
        panel_pendapatanHarian1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Laporan/iconMoney.png"))); // NOI18N
        panel_pendapatanHarian1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 110));

        txt_PemasukanHariIni1.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_PemasukanHariIni1.setText("Rp0");
        panel_pendapatanHarian1.add(txt_PemasukanHariIni1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, 30));

        jLabel9.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel9.setText("Pemasukan Lain-Lain Bulan Ini");
        panel_pendapatanHarian1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 230, 30));

        jPanel1.add(panel_pendapatanHarian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 410, 110));

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

        txt_PemasukanHariIni.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        txt_PemasukanHariIni.setText("Rp0");
        panel_pendapatanHarian.add(txt_PemasukanHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, 30));

        jLabel8.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        jLabel8.setText("Pemasukan Lain-Lain Hari Ini");
        panel_pendapatanHarian.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 210, 30));

        jPanel1.add(panel_pendapatanHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 400, 110));

        tb_produk.setBorder(null);

        tabel_pemaukan.setFont(new java.awt.Font("Quicksand Medium", 0, 15)); // NOI18N
        tabel_pemaukan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Satuan", "Harga Beli", "Harga Jual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_pemaukan.setRowHeight(40);
        tabel_pemaukan.getTableHeader().setResizingAllowed(false);
        tabel_pemaukan.getTableHeader().setReorderingAllowed(false);
        tabel_pemaukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pemaukanMouseClicked(evt);
            }
        });
        tb_produk.setViewportView(tabel_pemaukan);

        jPanel1.add(tb_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 860, 310));

        button1.setBackground(new java.awt.Color(238, 238, 238));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengeluaranPemasukanKaryawan/ic_round-arrow-back.png"))); // NOI18N
        button1.setEffectColor(new java.awt.Color(247, 125, 6));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 31, 50, 50));

        jLabel2.setFont(new java.awt.Font("Quicksand SemiBold", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 125, 6));
        jLabel2.setText("Data Pemasukan Lain - Lain");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 18, 450, 75));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengeluaranPemasukanAdmin/formPolos.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 700));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String setIDPemasukan(String id_pemasukan){
        this.IDPemasukan2 = id_pemasukan;
        return id_pemasukan;
    }
    public String getIDPemasukan(){
        return this.IDPemasukan2;
    }
    private void tabel_pemaukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pemaukanMouseClicked
        // TODO add your handling code here:
        int baris = tabel_pemaukan.rowAtPoint(evt.getPoint());
        setIDPemasukan(tabel_pemaukan.getValueAt(baris, 0).toString());
//        kategor_jasa = TabelProduk.getValueAt(baris, 2).toString();
//        id_produk = TabelProduk.getValueAt(baris, 0).toString();
//        nama = TabelProduk.getValueAt(baris, 1).toString();
//        hargabeli = TabelProduk.getValueAt(baris, 3).toString();
//        hargajual = TabelProduk.getValueAt(baris, 4).toString();
//        System.out.println("ID PRODUK = "+id_produk);
//        System.out.println("Nama PRODUK = "+nama);
//        System.out.println("Kategori "+ kategor_jasa);
//        System.out.println("Harga Beli "+ hargabeli);
//        System.out.println("Harga Jual "+ hargajual);
        //        lbl_id.setText(id_produk);
        //        int i = TabelProduk.getSelectedRow();
        //        TableModel tm = TabelProduk.getModel();
        //        DataProdukJasa.formUbahJasa mn = new DataProdukJasa.formUbahJasa(this, true);
        //        id_produk= tm.getValueAt(i, 0).toString();
        //        mn.txt_kodeProdukJasa.setText(id_produk);
    }//GEN-LAST:event_tabel_pemaukanMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        this.getDesktopPane().add(new form_tambahPemasukanAdmin()).setVisible(true);
        loadTablePemasukan();
        
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        loadTablePemasukan();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        if (getIDPemasukan()==null) {
            JOptionPane.showMessageDialog(this, "Harap Pilih Salah Satu Pemasukan");
        } else {
            try {
                int jawab = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus Pemasukan ini??");
                switch(jawab){
                    case JOptionPane.YES_OPTION:
                        String sql = "DELETE FROM tb_pemasukan WHERE id_pemasukan = '" + getIDPemasukan() + "';";
                        java.sql.Connection con = (Connection) konekdb.GetConnection();
                        java.sql.Statement st = con.createStatement();
                        st.execute(sql);
                        JOptionPane.showMessageDialog(this, "Berhasil Terhapus");
                        loadTablePemasukan();
                        loadPemasukanBulanIni();
                        loadPemasukanHariIni();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private Swing.PanelRound panel_pendapatanHarian;
    private Swing.PanelRound panel_pendapatanHarian1;
    public javax.swing.JTable tabel_pemaukan;
    private javax.swing.JScrollPane tb_produk;
    private javax.swing.JLabel txt_PemasukanHariIni;
    private javax.swing.JLabel txt_PemasukanHariIni1;
    // End of variables declaration//GEN-END:variables
}
