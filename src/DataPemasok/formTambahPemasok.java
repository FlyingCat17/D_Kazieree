/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataPemasok;

import db.konekdb;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author LenataHoma
 */
public class formTambahPemasok extends javax.swing.JDialog {
Main.MainFrame k = new Main.MainFrame();
form_dataPemasok daata = new form_dataPemasok();
    Connection con;
    PreparedStatement pst;
    
    /**
     * Creates new form formTambahPemasok
     */
    public formTambahPemasok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        user_id();
    }

    public void user_id() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        String id_kar = "SP";
        txt_idPemasok.setText(id_kar+saltStr);
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txt_noTelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_usaha = new javax.swing.JTextField();
        txt_namaPemasok = new javax.swing.JTextField();
        txt_idPemasok = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataProduk/btn_simpan.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 95, 30));

        txt_noTelp.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.setOpaque(false);
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 410, 40));

        jScrollPane1.setBackground(new java.awt.Color(196, 196, 196));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        txt_alamat.setBackground(new java.awt.Color(196, 196, 196));
        txt_alamat.setColumns(20);
        txt_alamat.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_alamat.setLineWrap(true);
        txt_alamat.setRows(5);
        txt_alamat.setBorder(null);
        txt_alamat.setOpaque(false);
        jScrollPane1.setViewportView(txt_alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 336, 420, 90));

        txt_usaha.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_usaha.setBorder(null);
        txt_usaha.setOpaque(false);
        getContentPane().add(txt_usaha, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 269, 410, 40));

        txt_namaPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_namaPemasok.setBorder(null);
        txt_namaPemasok.setOpaque(false);
        getContentPane().add(txt_namaPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 206, 410, 40));

        txt_idPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_idPemasok.setBorder(null);
        txt_idPemasok.setEnabled(false);
        txt_idPemasok.setOpaque(false);
        getContentPane().add(txt_idPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 146, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataProduk/btn_bstal.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 95, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataPemasok/form_tambahPemasok.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        daata.loadDataPemasok();
        this.dispose();
        daata.loadDataPemasok();
        System.out.println("Menutup Form Tambah Pemasok");
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        String ip, np, nu, au, no;
        ip= txt_idPemasok.getText().toString();
        np=txt_namaPemasok.getText().toString();
        nu=txt_usaha.getText().toString();
        au=txt_alamat.getText().toString();
        no=txt_noTelp.getText().toString();
        if (ip.equals("")) {
            JOptionPane.showMessageDialog(null, "Kode Pemasok harap diisi!");
        } else if(np.equals("")){
             JOptionPane.showMessageDialog(null, "Nama Pemasok harap diisi!");
        } else if(nu.equals("")){
            JOptionPane.showMessageDialog(null, "Nama Usaha harap diisi!");
        } else if(au.equals("")){
            JOptionPane.showMessageDialog(null, "Alamat Usaha harap diisi!");
        } else if(no.equals("")){
            JOptionPane.showMessageDialog(null, "No Telpon Pemasok harap diisi!");
        } else {
            try {
                String a = "INSERT INTO tb_pemasok VALUES('"+ip+"','"+np
                        +"','"+nu+"','"+au+"','"+no+"');";
                con = (Connection)konekdb.GetConnection();
                pst = con.prepareStatement(a);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Berhasil Tersimpan!");
                System.out.println("Berhasil Menyimpan Data Pemasok");
                System.out.println("ID      : "+ip);
                System.out.println("Nama    : "+np);
                System.out.println("Usaha   : "+nu);
                System.out.println("Alamat  : "+au);
                System.out.println("No Telp : "+no);
                user_id();
                txt_namaPemasok.setText("");
                txt_alamat.setText("");
                txt_usaha.setText("");
                txt_noTelp.setText("");
                this.dispose();
                daata.loadDataPemasok();
            } catch (Exception e) {
                System.out.println("Something Wrong. You can check it below");
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txt_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noTelpKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_noTelpKeyTyped

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
            java.util.logging.Logger.getLogger(formTambahPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formTambahPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formTambahPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formTambahPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formTambahPemasok dialog = new formTambahPemasok(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_idPemasok;
    private javax.swing.JTextField txt_namaPemasok;
    private javax.swing.JTextField txt_noTelp;
    private javax.swing.JTextField txt_usaha;
    // End of variables declaration//GEN-END:variables
}
