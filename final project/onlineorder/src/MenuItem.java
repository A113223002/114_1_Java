/**
 * 檔名：MenuItem.java
 * 類型：抽象類別（Abstract Class）

 * 【用途說明】
 * 本類別為線上訂餐系統中所有餐點的共同父類別，
 * 定義所有菜單項目都必須具備的基本屬性與行為。

 * 【對應系統規格書】
 * 抽象類別：MenuItem（菜單項目）

 * 【設計重點】
 * 1. 強制所有子類別實作 calculatePrice() 方法
 * 2. 確保餐點名稱與價格的正確性
 */

public abstract class MenuItem {

    // protected：子類別可以直接使用
    protected String name;       // 餐點名稱
    protected double basePrice;  // 餐點基礎價格

    /**
     * 建構子：初始化所有餐點共通資料
     *
     * @param name 餐點名稱（不可為空）
     * @param basePrice 餐點基礎價格（不可為負數）
     */
    public MenuItem(String name, double basePrice) {

        // 驗證餐點名稱不可為 null 或空字串
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }

        // 驗證價格必須大於 0
        if (basePrice < 0) {
            throw new IllegalArgumentException("basePrice cannot be negative");
        }

        this.name = name;
        this.basePrice = basePrice;
    }

    // Getter：符合封裝（Encapsulation）
    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    /**
     * 抽象方法：calculatePrice
     * 功能：由子類別負責實作實際的價格計算邏輯
     */
    public abstract double calculatePrice();

    /**
     * 方法：toString
     * 功能：以「餐點名稱 - 價格」格式回傳字串
     */
    @Override
    public String toString() {
        return name + "：" + calculatePrice() + " 元";
    }
}