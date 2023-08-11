import java.util.ArrayList;

public class ViewData {
    public static void viewCategory(ArrayList<String> category){
        System.out.println("\n=========== Pilih Kategori ===========");

        // Menampilkan daftar kategori yang tersedia
        for (int i = 0; i < category.size(); i++) {
            System.out.println(">  "+(i + 1) + ". " + category.get(i));
        }
        System.out.println(">  0. Kembali");

        if (category.size() == 1) {
            System.out.print("Pilih angka kategori : ");
        } else {
            System.out.print("Pilih kategori dari 1 s/d "+category.size()+": ");
        }
    }

    public static  void viewBarangByNama(ArrayList<ArrayList<String>> warehouse, int namaBarang){
        // Menampilkan data sesuai pilihan kategori dan nama barang
        ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
        System.out.println("\nKategori: " + selectedBarangData.get(0));
        System.out.println("Nama Barang: " + selectedBarangData.get(1));
        System.out.println("Stok Barang: " + selectedBarangData.get(2));
    }
}
