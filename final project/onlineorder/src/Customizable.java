import java.util.ArrayList;

/**
 * 檔名：Customizable.java
 * 類型：介面（Interface）

 * 【用途說明】
 * 定義可進行客製化設定的餐點行為。

 * 【對應系統規格書】
 * 介面：Customizable（可客製化項目）
 */

public interface Customizable {

    // 套用客製化選項
    void applyCustomization(String option);

    // 取得所有客製化內容
    ArrayList<String> getCustomizations();

    // 客製化額外費用
    double getCustomizationFee();
}