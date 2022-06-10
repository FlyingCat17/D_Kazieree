package TransaksiBeli;
import Main.user;
import db.konekdb;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utils.CustomDocumentFilter;

public class Transbeli extends javax.swing.JInternalFrame {

    String id;
    String id_produk;
    String id_produkjasa;
    String id_pemasok, namaPemasok;
    int totalSemua;
    private DefaultTableModel mod, mod2, mod3;

    public Transbeli() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI gui = (BasicInternalFrameUI) this.getUI();
        gui.setNorthPane(null);

        jTable1.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(255, 144, 39));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.getRowHeight(30);
        kode_transaksi();
        user usr = new user();
        String nama = usr.getNama();
        txt_Kasir.setText(usr.getNama());
        mod = new DefaultTableModel();

        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jTable1.setModel(mod);

        mod.addColumn("No");
        mod.addColumn("ID Produk");
        mod.addColumn("Nama Produk");
        mod.addColumn("Harga");
        mod.addColumn("Qty");
        mod.addColumn("Total Harga");

    }

    public void loadTableProduk() {
        jTable2.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(255, 144, 39));
        jTable2.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable2.getRowHeight(30);
        mod3 = new DefaultTableModel();
        mod3.addColumn("ID Produk");
        mod3.addColumn("Nama Produk");
        mod3.addColumn("Satuan");
        mod3.addColumn("Harga Jual");
        mod3.addColumn("Stok");
        jTable2.setModel(mod3);
        try {
            String sqll = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.satuan, tb_produk.harga_jual, tb_stokbarang.stok_produk FROM tb_produk\n"
                    + "JOIN tb_stokbarang\n"
                    + "ON tb_produk.id_produk = tb_stokbarang.id_produk";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet res = st.executeQuery(sqll);
            while (res.next()) {
                mod3.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),});
                jTable2.setModel(mod3);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadTablePemasok() {
        jTable2.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(255, 144, 39));
        jTable2.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable2.getRowHeight(30);
        mod3 = new DefaultTableModel();
        mod3.addColumn("ID Pemasok");
        mod3.addColumn("Nama Pemasok");
        mod3.addColumn("Nama Usaha");
        mod3.addColumn("Alamat Usaha");
        mod3.addColumn("Nomor Telepon Pemasok");
        jTable2.setModel(mod3);
        try {
            String sqll = "SELECT * FROM `tb_pemasok`";
            java.sql.Connection con = (Connection) konekdb.GetConnection();
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet res = st.executeQuery(sqll);
            while (res.next()) {
                mod3.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),});
                jTable3.setModel(mod3);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
//    public void loadTableJasa(){
//        jTable3.getTableHeader().setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
//        jTable3.getTableHeader().setOpaque(false);
//        jTable3.getTableHeader().setBackground(new Color(255,144,39));
//        jTable3.getTableHeader().setForeground(new Color(255,255,255));
//        jTable3.getRowHeight(30);
//        mod2 = new DefaultTableModel();
//        mod2.addColumn("ID Produk");
//        mod2.addColumn("Nama Produk");
//        mod2.addColumn("Satuan");
//        mod2.addColumn("Harga Jual");
//        jTable3.setModel(mod2);
//        try {
//            String sqll = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.satuan, tb_produk.harga_jual\n"
//                    + "FROM tb_produk\n"
//                    + "WHERE tb_produk.satuan = 'jasa'";
//            java.sql.Connection con = (Connection)konekdb.GetConnection();
//            java.sql.Statement st = con.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sqll);
//            while (res.next()) {                
//                mod2.addRow(new Object[]{
//                    res.getString(1),
//                    res.getString(2),
//                    res.getString(3),
//                    res.getString(4)
//                });
//                jTable3.setModel(mod2);
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }

    public void auto_numberTable() {
        int idk = jTable1.getRowCount();
        if (idk > 0) {
            for (int i = 0; i < idk; i++) {
                String jumlah = (String) jTable1.getValueAt(i, 0);
                int auto_id = Integer.parseInt(jumlah.substring(jumlah.length() - 3)) + 1;
                String no = String.valueOf(auto_id);
                int NomorJual = no.length();
                //MENGATUR jumlah 0
                for (int j = 0; j < 3 - NomorJual; j++) {
                    no = "0" + no;
                }
                id = no;
            }
        } else {
            id = "001";
        }
    }
//    public void diskon(){
//        int sub = totalSemua;
//        double sube = Double.valueOf(totalSemua);
//        int disk;
//        if (!(txt_diskonRP.getText().equals(""))) {
//            String dik = txt_diskonRP.getText();
//            double diks = Double.valueOf(dik);
//            disk = Integer.parseInt(txt_diskonRP.getText());
//            if (disk == 0) {
//                int ttl = sub;
//                txt_totalAfterDiskon.setText(Integer.toString(ttl));
//            } else if (disk > 0) {
//                double persen = diks/sube*100;
//                int ttl = sub - disk;
//                if (!(ttl < 0)) {
//                    txt_diskonPersen.setText(String.valueOf(Math.round(persen)));
//                    txt_totalAfterDiskon.setText(Integer.toString(ttl));
//                    txt_nominal.setText("");
//                } else {
//                    txt_diskonRP.setText("");
//                    txt_diskonPersen.setText("");
//                    JOptionPane.showMessageDialog(null, "Diskon tidak boleh lebih dari Sub Total!");
//                }
//            }
//        } else {
//            txt_totalAfterDiskon.setText("");
//            txt_diskonPersen.setText("");
//            
//        }
//    }
//    public void diskonPersen(){
//        int sub = totalSemua;
//        int disk;
//        if (!(txt_diskonPersen.getText().equals(""))) {
//            disk = Integer.parseInt(txt_diskonPersen.getText());
//            if (!(disk > 100)) {
//                if (disk == 0) {
//                    int ttl = sub;
//                    txt_totalAfterDiskon.setText(Integer.toString(ttl));
//                } else if (disk > 0) {
//                    int persen = sub * disk / 100;
//                    int ttl = sub - disk;
//                    if (!(ttl < 0)) {
//                        txt_diskonRP.setText(String.valueOf(persen));
//                        int diskharga = Integer.parseInt(txt_diskonRP.getText());
//                        int totalnya = sub - diskharga;
//                        txt_totalAfterDiskon.setText(Integer.toString(totalnya));
//                        txt_nominal.setText("");
//                    } else {
//                        txt_diskonRP.setText("");
//                        JOptionPane.showMessageDialog(null, "Diskon tidak boleh lebih dari Sub Total!");
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Diskon maksimal 100%");
//                txt_diskonPersen.setText("");
//                txt_diskonRP.setText("");
//            }
//        } else {
//            txt_totalAfterDiskon.setText("");
//            txt_diskonRP.setText("");
//        }
//    }

    public void kode_transaksi() {
        DateFormat vblnth = new SimpleDateFormat("yyyyMMdd");
        String blnth = vblnth.format(Calendar.getInstance().getTime());

        DateFormat hari = new SimpleDateFormat("yyyy-MM-dd");
        String a = hari.format(Calendar.getInstance().getTime());
        try {
            String sql = "SELECT MAX(right(id_transaksi,6)) AS Kode_Pinjam "
                    + "FROM tb_beli Where tgl_transaksi like '" + a + "';";
            java.sql.Connection con = (java.sql.Connection) konekdb.GetConnection();
            java.sql.Statement pst = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                if (rs.last()) {
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txt_idTransaksi.setText("TB/" + blnth + "/" + no);
                    System.out.println("TB/" + blnth + "/" + no);
                    txt_TanggalTransaksi.setText(a);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR " + e.getMessage());
        }
    }

    public void hitung() {
//        auto_numberTable();
//        int total = 0;
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            int amount = Integer.parseInt((String) jTable1.getValueAt(i, 5).toString());
//            total += amount;
//        }
//        System.out.println(total);
//        total_harga.setText(Integer.toString(total));
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
//        txTotalBayar.setText(String.valueOf(totalBiaya));
        System.out.println(totalBiaya);
        total_harga.setText("Rp" + totalBiaya + "");
        totalSemua = totalBiaya;

    }

    public void cari() {
        if (txt_KodeProduk.getText().equals("")) {
            txt_namaProduk.setText("");
            txt_hargaSatuan.setText("");
            txt_Jumlah.setText("");
//            txt_stok.setText("");
        } else {
            try {
                String sql = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.harga_jual, tb_stokbarang.stok_produk "
                        + "FROM tb_produk LEFT JOIN tb_stokbarang ON tb_produk.id_produk = tb_stokbarang.id_produk WHERE tb_produk.id_produk like '%" + txt_KodeProduk.getText() + "%'";
                java.sql.Connection conn = (Connection) konekdb.GetConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                if (res.next()) {
                    if (txt_KodeProduk.getText().equals(res.getString("id_produk"))) {
                        txt_namaProduk.setText(res.getString("nama_produk"));
                        txt_hargaSatuan.setText(res.getString("harga_jual"));
                        txt_Jumlah.requestFocus();
                        txt_Jumlah.setEditable(true);

                    } else if (txt_KodeProduk.getText().isEmpty()) {
                        txt_namaProduk.setText("");
                        txt_hargaSatuan.setText("");
                        txt_Jumlah.setText("");
//                        txt_stok.setText("");
//                        stok_prod.setText("");
                    } else {
                        txt_namaProduk.setText("");
                        txt_hargaSatuan.setText("");
                        txt_Jumlah.setText("");
//                        txt_stok.setText("");
                    }
                } else {
                    txt_namaProduk.setText("");
                    txt_hargaSatuan.setText("");
                    txt_Jumlah.setText("");
//                    txt_stok.setText("");
                }
            } catch (Exception e) {
//                notif_produk.setText("Produk Tidak Terdaftar");
//                JOptionPane.showMessageDialog(this, "Produk Tidak Terdaftar");
//                txt_namaProduk.setText("");
//                txt_hargaSatuan.setText("");
//                txt_Jumlah.setText("");
//                txt_stok.setText("");
                System.err.println(e.getMessage());
            }
        }
    }

    public void simpanKeranjang() {

        if (!(txt_KodeProduk.getText().equals(""))) {
            if (!(txt_Jumlah.getText().equals(""))) {

                int juml = Integer.parseInt(txt_Jumlah.getText());
                int har = Integer.parseInt(txt_hargaSatuan.getText());
                int total = har * juml;
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                Object row[] = {
                    id,
                    txt_KodeProduk.getText(),
                    txt_namaProduk.getText(),
                    txt_hargaSatuan.getText(),
                    txt_Jumlah.getText(),
                    total
                };
                model.addRow(row);

                //Hapus top
                txt_KodeProduk.setText("");
                txt_namaProduk.setText("");
                txt_hargaSatuan.setText("");
                txt_Jumlah.setText("");
//                txt_stok.setText("");

                //Hapus Bottom
                txt_idPemasok.setText("");
//                    diskon_persen.setText("");
                txt_namaPemasok.setText("");
                txt_nominal.setText("");
//                txt_kembalian.setText("");

                txt_KodeProduk.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kode Produk tidak boleh kosong");
        }
    }

//    public void kembalian() {
//        if (txt_namaPemasok.getText().equals("")) {
//            txt_kembalian.setText("-");
//        } else if (txt_namaPemasok.getText().isEmpty()) {
//            txt_kembalian.setText("-");
//        } else if (!(txt_nominal.getText().equals(""))) {
//            int sub = Integer.parseInt(txt_namaPemasok.getText());
//            int disk;
//            disk = Integer.parseInt(txt_nominal.getText());
//            int ttl = disk - sub;
//            if (!(ttl < 0)) {
//                txt_kembalian.setText(Integer.toString(ttl));
//            } else {
//                txt_kembalian.setText("-");
//            }
//        } else {
//            txt_kembalian.setText("-");
//        }
//    }
    public void cetakStruk() {

    }

    public void bayar() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int jumlah_baris = jTable1.getRowCount();
        System.out.println(jumlah_baris);
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tabel Keranjang masih kosong!");
        } else {
            try {
                String sql = "INSERT INTO `tb_beli`(`id_transaksi`, "
                        + "`tgl_transaksi`,`id_pengguna`, `id_pemasok`,"
                        + "`total_harga`) VALUES ('" + txt_idTransaksi.getText() + "',"
                        + "'" + txt_TanggalTransaksi.getText() + "', '" + txt_Kasir.getText() + "', '" + txt_idPemasok.getText() + "',"
                        + "" + totalSemua + ")";

                java.sql.Connection con = (Connection) konekdb.GetConnection();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                try {
                    for (int i = 0; i < jumlah_baris; i++) {
//                            String jumlah = (String) model.getValueAt(i, 4).toString();
//                            String harga = (String) model.getValueAt(i, 5).toString();
//                            String id_prodk = (String) model.getValueAt(i, 1).toString();
//                            String nama_p = (String) model.getValueAt(i, 2).toString();
//                            String har = (String) model.getValueAt(i, 3).toString();


                        String idProduk = model.getValueAt(i, 1).toString();
                        String namaProduk = model.getValueAt(i, 2).toString();
                        String hargaSatuan = model.getValueAt(i, 3).toString();
                        String Qty = model.getValueAt(i, 4).toString();
                        String totalHarga = model.getValueAt(i, 5).toString();
                        String sql1 = "INSERT INTO `tb_detailbeli`(`id_transaksi`, `id_produk`, `nama_produk`, `jumlah_produk`, `harga_beli`, `total_harga`) VALUES ("
                                + "'" + txt_idTransaksi.getText() + "', '" + idProduk + "', '" + namaProduk + "', " + Qty + ", " + hargaSatuan + ", " + totalHarga + ")";
                        java.sql.PreparedStatement ps1 = con.prepareStatement(sql1);
                        ps1.execute();

                    }
                    JOptionPane.showMessageDialog(this, "Berhasil Tersimpan");
                    try {
                        String namaFile = "src" + File.separator + "TransaksiBeli" + File.separator + "StrukBeli.jasper";
                        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/d'kazieree", "root", "");
                        HashMap hash = new HashMap();
                        hash.put("kodeTransaksi", txt_idTransaksi.getText());
                        File file = new File(namaFile);
                        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());

                    }
                    for (int i = jumlah_baris - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }

                    //Hapus top
                    txt_KodeProduk.setText("");
                    txt_namaProduk.setText("");
                    txt_hargaSatuan.setText("");
                    txt_Jumlah.setText("");

                            
                            String idProduk = model.getValueAt(i, 1).toString();
                            String namaProduk = model.getValueAt(i, 2).toString();
                            String hargaSatuan = model.getValueAt(i, 3).toString();
                            String Qty = model.getValueAt(i, 4).toString();
                            String totalHarga = model.getValueAt(i, 5).toString();
                            
                            
                            String sql1 = "INSERT INTO `tb_detailbeli`(`id_transaksi`, `id_produk`, `nama_produk`, `jumlah_produk`, `harga_beli`, `total_harga`) VALUES ("
                                    + "'"+ txt_idTransaksi.getText() +"', '" + idProduk + "', '" + namaProduk + "', " + Qty + ", " + hargaSatuan + ", " + totalHarga + ")";
                            java.sql.PreparedStatement ps1 = con.prepareStatement(sql1);
                            ps1.execute();
                       
                          
                            
                        }
                        JOptionPane.showMessageDialog(this, "Berhasil Tersimpan");
                        try {
                            String namaFile = "src" + File.separator + "TransaksiBeli" + File.separator + "StrukBeliBesar.jasper";
                            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/d'kazieree", "root", "");
                            HashMap hash = new HashMap();
                            hash.put("kodeTransaksi", txt_idTransaksi.getText());
                            File file = new File(namaFile);
                            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                            JasperViewer.viewReport(jasperPrint, false);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());

                        }
                        for (int i = jumlah_baris - 1; i >= 0; i--) {
                            model.removeRow(i);
                        }

                        //Hapus top
                        txt_KodeProduk.setText("");
                        txt_namaProduk.setText("");
                        txt_hargaSatuan.setText("");
                        txt_Jumlah.setText("");

//                        txt_stok.setText("");

                    //Hapus Bottom
                    txt_idPemasok.setText("");
//                        txt_diskonPersen.setText("");
                    txt_namaPemasok.setText("");
                    txt_nominal.setText("");
//                    txt_kembalian.setText("");

                    txt_KodeProduk.requestFocus();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan! Error : " + e);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }

    private void cariData(String key) {
        try {
            Object[] judul_kolom = {"ID Produk", "Nama Produk", "Satuan", "Harga Jual", "Stok"};
            DefaultTableModel tabModel = new DefaultTableModel(null, judul_kolom);
            jTable2.setModel(tabModel);

            Connection conn = (Connection) konekdb.GetConnection();
            Statement st = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            ResultSet rst;
//            rst = st.executeQuery("SELECT * FROM tb_supir WHERE nama_supir LIKE '%"+ key + "%' OR alamat_supir LIKE '%" + key 
//            + "%' OR no_telp_supir LIKE '%"+ key +"%'");

            rst = st.executeQuery("SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.satuan, tb_produk.harga_jual, tb_stokbarang.stok_produk FROM tb_produk JOIN tb_stokbarang\n"
                    + "ON tb_produk.id_produk = tb_stokbarang.id_produk\n"
                    + "WHERE tb_produk.nama_produk LIKE '%" + key + "%' OR tb_produk.satuan LIKE '%" + key + "%'");
            while (rst.next()) {
                Object[] data = {
                    rst.getString("id_produk"),
                    rst.getString("nama_produk"),
                    rst.getString("satuan"),
                    rst.getString("harga_jual"),
                    rst.getString("stok_produk")
                };
                tabModel.addRow(data);

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void cariDataPemasok(String key) {
        try {
            Object[] judul_kolom = {"ID Pemasok", "Nama Pemasok", "Nama Usaha", "Alamat Usaha", "Nomor Telepon Pemasok"};
            DefaultTableModel tabModel = new DefaultTableModel(null, judul_kolom);
            jTable3.setModel(tabModel);

            Connection conn = (Connection) konekdb.GetConnection();
            Statement st = conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            ResultSet rst;
//            rst = st.executeQuery("SELECT * FROM tb_supir WHERE nama_supir LIKE '%"+ key + "%' OR alamat_supir LIKE '%" + key 
//            + "%' OR no_telp_supir LIKE '%"+ key +"%'");

            rst = st.executeQuery("SELECT * FROM `tb_pemasok`"
                    + "WHERE id_pemasok LIKE '%" + key + "%' OR nama_pemasok LIKE '%" + key + "%' OR nama_usaha LIKE '%" + key + "%'"
                    + "OR alamat_usaha LIKE '%" + key + "%' OR no_telp_pemasok LIKE '%" + key + "%'");
            while (rst.next()) {
                Object[] data = {
                    rst.getString("id_pemasok"),
                    rst.getString("nama_pemasok"),
                    rst.getString("nama_usaha"),
                    rst.getString("alamat_usaha"),
                    rst.getString("no_telp_pemasok")
                };
                tabModel.addRow(data);

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
//    public void cariDataJasa(String key1){
//        try {
//            Object[] judul_kolom = {"ID Produk", "Nama Produk", "Satuan", "Harga Jual"};
//            DefaultTableModel tabModel = new DefaultTableModel(null, judul_kolom);
//            jTable3.setModel(tabModel);
//            
//            Connection conn = (Connection)konekdb.GetConnection();
//            Statement st = conn.createStatement();
//            tabModel.getDataVector().removeAllElements();
//            ResultSet rst;
////            rst = st.executeQuery("SELECT * FROM tb_supir WHERE nama_supir LIKE '%"+ key + "%' OR alamat_supir LIKE '%" + key 
////            + "%' OR no_telp_supir LIKE '%"+ key +"%'");
//            
////            rst = st.executeQuery("SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.satuan, tb_produk.harga_jual, tb_stokbarang.stok_produk FROM tb_produk JOIN tb_stokbarang\n"
////                    + "ON tb_produk.id_produk = tb_stokbarang.id_produk\n"
////                    + "WHERE tb_produk.nama_produk LIKE '%"+key1+"%' OR tb_produk.satuan LIKE '%"+key1+"%'");
//            rst = st.executeQuery("SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.satuan, tb_produk.harga_jual\n"
//                    + "FROM tb_produk\n"
//                    + "WHERE tb_produk.satuan = 'jasa' AND tb_produk.nama_produk LIKE '%"+key1+"%'");
//            while(rst.next()){
//                Object[] data = {
//                    rst.getString("id_produk"),
//                    rst.getString("nama_produk"),
//                    rst.getString("satuan"),
//                    rst.getString("harga_jual")
//                };
//                tabModel.addRow(data);
//            
//            }
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//    
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        FieldCariPemasok = new Swing.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        btn_pilihPemasok = new Swing.Button();
        button8 = new Swing.Button();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        FieldCariProdukBarang = new Swing.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        btn_pilihBarang = new Swing.Button();
        button7 = new Swing.Button();
        jLabel4 = new javax.swing.JLabel();
        button6 = new Swing.Button();
        button5 = new Swing.Button();
        button4 = new Swing.Button();
        total_harga = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nominal = new Swing.TextField();
        txt_namaPemasok = new Swing.TextField();
        txt_idPemasok = new Swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        }
        ;
        button3 = new Swing.Button();
        button2 = new Swing.Button();
        button1 = new Swing.Button();
        txt_Jumlah = new Swing.TextField();
        txt_hargaSatuan = new Swing.TextField();
        txt_namaProduk = new Swing.TextField();
        txt_KodeProduk = new Swing.TextField();
        txt_Kasir = new Swing.TextField();
        txt_TanggalTransaksi = new Swing.TextField();
        txt_idTransaksi = new Swing.TextField();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(960, 707));
        setMinimumSize(new java.awt.Dimension(960, 707));
        setPreferredSize(new java.awt.Dimension(960, 707));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FieldCariPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        FieldCariPemasok.setLabelText("Cari Pemasok");
        FieldCariPemasok.setLineColor(new java.awt.Color(255, 144, 39));
        FieldCariPemasok.setOpaque(false);
        FieldCariPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCariPemasokActionPerformed(evt);
            }
        });
        FieldCariPemasok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FieldCariPemasokKeyReleased(evt);
            }
        });
        jPanel2.add(FieldCariPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 400, 50));

        jScrollPane3.setBorder(null);

        jTable3.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Produk", "Nama Produk", "Satuan", "Stok", "Harga Jual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(102, 102, 102));
        jTable3.setRowHeight(30);
        jTable3.setSelectionBackground(new java.awt.Color(253, 144, 39));
        jTable3.setShowVerticalLines(false);
        jTable3.getTableHeader().setResizingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 860, 430));

        btn_pilihPemasok.setBackground(new java.awt.Color(51, 249, 51));
        btn_pilihPemasok.setForeground(new java.awt.Color(255, 255, 255));
        btn_pilihPemasok.setText("Pilih");
        btn_pilihPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        btn_pilihPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihPemasokActionPerformed(evt);
            }
        });
        jPanel2.add(btn_pilihPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, 80, 30));

        button8.setBackground(new java.awt.Color(255, 51, 51));
        button8.setForeground(new java.awt.Color(255, 255, 255));
        button8.setText("Batal");
        button8.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        jPanel2.add(button8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 630, 80, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/Group 129.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 690));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FieldCariProdukBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        FieldCariProdukBarang.setLabelText("Cari Barang");
        FieldCariProdukBarang.setLineColor(new java.awt.Color(255, 144, 39));
        FieldCariProdukBarang.setOpaque(false);
        FieldCariProdukBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCariProdukBarangActionPerformed(evt);
            }
        });
        FieldCariProdukBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FieldCariProdukBarangKeyReleased(evt);
            }
        });
        jPanel1.add(FieldCariProdukBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 400, 50));

        jScrollPane2.setBorder(null);

        jTable2.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Produk", "Nama Produk", "Satuan", "Stok", "Harga Jual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(102, 102, 102));
        jTable2.setRowHeight(30);
        jTable2.setSelectionBackground(new java.awt.Color(253, 144, 39));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 860, 430));

        btn_pilihBarang.setBackground(new java.awt.Color(51, 249, 51));
        btn_pilihBarang.setForeground(new java.awt.Color(255, 255, 255));
        btn_pilihBarang.setText("Pilih");
        btn_pilihBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        btn_pilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihBarangActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pilihBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, 80, 30));

        button7.setBackground(new java.awt.Color(255, 51, 51));
        button7.setForeground(new java.awt.Color(255, 255, 255));
        button7.setText("Batal");
        button7.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        jPanel1.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 630, 80, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/CariBarang.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 690));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        button6.setBackground(new java.awt.Color(0, 51, 51));
        button6.setForeground(new java.awt.Color(255, 255, 255));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiJual/refresh.png"))); // NOI18N
        button6.setText("Transaksi Baru");
        button6.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        getContentPane().add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 610, 150, 40));

        button5.setBackground(new java.awt.Color(255, 127, 0));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setText("Pilih Pemasok");
        button5.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        getContentPane().add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 120, 40));

        button4.setBackground(new java.awt.Color(0, 51, 51));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Simpan");
        button4.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        getContentPane().add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 150, 40));

        total_harga.setFont(new java.awt.Font("Quicksand Medium", 0, 40)); // NOI18N
        total_harga.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        total_harga.setText("-");
        total_harga.setToolTipText("");
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 270, 70));

        jLabel6.setFont(new java.awt.Font("Quicksand Medium", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Total");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, 140, 30));

        txt_nominal.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_nominal.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_nominal.setLabelText("Total");
        txt_nominal.setLineColor(new java.awt.Color(253, 144, 39));
        txt_nominal.setOpaque(false);
        txt_nominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nominalActionPerformed(evt);
            }
        });
        txt_nominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nominalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nominalKeyTyped(evt);
            }
        });
        getContentPane().add(txt_nominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 240, 40));

        txt_namaPemasok.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_namaPemasok.setEnabled(false);
        txt_namaPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_namaPemasok.setLabelText("Nama Pemasok");
        txt_namaPemasok.setLineColor(new java.awt.Color(253, 144, 39));
        txt_namaPemasok.setOpaque(false);
        getContentPane().add(txt_namaPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 240, 40));

        txt_idPemasok.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_idPemasok.setEnabled(false);
        txt_idPemasok.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_idPemasok.setLabelText("ID Pemasok");
        txt_idPemasok.setLineColor(new java.awt.Color(253, 144, 39));
        txt_idPemasok.setOpaque(false);
        txt_idPemasok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idPemasokKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idPemasokKeyTyped(evt);
            }
        });
        getContentPane().add(txt_idPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 240, 40));

        jScrollPane1.setBorder(null);

        jTable1.setFont(new java.awt.Font("Quicksand Medium", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Produk", "Nama Produk", "Satuan", "Qty", "Harga Jual", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(253, 144, 39));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setHeaderValue("No");
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Total Harga");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 850, 160));

        button3.setBackground(new java.awt.Color(255, 78, 78));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Hapus");
        button3.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        getContentPane().add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 90, 40));

        button2.setBackground(new java.awt.Color(95, 249, 90));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Tambah Ke Keranjang");
        button2.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        button2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                button2KeyTyped(evt);
            }
        });
        getContentPane().add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 190, 40));

        button1.setBackground(new java.awt.Color(0, 51, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Cari Produk");
        button1.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 100, 40));

        txt_Jumlah.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_Jumlah.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_Jumlah.setLabelText("Jumlah");
        txt_Jumlah.setLineColor(new java.awt.Color(253, 144, 39));
        txt_Jumlah.setOpaque(false);
        txt_Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_JumlahActionPerformed(evt);
            }
        });
        txt_Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_JumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_JumlahKeyTyped(evt);
            }
        });
        getContentPane().add(txt_Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 120, 50));

        txt_hargaSatuan.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_hargaSatuan.setEnabled(false);
        txt_hargaSatuan.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_hargaSatuan.setLabelText("Harga Satuan");
        txt_hargaSatuan.setLineColor(new java.awt.Color(253, 144, 39));
        txt_hargaSatuan.setOpaque(false);
        getContentPane().add(txt_hargaSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 150, 50));

        txt_namaProduk.setDisabledTextColor(new java.awt.Color(0, 204, 153));
        txt_namaProduk.setEnabled(false);
        txt_namaProduk.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_namaProduk.setLabelText("Nama Produk");
        txt_namaProduk.setLineColor(new java.awt.Color(253, 144, 39));
        txt_namaProduk.setOpaque(false);
        getContentPane().add(txt_namaProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 440, 50));

        txt_KodeProduk.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_KodeProduk.setLabelText("Kode Produk/Barcode");
        txt_KodeProduk.setLineColor(new java.awt.Color(253, 144, 39));
        txt_KodeProduk.setOpaque(false);
        txt_KodeProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_KodeProdukActionPerformed(evt);
            }
        });
        txt_KodeProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_KodeProdukKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_KodeProdukKeyTyped(evt);
            }
        });
        getContentPane().add(txt_KodeProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 153, 280, -1));

        txt_Kasir.setEnabled(false);
        txt_Kasir.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_Kasir.setLabelText("Kasir");
        txt_Kasir.setLineColor(new java.awt.Color(253, 144, 39));
        txt_Kasir.setOpaque(false);
        getContentPane().add(txt_Kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 280, 40));

        txt_TanggalTransaksi.setEnabled(false);
        txt_TanggalTransaksi.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_TanggalTransaksi.setLabelText("Tanggal Transaksi");
        txt_TanggalTransaksi.setLineColor(new java.awt.Color(253, 144, 39));
        txt_TanggalTransaksi.setOpaque(false);
        getContentPane().add(txt_TanggalTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 260, 40));

        txt_idTransaksi.setEnabled(false);
        txt_idTransaksi.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        txt_idTransaksi.setLabelText("Kode Transaksi");
        txt_idTransaksi.setLineColor(new java.awt.Color(253, 144, 39));
        txt_idTransaksi.setOpaque(false);
        getContentPane().add(txt_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 280, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TransaksiBeli/Group 87 (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
//        kolom();

        if (txt_namaProduk.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tekan Enter Setelah ID Produk Terisi");
            txt_KodeProduk.requestFocus();
        } else if (txt_Jumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Jumlah produk tidak boleh kosong");
        } else {
            auto_numberTable();
            simpanKeranjang();
            hitung();
        }


    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (!(jTable1.getSelectedRow() < 0)) {
            int jawab = JOptionPane.showOptionDialog(this,
                    "Ingin menghapus tabel yang pilih?",
                    "Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (jawab == JOptionPane.YES_OPTION) {
                model.removeRow(jTable1.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Baris yang dipilih terhapus");
                hitung();
                //Hapus top
                txt_KodeProduk.setText("");
                txt_namaProduk.setText("");
                txt_hargaSatuan.setText("");
                txt_Jumlah.setText("");
//                txt_stok.setText("");

                //Hapus Bottom
                txt_idPemasok.setText("");
//                    diskon_persen.setText("");
                txt_namaPemasok.setText("");
                txt_nominal.setText("");
//                txt_kembalian.setText("");

                txt_KodeProduk.requestFocus();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih baris yang ingin dihapus");
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        int hitungTabel = jTable1.getRowCount();
        int opsi1 = JOptionPane.showOptionDialog(this,
                "Ingin menyimpan transaksi?",
                "Pesan Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (hitungTabel == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang Masih Kosong");
        } else if (txt_idPemasok.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "   Silahkan Pilih "
                    + "Pemasok Terlebih Dahulu");

        } else if (opsi1 == JOptionPane.YES_OPTION) {
            bayar();

        }


    }//GEN-LAST:event_button4ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getRowCount();
        System.out.println(baris);
        int opsi = JOptionPane.showOptionDialog(this,
                "Ingin memulai transaksi baru lagi?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (baris < 1) {
            JOptionPane.showMessageDialog(this, "Keranjang Kosong atau Tidak ada yang dibershikan");

        } else if (opsi == JOptionPane.YES_OPTION) {
            txt_KodeProduk.setText("");
            txt_KodeProduk.setText("");
            txt_namaProduk.setText("");
            txt_Jumlah.setText("");
//        txt_stok.setText("");

            //Hapus Bottom
//            txt_diskonPersen.setText("");
            txt_idPemasok.setText("");
            txt_namaPemasok.setText("");
            txt_nominal.setText("");
//            txt_kembalian.setText("");

            //Hapus Tabel
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            if (!(baris < 0)) {
                for (int i = baris - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
            }
            kode_transaksi();
            txt_KodeProduk.requestFocus();

        }


    }//GEN-LAST:event_button6ActionPerformed

    private void txt_KodeProdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_KodeProdukKeyReleased
        // TODO add your handling code here:
        String key = txt_KodeProduk.getText();
        System.out.println(key);
        txt_KodeProduk.setText(txt_KodeProduk.getText().toUpperCase());
        cari();
//        if (key != "") {
//            cari(key);
//        } else {
//            notif_produk.setText("Produk Tidak Tersedia");
//        }

//        if (!txt_KodeProduk) {
//            
//        }
//        if (id_prod.getText().equals("")) {
//            txt_namaProduk.setText("");
//            txt_hargaSatuan.setText("");
//            txt_Jumlah.setText("");
//            txt_stok.setText("");
//        } else {
//            try {
//                String sql = "SELECT tb_produk.id_produk, tb_produk.nama_produk, tb_produk.harga_jual, tb_stokbarang.stok_produk "
//                        + "FROM tb_produk LEFT JOIN tb_stokbarang ON tb_produk.id_produk = tb_stokbarang.id_produk WHERE tb_produk.id_produk like '" + id_prod.getText() + "'";
//                java.sql.Connection conn = (Connection) konekdb.GetConnection();
//                java.sql.Statement stm = conn.createStatement();
//                java.sql.ResultSet res = stm.executeQuery(sql);
//                if (res.next()) {
//                    if (id_prod.getText().equals(res.getString("id_produk"))) {
//                        txt_namaProduk.setText(res.getString("nama_produk"));
//                        txt_hargaSatuan.setText(res.getString("harga_jual"));
//                        txt_Jumlah.requestFocus();
//                        txt_Jumlah.setEditable(true);
//                        String stoknya = res.getString("stok_produk");
//                        if (stoknya == null) {
////                            stok_prod.setText("Tersedia");
//                            txt_stok.setText("1");
//                            txt_Jumlah.setText("1");
//                            txt_Jumlah.setEditable(false);
//                        } else {
//                            txt_stok.setText(res.getString("stok_produk"));
////                            stok_prod.setText(res.getString(4));
//                        }
//                    } else if (id_prod.getText().isEmpty()) {
//                        txt_namaProduk.setText("");
//                        txt_hargaSatuan.setText("");
//                        txt_Jumlah.setText("");
//                        txt_stok.setText("");
////                        stok_prod.setText("");
//                    } else {
//                        txt_namaProduk.setText("");
//                        txt_hargaSatuan.setText("");
//                        txt_Jumlah.setText("");
//                        txt_stok.setText("");
//                    } 
//                }else {
//                    txt_namaProduk.setText("");
//                    txt_hargaSatuan.setText("");
//                    txt_Jumlah.setText("");
//                    txt_stok.setText("");
//                }
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
////        }
    }//GEN-LAST:event_txt_KodeProdukKeyReleased

    private void txt_KodeProdukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_KodeProdukKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();

        }
    }//GEN-LAST:event_txt_KodeProdukKeyTyped

    private void txt_JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_JumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_JumlahActionPerformed

    private void txt_JumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_JumlahKeyReleased
        // TODO add your handling code here:
//        if (!(txt_stok.getText().equals(""))) {
//            if (!(txt_stok.getText().equals("Tersedia"))) {
//                if (!(txt_Jumlah.getText().equals(""))) {
//                    int stk = Integer.parseInt(txt_stok.getText());
//                    int jml = Integer.parseInt(txt_Jumlah.getText());
//                    if (jml > stk) {
//                        JOptionPane.showMessageDialog(this, "Jumlah Tidak Boleh Lebih Dari Stok!!");
//                        txt_Jumlah.setText("");
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_txt_JumlahKeyReleased

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
//            this.getDesktopPane().add(new data_barang()).setVisible(true);
        jPanel1.setVisible(true);
        jTable1.getTableHeader().disable();
        loadTableProduk();
    }//GEN-LAST:event_button1ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
        int opsi1 = JOptionPane.showOptionDialog(this,
                "Ingin membatalkan memilih barang?",
                "Konfirmasi Pilihan",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (opsi1 == JOptionPane.YES_OPTION) {
            jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_button7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int baris = jTable2.getSelectedRow();
        id_produk = jTable2.getValueAt(baris, 0).toString();
        System.out.println(id_produk);
    }//GEN-LAST:event_jTable2MouseClicked

    private void btn_pilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihBarangActionPerformed
        // TODO add your handling code here:
        int pilihan = jTable2.getSelectedRow();

        if (pilihan < 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada barang yang dipilih");
        } else {
            txt_KodeProduk.setText(id_produk);
            jPanel1.setVisible(false);
            jTable2.getTableHeader().enable();
        }

    }//GEN-LAST:event_btn_pilihBarangActionPerformed

    private void txt_idPemasokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idPemasokKeyReleased
        // TODO add your handling code here:
//        diskon();
    }//GEN-LAST:event_txt_idPemasokKeyReleased

    private void txt_idPemasokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idPemasokKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = txt_idPemasok.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_idPemasokKeyTyped

    private void txt_nominalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nominalKeyReleased
        // TODO add your handling code here:
//        kembalian();
    }//GEN-LAST:event_txt_nominalKeyReleased

    private void txt_JumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_JumlahKeyTyped
        // TODO add your handling code here:
//        ((AbstractDocument) txt_Jumlah.getDocument()).setDocumentFilter(new CustomDocumentFilter());

        char c = evt.getKeyChar();
        int panjang = txt_Jumlah.getText().length();
        if ((this.txt_Jumlah.getText().isEmpty() && (!Character.isDigit(c) || c == '0')) || !(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_JumlahKeyTyped

    private void txt_nominalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nominalKeyTyped
        // TODO add your handling code here:
        char k = evt.getKeyChar();
        int panjang = txt_nominal.getText().length();
        if (!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE || k == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
        }
        if (panjang > 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nominalKeyTyped

    private void FieldCariProdukBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCariProdukBarangKeyReleased
        String key = FieldCariProdukBarang.getText();
        System.out.println(key);

        if (key != "") {
            cariData(key);
        } else {
            loadTableProduk();
        }
    }//GEN-LAST:event_FieldCariProdukBarangKeyReleased

    private void FieldCariPemasokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FieldCariPemasokKeyReleased
        String key = FieldCariPemasok.getText();
        System.out.println(key);

        if (key != "") {
            cariDataPemasok(key);
        } else {
            loadTablePemasok();
        }
    }//GEN-LAST:event_FieldCariPemasokKeyReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int baris = jTable3.getSelectedRow();
        id_pemasok = jTable3.getValueAt(baris, 0).toString();
        namaPemasok = jTable3.getValueAt(baris, 1).toString();
        System.out.println(id_pemasok);
        System.out.println(namaPemasok);
    }//GEN-LAST:event_jTable3MouseClicked

    private void btn_pilihPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihPemasokActionPerformed
        txt_idPemasok.setText(id_pemasok);
        txt_namaPemasok.setText(namaPemasok);
        jPanel2.setVisible(false);
        jTable3.getTableHeader().enable();
    }//GEN-LAST:event_btn_pilihPemasokActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed

        int opsi1 = JOptionPane.showOptionDialog(this,
                "Batal Memilih Pemasok?",
                "Pesan Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (opsi1 == JOptionPane.YES_OPTION) {
            jPanel2.setVisible(false);
        }


    }//GEN-LAST:event_button8ActionPerformed

    private void FieldCariPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCariPemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCariPemasokActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        jPanel2.setVisible(true);
        jTable3.getTableHeader().disable();
        loadTablePemasok();
    }//GEN-LAST:event_button5ActionPerformed

    private void FieldCariProdukBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCariProdukBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCariProdukBarangActionPerformed

    private void txt_KodeProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_KodeProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_KodeProdukActionPerformed

    private void txt_nominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nominalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nominalActionPerformed

    private void button2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_button2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_button2KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.TextField FieldCariPemasok;
    private Swing.TextField FieldCariProdukBarang;
    private Swing.Button btn_pilihBarang;
    private Swing.Button btn_pilihPemasok;
    private Swing.Button button1;
    private Swing.Button button2;
    private Swing.Button button3;
    private Swing.Button button4;
    private Swing.Button button5;
    private Swing.Button button6;
    private Swing.Button button7;
    private Swing.Button button8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel total_harga;
    private Swing.TextField txt_Jumlah;
    private Swing.TextField txt_Kasir;
    public Swing.TextField txt_KodeProduk;
    private Swing.TextField txt_TanggalTransaksi;
    private Swing.TextField txt_hargaSatuan;
    private Swing.TextField txt_idPemasok;
    private Swing.TextField txt_idTransaksi;
    private Swing.TextField txt_namaPemasok;
    private Swing.TextField txt_namaProduk;
    private Swing.TextField txt_nominal;
    // End of variables declaration//GEN-END:variables
}
