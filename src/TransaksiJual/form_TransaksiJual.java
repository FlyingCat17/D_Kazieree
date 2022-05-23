package TransaksiJual;

import TransaksiBeli.*;
import DataStok.*;
import Beranda.*;
import Main.user;
import db.konekdb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
public class form_TransaksiJual extends javax.swing.JInternalFrame {

    /**
     * Creates new form Beranda
     */
    String id;

    public form_TransaksiJual() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        kolom();
        Tampil_Jam();
    }

    public void kolom() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        jTable1.setModel(model);
    }

    public void otomatis_number() {
        int idk = jTable1.getRowCount();
        if (idk > 0) {
            for (int i = 0; i < idk; i++) {
                String jumlah = (String) jTable1.getValueAt(i, 0);
                int auto_id = Integer.parseInt(jumlah.substring(jumlah.length() - 3)) + 1;
                String no = String.valueOf(auto_id);
                int NomorJual = no.length();
                //MENGATUR jumlah 0
                for (int j = 0; j < 3 - NomorJual; j++) {
                    no = "0" + no;
                }
                id = no;
            }
        } else {
            id = "001";
        }
    }

    public void otomatis() {  //Otomatis id transaksi, jika tanggal ganti kembali ke 1 lagi
        try {
            DateFormat vblnth = new SimpleDateFormat("yyyyMMdd");
            String blnth = vblnth.format(Calendar.getInstance().getTime());

            DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
            String a = hari.format(Calendar.getInstance().getTime());

            String sql = "SELECT MAX(right(id_transaksi,6)) AS Kode_Pinjam "
                    + "FROM tb_jual Where tgl_transaksi like '" + a + "';";
            java.sql.Connection con = (java.sql.Connection) konekdb.GetConnection();
            java.sql.Statement pst = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.last()) {
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    id_trx.setText("TJ/" + blnth + "/" + no);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    public void Tampil_Jam() {
        ActionListener taskPerformer = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                java.util.Date tglsekarang = new java.util.Date();
                SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggal = smpdtfmt.format(tglsekarang);

                tgl_trx.setText(tanggal + " " + jam + ":" + menit + ":" + detik + "");

                int total = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) jTable1.getValueAt(i, 5).toString());
                    total += amount;
                }
                total_tabel.setText("" + total);
                otomatis();
                otomatis_number();

                user usr = new user();
                String nama = usr.getNama();
                kasir.setText(usr.getNama());
            }
        };
        new Timer(1, taskPerformer).start();
    }

    public void search() {
        if (id_prod.getText().equals("")) {
            nama_prod.setText("");
            harga_prod.setText("");
            jumlah_prod.setText("");
        } else {
            try {
                String sql = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.harga_jual, tb_stokbarang.stok_produk "
                        + "FROM tb_produk LEFT JOIN tb_stokbarang ON tb_produk.id_produk = tb_stokbarang.id_produk WHERE tb_produk.id_produk like '" + id_prod.getText() + "'";
                java.sql.Connection conn = (Connection) konekdb.GetConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                if (res.next()) {
                    if (id_prod.getText().equals(res.getString(1))) {
                        nama_prod.setText(res.getString(2));
                        harga_prod.setText(res.getString(3));
                    } else if (id_prod.getText().isEmpty()) {
                        nama_prod.setText("");
                        harga_prod.setText("");
                        jumlah_prod.setText("");
                    } else {
                        nama_prod.setText("");
                        harga_prod.setText("");
                        jumlah_prod.setText("");
                    }
                } else {
                    nama_prod.setText("");
                    harga_prod.setText("");
                    jumlah_prod.setText("");
                }
            } catch (Exception e) {
                nama_prod.setText("");
                harga_prod.setText("");
                jumlah_prod.setText("");
            }
        }
    }

    public void kembalian() {
        if (jLabel3.getText().equals("")) {
            kembalian_harga.setText("");
        } else if (jLabel3.getText().isEmpty()) {
            kembalian_harga.setText("");
        } else if (!(pembayaran.getText().equals(""))) {
            int sub = Integer.parseInt(jLabel3.getText());
            int disk;
            disk = Integer.parseInt(pembayaran.getText());
            int ttl = disk - sub;
            if (!(ttl < 0)) {
                kembalian_harga.setText(Integer.toString(ttl));
            } else {
                kembalian_harga.setText("");
            }
        } else {
            kembalian_harga.setText("");
        }
    }

    public void diskon() {
        int sub = Integer.parseInt(total_tabel.getText());
        int disk;
        if (!(diskon_harga.getText().equals(""))) {
            disk = Integer.parseInt(diskon_harga.getText());
            if (disk == 0) {
                int ttl = sub;
                jLabel3.setText(Integer.toString(ttl));
            } else if (disk > 0) {
                int ttl = sub - disk;
                if (!(ttl < 0)) {
                    jLabel3.setText(Integer.toString(ttl));
                } else {
                    diskon_harga.setText("0");
                    JOptionPane.showMessageDialog(null, "Diskon tidak boleh lebih dari Sub Total!");
                }
            }
        } else if (diskon_harga.getText().equals("")) {
            jLabel3.setText(total_tabel.getText());
        } else {
            jLabel3.setText("");
        }
    }

    public void bayar() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int jumlah_baris = jTable1.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table Masih Kosong!");
        } else {
            if (diskon_harga.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "diskon harus di isi minimal 0");
            } else if (pembayaran.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "total bayar harus di isi minimal 0");
            } else {
                try {
                    String sql = "INSERT INTO `tb_jual`(`id_transaksi`, "
                            + "`tgl_transaksi`,`id_pengguna`, `total_harga`, `total_diskon`, "
                            + "`nominal_bayar`) VALUES ('" + id_trx.getText() + "',"
                            + "'" + tgl_trx.getText() + "', 'USER', '" + jLabel3.getText() + "',"
                            + "'" + diskon_harga.getText() + "', '" + pembayaran.getText() + "')";
                    java.sql.Connection con = (Connection) konekdb.GetConnection();
                    java.sql.PreparedStatement pst = con.prepareStatement(sql);
                    pst.execute();
                    try {
                        for (int i = 0; i < jumlah_baris; i++) {
                            String jumlah = (String) model.getValueAt(i, 4).toString();
                            String harga = (String) model.getValueAt(i, 5).toString();
                            String id_prodk = (String) model.getValueAt(i, 1).toString();
                            String nama_p = (String) model.getValueAt(i, 2).toString();
                            String har = (String) model.getValueAt(i, 3).toString();
                            String sql1 = "INSERT INTO `tb_detailjual`(`id_transaksi`, `id_produk`, `nama_produk`, `harga_jual`, `jumlah_produk`, `total_harga`) VALUES ("
                                    + "'" + id_trx.getText() + "', '" + id_prodk + "', '" + nama_p + "', '" + har + "', '" + jumlah + "', '" + harga + "')";
                            java.sql.PreparedStatement ps1 = con.prepareStatement(sql1);
                            ps1.execute();
                        }
                        for (int i = jumlah_baris - 1; i >= 0; i--) {
                            model.removeRow(i);
                        }

                        //Hapus top
                        id_prod.setText("");
                        nama_prod.setText("");
                        harga_prod.setText("");
                        jumlah_prod.setText("");

                        //Hapus Bottom
                        diskon_harga.setText("");
                        diskon_persen.setText("");
                        jLabel3.setText("");
                        pembayaran.setText("");
                        kembalian_harga.setText("");

                        id_prod.requestFocus();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e);
                }
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

        pilih_prod = new javax.swing.JLabel();
        kembalian_harga = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        id_prod = new javax.swing.JTextField();
        nama_prod = new javax.swing.JLabel();
        harga_prod = new javax.swing.JLabel();
        jumlah_prod = new javax.swing.JTextField();
        diskon_harga = new javax.swing.JTextField();
        diskon_persen = new javax.swing.JTextField();
        pembayaran = new javax.swing.JTextField();
        kasir = new javax.swing.JLabel();
        tgl_trx = new javax.swing.JLabel();
        id_trx = new javax.swing.JLabel();
        total_tabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botom = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilih_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilih_prodMouseClicked(evt);
            }
        });
        getContentPane().add(pilih_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 30, 30));
        getContentPane().add(kembalian_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 170, 30));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 170, 30));

        id_prod.setBorder(null);
        id_prod.setOpaque(false);
        id_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_prodKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                id_prodKeyTyped(evt);
            }
        });
        getContentPane().add(id_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 340, 30));
        getContentPane().add(nama_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 460, 30));
        getContentPane().add(harga_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 150, 30));

        jumlah_prod.setBorder(null);
        jumlah_prod.setOpaque(false);
        jumlah_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlah_prodKeyTyped(evt);
            }
        });
        getContentPane().add(jumlah_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 110, 30));

        diskon_harga.setBorder(null);
        diskon_harga.setOpaque(false);
        diskon_harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                diskon_hargaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                diskon_hargaKeyTyped(evt);
            }
        });
        getContentPane().add(diskon_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 170, 40));

        diskon_persen.setBorder(null);
        diskon_persen.setOpaque(false);
        diskon_persen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                diskon_persenKeyTyped(evt);
            }
        });
        getContentPane().add(diskon_persen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 60, 40));

        pembayaran.setBorder(null);
        pembayaran.setOpaque(false);
        pembayaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pembayaranKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pembayaranKeyTyped(evt);
            }
        });
        getContentPane().add(pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 170, 40));
        getContentPane().add(kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 136, 270, 40));
        getContentPane().add(tgl_trx, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 260, 30));
        getContentPane().add(id_trx, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 136, 290, 40));

        total_tabel.setFont(new java.awt.Font("Quicksand", 0, 40)); // NOI18N
        total_tabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total_tabel.setText("0");
        getContentPane().add(total_tabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 530, 270, 70));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/Total.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 490, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/btn_newtrx.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 629, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/btn_bayar.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 629, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/btn_hapus.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 277, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/btn_tambah.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 277, -1, -1));

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 860, 140));

        botom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/bottom_tjual.png"))); // NOI18N
        getContentPane().add(botom, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/top_tjual.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/Group 87 (2).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            if (!(jTable1.getSelectedRow() < 0)) {
                int jawab = JOptionPane.showOptionDialog(this,
                        "Ingin menghapus tabel yang pilih?",
                        "Keluar",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (jawab == JOptionPane.YES_OPTION) {
                    model.removeRow(jTable1.getSelectedRow());
                    JOptionPane.showMessageDialog(this, "Baris yang dipilih terhapus");

                    //Hapus top
                    id_prod.setText("");
                    nama_prod.setText("");
                    harga_prod.setText("");
                    jumlah_prod.setText("");

                    //Hapus Bottom
                    diskon_harga.setText("");
                    diskon_persen.setText("");
                    jLabel3.setText("");
                    pembayaran.setText("");
                    kembalian_harga.setText("");

                    id_prod.requestFocus();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin dihapus");
            }
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (!(id_prod.getText().equals(""))) {
                if (!(jumlah_prod.getText().equals(""))) {
                    int har = Integer.parseInt(harga_prod.getText());
                    int juml = Integer.parseInt(jumlah_prod.getText());
                    int total = har * juml;
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    Object row[] = {
                        id,
                        id_prod.getText(),
                        nama_prod.getText(),
                        harga_prod.getText(),
                        jumlah_prod.getText(),
                        total
                    };
                    model.addRow(row);

                    //Hapus top
                    id_prod.setText("");
                    nama_prod.setText("");
                    harga_prod.setText("");
                    jumlah_prod.setText("");

                    //Hapus Bottom
                    diskon_harga.setText("");
                    diskon_persen.setText("");
                    jLabel3.setText("");
                    pembayaran.setText("");
                    kembalian_harga.setText("");

                    id_prod.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kode Produk tidak boleh kosong");
            }
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void id_prodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_prodKeyReleased
        // TODO add your handling code here:
        id_prod.setText(id_prod.getText().toUpperCase());
        search();
    }//GEN-LAST:event_id_prodKeyReleased

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Hapus Top
            id_prod.setText("");
            nama_prod.setText("");
            harga_prod.setText("");
            jumlah_prod.setText("");

            //Hapus Bottom
            diskon_harga.setText("");
            diskon_persen.setText("");
            jLabel3.setText("");
            pembayaran.setText("");
            kembalian_harga.setText("");

            //Hapus Tabel
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int baris = jTable1.getRowCount();
            if (!(baris < 0)) {
                for (int i = baris - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
            }
            id_prod.requestFocus();
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void pembayaranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pembayaranKeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_pembayaranKeyReleased

    private void diskon_hargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diskon_hargaKeyReleased
        // TODO add your handling code here:
        diskon();
    }//GEN-LAST:event_diskon_hargaKeyReleased

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            bayar();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void diskon_hargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diskon_hargaKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = diskon_harga.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_diskon_hargaKeyTyped

    private void diskon_persenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diskon_persenKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = diskon_persen.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
        if (panjang > 2) {
            evt.consume();
        }
    }//GEN-LAST:event_diskon_persenKeyTyped

    private void pembayaranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pembayaranKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = pembayaran.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_pembayaranKeyTyped

    private void jumlah_prodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_prodKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = jumlah_prod.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_jumlah_prodKeyTyped

    private void id_prodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_prodKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_id_prodKeyTyped

    private void pilih_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilih_prodMouseClicked
        // TODO add your handling code here:
        if (evt.getButton()==MouseEvent.BUTTON1) {
            
        }
    }//GEN-LAST:event_pilih_prodMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botom;
    private javax.swing.JTextField diskon_harga;
    private javax.swing.JTextField diskon_persen;
    private javax.swing.JLabel harga_prod;
    private javax.swing.JTextField id_prod;
    private javax.swing.JLabel id_trx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlah_prod;
    private javax.swing.JLabel kasir;
    private javax.swing.JLabel kembalian_harga;
    private javax.swing.JLabel nama_prod;
    private javax.swing.JTextField pembayaran;
    private javax.swing.JLabel pilih_prod;
    private javax.swing.JLabel tgl_trx;
    private javax.swing.JLabel total_tabel;
    // End of variables declaration//GEN-END:variables
}
