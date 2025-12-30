import model.Beverage;
import model.ComboMeal;
import model.Order;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("請輸入顧客姓名：");
        String customerName = sc.nextLine();

        System.out.print("請輸入餐廳名稱：");
        String restaurantName = sc.nextLine();

        Order order = new Order(customerName, restaurantName);
        ArrayList<ComboMeal> items = new ArrayList<>();

        System.out.print("請選擇訂餐方式 1.套餐（享85折優惠），2.單點：");
        int type = sc.nextInt(); sc.nextLine();
        if(type == 1) handleComboMeal(items);

        // 單點流程
        while(true){
            System.out.print("是否要單點餐點？(Y/N)：");
            String ans = sc.nextLine();
            if(ans.equalsIgnoreCase("N")) break;
            handleSingleItem(items);
        }

        // 外送費
        System.out.print("請輸入外送距離（公里）：");
        int distance = sc.nextInt(); sc.nextLine();
        order.setDeliveryFee(distance * 10);

        // 外送時段
        System.out.println("請選擇外送時段：");
        System.out.println("1. 07:00~09:00");
        System.out.println("2. 09:00~11:00");
        System.out.println("3. 11:00~13:00（尖峰）");
        System.out.println("4. 13:00~15:00");
        System.out.println("5. 15:00~17:00");
        System.out.println("6. 17:00~19:00（尖峰）");
        System.out.println("7. 19:00~21:00");
        System.out.println("8. 21:00~23:00");
        System.out.print("請輸入選項：");
        int timeChoice = sc.nextInt(); sc.nextLine();
        boolean peak = (timeChoice == 3 || timeChoice == 6);
        order.setPeakFee(peak ? 15 : 0);

        // 加入訂單
        for(ComboMeal c : items) order.addComboMeal(c);

        // 顯示明細
        System.out.println("\n===== 訂單明細 =====");
        System.out.println("顧客：" + customerName);
        System.out.println("餐廳：" + order.getRestaurantName());
        for(ComboMeal c : items) c.printDetail();
        System.out.println("外送費：" + order.getDeliveryFee() + " 元");
        System.out.println("尖峰費：" + order.getPeakFee() + " 元");
        System.out.println("總金額：" + order.getTotal() + " 元");

        System.out.println("\n已下單，正在等待商家接單...（按 Enter 繼續）");
        sc.nextLine();
        if(peak) System.out.println("商家已接單，尖峰時段預計準備時間為 15～20 分鐘，請耐心等候！🙏🏻");
        else System.out.println("商家已接單，預計準備時間為 10～15 分鐘，請耐心等候！😊");

        // 存檔
        try(FileWriter fw = new FileWriter("order.txt", true)){
            fw.write(order.toFileString());
        }catch(IOException e){
            System.out.println("存檔失敗：" + e.getMessage());
        }
        System.out.println("\n訂單已存檔到 order.txt");
    }

    // 套餐流程
    private static void handleComboMeal(ArrayList<ComboMeal> list){
        System.out.print("請輸入套餐數量：");
        int qty = sc.nextInt(); sc.nextLine();

        for(int i=0;i<qty;i++){
            if(i>0){
                System.out.print("下一個套餐是否一樣？(Y/N)：");
                if(sc.nextLine().equalsIgnoreCase("Y")){
                    list.get(list.size()-1).increaseQuantity(1);
                    continue;
                }
            }

            System.out.print("請輸入主餐名稱："); String main = sc.nextLine();
            System.out.print("請輸入主餐價格："); int mainPrice = sc.nextInt(); sc.nextLine();
            System.out.print("請輸入附餐名稱："); String side = sc.nextLine();
            System.out.print("請輸入附餐價格："); int sidePrice = sc.nextInt(); sc.nextLine();
            System.out.print("請輸入飲料名稱："); String drinkName = sc.nextLine();
            System.out.print("請輸入飲料價格："); int drinkPrice = sc.nextInt(); sc.nextLine();

            Beverage drink = new Beverage(drinkName, drinkPrice);
            System.out.println("飲料冰塊選擇：1.去冰 2.少冰 3.正常冰");
            int iceChoice = sc.nextInt(); sc.nextLine();
            switch(iceChoice){
                case 1 -> drink.setIceLevel("去冰");
                case 2 -> drink.setIceLevel("少冰");
                default -> drink.setIceLevel("正常冰");
            }

            ComboMeal combo = new ComboMeal(main, mainPrice, side, sidePrice, drink);
            combo.setCombo(true);

            System.out.print("是否要客製化主餐或附餐？(Y/N)：");
            String custAns = sc.nextLine();
            if(custAns.equalsIgnoreCase("Y")){
                System.out.print("請輸入客製化內容：");
                combo.addCustomization(sc.nextLine(), true);
            }

            list.add(combo);
        }
    }

    // 單點流程
    private static void handleSingleItem(ArrayList<ComboMeal> list){
        System.out.println("單點選擇類型：1=主餐 2=附餐 3=飲料");
        int type = sc.nextInt(); sc.nextLine();

        String main=""; int mainPrice=0;
        String side=""; int sidePrice=0;
        Beverage drink = new Beverage("",0);

        switch(type){
            case 1 -> { System.out.print("請輸入主餐名稱："); main=sc.nextLine();
                System.out.print("請輸入主餐價格："); mainPrice=sc.nextInt(); sc.nextLine(); }
            case 2 -> { System.out.print("請輸入附餐名稱："); side=sc.nextLine();
                System.out.print("請輸入附餐價格："); sidePrice=sc.nextInt(); sc.nextLine(); }
            case 3 -> { System.out.print("請輸入飲料名稱："); String drinkName=sc.nextLine();
                System.out.print("請輸入飲料價格："); int drinkPrice=sc.nextInt(); sc.nextLine();
                drink = new Beverage(drinkName, drinkPrice);
                System.out.println("飲料冰塊選擇：1.去冰 2.少冰 3.正常冰");
                int iceChoice = sc.nextInt(); sc.nextLine();
                switch(iceChoice){
                    case 1 -> drink.setIceLevel("去冰");
                    case 2 -> drink.setIceLevel("少冰");
                    default -> drink.setIceLevel("正常冰");
                }
            }
        }

        System.out.print("是否要客製化？(Y/N)：");
        String custAns=sc.nextLine(); String custText=""; boolean chargeable=false;
        if(custAns.equalsIgnoreCase("Y") && type!=3){
            System.out.print("請輸入客製化內容："); custText=sc.nextLine(); chargeable=true;
        }

        System.out.print("請輸入數量："); int qty=sc.nextInt(); sc.nextLine();

        ComboMeal single;
        if(type==3) single = new ComboMeal("",0,"",0,drink);
        else single = new ComboMeal(main,mainPrice,side,sidePrice,drink);

        if(!custText.isEmpty()) single.addCustomization(custText, chargeable);
        single.setQuantity(qty);
        list.add(single);
    }
}
