
package PengaturanAkun;

import Main.user;
import db.konekdb;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class PengaturanAkun extends javax.swing.JInternalFrame {
String id, nama;
user usr = new user();
int totalSemua;
String id_penggunaa = usr.getId_pengguna();
String al = "Ds. Wonokerto RT.01/RW.01, Klabang, Bondowoso";
private DefaultTableModel mod, mod2, mod3;
    public PengaturanAkun() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);
        nama = usr.getNama();
        mod = new DefaultTableModel();
        txt_Alamat.setText("<html>"+al+"</html>");
        System.out.println(usr.getId_pengguna());
        loadData();
        panel_akun.setVisible(false);
    }
    
    public void loadData(){
        try {
            String sql = "SELECT * FROM tb_pengguna WHERE id_pengguna = '"+id_penggunaa+"';";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                txt_namaLengkap.setText(rs.getString("nama_pengguna").toString());
                txt_hakAkses.setText(rs.getString("hak_akses").toString());
                txt_IDPengguna.setText(rs.getString("id_pengguna").toString());
                txt_username.setText(rs.getString("username"));
                txt_Alamat.setText("<html>"+rs.getString("alamat_pengguna").toString()+"</html>");
                txt_noTelp.setText(rs.getString("no_telp_pengguna"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void loadUbahData(){
        try {
            String sql = "SELECT * FROM tb_pengguna WHERE id_pengguna = '"+id_penggunaa+"';";
            java.sql.Connection con = (Connection)konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                txt_UbahNamaLengkap.setText(rs.getString("nama_pengguna").toString());
                txt_UbahIDPengguna.setText(rs.getString("id_pengguna").toString());
                txt_UbahNamaPengguna.setText(rs.getString("username"));
                txt_UbahAlamat.setText(rs.getString("alamat_pengguna").toString());
                txt_UbahNoTelp.setText(rs.getString("no_telp_pengguna"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_akun = new Swing.PanelRound();
        panelRound4 = new Swing.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        passwordField1 = new Swing.PasswordField();
        button4 = new Swing.Button();
        btn_simpanUbah = new Swing.Button();
        button2 = new Swing.Button();
        txt_UbahNoTelp = new Swing.TextField();
        txt_UbahAlamat = new Swing.TextField();
        txt_UbahNamaPengguna = new Swing.TextField();
        txt_UbahNamaLengkap = new Swing.TextField();
        txt_UbahIDPengguna = new Swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        panel_utama = new Swing.PanelRound();
        panelRound1 = new Swing.PanelRound();
        txt_noTelp = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_Alamat = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_username = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelRound2 = new Swing.PanelRound();
        txt_IDPengguna = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        button1 = new Swing.Button();
        txt_hakAkses = new javax.swing.JLabel();
        txt_namaLengkap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(185, 185, 185));
        setMaximumSize(new java.awt.Dimension(960, 707));
        setMinimumSize(new java.awt.Dimension(960, 707));
        setPreferredSize(new java.awt.Dimension(960, 707));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_akun.setBackground(new java.awt.Color(185, 185, 185));
        panel_akun.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(253, 144, 39));
        jLabel6.setText("UBAH PROFIL AKUN");
        panelRound4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, 40));

        passwordField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passwordField1.setLabelText("Konfirmasi Kata Sandi");
        passwordField1.setLineColor(new java.awt.Color(253, 144, 39));
        passwordField1.setOpaque(false);
        passwordField1.setShowAndHide(true);
        panelRound4.add(passwordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 470, -1));

        button4.setBackground(new java.awt.Color(240, 240, 240));
        button4.setForeground(new java.awt.Color(115, 115, 115));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengaturanAkun/bx_edit (1).png"))); // NOI18N
        button4.setText("UBAH KATA SANDI");
        button4.setEffectColor(new java.awt.Color(253, 144, 39));
        button4.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        panelRound4.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 140, 30));

        btn_simpanUbah.setBackground(new java.awt.Color(51, 255, 51));
        btn_simpanUbah.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpanUbah.setText("SIMPAN");
        btn_simpanUbah.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        btn_simpanUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanUbahActionPerformed(evt);
            }
        });
        panelRound4.add(btn_simpanUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 80, 30));

        button2.setBackground(new java.awt.Color(255, 51, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("BATAL");
        button2.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelRound4.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 80, 30));

        txt_UbahNoTelp.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_UbahNoTelp.setLabelText("Nomor Telepon");
        txt_UbahNoTelp.setLineColor(new java.awt.Color(253, 144, 39));
        txt_UbahNoTelp.setOpaque(false);
        txt_UbahNoTelp.setSelectionColor(new java.awt.Color(153, 153, 153));
        panelRound4.add(txt_UbahNoTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 470, 50));

        txt_UbahAlamat.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_UbahAlamat.setLabelText("Alamat");
        txt_UbahAlamat.setLineColor(new java.awt.Color(253, 144, 39));
        txt_UbahAlamat.setOpaque(false);
        txt_UbahAlamat.setSelectionColor(new java.awt.Color(153, 153, 153));
        panelRound4.add(txt_UbahAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 470, 50));

        txt_UbahNamaPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_UbahNamaPengguna.setLabelText("Nama Pengguna (Username)");
        txt_UbahNamaPengguna.setLineColor(new java.awt.Color(253, 144, 39));
        txt_UbahNamaPengguna.setOpaque(false);
        txt_UbahNamaPengguna.setSelectionColor(new java.awt.Color(153, 153, 153));
        panelRound4.add(txt_UbahNamaPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 470, 50));

        txt_UbahNamaLengkap.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_UbahNamaLengkap.setLabelText("Nama Lengkap");
        txt_UbahNamaLengkap.setLineColor(new java.awt.Color(253, 144, 39));
        txt_UbahNamaLengkap.setOpaque(false);
        txt_UbahNamaLengkap.setSelectionColor(new java.awt.Color(153, 153, 153));
        panelRound4.add(txt_UbahNamaLengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 470, 50));

        txt_UbahIDPengguna.setEnabled(false);
        txt_UbahIDPengguna.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        txt_UbahIDPengguna.setLabelText("ID Pengguna");
        txt_UbahIDPengguna.setLineColor(new java.awt.Color(253, 144, 39));
        txt_UbahIDPengguna.setOpaque(false);
        txt_UbahIDPengguna.setSelectionColor(new java.awt.Color(153, 153, 153));
        panelRound4.add(txt_UbahIDPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 230, 50));

        panel_akun.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 530, 510));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengaturanAkun/form_editAkun (1).png"))); // NOI18N
        panel_akun.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 690));

        getContentPane().add(panel_akun, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 690));

        panel_utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_noTelp.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        txt_noTelp.setForeground(new java.awt.Color(78, 78, 78));
        txt_noTelp.setText("081916778922");
        panelRound1.add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 280, 30));

        jLabel9.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(78, 78, 78));
        jLabel9.setText("Nomor Telepon");
        panelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 270, 20));

        txt_Alamat.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        txt_Alamat.setForeground(new java.awt.Color(78, 78, 78));
        txt_Alamat.setText("Ds. Wonokerto RT.01/RW.01, Klabang, Bondowoso");
        txt_Alamat.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txt_Alamat.setDoubleBuffered(true);
        panelRound1.add(txt_Alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 420, 80));

        jLabel7.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(78, 78, 78));
        jLabel7.setText("Alamat");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 270, 20));

        txt_username.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        txt_username.setForeground(new java.awt.Color(78, 78, 78));
        txt_username.setText("FlyingCat");
        panelRound1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 300, 30));

        jLabel5.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(78, 78, 78));
        jLabel5.setText("Nama Pengguna (Username)");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 270, 20));

        panelRound2.setBackground(new java.awt.Color(200, 200, 200));
        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);
        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 70, 6));

        txt_IDPengguna.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        txt_IDPengguna.setForeground(new java.awt.Color(78, 78, 78));
        txt_IDPengguna.setText("IDK0001");
        panelRound1.add(txt_IDPengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 300, 30));

        jLabel3.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(78, 78, 78));
        jLabel3.setText("ID Pengguna");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, 20));

        button1.setForeground(new java.awt.Color(51, 51, 51));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengaturanAkun/bx_edit (1).png"))); // NOI18N
        button1.setText("Ubah Profil Akun");
        button1.setEffectColor(new java.awt.Color(253, 144, 39));
        button1.setFont(new java.awt.Font("Quicksand", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelRound1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 140, 40));

        panel_utama.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 479, 330));

        txt_hakAkses.setFont(new java.awt.Font("Quicksand", 0, 20)); // NOI18N
        txt_hakAkses.setForeground(new java.awt.Color(78, 78, 78));
        txt_hakAkses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_hakAkses.setText("Karyawan");
        panel_utama.add(txt_hakAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 950, 30));

        txt_namaLengkap.setFont(new java.awt.Font("Quicksand", 1, 30)); // NOI18N
        txt_namaLengkap.setForeground(new java.awt.Color(78, 78, 78));
        txt_namaLengkap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_namaLengkap.setText("Fathan Maulana");
        panel_utama.add(txt_namaLengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 950, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengaturanAkun/bi_person-circle.png"))); // NOI18N
        panel_utama.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 960, 150));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PengaturanAkun/formAkun.png"))); // NOI18N
        panel_utama.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 680));

        getContentPane().add(panel_utama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        panel_utama.setVisible(false);
        panel_akun.setVisible(true);
        loadUbahData();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
         panel_utama.setVisible(true);
        panel_akun.setVisible(false);
        loadData();
    }//GEN-LAST:event_button2ActionPerformed

    private void btn_simpanUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanUbahActionPerformed
        // TODO add your handling code here:
        String nl, np, al, no, ko;
        nl = txt_UbahNamaLengkap.getText().toString();
        np = txt_UbahNamaPengguna.getText().toString();
        al = txt_UbahAlamat.getText().toString();
        no = txt_UbahNoTelp.getText().toString();
        ko = passwordField1.getText().toString();
        try {
        String verified = "SELECT * FROM tb_pengguna WHERE id_pengguna = '"+id_penggunaa+"';";
        java.sql.Connection con = (Connection)konekdb.GetConnection();
        java.sql.Statement st = con.createStatement();
        java.sql.ResultSet rs = st.executeQuery(verified);
            if (rs.next()) {
                if (nl.equals("")) {
                    JOptionPane.showMessageDialog(this, "Nama Lengkap harap diisi");
                    txt_UbahNamaLengkap.requestFocus();
                } else if (np.equals("")) {
                    JOptionPane.showMessageDialog(this, "Nama Pengguna/Username harap diisi");
                    txt_UbahNamaPengguna.requestFocus();
                } else if (al.equals("")) {
                    JOptionPane.showMessageDialog(this, "Alamat harap diisi");
                    txt_UbahAlamat.requestFocus();
                } else if (no.equals("")) {
                    JOptionPane.showMessageDialog(this, "Nomor Telepon harap diisi");
                    txt_UbahNoTelp.requestFocus();
                } else if (ko.equals("")) {
                    JOptionPane.showMessageDialog(this, "Harap Konfirmasi Kata Sandi terlebih dahulu");
                } else if (passwordField1.getText().equals(rs.getString("kata_sandi"))) {
                    try {
                        String update = "UPDATE `tb_pengguna` SET `id_pengguna`='" + txt_UbahIDPengguna.getText() + "',"
                                + "`username`='" + txt_UbahNamaPengguna.getText() + "',`nama_pengguna`='" + txt_UbahNamaLengkap.getText() + "',"
                                + "`alamat_pengguna`='" + txt_UbahAlamat.getText() + "',`no_telp_pengguna`='" + txt_UbahNoTelp.getText() + "' WHERE id_pengguna = '" + txt_UbahIDPengguna.getText() + "';";
                        java.sql.Statement stt = con.createStatement();
                        stt.execute(update);
                        JOptionPane.showMessageDialog(this, "Berhasil Diubah");
                        loadUbahData();
                    } catch (Exception e) {
                        System.err.println("Dalam Proses Update : " + e.getMessage());
                    }
                }
            }
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Password Salah!");
            System.err.println("Cari Kata Sandi :"+ e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanUbahActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.Button btn_simpanUbah;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private Swing.PanelRound panelRound1;
    private Swing.PanelRound panelRound2;
    private Swing.PanelRound panelRound4;
    private Swing.PanelRound panel_akun;
    private Swing.PanelRound panel_utama;
    private Swing.PasswordField passwordField1;
    private javax.swing.JLabel txt_Alamat;
    private javax.swing.JLabel txt_IDPengguna;
    private Swing.TextField txt_UbahAlamat;
    private Swing.TextField txt_UbahIDPengguna;
    private Swing.TextField txt_UbahNamaLengkap;
    private Swing.TextField txt_UbahNamaPengguna;
    private Swing.TextField txt_UbahNoTelp;
    private javax.swing.JLabel txt_hakAkses;
    private javax.swing.JLabel txt_namaLengkap;
    private javax.swing.JLabel txt_noTelp;
    private javax.swing.JLabel txt_username;
    // End of variables declaration//GEN-END:variables
}
