    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProduk;


import Main.MainFrame;
import java.awt.Color;

/**
 *
 * @author LenataHoma
 */
public class formUbahProdukBarang extends javax.swing.JDialog {
int x, y;
DataProduk.form_DataProduk m = new DataProduk.form_DataProduk();
    /**
     * Creates new form formTambahProdukBarang
     */
    public formUbahProdukBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_hargaJual = new javax.swing.JTextField();
        txt_hargaBeli = new javax.swing.JTextField();
        txt_satuanProduk = new javax.swing.JTextField();
        txt_namaProduk = new javax.swing.JTextField();
        txt_kodeProdukBarang = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JLabel();
        btn_batal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_hargaJual.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_hargaJual.setBorder(null);
        txt_hargaJual.setOpaque(false);
        getContentPane().add(txt_hargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 370, 40));

        txt_hargaBeli.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_hargaBeli.setBorder(null);
        txt_hargaBeli.setOpaque(false);
        getContentPane().add(txt_hargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 335, 370, 40));

        txt_satuanProduk.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_satuanProduk.setBorder(null);
        txt_satuanProduk.setOpaque(false);
        getContentPane().add(txt_satuanProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 120, 40));

        txt_namaProduk.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_namaProduk.setBorder(null);
        txt_namaProduk.setOpaque(false);
        getContentPane().add(txt_namaProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 209, 370, 40));

        txt_kodeProdukBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_kodeProdukBarang.setBorder(null);
        txt_kodeProdukBarang.setOpaque(false);
        getContentPane().add(txt_kodeProdukBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 145, 370, 40));

        btn_simpan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataProduk/btn_simpan.png"))); // NOI18N
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanMouseClicked(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 450, 100, 40));

        btn_batal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataProduk/btn_bstal.png"))); // NOI18N
        btn_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_batalMouseClicked(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 100, 40));

        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DataProduk/form_ubahProdukBarang.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        // TODO add your handling code here:
        
//        String kp, np, sp, hb, hj;
//        kp = txt_kodeProduk.getText().toString();
//        np = txt_namaProduk.getText().toString();
//        sp = txt_satuan.getText();
//        hb = txt_hargabeli.getText();
//        hj = txt_hargajual.getText();

    }//GEN-LAST:event_btn_simpanMouseClicked

    private void btn_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseClicked
        // TODO add your handling code here:
        m.loadTableProduk();
        this.dispose();
//        this.setVisible(false);
//        mn.loadTableProduk();
//        Mainhome mn = new Mainhome();
        //        panel_tambahProdukBarang.setVisible(false);
        //        TabelProduk.setVisible(true);
        //        jScrollPane1.setVisible(true);
        //        loadTableProduk();
        //        txt_kodeprodukTambahProdukBarang.setText("");
        //        txt_tambahnamaprodukBarang.setText("");
        //        txt_satuanTambahProdukBarang.setText("");
        //        txt_hargabeliTambahProdukBarang.setText("");
        //        txt_hargajualTambahProdukBarang.setText("");
    }//GEN-LAST:event_btn_batalMouseClicked

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        // TODO add your handling code here:
        int ex = evt.getXOnScreen();
        int ye = evt.getYOnScreen();

        this.setLocation(ex-x, ye-y);
    }//GEN-LAST:event_jLabel2MouseDragged

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formUbahProdukBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formUbahProdukBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formUbahProdukBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formUbahProdukBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formUbahProdukBarang dialog = new formUbahProdukBarang(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btn_batal;
    private javax.swing.JLabel btn_simpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_hargaBeli;
    private javax.swing.JTextField txt_hargaJual;
    public static javax.swing.JTextField txt_kodeProdukBarang;
    private javax.swing.JTextField txt_namaProduk;
    private javax.swing.JTextField txt_satuanProduk;
    // End of variables declaration//GEN-END:variables
}
