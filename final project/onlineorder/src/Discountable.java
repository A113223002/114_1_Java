/**
 * 介面：Discountable
 * 功能：代表「可以套用折扣」的項目
 * 教學重點：interface、方法規格定義
 *
 * 設計理由：
 * 不是所有餐點都能折扣，因此用介面區分
 */
public interface Discountable {

    /**
     * 計算折扣金額
     * @param type 折扣類型（例如會員折扣、活動折扣）
     * @return 折扣金額
     */
    double calculateDiscount(String type);

    /**
     * 判斷是否可套用指定折扣
     * @param type 折扣類型
     * @return 是否可折扣
     */
    boolean isDiscountApplicable(String type);
}
