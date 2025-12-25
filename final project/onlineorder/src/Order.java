import java.util.ArrayList;

/**
 * 檔名：Order.java

 * 【用途說明】
 * 表示顧客所建立的一筆訂單，
 * 訂單中可包含多個餐點，並負責計算總金額。

 * 【對應系統規格書】
 * 類別：Order（訂單）

 * 【設計重點】
 * 1. 訂單可新增多個 MenuItem
 * 2. 總金額由各餐點自行計算後加總
 */

public class Order {

    private ArrayList<MenuItem> items = new ArrayList<>();
    private Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * 使用多型計算總價
     */
    public double calculateTotalPrice() {
        double total = 0;
        for (MenuItem m : items) {
            total += m.calculatePrice();
        }
        return total;
    }
}