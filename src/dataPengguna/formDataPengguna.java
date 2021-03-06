/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataPengguna;

import db.konekdb;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LenataHoma
 */
public class formDataPengguna extends javax.swing.JDialog {
DefaultTableModel tabmodel;
    ResultSet rs;
    /**
     * Creates new form formDataPengguna
     */
    public formDataPengguna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        loadDataPengguna();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filterAkses = new javax.swing.JComboBox<>();
        tb_pengguna = new javax.swing.JScrollPane();
        TabelPengguna = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }

        };
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(963, 707));
        setMinimumSize(new java.awt.Dimension(963, 707));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 125, 270, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Hapus.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 110, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Ubah.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 110, 40));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Tambah.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 110, 40));

        filterAkses.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        filterAkses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Karyawan", "Admin" }));
        filterAkses.setOpaque(false);
        filterAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterAksesActionPerformed(evt);
            }
        });
        getContentPane().add(filterAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 125, 140, 30));

        tb_pengguna.setBorder(null);

        TabelPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        TabelPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Pengguna", "Nama Pengguna", "Nama Lengkap", "Alamat", "No Telepon", "Tingkatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelPengguna.setRowHeight(40);
        TabelPengguna.getTableHeader().setResizingAllowed(false);
        TabelPengguna.getTableHeader().setReorderingAllowed(false);
        TabelPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPenggunaMouseClicked(evt);
            }
        });
        tb_pengguna.setViewportView(TabelPengguna);

        getContentPane().add(tb_pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 860, 490));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 44, 50, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/formDataPengguna (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadDataPengguna(){
        DefaultTableModel tab = new DefaultTableModel();
        tab.addColumn("ID Pengguna");
        tab.addColumn("Username");
        tab.addColumn("Nama Lengkap");
        tab.addColumn("Alamat");
        tab.addColumn("No Telp");
        tab.addColumn("Hak Akses");
        tab.addColumn("Status");
        try {
            String sql = "SELECT * FROM tb_pengguna";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {     
                tab.addRow(new Object[]{
                rs.getString("id_pengguna"),
                rs.getString("username"),
                rs.getString("nama_pengguna"),
                rs.getString("alamat_pengguna"),
                rs.getString("no_telp_pengguna"),
                rs.getString("hak_akses"),
                rs.getString("status")
                });
                TabelPengguna.setModel(tab);
            };
        } catch (Exception e) {
            System.out.println("Gagal Mendapatkan Data!");
            System.err.println(e.getMessage());
        }
    }
    private void TabelPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPenggunaMouseClicked
        // TODO add your handling code here:
        int baris= TabelPengguna.rowAtPoint(evt.getPoint());
        //        kategor_jasa = TabelPengguna.getValueAt(baris, 2).toString();
        //        id_produk = TabelPengguna.getValueAt(baris, 0).toString();
        //        System.out.println("ID PRODUK = "+id_produk);
        //        System.out.println("Kategori "+ kategor_jasa);
        //        lbl_id.setText(id_produk);
        //        int i = TabelProduk.getSelectedRow();
        //        TableModel tm = TabelProduk.getModel();
        //        DataProdukJasa.formUbahJasa mn = new DataProdukJasa.formUbahJasa(this, true);
        //        id_produk= tm.getValueAt(i, 0).toString();
        //        mn.txt_kodeProdukJasa.setText(id_produk);
    }//GEN-LAST:event_TabelPenggunaMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        if (evt.getButton()==MouseEvent.BUTTON1) {
            this.dispose();
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
//        OptionPilihPengguna nn = new OptionPilihPengguna(, true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void filterAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterAksesActionPerformed
        // TODO add your handling code here:
        String tipe = (String)filterAkses.getSelectedItem();
        if (tipe == "ADMIN") {
            try {
            Object[] judul_kolom = {"ID Pengguna", "Username", "Nama Lengkap", "No Telepon", "Hak Akses", "Status"};
            tabmodel = new DefaultTableModel(null, judul_kolom);
            TabelPengguna.setModel(tabmodel);
            
            Connection conn = (Connection)konekdb.GetConnection();
            Statement st = conn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            
            rs = st.executeQuery("SELECT * FROM tb_pengguna WHERE hak_akses = 'ADMIN'");
            while(rs.next()){
                Object[] data = {
                    rs.getString("id_pengguna"),
                    rs.getString("username"),
                    rs.getString("nama_pengguna"),
                    rs.getString("alamat_pengguna"),
                    rs.getString("no_telp_pengguna"),
                    rs.getString("hak_akses"),
                    rs.getString("status")
                };
                tabmodel.addRow(data);
                System.out.println("Berhasil Admin");
            }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_filterAksesActionPerformed

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
            java.util.logging.Logger.getLogger(formDataPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formDataPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formDataPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formDataPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formDataPengguna dialog = new formDataPengguna(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable TabelPengguna;
    private javax.swing.JComboBox<String> filterAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane tb_pengguna;
    // End of variables declaration//GEN-END:variables
}
