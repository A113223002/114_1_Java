/**
 * 檔名：MainDish.java
 * 類型：類別（Class）

 * 【用途說明】
 * 表示主餐項目，主餐會依照份量大小影響價格。

 * 【對應系統規格書】
 * 類別：MainDish（主餐）
 */

public class MainDish extends MenuItem {

    private String size; // 份量

    /**
     * 建構子
     *
     * @param name 主餐名稱
     * @param basePrice 基礎價格（Small）
     * @param size 份量
     */
    public MainDish(String name, double basePrice, String size) {
        super(name, basePrice);

        // 驗證份量是否為合法選項
        if (!size.equals("Small") &&
                !size.equals("Medium") &&
                !size.equals("Large")) {
            throw new IllegalArgumentException("Invalid size");
        }

        this.size = size;
    }

    /**
     * 覆寫 calculatePrice()
     * 根據份量計算最終價格
     */
    @Override
    public double calculatePrice() {
        switch (size) {
            case "Medium":
                return basePrice + 20;
            case "Large":
                return basePrice + 40;
            default: // Small
                return basePrice;
        }
    }
}
