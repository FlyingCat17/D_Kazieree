/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataPengguna;

import db.konekdb;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author LenataHoma
 */
public class form_TambahKaryawan extends javax.swing.JDialog {

    /**
     * Creates new form form_TambahAdmin
     */
    public form_TambahKaryawan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        autoKode();
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        
    }

//    public void autoID(){
//        try {
//            String sql = "SELECT * FROM tb_pengguna WHERE hak_akses = 'ADMIN' order by id_pengguna desc;";
//            java.sql.Connection con = (Connection)konekdb.GetConnection();
//            java.sql.Statement st = con.createStatement();
//            java.sql.ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                String kode = rs.getString("id_pengguna").substring(1);
//                String AN = ""+(Integer.parseInt(kode)+1);
//                String Nol = "";
//                
//                if (AN.length()==1) {
//                    Nol = "00";
//                } else if (AN.length()==2) {
//                    Nol = "0";
//                } else if(AN.length()==3){
//                    Nol = "";
//                }
//                txt_IdPengguna.setText("ADM"+Nol+AN);
//            } else {
//                txt_IdPengguna.setText("ADM0001");
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }
    public void autoKode(){
        try {
            String sql = "SELECT MAX(RIGHT(id_pengguna,4)) AS nomor\n" + "FROM tb_pengguna";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                if (rs.first()==false) {
                    txt_IdPengguna.setText("IDK0001");
                } else {
                    rs.last();
                    int autoId = rs.getInt(1)+1;
                    String no = String.valueOf(autoId);
                    int noLong = no.length();
                    for (int i = 0; i < 4-noLong; i++) {
                        no = "0"+no;
                    }
                    txt_IdPengguna.setText("IDK"+no);
                    System.out.println(no);
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

        txt_namaLengkap = new javax.swing.JTextField();
        txt_konfirmasiKataSandi = new javax.swing.JPasswordField();
        txt_kataSandi = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txt_noTelp = new javax.swing.JTextField();
        txt_namaPengguna = new javax.swing.JTextField();
        txt_IdPengguna = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_namaLengkap.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_namaLengkap.setBorder(null);
        txt_namaLengkap.setOpaque(false);
        getContentPane().add(txt_namaLengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 265, 420, 35));

        txt_konfirmasiKataSandi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_konfirmasiKataSandi.setBorder(null);
        txt_konfirmasiKataSandi.setOpaque(false);
        getContentPane().add(txt_konfirmasiKataSandi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 190, 30));

        txt_kataSandi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_kataSandi.setBorder(null);
        txt_kataSandi.setOpaque(false);
        getContentPane().add(txt_kataSandi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 180, 30));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 342, 420, 70));

        txt_noTelp.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.setOpaque(false);
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 455, 420, 35));

        txt_namaPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_namaPengguna.setBorder(null);
        txt_namaPengguna.setOpaque(false);
        getContentPane().add(txt_namaPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 195, 420, 35));

        txt_IdPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        txt_IdPengguna.setBorder(null);
        txt_IdPengguna.setOpaque(false);
        getContentPane().add(txt_IdPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 125, 180, 35));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_batal2.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, 70, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_simpan2.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, 70, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/formTAMBAHKARYAWAN.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 680));

        jTextField4.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        jTextField4.setBorder(null);
        jTextField4.setOpaque(false);
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 265, 420, 35));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        formDataPenggunaa fd = new formDataPenggunaa();
        fd.loadDataPengguna();
        dispose();
        fd.loadDataPengguna();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        String ip, np, nl, al, no, pw1, pw2;
        ip = txt_IdPengguna.getText().toString();
        np = txt_namaPengguna.getText().toString();
        nl = txt_namaLengkap.getText().toString();
        al = jTextArea1.getText().toString();
        no = txt_noTelp.getText().toString();
        pw1 = txt_kataSandi.getText().toString();
        pw2 = txt_konfirmasiKataSandi.getText().toString();
        if (np.equals("")) {
            JOptionPane.showMessageDialog(this, "Nama Pengguna Harap disii!", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_namaPengguna.requestFocus();
        } else if(nl.equals("")){
            JOptionPane.showMessageDialog(this, "Nama Lengkap Harap disii!", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_namaLengkap.requestFocus();
        } else if(al.equals("")){
            JOptionPane.showMessageDialog(this, "Alamat Harap disii!", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            jTextArea1.requestFocus();
        } else if(no.equals("")){
            JOptionPane.showMessageDialog(this, "Nomor Telpon Harap disii!", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_noTelp.requestFocus();
        } else if(pw1.equals("")){
            JOptionPane.showMessageDialog(this, "Kata Sandi Harap disii!", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_kataSandi.requestFocus();
        } else if (pw2.equals("")) {
            JOptionPane.showMessageDialog(this, "Harap Konfirmasi Kata Sandi", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_konfirmasiKataSandi.requestFocus();
        } else if(pw2.equals(pw1)){
            try {
                String sql = "INSERT INTO tb_pengguna VALUES ('"+ip+"', '"+np+"', '"+pw2+"', '"+nl+"', '"+al+"', '"+no+"', 'KARYAWAN', 'AKTIF');";
                java.sql.Connection con = (Connection)konekdb.GetConnection();
                java.sql.Statement st = con.createStatement();
                st.execute(sql);
                JOptionPane.showMessageDialog(this, "Berhasil Tersimpan!");
                autoKode();
                txt_namaPengguna.setText("");
                txt_namaLengkap.setText("");
                jTextArea1.setText("");
                txt_noTelp.setText("");
                txt_kataSandi.setText("");
                txt_konfirmasiKataSandi.setText("");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Kata Sandi Salah", "Kosong!", JOptionPane.INFORMATION_MESSAGE);
            txt_konfirmasiKataSandi.requestFocus();
        }
        
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(form_TambahKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_TambahKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_TambahKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_TambahKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                form_TambahKaryawan dialog = new form_TambahKaryawan(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField txt_IdPengguna;
    private javax.swing.JPasswordField txt_kataSandi;
    private javax.swing.JPasswordField txt_konfirmasiKataSandi;
    private javax.swing.JTextField txt_namaLengkap;
    private javax.swing.JTextField txt_namaPengguna;
    private javax.swing.JTextField txt_noTelp;
    // End of variables declaration//GEN-END:variables
}
