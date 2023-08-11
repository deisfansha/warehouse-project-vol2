import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isMenu = true;

        ArrayList<ArrayList<String>> warehouse = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<String> item = new ArrayList<>();
        ArrayList<ArrayList<String>> log = new ArrayList<>();
        int menu;

        category.add("Bunga");
        category.add("Makanan");
        category.add("Manisan");


        item.add("Bunga");
        item.add("Mawar");
        item.add("50");

        warehouse.add(item);
        item.add("Makanan");
        item.add("Seblak");
        item.add("20");
        warehouse.add(item);

        // Tampilan menu
        while (isMenu) {
            System.out.println("\n||=============== Menu =================||");
            System.out.println("|| 1. Tambah Kategori\t\t\t\t\t||");
            System.out.println("|| 2. Update Nama Kategori\t\t\t\t||");
            System.out.println("|| 3. Hapus Kategori\t\t\t\t\t||");
            System.out.println("|| 4. Tambah Barang\t\t\t\t\t\t||");
            System.out.println("|| 5. Update Nama Barang\t\t\t\t||");
            System.out.println("|| 6. Hapus Nama Barang\t\t\t\t\t||");
            System.out.println("|| 7. Mencari Nama Barang\t\t\t\t||");
            System.out.println("|| 8. Update Stok Barang\t\t\t\t||");
            System.out.println("|| 9. Melihat Stok Barang\t\t\t\t||");
            System.out.println("|| 10. Lihat Log\t\t\t\t\t\t||");
            System.out.println("|| 0. Exit\t\t\t\t\t\t\t\t||");
            System.out.println("||======================================||");
            System.out.print("\nPilih Menu: ");

            // Validasi angka tertentu
            if (input.hasNextInt()) {
                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        // Memanggil metode untuk menambahkan kategori baru
                        KelolaBarang.tambahKategori(category, input);
                        break;
                    case 2:
                        // Memanggil metode untuk mengubah kategori
                        KelolaBarang.gantiCategory(warehouse, category, input);
                        break;
                    case 3:
                        // Memanggil metode untuk menghapus kategori
                        KelolaBarang.hapusCategory(warehouse, category, input);
                        break;
                    case 4:
                        // Memanggil metode untuk menambahkan barang baru
                        KelolaBarang.tambahBarang(warehouse, category, input);
                        break;
                    case 5:
                        // Memanggil metode untuk mengubah nama barang
                        KelolaBarang.gantiNamaBarang(warehouse, category, input);
                        break;
                    case 6:
                        // Memanggil metode untuk menghapus barang
                        KelolaBarang.hapusBarang(warehouse, category, input);
                        break;
                    case 7:
                        // Memanggil metode untuk mencari barang
                        KelolaBarang.cariBarang(warehouse, category, input);
                        break;
                    case 8:
                        // Memanggil metode untuk mengupdate stok
                        MenuStok.menuUpdateStock(warehouse, category, input, isMenu, log);
                        break;
                    case 9:
                        // Memanggil metode untuk menampilkan barang
                        KelolaBarang.tampil(warehouse, category);
                        break;
                    case 10:
                        // Memanggil metode untuk menampilkan log
                        KelolaBarang.tampilLog(log);
                        break;
                    case 0:
                        // Keluar dari program
                        System.out.println("Terimakasih sudah menggunakan aplikasi ini!!!");
                        input.close();
                        System.exit(0);
                }
            } else {
                System.out.println("Maaf, input harus berupa angka.");
                input.next();
            }

        }
    }
}