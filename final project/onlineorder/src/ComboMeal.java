import java.util.ArrayList;

/**
 * 檔名：ComboMeal.java

 * 【用途說明】
 * 表示套餐，內含多個餐點並統一套用 85 折優惠。

 * 【對應系統規格書】
 * 類別：ComboMeal（套餐）
 */
public class ComboMeal extends MenuItem {

    private ArrayList<MenuItem> items = new ArrayList<>();
    private final double discountRate = 0.85;

    public ComboMeal(String name) {
        super(name, 0); // 套餐本身沒有單價
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * 套餐價格 = 所有餐點加總 * 折扣
     */
    @Override
    public double calculatePrice() {
        double total = 0;
        for (MenuItem m : items) {
            total += m.calculatePrice();
        }
        return Math.round(total * discountRate);
    }
}