import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 建立 Scanner 物件，用來讀取使用者輸入
        Scanner sc = new Scanner(System.in);

        try {
            /* ===============================
               0️⃣ 顯示餐廳名稱
               =============================== */
            // 提示使用者輸入餐廳名稱
            System.out.print("請輸入餐廳名稱：");
            String restaurantName = sc.nextLine();
            // 顯示餐廳名稱給使用者
            System.out.println("===== 歡迎光臨 " + restaurantName + " =====\n");

            /* ===============================
               1️⃣ 建立顧客
               =============================== */
            System.out.print("請輸入顧客編號：");
            String customerId = sc.nextLine();

            System.out.print("請輸入顧客姓名：");
            String customerName = sc.nextLine();

            Customer customer = new Customer(customerId, customerName);

            Order comboOrder = new Order(customer);
            Order singleOrder = new Order(customer);

            /* ===============================
               2️⃣ 建立菜單
               =============================== */
            ArrayList<MainDish> mainMenu = new ArrayList<>();
            mainMenu.add(new MainDish("雞腿便當", 120, "Small"));
            mainMenu.add(new MainDish("排骨便當", 90, "Small"));
            mainMenu.add(new MainDish("滷肉便當", 70, "Small"));

            ArrayList<SideDish> sideMenu = new ArrayList<>();
            sideMenu.add(new SideDish("小菜", 30));
            sideMenu.add(new SideDish("滷蛋", 15));
            sideMenu.add(new SideDish("豬血湯", 20));

            ArrayList<Beverage> drinkMenu = new ArrayList<>();
            drinkMenu.add(new Beverage("可樂", 25));
            drinkMenu.add(new Beverage("柳橙汁", 30));
            drinkMenu.add(new Beverage("無糖綠茶", 20));

            /* ===============================
               3️⃣ 顯示菜單給使用者
               =============================== */
            System.out.println("\n----- 主餐 -----");
            for (int i = 0; i < mainMenu.size(); i++) {
                System.out.println((i + 1) + ". " + mainMenu.get(i).name + " "
                        + mainMenu.get(i).basePrice + " 元");
            }

            System.out.println("\n----- 附餐 -----");
            for (int i = 0; i < sideMenu.size(); i++) {
                System.out.println((i + 1) + ". " + sideMenu.get(i).name + " "
                        + sideMenu.get(i).basePrice + " 元");
            }

            System.out.println("\n----- 飲料 -----");
            for (int i = 0; i < drinkMenu.size(); i++) {
                System.out.println((i + 1) + ". " + drinkMenu.get(i).name + " "
                        + drinkMenu.get(i).basePrice + " 元");
            }

            /* ===============================
               4️⃣ 套餐選擇流程
               =============================== */
            System.out.print("\n是否要點套餐？(true/false)：");
            boolean wantCombo = sc.nextBoolean();
            sc.nextLine();

            if (wantCombo) {
                ComboMeal combo = new ComboMeal("今日套餐");

                System.out.print("請選擇套餐主餐編號：");
                int mainChoice = sc.nextInt() - 1;
                sc.nextLine();
                MainDish comboMain = mainMenu.get(mainChoice);

                System.out.print("主餐預設 Small，是否更換份量？(true/false)：");
                boolean changeSize = sc.nextBoolean();
                sc.nextLine();
                if (changeSize) {
                    System.out.print("請輸入份量 (Medium / Large)：");
                    String size = sc.nextLine();
                    if (size.equals("Medium") || size.equals("Large")) {
                        comboMain = new MainDish(comboMain.name, comboMain.basePrice, size);
                    }
                }
                combo.addItem(comboMain);

                System.out.print("請選擇套餐附餐編號：");
                int sideChoice = sc.nextInt() - 1;
                sc.nextLine();
                SideDish comboSide = sideMenu.get(sideChoice);

                System.out.print("是否升級附餐？(true/false)：");
                boolean upgrade = sc.nextBoolean();
                sc.nextLine();
                if (upgrade) comboSide.upgrade();
                combo.addItem(comboSide);

                System.out.print("請選擇套餐飲料編號：");
                int drinkChoice = sc.nextInt() - 1;
                sc.nextLine();
                Beverage comboDrink = drinkMenu.get(drinkChoice);

                System.out.print("是否要客製化冰塊？(true/false)：");
                boolean customize = sc.nextBoolean();
                sc.nextLine();
                if (customize) {
                    System.out.println("冰塊選擇：1.去冰 2.少冰 3.正常冰");
                    System.out.print("請輸入冰塊編號：");
                    int ice = sc.nextInt();
                    sc.nextLine();
                    String iceText;
                    if (ice == 1) iceText = "去冰";
                    else if (ice == 2) iceText = "少冰";
                    else iceText = "正常冰";
                    comboDrink.applyCustomization(iceText);
                }
                combo.addItem(comboDrink);

                comboOrder.addItem(combo);
            }

            /* ===============================
               5️⃣ 單點選擇流程
               =============================== */
            while (true) {
                System.out.print("\n是否要加點單點？(true/false)：");
                boolean wantSingle = sc.nextBoolean();
                sc.nextLine();
                if (!wantSingle) break;

                System.out.println("單點類別：1.主餐 2.附餐 3.飲料");
                int type = sc.nextInt();
                sc.nextLine();

                if (type == 1) {
                    System.out.print("主餐編號：");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    MainDish item = mainMenu.get(idx);

                    System.out.print("是否更換份量？(true/false)：");
                    boolean changeSize = sc.nextBoolean();
                    sc.nextLine();
                    if (changeSize) {
                        System.out.print("請輸入份量 (Medium / Large)：");
                        String size = sc.nextLine();
                        if (size.equals("Medium") || size.equals("Large")) {
                            item = new MainDish(item.name, item.basePrice, size);
                        }
                    }
                    singleOrder.addItem(item);

                } else if (type == 2) {
                    System.out.print("附餐編號：");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    SideDish item = sideMenu.get(idx);

                    System.out.print("是否升級附餐？(true/false)：");
                    boolean upgrade = sc.nextBoolean();
                    sc.nextLine();
                    if (upgrade) item.upgrade();
                    singleOrder.addItem(item);

                } else if (type == 3) {
                    System.out.print("飲料編號：");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    Beverage item = drinkMenu.get(idx);

                    System.out.print("是否要客製化冰塊？(true/false)：");
                    boolean customize = sc.nextBoolean();
                    sc.nextLine();
                    if (customize) {
                        System.out.println("冰塊選擇：1.去冰 2.少冰 3.正常冰");
                        int ice = sc.nextInt();
                        sc.nextLine();
                        String iceText;
                        if (ice == 1) iceText = "去冰";
                        else if (ice == 2) iceText = "少冰";
                        else iceText = "正常冰";
                        item.applyCustomization(iceText);
                    }
                    singleOrder.addItem(item);
                }
            }

            /* ===============================
               6️⃣ 外送費計算
               =============================== */
            System.out.print("\n請輸入外送距離(公里)：");
            double distance = sc.nextDouble();
            System.out.print("是否為尖峰時段？(true/false)：");
            boolean peak = sc.nextBoolean();
            double deliveryFee = DeliveryFeeCalculator.calculateFee(distance, peak);

            /* ===============================
               7️⃣ 顯示消費紀錄
               =============================== */
            System.out.println("\n===== 消費紀錄 =====");

            if (!comboOrder.getItems().isEmpty()) {
                System.out.println("\n--- 套餐 ---");
                for (MenuItem m : comboOrder.getItems()) {
                    ComboMeal cm = (ComboMeal) m;
                    double original = 0;
                    for (MenuItem sub : cm.getItems()) {
                        double price = sub.calculatePrice();
                        original += price;
                        System.out.println(sub.name + "：" + price + " 元");
                        if (sub instanceof Beverage b && !b.getCustomizations().isEmpty()) {
                            System.out.println("  客製化：" + String.join(", ", b.getCustomizations()));
                        }
                    }
                    System.out.println("套餐原價：" + original + " 元");
                    System.out.println("套餐85折後：" + cm.calculatePrice() + " 元");
                }
            }

            if (!singleOrder.getItems().isEmpty()) {
                System.out.println("\n--- 單點 ---");
                for (MenuItem m : singleOrder.getItems()) {
                    System.out.println(m.name + "：" + m.calculatePrice() + " 元");
                    if (m instanceof Beverage b && !b.getCustomizations().isEmpty()) {
                        System.out.println("  客製化：" + String.join(", ", b.getCustomizations()));
                    }
                }
                System.out.println("單點小計：" + singleOrder.calculateTotalPrice() + " 元");
            }

            System.out.println("\n外送費：" + deliveryFee + " 元");

            double total = comboOrder.calculateTotalPrice() + singleOrder.calculateTotalPrice() + deliveryFee;

            System.out.println("總應付金額：" + total + " 元");

        } catch (Exception e) {
            System.out.println("發生錯誤：" + e.getMessage());
        }

        sc.close();
    }
}