package TransaksiBeli;

import DataStok.*;
import Beranda.*;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LenataHoma
 */
public class form_TransaksiBeli extends javax.swing.JInternalFrame {

    /**
     * Creates new form Beranda
     */
    public form_TransaksiBeli() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        jTable1.getTableHeader().setFont(new Font("Quicksand", Font.PLAIN, 17));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(254, 149, 46));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.setRowHeight(20);

        tanggal();
        search();
        colom();

    }

    public void colom() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga Beli");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        jTable1.setModel(model);
    }

    public void tanggal() {

        ActionListener taskPerformer = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                java.util.Date tglsekarang = new java.util.Date();
                SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggal = smpdtfmt.format(tglsekarang);

                jTextField3.setText(tanggal);

                int total = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    int amount = Integer.parseInt((String) jTable1.getValueAt(i, 4).toString());
//                    int mount = (Integer)jTable1.getValueAt(i, 4);
                    total += amount;
                }
                jLabel7.setText(String.valueOf(total));
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
                    jTextField1.setText("TB/" + blnth + "/" + no);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    public void search() {
        if (jTextField2.getText().equals("")) {
            jTextField5.setText("");
            jTextField4.setText("");
        } else {
            try {
                String sql = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.harga_beli "
                        + "FROM tb_produk JOIN tb_stokbarang ON tb_produk.id_produk = tb_stokbarang.id_produk";;
                java.sql.Connection conn = (Connection) konekdb.GetConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    if (jTextField2.getText().equals(res.getString(1))) {
                        jTextField5.setText(res.getString(2));
                        jTextField8.setText(res.getString(3));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                jTextField5.setText("");
                jTextField4.setText("");
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
        idPemasok = new javax.swing.JLabel();
        namaPemasok = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 860, 150));

        idPemasok.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        getContentPane().add(idPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 150, 30));

        namaPemasok.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        getContentPane().add(namaPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 150, 30));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(196, 196, 196));
        jTextField1.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField1.setBorder(null);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 280, 30));

        jTextField2.setBackground(new java.awt.Color(196, 196, 196));
        jTextField2.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField2.setBorder(null);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 370, 30));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(196, 196, 196));
        jTextField3.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField3.setBorder(null);
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 250, 30));

        jTextField4.setBackground(new java.awt.Color(196, 196, 196));
        jTextField4.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField4.setBorder(null);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 110, 30));

        jTextField5.setBackground(new java.awt.Color(196, 196, 196));
        jTextField5.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField5.setBorder(null);
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 450, 30));

        jTextField6.setBackground(new java.awt.Color(196, 196, 196));
        jTextField6.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField6.setBorder(null);
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 260, 30));

        jTextField7.setBackground(new java.awt.Color(196, 196, 196));
        jTextField7.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField7.setBorder(null);
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 170, 30));

        jTextField8.setBackground(new java.awt.Color(196, 196, 196));
        jTextField8.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField8.setBorder(null);
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 150, 30));

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(196, 196, 196));
        jTextField9.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField9.setBorder(null);
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 170, 30));

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(196, 196, 196));
        jTextField10.setFont(new java.awt.Font("Quicksand SemiBold", 0, 15)); // NOI18N
        jTextField10.setBorder(null);
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 170, 30));

        jLabel7.setFont(new java.awt.Font("Quicksand SemiBold", 1, 40)); // NOI18N
        jLabel7.setText("200,000");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 170, 80));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.5.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 620, 180, 40));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.0.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 230, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.1.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 120, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.3.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 140, 40));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/2.4.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 630, 90, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/gmbr_beli.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 970, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        search();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int jumlahRow = jTable1.getRowCount();
            if (jTextField2.getText().equals("")) {
                JOptionPane.showMessageDialog(this,"Kode Produk tidak boleh kosong");
            }else if (jTextField5.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Nama Produk tidak boleh kosong");
            }else if (jTextField8.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Kolom Harga tidak boleh kosong");
            }else if (jTextField4.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Jumlah produk tidak boleh kosong ");
            }else{
                  int hargad = Integer.parseInt(jTextField8.getText());
            int juml = Integer.parseInt(jTextField4.getText());
            int total = hargad * juml;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object row[] = {
                jTextField2.getText(),
                jTextField5.getText(),
                jTextField8.getText(),
                jTextField4.getText(),
                total
            };
            model.addRow(row);
            jTextField2.requestFocus();
            jTextField2.setText("");
            jTextField5.setText("");
            jTextField8.setText("");
            jTextField4.setText("");
            }
          

        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) {
            int jikaTidakpilih = jTable1.getSelectedRow();
            if (jikaTidakpilih < 0) {
                JOptionPane.showMessageDialog(rootPane, "Tidak ada baris yang dipilih");

            } else {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(jTable1.getSelectedRow());
            }

        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            this.getDesktopPane().add(new pemasok()).setVisible(true);

        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int jumlhTabel = jTable1.getRowCount();
            if (jumlhTabel == 0) {
                JOptionPane.showMessageDialog(rootPane, "Keranjang tidak boleh kosong");
            } else {
                try {
                    String sql = "INSERT INTO `tb_beli`(`id_transaksi`, `tgl_transaksi`, `id_pengguna`, `id_pemasok`, `total_harga`) VALUES ('"
                            + jTextField1.getText() + "','" + jTextField3.getText() + "','" + jTextField6.getText() + "','" + idPemasok.getText() + "','" + jLabel7.getText() + "')";
                    java.sql.Connection c = (Connection) konekdb.GetConnection();
                    java.sql.PreparedStatement pst = c.prepareStatement(sql);
                    pst.execute();
                    jTextField2.setText("");
                    jTextField5.setText("");
                    jTextField8.setText("");
                    jTextField4.setText("");

                    try {
                        for (int i = 0; i < jumlhTabel; i++) {
                            String idProduk = model.getValueAt(i, 0).toString();
                            String namaProduk = model.getValueAt(i, 1).toString();
                            String jumlahProduk = model.getValueAt(i, 3).toString();
                            String hargaBeli = model.getValueAt(i, 2).toString();
                            String totalHarga = model.getValueAt(i, 4).toString();
                            String squl = "INSERT INTO `tb_detailbeli`(`id_transaksi`, `id_produk`, `nama_produk`, `jumlah_produk`, `harga_beli`, `total_harga`) VALUES "
                                    + "('" + jTextField1.getText() + "','" + idProduk + "','" + namaProduk + "'," + jumlahProduk + "," + hargaBeli + "," + totalHarga + ")";
                            java.sql.PreparedStatement pstt = c.prepareStatement(squl);
                            pstt.execute();
                        }
                        for (int j = jumlhTabel - 1; j >= 0; j--) {
                            model.removeRow(j);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Gagal Tersimpan" + e);
                }

            }

        }

//        DefaultTableModel mengmodel = (DefaultTableModel)jTable1.getModel();
//            if (mengmodel.getRowCount()==0) {
//                JOptionPane.showMessageDialog(this, "Tabelnya Kosong kau orang udik!");
//            }else {
//                try {
//                    for (int i = 0; i <mengmodel.getRowCount(); i++) {
//                        String idProduk = mengmodel.getValueAt(i, 0).toString();
//                        String namaProduk = mengmodel.getValueAt(i, 1).toString();
//                        String jumlahProduk = mengmodel.getValueAt(i, 3).toString();
//                        String hargaBeli = mengmodel.getValueAt(i, 2).toString();
//                        String totalHarga = mengmodel.getValueAt(i, 4).toString();
//                        
//                        String querya = "INSERT INTO `tb_detailbeli`(`id_transaksi`, `id_produk`, `nama_produk`, `jumlah_produk`, `harga_beli`, `total_harga`) "
//                                + "VALUES ('"+jTextField1.getText()+"','"+idProduk+"','"+namaProduk+"',"+jumlahProduk+","+hargaBeli+","+totalHarga+")";
//                        System.out.println(querya);
//                        
//                    }
//                } catch (Exception e) {
//                }
//            }
//        
//        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char k = evt.getKeyChar();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (jTextField4.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int bersihTabel = jTable1.getRowCount();
            for (int j = bersihTabel - 1; j >= 0; j--) {
                model.removeRow(j);

            }
            jTextField2.setText("");
            jTextField5.setText("");
            jTextField8.setText("");
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel idPemasok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    public static javax.swing.JLabel namaPemasok;
    // End of variables declaration//GEN-END:variables
}
