import java.util.ArrayList;

/**
 * 檔名：Beverage.java

 * 【用途說明】
 * 表示飲料項目，支援冰量客製化，但不影響價格。

 * 【對應系統規格書】
 * 類別：Beverage（飲料）
 */
public class Beverage extends MenuItem implements Customizable {

    // 儲存客製化選項（去冰、少冰等）
    private ArrayList<String> customizations = new ArrayList<>();

    public Beverage(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public void applyCustomization(String option) {
        customizations.add(option);
    }

    @Override
    public ArrayList<String> getCustomizations() {
        return customizations;
    }

    /**
     * 本系統飲料不因客製化加價
     */
    @Override
    public double getCustomizationFee() {
        return 0;
    }

    /**
     * 飲料最終價格
     */
    @Override
    public double calculatePrice() {
        return basePrice + getCustomizationFee();
    }
}
