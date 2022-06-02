/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TransaksiBeli;

import DataStok.*;
import Beranda.*;
import Main.user;
import com.sun.org.apache.bcel.internal.generic.DDIV;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import db.konekdb;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class form_TransaksiBeli extends javax.swing.JInternalFrame {

    /**
     * Creates new form beli_utama
     */
    DefaultTableModel modelkan = new DefaultTableModel();

    public form_TransaksiBeli() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        kode_prodd.requestFocus();
        jTable1.getTableHeader().setFont(new Font("Quicksand", Font.PLAIN, 17));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(254, 149, 46));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.setRowHeight(20);

        tanggal();
        search();
        colom();
        id_kasirr.setText(new user().getNama());
    }

    public void colom() {

        modelkan.addColumn("ID Produk");
        modelkan.addColumn("Nama Produk");
        modelkan.addColumn("Harga Beli");
        modelkan.addColumn("Jumlah");
        modelkan.addColumn("Total Harga");
        jTable1.setModel(modelkan);
    }

    public void tanggal() {

        ActionListener taskPerformer = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                java.util.Date tglsekarang = new java.util.Date();
                SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggal = smpdtfmt.format(tglsekarang);

                tanggal_transaksi.setText(tanggal);

                int total = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) jTable1.getValueAt(i, 4).toString());
//                    int mount = (Integer)jTable1.getValueAt(i, 4);
                    total += amount;
                }
                total_hrg_label.setText(String.valueOf(total));
                total_harga.setText(String.valueOf(total));
                idTransaksi();
            }
        };
        new Timer(1, taskPerformer).start();

    }

    public void idTransaksi() {
        try {
            DateFormat vblnth = new SimpleDateFormat("yyyyMMdd");
            String blnth = vblnth.format(Calendar.getInstance().getTime());

            DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
            String a = hari.format(Calendar.getInstance().getTime());

            String sql = "SELECT MAX(right(id_transaksi,6)) AS Kode_Pinjam "
                    + "FROM tb_beli Where tgl_transaksi like '" + a + "';";
            java.sql.Connection con = (java.sql.Connection) konekdb.GetConnection();
            java.sql.Statement pst = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.last()) {
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    id_trbeli.setText("TB/" + blnth + "/" + no);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    public void search() {
        if (kode_prodd.getText().equals("")) {
            nama_prodd.setText("");
            jmlh_prodd.setText("");
        } else {
            try {
                String sql = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.harga_beli "
                        + "FROM tb_produk JOIN tb_stokbarang ON tb_produk.id_produk = tb_stokbarang.id_produk";;
                java.sql.Connection conn = (Connection) konekdb.GetConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    if (kode_prodd.getText().equals(res.getString(1))) {
                        nama_prodd.setText(res.getString(2));
                        harga_prodd.setText(res.getString(3));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                nama_prodd.setText("");
                jmlh_prodd.setText("");
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tanggal_transaksi = new javax.swing.JTextField();
        id_trbeli = new javax.swing.JTextField();
        id_kasirr = new javax.swing.JTextField();
        kode_prodd = new javax.swing.JTextField();
        nama_prodd = new javax.swing.JTextField();
        harga_prodd = new javax.swing.JTextField();
        jmlh_prodd = new javax.swing.JTextField();
        id_pemasok = new javax.swing.JTextField();
        nama_pemasok = new javax.swing.JTextField();
        total_harga = new javax.swing.JTextField();
        total_hrg_label1 = new javax.swing.JLabel();
        total_hrg_label = new javax.swing.JLabel();
        btn_bayar1 = new javax.swing.JLabel();
        btn_bayar = new javax.swing.JLabel();
        btnPilihPemasok = new javax.swing.JLabel();
        btnhapus_keranjang = new javax.swing.JLabel();
        btntambah_keranjang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(960, 723));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 870, 160));

        tanggal_transaksi.setBackground(new java.awt.Color(196, 196, 196));
        tanggal_transaksi.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        tanggal_transaksi.setBorder(null);
        tanggal_transaksi.setEnabled(false);
        getContentPane().add(tanggal_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 250, 30));

        id_trbeli.setBackground(new java.awt.Color(196, 196, 196));
        id_trbeli.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        id_trbeli.setBorder(null);
        id_trbeli.setEnabled(false);
        id_trbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_trbeliActionPerformed(evt);
            }
        });
        getContentPane().add(id_trbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 280, 30));

        id_kasirr.setBackground(new java.awt.Color(196, 196, 196));
        id_kasirr.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        id_kasirr.setBorder(null);
        id_kasirr.setEnabled(false);
        getContentPane().add(id_kasirr, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 260, 30));

        kode_prodd.setBackground(new java.awt.Color(196, 196, 196));
        kode_prodd.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        kode_prodd.setBorder(null);
        kode_prodd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_proddActionPerformed(evt);
            }
        });
        kode_prodd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_proddKeyReleased(evt);
            }
        });
        getContentPane().add(kode_prodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 370, 30));

        nama_prodd.setBackground(new java.awt.Color(196, 196, 196));
        nama_prodd.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        nama_prodd.setBorder(null);
        nama_prodd.setEnabled(false);
        getContentPane().add(nama_prodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 450, 30));

        harga_prodd.setBackground(new java.awt.Color(196, 196, 196));
        harga_prodd.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        harga_prodd.setBorder(null);
        harga_prodd.setEnabled(false);
        getContentPane().add(harga_prodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 150, 30));

        jmlh_prodd.setBackground(new java.awt.Color(196, 196, 196));
        jmlh_prodd.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jmlh_prodd.setBorder(null);
        jmlh_prodd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jmlh_proddKeyTyped(evt);
            }
        });
        getContentPane().add(jmlh_prodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 110, 30));

        id_pemasok.setBackground(new java.awt.Color(196, 196, 196));
        id_pemasok.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        id_pemasok.setBorder(null);
        getContentPane().add(id_pemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 170, 30));

        nama_pemasok.setBackground(new java.awt.Color(196, 196, 196));
        nama_pemasok.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        nama_pemasok.setBorder(null);
        getContentPane().add(nama_pemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 580, 170, 30));

        total_harga.setEditable(false);
        total_harga.setBackground(new java.awt.Color(196, 196, 196));
        total_harga.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        total_harga.setBorder(null);
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 170, 30));

        total_hrg_label1.setFont(new java.awt.Font("Quicksand SemiBold", 0, 40)); // NOI18N
        total_hrg_label1.setText("RP");
        getContentPane().add(total_hrg_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 520, 60, 60));

        total_hrg_label.setFont(new java.awt.Font("Quicksand SemiBold", 0, 40)); // NOI18N
        total_hrg_label.setText("999,999");
        getContentPane().add(total_hrg_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, 150, 60));

        btn_bayar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_bayar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.5.png"))); // NOI18N
        btn_bayar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_bayar1MouseClicked(evt);
            }
        });
        getContentPane().add(btn_bayar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, 180, 40));

        btn_bayar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.4.png"))); // NOI18N
        btn_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_bayarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 620, 90, 40));

        btnPilihPemasok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPilihPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.3.png"))); // NOI18N
        btnPilihPemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPilihPemasokMouseClicked(evt);
            }
        });
        getContentPane().add(btnPilihPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 140, 40));

        btnhapus_keranjang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnhapus_keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.1.png"))); // NOI18N
        btnhapus_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhapus_keranjangMouseClicked(evt);
            }
        });
        getContentPane().add(btnhapus_keranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 120, 40));

        btntambah_keranjang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btntambah_keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.0.png"))); // NOI18N
        btntambah_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntambah_keranjangMouseClicked(evt);
            }
        });
        getContentPane().add(btntambah_keranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 230, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/form_beliUtama.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -60, 1020, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kode_proddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_proddKeyReleased
        search();
    }//GEN-LAST:event_kode_proddKeyReleased

    private void btntambah_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambah_keranjangMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int jumlahRow = jTable1.getRowCount();
            if (kode_prodd.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Kode Produk tidak boleh kosong");
            } else if (nama_prodd.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Nama Produk tidak boleh kosong");
            } else if (harga_prodd.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Kolom Harga tidak boleh kosong");
            } else if (jmlh_prodd.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Jumlah produk tidak boleh kosong ");
            } else {
                int hargad = Integer.parseInt(harga_prodd.getText());
                int juml = Integer.parseInt(jmlh_prodd.getText());
                int total = hargad * juml;

                jTable1.getModel();
                Object row[] = {
                    kode_prodd.getText(),
                    nama_prodd.getText(),
                    harga_prodd.getText(),
                    jmlh_prodd.getText(),
                    total
                };
                modelkan.addRow(row);
                kode_prodd.requestFocus();
                kode_prodd.setText("");
                nama_prodd.setText("");
                harga_prodd.setText("");
                jmlh_prodd.setText("");
            }

        }
    }//GEN-LAST:event_btntambah_keranjangMouseClicked

    private void btnhapus_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapus_keranjangMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int jikaTidakpilih = jTable1.getSelectedRow();
            if (jikaTidakpilih < 0) {
                JOptionPane.showMessageDialog(rootPane, "Tidak ada baris yang dipilih");

            } else {

                jTable1.getModel();
                modelkan.removeRow(jTable1.getSelectedRow());
            }

        }
    }//GEN-LAST:event_btnhapus_keranjangMouseClicked

    private void btnPilihPemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihPemasokMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {

            this.getDesktopPane().add(new pemasok()).setVisible(true);

        }
    }//GEN-LAST:event_btnPilihPemasokMouseClicked

    private void btn_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bayarMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {

            jTable1.getModel();
            int tanya = JOptionPane.showOptionDialog(this,
                    "Ingin Menyelesaikan Transaki?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            int jumlhTabel = jTable1.getRowCount();
            if (jumlhTabel == 0) {
                JOptionPane.showMessageDialog(rootPane, "Keranjang tidak boleh kosong");
            }
            if (id_pemasok.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Pemasok tidak boleh kosong");

            } else if (tanya == JOptionPane.YES_OPTION) {

                try {
                    String sql = "INSERT INTO `tb_beli`(`id_transaksi`, `tgl_transaksi`, `id_pengguna`, `id_pemasok`, `total_harga`) VALUES ('"
                            + id_trbeli.getText() + "','" + tanggal_transaksi.getText() + "','" + id_kasirr.getText() + "','" + id_pemasok.getText() + "','" + total_hrg_label.getText() + "')";
                    java.sql.Connection c = (Connection) konekdb.GetConnection();
                    java.sql.PreparedStatement pst = c.prepareStatement(sql);
                    pst.execute();
                    kode_prodd.setText("");
                    nama_prodd.setText("");
                    harga_prodd.setText("");
                    jmlh_prodd.setText("");

                    try {
                        for (int i = 0; i < jumlhTabel; i++) {
                            String idProduk = modelkan.getValueAt(i, 0).toString();
                            String namaProduk = modelkan.getValueAt(i, 1).toString();
                            String jumlahProduk = modelkan.getValueAt(i, 3).toString();
                            String hargaBeli = modelkan.getValueAt(i, 2).toString();
                            String totalHarga = modelkan.getValueAt(i, 4).toString();
                            String squl = "INSERT INTO `tb_detailbeli`(`id_transaksi`, `id_produk`, `nama_produk`, `jumlah_produk`, `harga_beli`, `total_harga`) VALUES "
                                    + "('" + id_trbeli.getText() + "','" + idProduk + "','" + namaProduk + "'," + jumlahProduk + "," + hargaBeli + "," + totalHarga + ")";
                            java.sql.PreparedStatement pstt = c.prepareStatement(squl);
                            pstt.execute();
                        }
                        for (int j = jumlhTabel - 1; j >= 0; j--) {
                            modelkan.removeRow(j);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Gagal Tersimpan" + e);
                }

            }

        }
    }//GEN-LAST:event_btn_bayarMouseClicked

    private void jmlh_proddKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jmlh_proddKeyTyped
        char k = evt.getKeyChar();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (jmlh_prodd.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_jmlh_proddKeyTyped

    private void btn_bayar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bayar1MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {

            jTable1.getModel();
            int bersihTabel = jTable1.getRowCount();
            for (int j = bersihTabel - 1; j >= 0; j--) {
                modelkan.removeRow(j);

            }
            kode_prodd.setText("");
            nama_prodd.setText("");
            harga_prodd.setText("");
            jmlh_prodd.setText("");
        }
    }//GEN-LAST:event_btn_bayar1MouseClicked

    private void id_trbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_trbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_trbeliActionPerformed

    private void kode_proddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_proddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_proddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnPilihPemasok;
    private javax.swing.JLabel btn_bayar;
    private javax.swing.JLabel btn_bayar1;
    private javax.swing.JLabel btnhapus_keranjang;
    private javax.swing.JLabel btntambah_keranjang;
    private javax.swing.JTextField harga_prodd;
    private javax.swing.JTextField id_kasirr;
    public static javax.swing.JTextField id_pemasok;
    private javax.swing.JTextField id_trbeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jmlh_prodd;
    private javax.swing.JTextField kode_prodd;
    public static javax.swing.JTextField nama_pemasok;
    private javax.swing.JTextField nama_prodd;
    private javax.swing.JTextField tanggal_transaksi;
    private javax.swing.JTextField total_harga;
    private javax.swing.JLabel total_hrg_label;
    private javax.swing.JLabel total_hrg_label1;
    // End of variables declaration//GEN-END:variables
}
