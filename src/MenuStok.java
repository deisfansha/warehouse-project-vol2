import java.util.ArrayList;
import java.util.Scanner;

public class MenuStok {

    public static void menuUpdateStock(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input, boolean isMenu, ArrayList<ArrayList<String>> log){
        int menu;
        while (isMenu) {
            System.out.println("\n||=========== Menu Update Stok =============||");
            System.out.println("|| 1. Tambah Stok Barang\t\t\t\t\t||");
            System.out.println("|| 2. Kurangi Stok Barang\t\t\t\t\t||");
            System.out.println("|| 0. Kembali\t\t\t\t\t\t\t\t||");
            System.out.println("||==========================================||");
            System.out.print("\nPilih Menu: ");

            // Validasi hanya angka tertentu
            if (input.hasNextInt()){
                menu = input.nextInt();
                switch (menu){
                    case 1:
                        // Memanggil metode untuk tambah stok barang
                        KelolaBarang.tambahStok(warehouse, category, input, log);
                        break;
                    case 2:
                        // Memanggil metode untuk kurangi stok barang
                        KelolaBarang.kurangiStok(warehouse, category, input, log);
                        break;
                    case 0:
                        // Kembali ke menu utama
                        isMenu = false;
                        break;
                }
            }
            else {
                System.out.println("Maaf, input harus berupa angka.");
                input.next();
            }
        }
    }
}
