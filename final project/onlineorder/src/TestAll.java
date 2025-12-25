// src/TestAll.java
public class TestAll {
    public static void main(String[] args) {
        System.out.println("===== 開始測試 MenuItem =====");
        testMenuItem();

        System.out.println("\n===== 開始測試 MainDish =====");
        testMainDish();

        System.out.println("\n===== 開始測試 SideDish =====");
        testSideDish();

        System.out.println("\n===== 開始測試 Beverage =====");
        testBeverage();

        System.out.println("\n===== 開始測試 ComboMeal =====");
        testComboMeal();

        System.out.println("\n===== 開始測試 Restaurant =====");
        testRestaurant();

        System.out.println("\n===== 開始測試 Order =====");
        testOrder();

        System.out.println("\n===== 開始測試 Customer =====");
        testCustomer();

        System.out.println("\n===== 開始測試 DeliveryFeeCalculator =====");
        testDeliveryFeeCalculator();
    }

    /* ================================
       MenuItem 測試
       ================================ */
    private static void testMenuItem() {
        try { new MainDish(null, 100, "Small"); System.out.println("TC-MI001 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-MI001 PASS: " + e.getMessage()); }

        try { new MainDish("", 100, "Small"); System.out.println("TC-MI002 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-MI002 PASS: " + e.getMessage()); }

        try { new MainDish("Chicken", -1, "Small"); System.out.println("TC-MI003 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-MI003 PASS: " + e.getMessage()); }

        try {
            MenuItem m = new MainDish("ChickenRice", 120, "Small");
            System.out.println("TC-MI004 " + ("ChickenRice".equals(m.getName()) && m.getBasePrice() == 120 ? "PASS" : "FAIL"));
        } catch (Exception e) { System.out.println("TC-MI004 FAIL: " + e.getMessage()); }
    }

    /* ================================
       MainDish 測試
       ================================ */
    private static void testMainDish() {
        try { new MainDish("Chicken", 100, "XL"); System.out.println("TC-MD001 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-MD001 PASS: " + e.getMessage()); }

        System.out.println("TC-MD002 " + (new MainDish("Chicken", 100, "Small").calculatePrice() == 100 ? "PASS" : "FAIL"));
        System.out.println("TC-MD003 " + (new MainDish("Chicken", 100, "Medium").calculatePrice() == 120 ? "PASS" : "FAIL"));
        System.out.println("TC-MD004 " + (new MainDish("Chicken", 100, "Large").calculatePrice() == 140 ? "PASS" : "FAIL"));
    }

    /* ================================
       SideDish 測試
       ================================ */
    private static void testSideDish() {
        SideDish sd1 = new SideDish("Egg", 40);
        System.out.println("TC-SD001 " + (sd1.calculatePrice() == 40 ? "PASS" : "FAIL"));

        SideDish sd2 = new SideDish("Egg", 40);
        sd2.upgrade();
        System.out.println("TC-SD002 " + (sd2.calculatePrice() == 50 ? "PASS" : "FAIL"));
    }

    /* ================================
       Beverage 測試
       ================================ */
    private static void testBeverage() {
        Beverage bv1 = new Beverage("Cola", 30);
        bv1.applyCustomization("去冰");
        System.out.println("TC-BV001 " + (bv1.getCustomizations().size() == 1 ? "PASS" : "FAIL"));
        bv1.applyCustomization("少冰");
        System.out.println("TC-BV002 " + (bv1.calculatePrice() == 30 ? "PASS" : "FAIL"));
    }

    /* ================================
       ComboMeal 測試
       ================================ */
    private static void testComboMeal() {
        ComboMeal cm = new ComboMeal("Lunch Set");
        MainDish md = new MainDish("Chicken", 100, "Small");
        SideDish sd = new SideDish("Egg", 50);
        cm.addItem(md); cm.addItem(sd);
        double expected = Math.round((md.calculatePrice() + sd.calculatePrice()) * 0.85);
        System.out.println("TC-CM001 " + (cm.calculatePrice() == expected ? "PASS" : "FAIL"));

        ComboMeal cm2 = new ComboMeal("Value Set");
        System.out.println("TC-CM002 " + (cm2.getBasePrice() == 0.0 ? "PASS" : "FAIL"));
    }

    /* ================================
       Restaurant 測試
       ================================ */
    private static void testRestaurant() {
        try { new Restaurant(""); System.out.println("TC-RS001 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-RS001 PASS: " + e.getMessage()); }

        try {
            Restaurant r = new Restaurant("MyRestaurant");
            Beverage b = new Beverage("cola", 30);
            r.addMenuItem(b);
            r.addMenuItem(new Beverage("cola", 30));
            System.out.println("TC-RS002 FAIL");
        } catch (IllegalArgumentException e) { System.out.println("TC-RS002 PASS: " + e.getMessage()); }
    }

    /* ================================
       Order 測試
       ================================ */
    private static void testOrder() {
        Customer c = new Customer("C001", "Mary");
        Order o = new Order(c);

        MainDish md = new MainDish("Chicken", 140, "Large");
        SideDish sd = new SideDish("Egg", 40);
        sd.upgrade();

        o.addItem(md);
        o.addItem(sd);

        // 印出每個 item 價格確認
        System.out.println("MainDish price: " + md.calculatePrice());
        System.out.println("SideDish price: " + sd.calculatePrice());
        System.out.println("Total: " + o.calculateTotalPrice());

        // 用實際計算總價作為預期
        double expectedTotal = md.calculatePrice() + sd.calculatePrice();
        System.out.println("TC-OR002 " + (o.calculateTotalPrice() == expectedTotal ? "PASS" : "FAIL"));
    }



    /* ================================
       Customer 測試
       ================================ */
    private static void testCustomer() {
        try { new Customer("", "Mary"); System.out.println("TC-CS001 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-CS001 PASS"); }

        try { new Customer("C001", ""); System.out.println("TC-CS002 FAIL"); }
        catch (IllegalArgumentException e) { System.out.println("TC-CS002 PASS"); }

        try { new Customer("C001", "Mary"); System.out.println("TC-CS003 PASS"); }
        catch (Exception e) { System.out.println("TC-CS003 FAIL"); }
    }

    /* ================================
       DeliveryFeeCalculator 測試
       ================================ */
    private static void testDeliveryFeeCalculator() {
        System.out.println("TC-DF001 " + (DeliveryFeeCalculator.calculateFee(5, false) == 50.0 ? "PASS" : "FAIL"));
        System.out.println("TC-DF002 " + (DeliveryFeeCalculator.calculateFee(5, true) == 70.0 ? "PASS" : "FAIL"));
    }
}