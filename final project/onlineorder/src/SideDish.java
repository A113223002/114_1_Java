/**
 * 檔名：SideDish.java

 * 【用途說明】
 * 表示附餐項目，附餐可選擇是否升級。

 * 【對應系統規格書】
 * 類別：SideDish（附餐）
 */
public class SideDish extends MenuItem {

    private boolean upgraded = false; // 是否升級

    public SideDish(String name, double basePrice) {
        super(name, basePrice);
    }

    /**
     * 升級附餐
     * 只改狀態，不直接改價格
     */
    public void upgrade() {
        upgraded = true;
    }

    /**
     * 計算附餐價格
     */
    @Override
    public double calculatePrice() {
        return upgraded ? basePrice + 10 : basePrice;
    }
}