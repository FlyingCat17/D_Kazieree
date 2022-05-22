package dataPengguna;


import Lainnya.*;
import DataStok.*;
import Beranda.*;
import java.awt.event.MouseEvent;
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
public class form_dataPengguna extends javax.swing.JInternalFrame {
Main.MainFrame b = new Main.MainFrame();
    /**
     * Creates new form Beranda
     */
    public form_dataPengguna() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI)this.getUI();
        gui.setNorthPane(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterAkses = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        tb_pengguna = new javax.swing.JScrollPane();
        TabelPengguna = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }

        };
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(960, 710));
        setMinimumSize(new java.awt.Dimension(960, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filterAkses.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        filterAkses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Karyawan", "Admin" }));
        filterAkses.setOpaque(false);
        getContentPane().add(filterAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 110, 130, 30));

        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 40, 40));

        tb_pengguna.setBorder(null);

        TabelPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 15)); // NOI18N
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
        if (TabelPengguna.getColumnModel().getColumnCount() > 0) {
            TabelPengguna.getColumnModel().getColumn(0).setResizable(false);
            TabelPengguna.getColumnModel().getColumn(1).setResizable(false);
            TabelPengguna.getColumnModel().getColumn(2).setResizable(false);
            TabelPengguna.getColumnModel().getColumn(3).setResizable(false);
            TabelPengguna.getColumnModel().getColumn(4).setResizable(false);
            TabelPengguna.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(tb_pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 157, 860, 490));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/formDataPengguna.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (evt.getButton()==MouseEvent.BUTTON1){
            b.dpanee.removeAll();
            b.dpanee.repaint();
            Lainnya.form_Lainnya n = new Lainnya.form_Lainnya();
            b.dpanee.add(n);
            n.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TabelPengguna;
    private javax.swing.JComboBox<String> filterAkses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane tb_pengguna;
    // End of variables declaration//GEN-END:variables
}
