/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Lutfi
 */
public class user {
    
    public static String nama;
    public static String id_pengguna;
    public static String hak_akses;

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        user.nama = nama;
    }

    public static String getId_pengguna() {
        return id_pengguna;
    }

    public static void setId_pengguna(String id_pengguna) {
        user.id_pengguna = id_pengguna;
    }

    public static String getHak_akses() {
        return hak_akses;
    }

    public static void setHak_akses(String hak_akses) {
        user.hak_akses = hak_akses;
    }
    
}
