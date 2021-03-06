/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Main.user;
import db.konekdb;
import java.awt.Toolkit;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author LenataHoma
 */
public class LoginPageNew extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    user usr = new user();

    public LoginPageNew() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIcon();
        Main.MainFrame nm = new Main.MainFrame();
        nm.setVisible(false);
        jPanel1.setVisible(false);
        jPanel1.setEnabled(false);
    }

    public void login() {
        try {
            String sql = "SELECT * FROM `tb_pengguna` WHERE username = '"+UsernamePengguna.getText()+"' && kata_sandi = '"+KataSandiPengguna.getText()+"';";
            Connection con = (Connection) konekdb.GetConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                usr.setNama(rs.getString("username"));
                usr.setHak_akses(rs.getString("hak_akses"));
                usr.setId_pengguna("id_pengguna");
                usr.setId_pengguna(rs.getString("id_pengguna"));
                if (rs.getString("hak_akses").equals("ADMIN")) {
                    JOptionPane.showMessageDialog(this, "Berhasil Masuk!!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    Main.MainFrame mn = new Main.MainFrame();
                    mn.setVisible(true);
                    this.dispose();
                } else if(rs.getString("hak_akses").equals("KARYAWAN")){
                    JOptionPane.showMessageDialog(this, "Berhasil Masuk!!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    Main.MainFrameKaryawan mn = new Main.MainFrameKaryawan();
                    mn.setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password yang anda masukkan Salah!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Username Atau Katasandi Salah!!");

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

        jPanel2 = new javax.swing.JPanel();
        KataSandiPengguna = new Swing.PasswordField();
        button2 = new Swing.Button();
        button1 = new Swing.Button();
        UsernamePengguna = new Swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelRound1 = new Swing.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_namapengguna = new Swing.TextField();
        button4 = new Swing.Button();
        button3 = new Swing.Button();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1153, 707));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KataSandiPengguna.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        KataSandiPengguna.setLabelText("Kata Sandi");
        KataSandiPengguna.setLineColor(new java.awt.Color(253, 144, 39));
        KataSandiPengguna.setOpaque(false);
        KataSandiPengguna.setShowAndHide(true);
        jPanel2.add(KataSandiPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(688, 340, 320, 50));

        button2.setBackground(new java.awt.Color(238, 238, 238));
        button2.setText("Lupa Kata Sandi?");
        button2.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel2.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 390, 110, -1));

        button1.setBackground(new java.awt.Color(253, 144, 39));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("MASUK");
        button1.setFont(new java.awt.Font("Quicksand", 1, 15)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 440, 100, 40));

        UsernamePengguna.setBackground(new java.awt.Color(238, 238, 238));
        UsernamePengguna.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        UsernamePengguna.setLabelText("Nama Pengguna");
        UsernamePengguna.setLineColor(new java.awt.Color(253, 144, 39));
        UsernamePengguna.setOpaque(false);
        UsernamePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernamePenggunaActionPerformed(evt);
            }
        });
        jPanel2.add(UsernamePengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(688, 270, 320, 50));

        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 100, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/loginPagenoTxtField.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 690));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 680));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(245, 245, 245));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Quicksand SemiBold", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(253, 144, 39));
        jLabel4.setText("LUPA KATA SANDI?");
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 230, 30));

        jLabel5.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel5.setText("Harap Masukkan Nama Pengguna Anda");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 300, -1));

        txt_namapengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_namapengguna.setLabelText("Nama Pengguna");
        txt_namapengguna.setLineColor(new java.awt.Color(253, 144, 39));
        txt_namapengguna.setOpaque(false);
        panelRound1.add(txt_namapengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 120, 370, -1));

        button4.setBackground(new java.awt.Color(245, 245, 245));
        button4.setForeground(new java.awt.Color(255, 0, 0));
        button4.setText("BATAL");
        button4.setEffectColor(new java.awt.Color(255, 51, 51));
        button4.setFont(new java.awt.Font("Quicksand SemiBold", 0, 13)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        panelRound1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 70, 30));

        button3.setBackground(new java.awt.Color(245, 245, 245));
        button3.setForeground(new java.awt.Color(253, 144, 39));
        button3.setText("KIRIM");
        button3.setEffectColor(new java.awt.Color(253, 144, 39));
        button3.setFont(new java.awt.Font("Quicksand SemiBold", 0, 13)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        panelRound1.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 70, 30));

        jPanel1.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 460, 290));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/loginPagenoTxtField (1).png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 690));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        if (!(UsernamePengguna.getText().equals(""))) {
            if (!(KataSandiPengguna.getText().equals(""))) {
                login();
            } else {
                JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong");
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void UsernamePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernamePenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernamePenggunaActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(true);
        button1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel1.setEnabled(true);
    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(false);
        txt_namapengguna.setText("");
        jPanel2.setVisible(true);
        button1.setVisible(true);
        jPanel1.setEnabled(false);
    }//GEN-LAST:event_button4ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        String id_pengguna = null;
        String username = null;
        
        
        if (txt_namapengguna.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nama Pengguna Kosong!");
        } else{
            try {
                String sql = "SELECT * FROM tb_pengguna WHERE username = '" + txt_namapengguna.getText() + "';";
                java.sql.Connection con = (Connection) konekdb.GetConnection();
                java.sql.Statement st = con.createStatement();
                java.sql.ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    id_pengguna = rs.getString("id_pengguna");
                }
                System.out.println("1. " + id_pengguna);
                if (id_pengguna == null) {
                JOptionPane.showMessageDialog(this, "Nama Pengguna yang anda masukkan belum terdaftar");
                } else {
                    try {
                    String sql1 = "UPDATE tb_pengguna SET status = 'LUPA' WHERE id_pengguna = '" + id_pengguna + "';";
                    java.sql.Connection conn = (Connection) konekdb.GetConnection();
                    java.sql.Statement stt = con.createStatement();
                    stt.execute(sql1);
                    JOptionPane.showMessageDialog(this, "Ajuan untuk reset kata sandi telah terkirim, harap tunggu konfirmasi dari Admin", "LUPA KATA SANDI", JOptionPane.INFORMATION_MESSAGE);
                    jPanel1.setVisible(false);
                    System.out.println("2. " + id_pengguna);
                    jPanel1.setEnabled(false);
                    txt_namapengguna.setText("");
                    jPanel2.setVisible(true);
                    button1.setVisible(true);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                }
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Nama Pengguna yang anda masukkan belum terdaftar");
            }
           
        }
    }//GEN-LAST:event_button3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPageNew().setVisible(true);
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("llogo app.png")));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.PasswordField KataSandiPengguna;
    private Swing.TextField UsernamePengguna;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private Swing.PanelRound panelRound1;
    private Swing.TextField txt_namapengguna;
    // End of variables declaration//GEN-END:variables
}
