/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Login.LoginPage;
import Login.LoginPageNew;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author LenataHoma
 */
public class MainFrameKaryawan extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrameKaryawan() {
        initComponents();
        this.setLocationRelativeTo(null);
        dpanee.removeAll();
        setIcon();
        Beranda.form_Beranda home = new Beranda.form_Beranda();
        dpanee.add(home);
        home.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new Swing.Button();
        btn_pengaturan = new javax.swing.JLabel();
        btn_dataPemasok = new javax.swing.JLabel();
        btn_pemasukan = new javax.swing.JLabel();
        btn_pengeluaran = new javax.swing.JLabel();
        btn_transJual = new javax.swing.JLabel();
        btn_TransaBeli = new javax.swing.JLabel();
        btn_dataProduk = new javax.swing.JLabel();
        btn_beranda = new javax.swing.JLabel();
        sidebarr = new javax.swing.JLabel();
        dpanee = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("D'Kazieree (KARYAWAN)");
        setMinimumSize(new java.awt.Dimension(1233, 707));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button1.setBackground(new java.awt.Color(255, 152, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/ic_baseline-log-out (1).png"))); // NOI18N
        button1.setText(" Keluar");
        button1.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 80, 40));

        btn_pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengaturanMouseClicked(evt);
            }
        });
        getContentPane().add(btn_pengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 240, 50));

        btn_dataPemasok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_dataPemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataPemasokMouseClicked(evt);
            }
        });
        getContentPane().add(btn_dataPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 240, 50));

        btn_pemasukan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pemasukanMouseClicked(evt);
            }
        });
        getContentPane().add(btn_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 240, 50));

        btn_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMouseClicked(evt);
            }
        });
        getContentPane().add(btn_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 396, 240, 50));

        btn_transJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_transJualMouseClicked(evt);
            }
        });
        getContentPane().add(btn_transJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 240, 50));

        btn_TransaBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_TransaBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TransaBeliMouseClicked(evt);
            }
        });
        getContentPane().add(btn_TransaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 265, 240, 50));

        btn_dataProduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_dataProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataProdukMouseClicked(evt);
            }
        });
        getContentPane().add(btn_dataProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 240, 50));

        btn_beranda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_beranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_berandaMouseClicked(evt);
            }
        });
        getContentPane().add(btn_beranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 240, 50));

        sidebarr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sidebar/Karyawan_Beranda.png"))); // NOI18N
        getContentPane().add(sidebarr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 710));

        javax.swing.GroupLayout dpaneeLayout = new javax.swing.GroupLayout(dpanee);
        dpanee.setLayout(dpaneeLayout);
        dpaneeLayout.setHorizontalGroup(
            dpaneeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        dpaneeLayout.setVerticalGroup(
            dpaneeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );

        getContentPane().add(dpanee, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 950, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_berandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseClicked
        // TODO add your handling code here:
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_Beranda.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        Beranda.form_Beranda home = new Beranda.form_Beranda();
        dpanee.add(home);
        home.setVisible(true);
        
        
        
    }//GEN-LAST:event_btn_berandaMouseClicked

    private void btn_dataProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataProdukMouseClicked
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_DataProduk.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        DataProduk.form_DataProduk home = new DataProduk.form_DataProduk();
        dpanee.add(home);
        home.setVisible(true);
    }//GEN-LAST:event_btn_dataProdukMouseClicked

    private void btn_TransaBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TransaBeliMouseClicked
        // TODO add your handling code here:
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_TransaksiBeli.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        TransaksiBeli.Transbeli home = new TransaksiBeli.Transbeli();
        dpanee.add(home);
        home.setVisible(true);
        
    }//GEN-LAST:event_btn_TransaBeliMouseClicked

    private void btn_transJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transJualMouseClicked
        // TODO add your handling code here:
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_TransaksiJual.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
//        TransaksiJual.form_TransaksiJual home = new TransaksiJual.form_TransaksiJual();
        TransaksiJual.Transjual home = new TransaksiJual.Transjual();
        dpanee.add(home);
        home.setVisible(true);
        
    }//GEN-LAST:event_btn_transJualMouseClicked

    private void btn_dataPemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataPemasokMouseClicked
        // TODO add your handling code here:
        dpanee.removeAll();
        dpanee.repaint();
        DataPemasok.form_dataPemasok home = new DataPemasok.form_dataPemasok();
        dpanee.add(home);
        home.setVisible(true);
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_DataPemasok.png");
        sidebarr.setIcon(a);
        
    }//GEN-LAST:event_btn_dataPemasokMouseClicked

    private void btn_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMouseClicked
        // TODO add your handling code here:
//        dpanee.removeAll();
//        dpanee.repaint();
//        DataPemasok.form_dataPemasok home = new DataPemasok.form_dataPemasok();
//        dpanee.add(home);
//        home.setVisible(true);

        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_Pengeluaran.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        PengeluaranPemasukanKaryawan.form_tambahPengeluaran home = new PengeluaranPemasukanKaryawan.form_tambahPengeluaran();
        dpanee.add(home);
        home.setVisible(true);
    }//GEN-LAST:event_btn_pengeluaranMouseClicked

    private void btn_pemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pemasukanMouseClicked
        // TODO add your handling code here:
        ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_Pemasukan.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        PengeluaranPemasukanKaryawan.form_tambahPemasukan home = new PengeluaranPemasukanKaryawan.form_tambahPemasukan();
        dpanee.add(home);
        home.setVisible(true);
    }//GEN-LAST:event_btn_pemasukanMouseClicked

    private void btn_pengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengaturanMouseClicked
        // TODO add your handling code here:
         ImageIcon a = new ImageIcon("src/Sidebar/Karyawan_Pengaturan.png");
        sidebarr.setIcon(a);
        dpanee.removeAll();
        dpanee.repaint();
        PengaturanAkun.PengaturanAkun home = new PengaturanAkun.PengaturanAkun();
        dpanee.add(home);
        home.setVisible(true);
    }//GEN-LAST:event_btn_pengaturanMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login.LoginPageNew nw = new Login.LoginPageNew();
        nw.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed
    public void logOut(){
        this.dispose();
        dispose();
        this.validate();
//        dpanee.removeAll();
//        dpanee.repaint();
//        dispose();
//        Login.LoginPage l  = new Login.LoginPage();
//        this.dispose();
//        l.setVisible(true);
        
    }
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
            java.util.logging.Logger.getLogger(MainFrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</btn_pengaturaneditor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login.LoginPage().setVisible(true);
            }
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("llogo app.png")));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_TransaBeli;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JLabel btn_dataPemasok;
    private javax.swing.JLabel btn_dataProduk;
    private javax.swing.JLabel btn_pemasukan;
    private javax.swing.JLabel btn_pengaturan;
    private javax.swing.JLabel btn_pengeluaran;
    private javax.swing.JLabel btn_transJual;
    private Swing.Button button1;
    public javax.swing.JDesktopPane dpanee;
    private javax.swing.JLabel sidebarr;
    // End of variables declaration//GEN-END:variables
}
