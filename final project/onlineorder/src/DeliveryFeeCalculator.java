/**
 * 檔名：DeliveryFeeCalculator.java

 * 【用途說明】
 * 負責計算外送費用。

 * 【對應系統規格書】
 * 類別：DeliveryFeeCalculator（外送費計算器）

 * 【設計重點】
 * 1. 依照距離計算基本外送費
 * 2. 尖峰時段加收額外費用
 */

public class DeliveryFeeCalculator {

    /**
     * 計算外送費
     * @param distance 距離（公里）
     * @param isPeakTime 是否尖峰時段
     * @return 最終外送費
     */
    public static double calculateFee(double distance, boolean isPeakTime) {
        double fee = distance * 10; // 基本費用
        if (isPeakTime) {
            fee += 20; // 尖峰加收
        }
        return fee;
    }
}