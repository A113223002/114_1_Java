// java
// src/OnlineFoodDemo.java
public class OnlineFoodDemo {
    public static void main(String[] args) {
        System.out.println("===== 線上訂餐系統展示 =====\n");

        // 建立客戶
        Customer mary = new Customer("001", "Mary");
        Customer john = new Customer("002", "John");

        // 建立餐點
        MainDish chickenRice = new MainDish("雞腿便當", 120, "Large");   // 主餐
        MainDish porkRice = new MainDish("排骨便當", 90, "Medium");
        SideDish vegetable = new SideDish("小菜", 30);                      // 附餐
        SideDish egg = new SideDish("滷蛋", 15);
        Beverage tea = new Beverage("無糖綠茶", 20);                    // 飲料
        tea.applyCustomization("去冰");
        Beverage juice = new Beverage("柳橙汁", 30);
        juice.applyCustomization("正常冰");

        // 升級附餐
        vegetable.upgrade();  // 小菜升級加價

        // 建立訂單
        Order order1 = new Order(mary);
        order1.addItem(chickenRice);
        order1.addItem(vegetable);
        order1.addItem(tea);

        Order order2 = new Order(john);
        order2.addItem(porkRice);
        order2.addItem(egg);
        order2.addItem(juice);

        // 設定外送距離與費用 (假設每公里 10 元)
        int maryDistanceKm = 3;
        int johnDistanceKm = 5;
        int deliveryRatePerKm = 10;

        int maryDeliveryFee = maryDistanceKm * deliveryRatePerKm;
        int johnDeliveryFee = johnDistanceKm * deliveryRatePerKm;

        // 顯示訂單明細與總價
        System.out.println("【Mary 的訂單】");
        System.out.println("主餐價格：" + chickenRice.calculatePrice() + " 元");
        System.out.println("附餐價格：" + vegetable.calculatePrice() + " 元");
        System.out.println("飲料價格：" + tea.calculatePrice() + " 元");
        System.out.println("外送距離：" + maryDistanceKm + " 公里，費用：" + maryDeliveryFee + " 元");
        double maryTotal = chickenRice.calculatePrice() + vegetable.calculatePrice() + tea.calculatePrice() + maryDeliveryFee;
        System.out.println("總價：" + maryTotal + " 元\n");

        System.out.println("【John 的訂單】");
        System.out.println("主餐價格：" + porkRice.calculatePrice() + " 元");
        System.out.println("附餐價格：" + egg.calculatePrice() + " 元");
        System.out.println("飲料價格：" + juice.calculatePrice() + " 元");
        System.out.println("外送距離：" + johnDistanceKm + " 公里，費用：" + johnDeliveryFee + " 元");
        double johnTotal = porkRice.calculatePrice() + egg.calculatePrice() + juice.calculatePrice() + johnDeliveryFee;
        System.out.println("總價：" + johnTotal + " 元\n");

        // 錯誤處理示範
        System.out.println("【錯誤處理示範】");
        try {
            SideDish salad = null;
            order1.addItem(salad);  // 嘗試加入 null 餐點
        } catch (IllegalArgumentException e) {
            System.out.println("  預期錯誤：" + e.getMessage());
        }

        // 顯示升級餐點價格
        System.out.println("\n【附餐升級價格檢查】");
        System.out.println("小菜 升級後價格：" + vegetable.calculatePrice() + " 元");
    }
}