import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorHandling {
    public static boolean isValidInput(String input) {
        // Membuat pola regex untuk memeriksa kecocokan angka dan huruf
        String regexPattern = "^[^0-9]*$";

        // Membuat objek Pattern dan Matcher
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);

        // Melakukan pencocokan regex
        return matcher.matches();
    }

    public static String cekNamaBarang(ArrayList<ArrayList<String>> warehouse, Scanner input, String kategoriBarang){
        String namaBarang;
        // Perulangan untuk validasi nama barang
        while (true) {
            System.out.print("Masukkan nama barang: ");
            // Inputan nama barang
            namaBarang = input.nextLine();

            boolean barangExists = false;

            // Mengecek nama barang pada Arraylist warehouse
            for (ArrayList<String> barangData : warehouse) {
                // Jika nama barang dan nama kategori sesuai maka menyimpan isi namaBarang
                if (barangData.get(1).equals(namaBarang) && barangData.get(0).equals(kategoriBarang)) {
                    barangExists = true;
                    // Pesan nama barang ditemukan
                    System.out.println("Nama barang sudah ada dalam warehouse.");
                    break;
                }
            }
            // Jika tidak ada maka tidak mengembalikkan String apapun
            if (!barangExists) {
                break;
            }
        }
        // Mengembalikan nilai String namaBarang
        return namaBarang;

    }

    public static ArrayList<ArrayList<String>> searchItemsInWarehouse(ArrayList<ArrayList<String>> warehouse, String cariBarang) {
        // Membuat ArrayList untuk menyimpan barang yang ditemukan
        ArrayList<ArrayList<String>> foundItems = new ArrayList<>();

        // Melakukan iterasi pada setiap data barang di dalam gudang
        for (ArrayList<String> itemData : warehouse) {
            // Memeriksa apakah nama barang (indeks 1) sama dengan cariBarang (dalam kasus ini, tidak case-sensitive)
            if (itemData.get(1).equalsIgnoreCase(cariBarang)) {
                // Jika ditemukan barang dengan nama yang sesuai, tambahkan data barang ini ke foundItems
                foundItems.add(itemData);
            }
        }

        // Mengembalikan daftar barang yang ditemukan
        return foundItems;
    }

//    public static void again(Scanner input, boolean isTryAgain){
//        while (true) {
//            System.out.print("Mencoba Kembali (Y/N)? ");
//            String coba = input.next().toUpperCase();
//            if (coba.equals("N")) {
//                System.out.println("Anda memilih untuk tidak melanjutkan.");
//                isTryAgain = true;
//            }else if(coba.equals("Y")){
//                isTryAgain = false;
//            }
//            else if (!coba.equals("Y")) {
//                System.out.println("Input tidak valid. Harap masukkan 'Y' atau 'N'.");
//                input.next();
//            }
//        }
//    }

//    public static void categoryFound(ArrayList<ArrayList<String>> warehouse, int kategoriBarang){
//        int itemNumber = 1;
//        // Iterasi melalui data gudang untuk mencari barang dalam kategori yang dipilih
//        for (ArrayList<String> barangData : warehouse) {
//            // Cetak nama barang dalam kategori
//            System.out.println(itemNumber + ". " + barangData.get(1));
//            itemNumber++;
//
//
//        }
//
//    }
//
//    public static boolean isCategoryFounded(){
//
//        boolean categoryFound = false;
//        if (barangData.get(0).equals(kategoriBarang)) {
//            categoryFound = true; // Setel flag categoryFound menjadi true
//        }else if (!categoryFound) {
//            // Jika tidak ada barang dalam kategori yang dipilih
//            System.out.println("\n>>>>>>> Tidak ada barang dalam kategori ini <<<<<<<");
//            break;
//        }
//    }
}
