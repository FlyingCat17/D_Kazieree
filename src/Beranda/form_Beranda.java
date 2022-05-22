package Beranda;

import Main.user;
import db.konekdb;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LenataHoma
 */
public class form_Beranda extends javax.swing.JInternalFrame {

    /**
     * Creates new form Beranda
     */
    user use = new user();

    public form_Beranda() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        jam();
        total();
        trx();
        masukan();
    }

    public void jam() {
        ActionListener taskPerformer = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "";
                String nama = use.getNama();

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }

                java.util.Date tglsekarang = new java.util.Date();
                SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggal = smpdtfmt.format(tglsekarang);

                if (nilai_jam >= 01 && nilai_jam <= 10) {
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Pagi.png")));
                    jLabel3.setText("Selamat Pagi, " + nama + " !");
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/pagi_barang.png")));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/pagi_trx.png")));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/pagi_pemasukan.png")));
                } else if (nilai_jam >= 10 && nilai_jam <= 14) {
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Siang.png")));
                    jLabel3.setText("Selamat Siang, " + nama + " !");
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/siang_barang.png")));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/siang_trx.png")));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/siang_pemasukan.png")));
                } else if (nilai_jam >= 14 && nilai_jam <= 17) {
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Sore.png")));
                    jLabel3.setText("Selamat Sore, " + nama + " !");
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/sore_barang.png")));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/sore_trx.png")));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/sore_pemasukan.png")));
                } else if (nilai_jam >= 17 && nilai_jam <= 23) {
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Malam.png")));
                    jLabel3.setText("Selamat Malam, " + nama + " !");
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_barang.png")));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_trx.png")));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_pemasukan.png")));
                } else {
                    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Malam.png")));
                    jLabel3.setText("Selamat Malam, " + nama + " !");
                    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_barang.png")));
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_trx.png")));
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/malam_pemasukan.png")));
                }
            }
        };
        new Timer(1, taskPerformer).start();
    }

    public void total() {
        try {
            String sql = "SELECT COUNT(id_produk) AS jumlah FROM tb_produk;";
            Connection con = (Connection) konekdb.GetConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                jLabel7.setText(rs.getString("jumlah"));
            }
        } catch (Exception e) {
            jLabel7.setText("0");
        }
    }

    public void trx() {
        java.util.Date tglsekarang = new java.util.Date();
        SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String tanggal = smpdtfmt.format(tglsekarang);
        try {
            String sql = "SELECT COUNT(tgl_transaksi) AS transaksi FROM tb_jual Where tgl_transaksi like '" + tanggal + "';";
            Connection con = (Connection) konekdb.GetConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                jLabel8.setText(rs.getString(1));
            }
        } catch (Exception e) {
            jLabel8.setText("0");
        }
    }

    public void masukan() {
        java.util.Date tglsekarang = new java.util.Date();
        SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String tanggal = smpdtfmt.format(tglsekarang);
        try {
            String sql = "SELECT SUM(total_harga) AS total FROM tb_jual Where tgl_transaksi like '" + tanggal + "';";
            Connection con = (Connection) konekdb.GetConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                jLabel9.setText(rs.getString(1));
            } 
        } catch (Exception e) {
            jLabel9.setText("0");
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

        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 130, 90));

        jLabel8.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 130, 90));

        jLabel7.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 386, 130, 90));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/pemasukan.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/trx_hari_ini.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 330, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/total_barang.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Quicksand", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 560, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/Pagi.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 870, 200));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Beranda/form_Beranda.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
