/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Main.user;
import db.konekdb;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author LenataHoma
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    user usr = new user();

    public LoginPage() {
        initComponents();
        this.setLocationRelativeTo(null);
        txt_password.setOpaque(false);
        txt_username.setOpaque(false);
    }

    public void login() {
        try {
            String sql = "SELECT * FROM `tb_pengguna` WHERE username = '"+txt_username.getText()+"' && kata_sandi = '"+txt_password.getText()+"';";
            Connection con = (Connection) konekdb.GetConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                usr.setNama(rs.getString("username"));
                usr.setHak_akses("hak_akses");
                usr.setId_pengguna("id_pengguna");
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
                } else {
                    JOptionPane.showMessageDialog(this, ". Masuk!!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Username Atau Katasandi Salah!!");
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
        jLabel1 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        btn_masuk = new javax.swing.JLabel();
        password_unreveal = new javax.swing.JLabel();
        Password_reveal = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1153, 707));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 410, 100, 10));

        txt_username.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        txt_username.setBorder(null);
        txt_username.setOpaque(false);
        txt_username.setSelectedTextColor(new java.awt.Color(238, 238, 238));
        txt_username.setSelectionColor(new java.awt.Color(246, 122, 2));
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 280, 300, 40));

        btn_masuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_masuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/btn_masuk.png"))); // NOI18N
        btn_masuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_masuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_masukMouseClicked(evt);
            }
        });
        jPanel2.add(btn_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, -1, 40));

        password_unreveal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/btn_password_unreveal.png"))); // NOI18N
        password_unreveal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        password_unreveal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                password_unrevealMouseClicked(evt);
            }
        });
        jPanel2.add(password_unreveal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 370, -1, -1));

        Password_reveal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/btn_password_reveal.png"))); // NOI18N
        Password_reveal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Password_reveal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Password_revealMouseClicked(evt);
            }
        });
        jPanel2.add(Password_reveal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 370, -1, -1));

        txt_password.setBorder(null);
        txt_password.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_password.setOpaque(false);
        txt_password.setSelectedTextColor(new java.awt.Color(238, 238, 238));
        txt_password.setSelectionColor(new java.awt.Color(246, 122, 2));
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 360, 260, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/loginPage (1).png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 690));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!(txt_username.getText().equals(""))) {
                if (!(txt_password.getText().equals(""))) {
                    login();
                } else {
                    JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong");
            }
        }
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void btn_masukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_masukMouseClicked
        // TODO add your handling code here:
        if (!(txt_username.getText().equals(""))) {
            if (!(txt_password.getText().equals(""))) {
                login();
            } else {
                JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong");
        }
    }//GEN-LAST:event_btn_masukMouseClicked

    private void password_unrevealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password_unrevealMouseClicked
        // TODO add your handling code here:
        Password_reveal.setVisible(true);
        Password_reveal.setEnabled(true);
        
        password_unreveal.setVisible(false);
        password_unreveal.setVisible(false);
        
        txt_password.setEchoChar((char)0);
        txt_password.setFont(new Font("Quicksand Bold", Font.PLAIN, 18));
    }//GEN-LAST:event_password_unrevealMouseClicked

    private void Password_revealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Password_revealMouseClicked
        // TODO add your handling code here:
        Password_reveal.setVisible(false);
        Password_reveal.setEnabled(false);
        
        password_unreveal.setVisible(true);
        password_unreveal.setVisible(true);
        
        
        txt_password.setEchoChar((char) 8226);
        txt_password.setFont(new Font("Arial", Font.BOLD, 18));
    }//GEN-LAST:event_Password_revealMouseClicked

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Password_reveal;
    private javax.swing.JLabel btn_masuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel password_unreveal;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
