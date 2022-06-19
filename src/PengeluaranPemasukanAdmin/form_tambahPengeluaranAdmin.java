package PengeluaranPemasukanAdmin;


import Main.user;
import db.konekdb;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LenataHoma
 */
public class form_tambahPengeluaranAdmin extends javax.swing.JInternalFrame {
String IDPengeluaran = null;
String IDPengguna = null;
user usr = new user();
    /**
     * Creates new form Beranda
     */
    public form_tambahPengeluaranAdmin() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI)this.getUI();
        gui.setNorthPane(null);
        System.out.println(IDPengeluaran);
        generateIDPemasukan();
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        jDateChooser1.setDate(cal.getTime());
        IDPengguna = usr.getId_pengguna();
        System.out.println("ID PENGGUNA : " + IDPengguna);
    }

    
    
    public void generateIDPemasukan(){
        DateFormat vblnth = new SimpleDateFormat("yyyyMMdd");
        String blnth = vblnth.format(Calendar.getInstance().getTime());
        
        DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
        String a = hari.format(Calendar.getInstance().getTime());
    try {
            String sql = "SELECT MAX(RIGHT(id_pengeluaran,6)) AS kode_unik\n"
                + "FROM tb_pengeluaran WHERE tgl_pengeluaran LIKE '"+a+"'";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                if (rs.last()) {
                    int autoId = rs.getInt(1)+1;
                    String no = String.valueOf(autoId);
                    int noLong = no.length();
                    for (int i = 0; i < 6-noLong; i++) {
                        no = "0"+no;
                    }
                    IDPengeluaran="OUT/"+blnth+"/"+no;
                    System.out.println("ID PENGELUARAN : OUT/"+blnth+"/"+no);
                } 
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
        button2 = new Swing.Button();
        button1 = new Swing.Button();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_nominal = new Swing.TextField();
        txt_keterangan = new Swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(185, 185, 185));
        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
        setOpaque(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(185, 185, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button2.setBackground(new java.awt.Color(255, 51, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("KEMBALI");
        button2.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 90, 30));

        button1.setBackground(new java.awt.Color(0, 255, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("SIMPAN");
        button1.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, 90, 30));

        jDateChooser1.setEnabled(false);
        jDateChooser1.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jDateChooser1.setOpaque(false);
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 210, 40));

        jLabel2.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Tanggal Pengeluaran");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 215, 180, 20));

        txt_nominal.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_nominal.setLabelText("Nominal");
        txt_nominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nominalKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 410, 50));

        txt_keterangan.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_keterangan.setLabelText("Keterangan Pengeluaran");
        jPanel1.add(txt_keterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 410, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengeluaranPemasukanKaryawan/TambahPengeluaranKaryawan.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 700));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 894, 60, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
        // TODO add your handling code here:
        Date date= jDateChooser1.getDate();
        String strdata = DateFormat.getDateInstance().format(date);
//        jLabel3.setText(strdata);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        
//        jLabel3.setText(String.valueOf(sdf.format(jDateChooser1.getDate())));
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance();
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(sdf.format(jDateChooser1.getDate()));
        
        System.out.println("INSERT INTO `tb_pengeluaran`(`id_pengeluaran`, `tgl_pengeluaran`, `id_pengguna`, `keterangan`, `jumlah_pengeluaran`, `cashbox`) "
                + "VALUES ('"+IDPengeluaran+"','"+tanggal+"','"+IDPengguna+"','"+txt_keterangan.getText()+"','"+txt_nominal.getText()+"','Default')");
        try {
            String sqla = "INSERT INTO `tb_pengeluaran`(`id_pengeluaran`, `tgl_pengeluaran`, `keterangan`, `jumlah_pengeluaran`, `cashbox`) "
                    + "VALUES ('"+IDPengeluaran+"','"+tanggal+"','"+txt_keterangan.getText()+"','"+txt_nominal.getText()+"','Default')";
            String sql = "INSERT INTO `tb_pengeluaran`(`id_pengeluaran`, `tgl_pengeluaran`, `id_pengguna`, `keterangan`, `jumlah_pengeluaran`, `cashbox`) "
                + "VALUES ('"+IDPengeluaran+"','"+tanggal+"','"+IDPengguna+"','"+txt_keterangan.getText()+"','"+txt_nominal.getText()+"','Default')";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            st.execute(sql);
            JOptionPane.showMessageDialog(this, "Berhasil Tersimpan");
            jDateChooser1.setDate(cal.getTime());
            txt_keterangan.setText("");
            txt_nominal.setText("");
            generateIDPemasukan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ada Masalah dalam Menyimpan");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_button2ActionPerformed

    private void txt_nominalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nominalKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (txt_nominal.getText().length()>=12){
            evt.consume();
        }
    }//GEN-LAST:event_txt_nominalKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.Button button1;
    private Swing.Button button2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private Swing.TextField txt_keterangan;
    private Swing.TextField txt_nominal;
    // End of variables declaration//GEN-END:variables
}
