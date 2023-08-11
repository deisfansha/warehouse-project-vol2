import java.util.ArrayList;
import java.util.Scanner;

public class KelolaBarang {
    public static void tambahKategori(ArrayList<String> category, Scanner input){
        String inputKategori;
        while (true) {
            System.out.println("\n========== List data kategori: ===========");
            if (category.isEmpty()){
                System.out.println("\n>>> Data Kategori masih kosong <<<");
            }else {
                // Menampilkan daftar kategori yang ada
                for (int i = 0; i < category.size(); i++) {
                    System.out.println((i + 1) + ". " + category.get(i));
                }
            }
            // Input nama kategori
            System.out.print("\nMasukkan Nama Kategori Baru: ");
            inputKategori = input.next();

            // Memeriksa apakah input kategori valid
            if (ErrorHandling.isValidInput(inputKategori)) {
                // Memeriksa apakah kategori sudah ada
                if (category.contains(inputKategori)) {
                    System.out.println("\nNama Kategori sudah ada");
                } else {
                    // Jika kategori belum ada, tambahkan ke daftar kategori
                    category.add(inputKategori);
                    System.out.println("\nData Kategori berhasil ditambah\n");
                    System.out.println("========== List data kategori: ===========");

                    // Menampilkan daftar kategori setelah penambahan
                    for (int i = 0; i < category.size(); i++) {
                        System.out.println((i + 1) + ". " + category.get(i));
                    }
                    break;
                }
            } else {
                System.out.println("Inputan tidak valid.");
            }
            break;
        }


    }

    public static void tambahBarang(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input){
        if (category.isEmpty()) {
            // Jika belum ada kategori, tampilkan pesan dan panggil fungsi untuk menambah kategori
            System.out.println("Belum ada kategori barang");
            tambahKategori(category, input);
        } else {
            while (true) {
                int chooseCategory = 0;
                ViewData.viewCategory(category);
                // Memeriksa input pemilihan kategori
                if (input.hasNextInt()) {
                    chooseCategory = input.nextInt();
                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        System.out.println("\nPilihan kategori tidak valid.\n");
                        input.nextLine(); // Konsumsi input yang tidak valid
                        continue;
                    } else if (chooseCategory == 0) {
                        System.out.println("\nAnda telah keluar dari menu Tambah Barang.\n");
                        break;
                    }
                    input.nextLine(); // Konsumsi karakter newline
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.next(); // Konsumsi input yang tidak valid
                    continue;
                }

                // Mengambil nama barang dengan memeriksa kesesuaian dengan kategori yang dipilih
                String kategoriBarang = category.get(chooseCategory - 1);
                String name = ErrorHandling.cekNamaBarang(warehouse, input, kategoriBarang);

                while (true) {
                    // Meminta input jumlah stok barang
                    System.out.print("Masukkan jumlah Stok Barang: ");
                    int stokBarang;
                    if (input.hasNextInt()) {
                        stokBarang = input.nextInt();
                        if (stokBarang < 0) {
                            System.out.println("\nStok Barang tidak boleh negatif.\n");
                            input.nextLine(); // Konsumsi input yang tidak valid
                            continue;
                        }
                        input.nextLine(); // Konsumsi karakter newline
                    } else {
                        System.out.println("\nInput jumlah Stok tidak valid.\n");
                        input.nextLine(); // Konsumsi input yang tidak valid
                        continue;
                    }

                    // Membuat data barang baru dan menambahkannya ke dalam gudang (warehouse)
                    ArrayList<String> barangData = new ArrayList<>();
                    barangData.add(kategoriBarang);
                    barangData.add(name);
                    barangData.add(Integer.toString(stokBarang));
                    warehouse.add(barangData);

                    // Menampilkan pesan berhasil dan informasi data barang yang baru ditambahkan
                    System.out.println("\nData barang berhasil ditambah");
                    System.out.println("\nData Barang Baru:");
                    System.out.println("Kategori: " + kategoriBarang);
                    System.out.println("Nama Barang: " + name);
                    System.out.println("Stok Barang: " + stokBarang);
                    break;
                }
                break;
            }
        }

    }

    public static void tampil(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category){
        // Loop melalui setiap kategori dalam daftar kategori
        for (String cat : category) {
            System.out.println("\nKategori: " + cat);

            // Flag untuk menandakan apakah ada barang dalam kategori tersebut
            boolean categoryFound = false;
            int itemIndex = 1; // Inisialisasi nomor urutan

            // Loop melalui setiap data barang dalam gudang (warehouse)
            for (ArrayList<String> barangData : warehouse) {
                // Memeriksa apakah kategori data barang sama dengan kategori saat ini
                if (barangData.get(0).equals(cat)) {
                    // Menampilkan nomor urutan dan informasi data barang
                    System.out.print("  " + itemIndex + ". ");
                    for (int i = 1; i < barangData.size(); i++) {
                        System.out.print(barangData.get(i));
                        // Menampilkan tanda "|" jika belum mencapai elemen terakhir
                        if (i < barangData.size() - 1) {
                            System.out.print(" | ");
                        }
                    }
                    System.out.println();
                    categoryFound = true; // Mengubah flag menjadi true karena ada barang dalam kategori ini
                    itemIndex++; // Tambahkan nomor urutan
                }
            }

            // Jika tidak ada barang dalam kategori, tampilkan pesan
            if (!categoryFound) {
                System.out.println("  Tidak ada barang dalam kategori ini.");
            }
        }



    }

    public static void cariBarang(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category,Scanner input){
        if (warehouse.isEmpty()) {
            System.out.println("Data warehouse masih kosong");
        } else {
            boolean foundItem = false; // Flag untuk menandai apakah barang ditemukan

            while (true) {
                System.out.print("(Input Angka 0 jika ingin keluar) ");
                System.out.print("\nMasukkan Nama barang: ");
                String cariBarang = input.next();

                // Menggunakan fungsi searchItemsInWarehouse untuk mencari barang dalam gudang
                ArrayList<ArrayList<String>> found = ErrorHandling.searchItemsInWarehouse(warehouse, cariBarang);

                if (!found.isEmpty()) {
                    foundItem = true; // Tandai bahwa barang ditemukan
                    for (ArrayList<String> itemData : found) {
                        System.out.println("Kategori: " + itemData.get(0));
                        System.out.println("Nama Barang: " + itemData.get(1));
                        System.out.println("Stok Barang: " + itemData.get(2));
                        System.out.println();
                    }

                    System.out.print("Cari Barang Lagi (Y/N)? ");
                    String coba = input.next().toUpperCase();

                    if (coba.equals("N")) {
                        System.out.println("Anda memilih untuk tidak melanjutkan.");
                        break;
                    } else if (!coba.equals("Y")) {
                        System.out.println("Input tidak valid. Harap masukkan 'Y' atau 'N'.");
                    }
                } else if (cariBarang.equalsIgnoreCase("0")) {
                    System.out.println("\nAnda Telah keluar dari menu Cari Barang\n");
                    break;
                } else {
                    System.out.println("Tidak ada barang yang ditemukan dalam data warehouse.");
                }
            }

            if (!foundItem) {
                System.out.println("Tidak ada barang yang ditemukan dalam data warehouse."); // Pesan jika tidak ada barang ditemukan
            }
        }
    }

    public static void gantiNamaBarang(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input){
        if (warehouse.isEmpty()) {
            System.out.println("Data warehouse masih kosong\n");
            tambahBarang(warehouse, category, input); // Panggil fungsi untuk menambah barang jika warehouse kosong
        } else {
            while (true) {
                ViewData.viewCategory(category);
                int chooseCategory = input.nextInt();

                if (chooseCategory < 0 || chooseCategory > category.size()) {
                    System.out.println("Pilihan kategori tidak valid.");
                } else if (chooseCategory == 0) {
                    System.out.println("\nAnda telah keluar dari menu Update Nama Barang\n");
                    break; // Keluar dari loop jika pengguna memilih kembali
                }

                String kategoriBarang = category.get(chooseCategory - 1);
                System.out.println("\nKategori: " + kategoriBarang);

                boolean categoryFound = false;
                int itemNumber = 1;

                // Loop untuk menampilkan daftar barang dalam kategori yang dipilih
                for (ArrayList<String> barangData : warehouse) {
                    if (barangData.get(0).equals(kategoriBarang)) {
                        System.out.println(">  " + itemNumber + ". " + barangData.get(1)); // Tampilkan nama barang
                        itemNumber++;
                        categoryFound = true;
                    }
                }

                if (!categoryFound) {
                    System.out.println("Tidak ada barang dalam kategori ini.");
                    continue; // Kembali ke awal loop untuk memilih kategori kembali
                }

                System.out.print("\nMasukkan pilihan nama barang: ");
                int namaBarang = input.nextInt();

                if (namaBarang < 1 || namaBarang > itemNumber - 1) {
                    System.out.println("Input pilihan nama barang tidak valid.");
                    continue; // Kembali ke awal loop untuk memilih nama barang kembali
                }

                // Tampilkan kategori, nama barang, dan stok barang
                ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
                input.nextLine(); // Konsumsi karakter newline

                System.out.print("\nMasukkan nama baru untuk barang: ");
                String newName = input.nextLine();
                selectedBarangData.set(1, newName); // Mengubah nama barang dalam data

                System.out.println("Nama barang berhasil diubah.");
                break; // Keluar dari loop setelah nama barang diubah
            }
        }


    }

    public static void tambahStok(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input, ArrayList<ArrayList<String>> log){
        if (warehouse.isEmpty()) {
            // Jika gudang kosong, cetak pesan dan panggil fungsi tambahBarang
            System.out.println("Data warehouse masih kosong");
            tambahBarang(warehouse, category, input);
        } else {
            while (true) {
                int chooseCategory = 0;
                ViewData.viewCategory(category);
                if (input.hasNextInt()) {
                    // Baca pilihan kategori dari input
                    chooseCategory = input.nextInt();
                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        // Cek validitas pilihan kategori
                        System.out.println("Pilihan kategori tidak valid.");
                        input.nextLine(); // Konsumsi input yang tidak valid
                        continue;
                    } else if (chooseCategory == 0) {
                        System.out.println("\nAnda telah keluar dari menu Tambah Stok Barang\n");
                        break; // Keluar dari loop jika pengguna memilih kembali
                    }
                    input.nextLine(); // Konsumsi karakter newline

                    // Dapatkan nama kategori yang dipilih
                    String kategoriBarang = category.get(chooseCategory - 1);
                    System.out.println("\nKategori: " + kategoriBarang);

                    boolean categoryFound = false;
                    int itemNumber = 1;
                    // Iterasi melalui data gudang untuk mencari barang dalam kategori yang dipilih
                    for (ArrayList<String> barangData : warehouse) {
                        if (barangData.get(0).equals(kategoriBarang)) {
                            // Cetak nama barang dalam kategori
                            System.out.println(itemNumber + ". " + barangData.get(1));
                            itemNumber++;
                            categoryFound = true; // Setel flag categoryFound menjadi true
                        }
                    }

                    if (!categoryFound) {
                        // Jika tidak ada barang dalam kategori yang dipilih
                        System.out.println("\n>>>>>>> Tidak ada barang dalam kategori ini <<<<<<<");
                        break;
                    }

                    System.out.print("\nMasukkan pilihan nama barang: ");
                    int namaBarang = 0;
                    if (input.hasNextInt()) {
                        // Baca pilihan nama barang dari input
                        namaBarang = input.nextInt();
                        if (namaBarang < 1 || namaBarang > warehouse.size()) {
                            // Cek validitas pilihan nama barang
                            System.out.println("\nPilihan nama barang tidak valid.");
                            input.nextLine(); // Konsumsi input yang tidak valid
                            continue;
                        }
                        input.nextLine(); // Konsumsi karakter newline

                        // Menampilkan data sesuai pilihan kategori dan nama barang
                        ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
                        ViewData.viewBarangByNama(warehouse, namaBarang);

                        int jumlahStok = Integer.parseInt(selectedBarangData.get(2));
                        int newStok = 0;

                        // Memulai loop hingga input jumlah stok valid
                        while (true) {
                            System.out.print("\nMasukkan jumlah stok: ");
                            if (input.hasNextInt()) {
                                newStok = input.nextInt();

                                // Pengecekan validitas jumlah stok
                                if (newStok < 0) {
                                    System.out.println("Stok tidak boleh negatif.");
                                    continue;
                                }
                                break;
                            } else {
                                System.out.println("Maaf, input harus berupa angka.");
                                input.next();
                            }
                        }
                        if (newStok == 0){
                            System.out.println("Jumlah stok tidak ada perubahan");
                        }else {
                            // Menambahkan stok baru ke stok saat ini
                            int result = newStok + jumlahStok;
                            selectedBarangData.set(2, Integer.toString(result));
                            System.out.println("\nStok berhasil ditambah!!!.");
                            ViewData.viewBarangByNama(warehouse, namaBarang);

                            // Membuat log perubahan stok
                            String namalog = selectedBarangData.get(0).charAt(0) + "" + selectedBarangData.get(1).charAt(1) + "" + selectedBarangData.get(1).charAt(0);
                            ArrayList<String> itemLog = new ArrayList<>();
                            itemLog.add(namalog);
                            itemLog.add(selectedBarangData.get(1));
                            itemLog.add("+" + newStok);
                            log.add(itemLog);
                        }
                        break; // Keluar dari loop jika proses selesai
                    } else {
                        System.out.println("Input pilihan nama barang tidak valid.");
                        input.nextLine(); // Konsumsi input yang tidak valid
                    }
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.nextLine(); // Consume the invalid input
                }
            }
        }


    }

    public static void tampilLog(ArrayList<ArrayList<String>> log){
        int no = 1;
        // Menampilkan data log dari Arraylist log
        for (ArrayList<String> barangData : log) {
            System.out.println(no+". " + barangData.get(0)+" | "+ barangData.get(1)+" | "+barangData.get(2));
            no++;
        }
    }

    public static void kurangiStok(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input, ArrayList<ArrayList<String>> log){
        if (warehouse.isEmpty()) {
            // Memeriksa apakah gudang kosong
            System.out.println("Data warehouse masih kosong");

            // Memanggil fungsi tambahBarang untuk menambahkan barang ke gudang
            tambahBarang(warehouse, category, input);
        } else {
            while (true) {
                int chooseCategory = 0;
                ViewData.viewCategory(category);
                if (input.hasNextInt()) {
                    // Memeriksa apakah input berupa integer
                    chooseCategory = input.nextInt();

                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        System.out.println("Pilihan kategori tidak valid.");
                        input.nextLine(); // Mengonsumsi input yang tidak valid
                        continue;
                    }else if (chooseCategory == 0) {
                        System.out.println("\nAnda telah keluar dari menu Tambah Stok Barang\n");
                        break; // Keluar dari loop jika pengguna memilih kembali
                    }
                    input.nextLine(); // Mengonsumsi karakter newline

                    String kategoriBarang = category.get(chooseCategory - 1);
                    System.out.println("\nKategori: " + kategoriBarang);

                    boolean categoryFound = false;
                    int itemNumber = 1;

                    // Menampilkan barang-barang dalam kategori yang dipilih
                    for (ArrayList<String> barangData : warehouse) {
                        if (barangData.get(0).equals(kategoriBarang)) {
                            System.out.println(itemNumber + ". " + barangData.get(1)); // Menampilkan nama barang
                            itemNumber++;
                            categoryFound = true; // Mengatur flag categoryFound menjadi true
                        }
                    }

                    if (!categoryFound) {
                        System.out.println("\n>>>>>>> Tidak ada barang dalam kategori ini <<<<<<<");
                        break;
                    }

                    System.out.print("\nMasukkan pilihan nama barang: ");
                    int namaBarang = 0;

                    if (input.hasNextInt()) {
                        // Memeriksa apakah input berupa integer
                        namaBarang = input.nextInt();

                        if (namaBarang < 1 || namaBarang > warehouse.size()) {
                            System.out.println("\nPilihan nama barang tidak valid.");
                            input.nextLine(); // Mengonsumsi input yang tidak valid
                            continue;
                        }
                        input.nextLine(); // Mengonsumsi karakter newline

                        // Menampilkan data sesuai pilihan kategori dan nama barang
                        ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
                        ViewData.viewBarangByNama(warehouse, namaBarang);

                        int jumlahStok = Integer.parseInt(selectedBarangData.get(2));
                        int newStok = 0;

                        while (true) {
                            System.out.print("\nMasukkan jumlah stok yang akan dikurangi: ");
                            String inputString = input.next();

                            if (inputString.matches("\\d+") && !inputString.startsWith("+") && !inputString.startsWith("*") && !inputString.startsWith("/")) {
                                // Memeriksa apakah input adalah angka positif dan tidak dimulai dengan operator matematika
                                newStok = Integer.parseInt(inputString);
                                if (newStok >= 0 && newStok <= jumlahStok) {
                                    break;
                                } else {
                                    System.out.println("Maaf, jumlah stok harus sesuai dengan batas yang ada.");
                                }
                            } else {
                                System.out.println("Maaf, input harus berupa angka.");
                            }
                        }
                        int result = jumlahStok - newStok;

                        selectedBarangData.set(2, Integer.toString(result));

                        System.out.println("\nStok berhasil dikurangi!!!.");

                        // Membuat log untuk perubahan stok
                        String namalog = selectedBarangData.get(0).charAt(0) + "" + selectedBarangData.get(1).charAt(1) + "" + selectedBarangData.get(1).charAt(0);
                        ArrayList<String> itemLog = new ArrayList<>();
                        itemLog.add(namalog);
                        itemLog.add(selectedBarangData.get(1));
                        itemLog.add("-" + newStok);
                        log.add(itemLog);

                        // Function menampilkan data barang yang sudah dikurangi
                        ViewData.viewBarangByNama(warehouse, namaBarang);

                        break; // Keluar dari loop setelah selesai
                    } else {
                        System.out.println("Input pilihan nama barang tidak valid.");
                        input.nextLine(); // Mengonsumsi input yang tidak valid
                    }
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.nextLine(); // Mengonsumsi input yang tidak valid
                }
            }
        }
    }

    public static void gantiCategory(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input){
        if (category.isEmpty()){
            // Memeriksa apakah data kategori kosong
            System.out.println("Data kategori masih kosong");

            // Memanggil fungsi tambahKategori untuk menambahkan kategori baru
            tambahKategori(category, input);
        } else {
            while (true) {
                int chooseCategory = 0;
                ViewData.viewCategory(category);
                if (input.hasNextInt()) {
                    // Memeriksa apakah input berupa integer
                    chooseCategory = input.nextInt();

                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        System.out.println("Pilihan kategori tidak valid.");
                        input.next(); // Mengonsumsi input yang tidak valid
                        continue;
                    }else if (chooseCategory == 0) {
                        System.out.println("\nAnda telah keluar dari menu Update Kategori\n");
                        break; // Keluar dari loop jika pengguna memilih kembali
                    }
                    input.nextLine(); // Mengonsumsi karakter newline
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.next(); // Mengonsumsi input yang tidak valid
                    continue;
                }

                String oldName = category.get(chooseCategory - 1);
                String newName;

                while (true){
                    System.out.print("\nMasukkan Nama Kategori yang baru: ");
                    newName = input.nextLine();

                    if (ErrorHandling.isValidInput(newName)) {
                        if (category.contains(newName)){
                            System.out.println("\nNama Kategori sudah ada");
                        } else {
                            for (ArrayList<String> itemData : warehouse) {
                                if (itemData.get(0).equals(oldName)) {
                                    itemData.set(0, newName); // Memperbarui nama kategori di gudang
                                }
                            }
                            category.set(chooseCategory - 1, newName); // Memperbarui nama kategori dalam daftar kategori

                            System.out.println("\nData Kategori berhasil diubah\n");
                            System.out.println("List data kategori:");

                            // Menampilkan kembali daftar kategori setelah perubahan
                            for (int i = 0; i < category.size(); i++) {
                                System.out.println((i + 1) + ". " + category.get(i));
                            }
                            break;
                        }
                    } else {
                        System.out.println("Inputan tidak valid.");
                    }
                }
                break; // Keluar dari loop setelah selesai
            }
        }


    }

    public static void hapusCategory(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input){
        if (category.isEmpty()) {
            // Memeriksa apakah data kategori kosong
            System.out.println("Data kategori masih kosong");

            // Memanggil fungsi tambahKategori untuk menambahkan kategori baru
            tambahKategori(category, input);
        } else {
            while (true) {
                int chooseCategory = 0;

                ViewData.viewCategory(category);
                if (input.hasNextInt()) {
                    // Memeriksa apakah input berupa integer
                    chooseCategory = input.nextInt();

                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        System.out.println("\nPilihan kategori tidak valid.\n");
                        input.nextLine(); // Mengonsumsi input yang tidak valid
                        continue;
                    } else if (chooseCategory == 0) {
                        System.out.println("\n Anda telah keluar dari menu Hapus Kategori \n");
                        break;
                    }
                    input.nextLine(); // Mengonsumsi karakter newline
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.nextLine(); // Mengonsumsi input yang tidak valid
                    continue;
                }

                String nama = category.get(chooseCategory - 1);

                // Menghapus kategori dari daftar kategori
                category.remove(chooseCategory - 1);
                System.out.println("\nData Kategori berhasil dihapus\n");

                // Menghapus semua barang dengan kategori yang sama dari gudang
                for (int i = warehouse.size() - 1; i >= 0; i--) {
                    if (warehouse.get(i).get(0).equals(nama)) {
                        warehouse.remove(i);
                    }
                }
                break; // Keluar dari loop setelah selesai
            }
        }

    }

    public static void hapusBarang(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input){
        if (warehouse.isEmpty()) {
            // Memeriksa apakah data gudang kosong
            System.out.println("Data warehouse masih kosong");
        } else {
            int chooseCategory = 0;
            while (true) {
                ViewData.viewCategory(category);
                if (input.hasNextInt()) {
                    // Memeriksa apakah input berupa integer
                    chooseCategory = input.nextInt();

                    if (chooseCategory < 0 || chooseCategory > category.size()) {
                        System.out.println("\nPilihan kategori tidak valid.\n");
                    } else if (chooseCategory == 0) {
                        System.out.println("\nAnda telah keluar dari menu Hapus Nama Barang.\n");
                        break;
                    } else {
                        input.nextLine(); // Mengonsumsi karakter newline

                        String kategoriBarang = category.get(chooseCategory - 1);
                        System.out.println("\nKategori: " + kategoriBarang);

                        boolean categoryFound = false;
                        int itemNumber = 1;

                        // Menampilkan barang-barang dalam kategori yang dipilih
                        for (ArrayList<String> barangData : warehouse) {
                            if (barangData.get(0).equals(kategoriBarang)) {
                                System.out.println(itemNumber + ". " + barangData.get(1)); // Menampilkan nama barang
                                itemNumber++;
                                categoryFound = true;
                            }
                        }
                        System.out.println("");

                        if (!categoryFound) {
                            System.out.println("\nTidak ada barang dalam kategori ini.");
                        } else {
                            System.out.print("\nMasukkan pilihan nama barang: ");
                            int namaBarang = 0;

                            if (input.hasNextInt()) {
                                // Memeriksa apakah input berupa integer
                                namaBarang = input.nextInt();

                                if (namaBarang < 1 || namaBarang > itemNumber - 1) {
                                    System.out.println("\nPilihan nama barang tidak valid.\n");
                                } else {
                                    input.nextLine(); // Mengonsumsi karakter newline

                                    ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
                                    String nama = selectedBarangData.get(1);

                                    // Menghapus barang dengan nama yang dipilih dari gudang
                                    for (int i = warehouse.size() - 1; i >= 0; i--) {
                                        if (warehouse.get(i).get(1).equals(nama)) {
                                            warehouse.remove(i);
                                        }
                                    }
                                    System.out.println("Nama barang berhasil dihapus.");
                                    break; // Keluar dari loop setelah selesai
                                }
                            } else {
                                System.out.println("Input pilihan nama barang tidak valid.");
                                input.nextLine(); // Mengonsumsi input yang tidak valid
                            }
                        }
                    }
                } else {
                    System.out.println("Input pilihan kategori tidak valid.");
                    input.next(); // Mengonsumsi input yang tidak valid
                }
            }
        }

    }

//     public static void newTambahStok(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input, ArrayList<ArrayList<String>> log){
//        if (warehouse.isEmpty()) {
//            System.out.println("Data warehouse masih kosong");
//            tambahBarang(warehouse, category, input);
//        } else {
//            System.out.println("\nPilih Kategori");
//            for (int i = 0; i < category.size(); i++) {
//                System.out.println((i + 1) + ". " + category.get(i));
//            }
//            while (true) {
//                int chooseCategory = 0;
//                System.out.print("Masukkan pilihan kategori: ");
//                if (input.hasNextInt()) {
//                    chooseCategory = input.nextInt();
//                    if (chooseCategory < 1 || chooseCategory > category.size()) {
//                        System.out.println("Pilihan kategori tidak valid.");
//                        input.nextLine(); // Consume the invalid input
//                        continue;
//                    }
//                    input.nextLine(); // Consume the newline character
//
//                    String kategoriBarang = category.get(chooseCategory - 1);
//                    System.out.println("\nKategori: " + kategoriBarang);
//
//                    boolean categoryFound = false;
//                    int itemNumber = 1;
//                    for (ArrayList<String> barangData : warehouse) {
//                        if (barangData.get(0).equals(kategoriBarang)) {
//                            System.out.println(itemNumber + ". " + barangData.get(1)); // Print the name of the item
//                            itemNumber++;
//                            categoryFound = true; // Set the categoryFound flag to true
//                        }
//                    }
//
//                    if (!categoryFound) {
//                        System.out.println("\n>>>>>>> Tidak ada barang dalam kategori ini <<<<<<<");
//                        break;
//                    }
//
//                    System.out.print("\nMasukkan pilihan nama barang: ");
//                    int namaBarang = 0;
//                    if (input.hasNextInt()) {
//                        namaBarang = input.nextInt();
//                        if (namaBarang < 1 || namaBarang > warehouse.size()) {
//                            System.out.println("\nPilihan nama barang tidak valid.");
//                            input.nextLine(); // Consume the invalid input
//                            continue;
//                        }
//                        input.nextLine(); // Consume the newline character
//
//                        // Menampilkan data sesuai pilihan kategori dan nama barang
//                        ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
//                        System.out.println("\nKategori: " + selectedBarangData.get(0));
//                        System.out.println("Nama Barang: " + selectedBarangData.get(1));
//                        System.out.println("Stok Barang: " + selectedBarangData.get(2));
//
//                        int jumlahStok = Integer.parseInt(selectedBarangData.get(2));
//                        int newStok = 0;
//                        while (true) {
//                            System.out.print("\nMasukkan jumlah stok: ");
//                            if (input.hasNextInt()) {
//                                newStok = input.nextInt();
//                                if (newStok < 0) {
//                                    System.out.println("Stok tidak boleh negatif.");
//                                    continue;
//                                }
//                                break;
//                            } else {
//                                System.out.println("Maaf, input harus berupa angka.");
//                                input.next();
//                            }
//                        }
//
//                        int result = newStok + jumlahStok;
//
//                        selectedBarangData.set(2, Integer.toString(result));
//
//                        System.out.println("\nStok berhasil ditambah!!!.");
//
//                        String namalog = selectedBarangData.get(0).charAt(0) + "" + selectedBarangData.get(1).charAt(1) + "" + selectedBarangData.get(1).charAt(0);
//                        ArrayList<String> itemLog = new ArrayList<>();
//
//                        itemLog.add(namalog);
//                        itemLog.add(selectedBarangData.get(1));
//                        itemLog.add("+" + Integer.toString(newStok));
//                        log.add(itemLog);
//
//                        int totalStok = 0;
//                        for (ArrayList<String> logData : log) {
//                            if (logData.get(1).equalsIgnoreCase(selectedBarangData.get(1))) {
//                                int stokChangeValue = Integer.parseInt(logData.get(2).substring(1)); // Remove the "+" sign
//                                totalStok += stokChangeValue;
//                            }
//                        }
//
//                        System.out.println("\nKategori: " + selectedBarangData.get(0));
//                        System.out.println("Nama Barang: " + selectedBarangData.get(1));
//                        System.out.println("Stok Barang: " + totalStok);
//                        break;
//                    } else {
//                        System.out.println("Input pilihan nama barang tidak valid.");
//                        input.nextLine(); // Consume the invalid input
//                    }
//                } else {
//                    System.out.println("Input pilihan kategori tidak valid.");
//                    input.nextLine(); // Consume the invalid input
//                }
//            }
//        }
//
//    }

//    public static void newKurangiStok(ArrayList<ArrayList<String>> warehouse, ArrayList<String> category, Scanner input, ArrayList<ArrayList<String>> log){
//        if (warehouse.isEmpty()) {
//            System.out.println("Data warehouse masih kosong");
//            tambahBarang(warehouse, category, input);
//        } else {
//            System.out.println("\nPilih Kategori");
//            for (int i = 0; i < category.size(); i++) {
//                System.out.println((i + 1) + ". " + category.get(i));
//            }
//            while (true) {
//                int chooseCategory = 0;
//                System.out.print("Masukkan pilihan kategori: ");
//                if (input.hasNextInt()) {
//                    chooseCategory = input.nextInt();
//                    if (chooseCategory < 1 || chooseCategory > category.size()) {
//                        System.out.println("Pilihan kategori tidak valid.");
//                        input.nextLine(); // Consume the invalid input
//                        continue;
//                    }
//                    input.nextLine(); // Consume the newline character
//
//                    String kategoriBarang = category.get(chooseCategory - 1);
//                    System.out.println("\nKategori: " + kategoriBarang);
//
//                    boolean categoryFound = false;
//                    int itemNumber = 1;
//                    for (ArrayList<String> barangData : warehouse) {
//                        if (barangData.get(0).equals(kategoriBarang)) {
//                            System.out.println(itemNumber + ". " + barangData.get(1)); // Print the name of the item
//                            itemNumber++;
//                            categoryFound = true; // Set the categoryFound flag to true
//                        }
//                    }
//
//                    if (!categoryFound) {
//                        System.out.println("\n>>>>>>> Tidak ada barang dalam kategori ini <<<<<<<");
//                        break;
//                    }
//
//                    System.out.print("\nMasukkan pilihan nama barang: ");
//                    int namaBarang = 0;
//                    if (input.hasNextInt()) {
//                        namaBarang = input.nextInt();
//                        if (namaBarang < 1 || namaBarang > warehouse.size()) {
//                            System.out.println("\nPilihan nama barang tidak valid.");
//                            input.nextLine(); // Consume the invalid input
//                            continue;
//                        }
//                        input.nextLine(); // Consume the newline character
//
//                        // Menampilkan data sesuai pilihan kategori dan nama barang
//                        ArrayList<String> selectedBarangData = warehouse.get(namaBarang - 1);
//                        System.out.println("\nKategori: " + selectedBarangData.get(0));
//                        System.out.println("Nama Barang: " + selectedBarangData.get(1));
//                        System.out.println("Stok Barang: " + selectedBarangData.get(2));
//
//                        int jumlahStok = Integer.parseInt(selectedBarangData.get(2));
//                        int newStok = 0;
//                        while (true) {
//                            System.out.print("\nMasukkan jumlah stok yang akan dikurangi: ");
//                            String inputString = input.next();
//
//                            if (inputString.matches("\\d+") && !inputString.startsWith("+") && !inputString.startsWith("*") && !inputString.startsWith("/")) {
//                                newStok = Integer.parseInt(inputString);
//                                if (newStok >= 0 && newStok <= jumlahStok) {
//                                    break;
//                                } else {
//                                    System.out.println("Maaf, jumlah stok harus sesuai dengan batas yang ada.");
//                                }
//                            } else {
//                                System.out.println("Maaf, input harus berupa angka.");
//                            }
//                        }
//                        int result = jumlahStok - newStok;
//
//                        selectedBarangData.set(2, Integer.toString(result));
//
//                        System.out.println("\nStok berhasil dikurangi!!!.");
//
//                        String namalog = selectedBarangData.get(0).charAt(0) + "" + selectedBarangData.get(1).charAt(1) + "" + selectedBarangData.get(1).charAt(0);
//                        ArrayList<String> itemLog = new ArrayList<>();
//
//                        itemLog.add(namalog);
//                        itemLog.add(selectedBarangData.get(1));
//                        itemLog.add("-" + Integer.toString(newStok));
//                        log.add(itemLog);
//
//                        int totalStok = 0;
//                        for (ArrayList<String> logData : log) {
//                            if (logData.get(1).equalsIgnoreCase(selectedBarangData.get(1))) {
//                                int stokChangeValue = Integer.parseInt(logData.get(2).substring(1)); // Remove the "+" sign
//                                totalStok += stokChangeValue;
//                            }
//                        }
//
//                        System.out.println("\nKategori: " + selectedBarangData.get(0));
//                        System.out.println("Nama Barang: " + selectedBarangData.get(1));
//                        System.out.println("Stok Barang: " + totalStok);
//                        break;
//                    } else {
//                        System.out.println("Input pilihan nama barang tidak valid.");
//                        input.nextLine(); // Consume the invalid input
//                    }
//                } else {
//                    System.out.println("Input pilihan kategori tidak valid.");
//                    input.nextLine(); // Consume the invalid input
//                }
//            }
//        }
//    }
}