/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataPengguna;

import db.konekdb;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LenataHoma
 */
public class form_UbahAdmin extends javax.swing.JDialog {
formDataPenggunaa nj = new formDataPenggunaa();
Connection con; Statement st; ResultSet rs;
    /**
     * Creates new form form_UbahAdminReset
     */
    public form_UbahAdmin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        txt_IDPengguna.setText(nj.getIdPengguna());
        loadDataAdmin();
        System.out.println("ID : "+nj.getIdPengguna());
    }

    public void loadDataAdmin(){
        try {
            String sql = "SELECT * FROM tb_pengguna WHERE id_pengguna = '"+txt_IDPengguna.getText()+"';";
            con = (Connection)konekdb.GetConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                txt_namaPengguna.setText(rs.getString("username"));
                txt_namaLengkap.setText(rs.getString("nama_pengguna"));
                txt_alamat.setText(rs.getString("alamat_pengguna"));
                txt_noTelp.setText(rs.getString("no_telp_pengguna"));
                txt_status.setText(rs.getString("status"));
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

        txt_status = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_namaLengkap = new javax.swing.JTextField();
        txt_namaPengguna = new javax.swing.JTextField();
        txt_IDPengguna = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_status.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_status.setBorder(null);
        txt_status.setEnabled(false);
        txt_status.setOpaque(false);
        getContentPane().add(txt_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 170, 30));

        txt_noTelp.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.setOpaque(false);
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 416, 340, 30));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        txt_alamat.setColumns(20);
        txt_alamat.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_alamat.setLineWrap(true);
        txt_alamat.setRows(5);
        txt_alamat.setOpaque(false);
        jScrollPane1.setViewportView(txt_alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 350, 80));

        txt_namaLengkap.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_namaLengkap.setBorder(null);
        txt_namaLengkap.setOpaque(false);
        getContentPane().add(txt_namaLengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 236, 340, 30));

        txt_namaPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_namaPengguna.setBorder(null);
        txt_namaPengguna.setOpaque(false);
        getContentPane().add(txt_namaPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 177, 340, 30));

        txt_IDPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_IDPengguna.setBorder(null);
        txt_IDPengguna.setEnabled(false);
        txt_IDPengguna.setOpaque(false);
        getContentPane().add(txt_IDPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 150, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_batal2.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 70, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_simpan2.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, 70, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/formUBAHADMIN.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 490, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        nj.loadDataPengguna();
        this.setVisible(false);
        nj.loadDataPengguna();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        try {
            String upp = "UPDATE tb_pengguna SET username = '"+txt_namaPengguna.getText()
                    +"', nama_pengguna = '"+txt_namaLengkap.getText()+"', alamat_pengguna = '"
                    +txt_alamat.getText()+"', no_telp_pengguna = '"+txt_noTelp.getText()+"' WHERE id_pengguna = '"
                    +txt_IDPengguna.getText()+"';";
            con = (Connection)konekdb.GetConnection();
            st = con.createStatement();
            st.execute(upp);
            JOptionPane.showMessageDialog(this, "Berhasil Tersimpan!");
            nj.show();
            nj.loadDataPengguna();
            this.dispose();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(form_UbahAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_UbahAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_UbahAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_UbahAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                form_UbahAdmin dialog = new form_UbahAdmin(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField txt_IDPengguna;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_namaLengkap;
    private javax.swing.JTextField txt_namaPengguna;
    private javax.swing.JTextField txt_noTelp;
    private javax.swing.JTextField txt_status;
    // End of variables declaration//GEN-END:variables
}
