/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataPengguna;
import db.konekdb;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LenataHoma
 */
public class formDataPenggunaa extends javax.swing.JInternalFrame {
    DefaultTableModel tabmodel;
    Connection con;
    ResultSet rs;
    Statement st;
    Main.MainFrame vm = new Main.MainFrame();
    String id_pengguna = null;
    String hakAkses = null;
    String status = null;
    /**
     * Creates new form formDataPenggunaa
     */
    public formDataPenggunaa() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI)this.getUI();
        gui.setNorthPane(null);
        loadDataPengguna();
        System.out.println(id_pengguna);
        System.out.println(hakAkses);
        System.out.println(status);
    }

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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        tb_pengguna = new javax.swing.JScrollPane();
        TabelPengguna = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }

        };
        filterAkses = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 50, 40));

        jTextField1.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 270, 30));

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

        getContentPane().add(tb_pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 860, 490));

        filterAkses.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        filterAkses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEMUA", "KARYAWAN", "ADMIN" }));
        filterAkses.setOpaque(false);
        filterAkses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterAksesActionPerformed(evt);
            }
        });
        getContentPane().add(filterAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 140, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Tambah.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 103, 110, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Ubah.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 103, 110, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/btn_Hapus.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 103, 110, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dataPengguna/formDataPengguna (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabelPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPenggunaMouseClicked
        // TODO add your handling code here:
        int baris= TabelPengguna.rowAtPoint(evt.getPoint());
        id_pengguna = TabelPengguna.getValueAt(baris, 0).toString();
        hakAkses = TabelPengguna.getValueAt(baris, 5).toString();
        status = TabelPengguna.getValueAt(baris, 6).toString();
        System.out.println("ID Pengguna : "+id_pengguna);
        System.out.println("Hak Akses   : "+hakAkses);
        System.out.println("Status      : "+status);
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

    public String getIdPengguna(){
        return id_pengguna;
    }
    public String getHakAkses(){
        return hakAkses;
    }
    public String getStatus(){
        return status;
    }
    
    
    private void filterAksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterAksesActionPerformed
        // TODO add your handling code here:
        String tipe = (String)filterAkses.getSelectedItem();
        if (tipe == "ADMIN") {
            try {
                Object[] judul_kolom = {"ID Pengguna", "Username", "Nama Lengkap", "Alamat", "No Telepon", "Hak Akses", "Status"};
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
        } else if (tipe == "KARYAWAN") {
            try {
                Object[] judul_kolom = {"ID Pengguna", "Username", "Nama Lengkap", "Alamat", "No Telepon", "Hak Akses", "Status"};
                tabmodel = new DefaultTableModel(null, judul_kolom);
                TabelPengguna.setModel(tabmodel);

                Connection conn = (Connection)konekdb.GetConnection();
                Statement st = conn.createStatement();
                tabmodel.getDataVector().removeAllElements();

                rs = st.executeQuery("SELECT * FROM tb_pengguna WHERE hak_akses = 'KARYAWAN'");
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
        } else if(tipe == "SEMUA"){
            try {
                Object[] judul_kolom = {"ID Pengguna", "Username", "Nama Lengkap", "Alamat", "No Telepon", "Hak Akses", "Status"};
                tabmodel = new DefaultTableModel(null, judul_kolom);
                TabelPengguna.setModel(tabmodel);

                Connection conn = (Connection)konekdb.GetConnection();
                Statement st = conn.createStatement();
                tabmodel.getDataVector().removeAllElements();

                rs = st.executeQuery("SELECT * FROM tb_pengguna");
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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        //        OptionPilihPengguna nn = new OptionPilihPengguna(, true);
        OptionPilihPengguna nn = new OptionPilihPengguna(vm, true);
        loadDataPengguna();
        nn.setVisible(true);
        loadDataPengguna();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
//        if (getIdPengguna()==null) {
//            JOptionPane.showMessageDialog(this, "Harap Pilih Salah Satu Pengguna!");
//        } 
////        else if(getIdPengguna().equals("ADMIN")&& getHakAkses().equals("LUPA")){
////            form_UbahAdminReset ubr = new form_UbahAdminReset(vm, true);
////            ubr.setVisible(true);
////        }
//        else if (getIdPengguna()=="ADMIN") {
//            form_UbahAdmin ua = new form_UbahAdmin(vm, true);
//            ua.setVisible(true);
//        }
//        form_UbahAdmin aa = new form_UbahAdmin(vm, true);
//        aa.setVisible(true);
        if (id_pengguna==null) {
            JOptionPane.showMessageDialog(this, "Harap Pilih Salah Satu Pengguna!");
        } else if (hakAkses.equals("ADMIN")&&status.equals("LUPA")) {
            
            
            loadDataPengguna();
            form_UbahAdminReset ubr = new form_UbahAdminReset(vm, true);
            ubr.txt_IDPengguna.setText(getIdPengguna());
            ubr.loadDataAdmin();
            loadDataPengguna();
            ubr.setVisible(true);
            loadDataPengguna();
            ubr.loadDataAdmin();
            ubr.txt_IDPengguna.setText(getIdPengguna());
            loadDataPengguna();
        } else if (hakAkses.equals("ADMIN")) {
            form_UbahAdmin ua = new form_UbahAdmin(vm, true);
            ua.txt_IDPengguna.setText(getIdPengguna());
            loadDataPengguna();
            ua.loadDataAdmin();
            ua.setVisible(true);
            ua.loadDataAdmin();
            ua.txt_IDPengguna.setText(getIdPengguna());
            loadDataPengguna();
        } else if (hakAkses.equals("KARYAWAN")&&status.equals("LUPA")) {
            form_UbahKaryawanReset ukr = new form_UbahKaryawanReset(vm, true);
            ukr.txt_IDPengguna.setText(getIdPengguna());
            ukr.loadDataKaryawan();
            loadDataPengguna();
            ukr.setVisible(true);
            loadDataPengguna();
            ukr.loadDataKaryawan();
            ukr.txt_IDPengguna.setText(getIdPengguna());
            loadDataPengguna();
        } else if(hakAkses.equals("KARYAWAN")){
            form_UbahKaryawan uk = new form_UbahKaryawan(vm, true);
            uk.txt_IDPengguna.setText(getIdPengguna());
            uk.loadDataLKaryawan();
            loadDataPengguna();
            uk.setVisible(true);
            loadDataPengguna();
            uk.txt_IDPengguna.setText(getIdPengguna());
            loadDataPengguna();
        }
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        if (getIdPengguna() == null) {
            JOptionPane.showMessageDialog(this, "Harap pilih salah satu pengguna!!");
        } else {
            int jawab = JOptionPane.showConfirmDialog(this, "Yakin ingin Menghapus Data Pengguna ini?");
            switch(jawab){
                case JOptionPane.YES_OPTION:
                    try {
                        String del = "DELETE FROM tb_pengguna WHERE id_pengguna = '"+getIdPengguna()+"';";
                        java.sql.Connection con = (Connection)konekdb.GetConnection();
                        java.sql.PreparedStatement pst = con.prepareStatement(del);
                        pst.execute();
                        JOptionPane.showMessageDialog(this, "Data berhasil terhapus");
                        loadDataPengguna();
                        
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        if (evt.getButton()==MouseEvent.BUTTON1) {
            this.dispose();
        }
    }//GEN-LAST:event_jLabel2MouseClicked


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
