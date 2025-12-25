/**
 * 檔名：Customer.java

 * 【用途說明】
 * 表示下訂單的顧客資料。

 * 【對應系統規格書】
 * 類別：Customer（顧客）

 * 【設計重點】
 * 1. 顧客編號與姓名不可為 null 或空字串
 */
public class Customer {

    private String customerId; // 顧客編號
    private String name;       // 顧客姓名

    /**
     * 建構子：初始化顧客資訊
     * @param customerId 顧客編號，不可為空
     * @param name 顧客姓名，不可為空
     */
    public Customer(String customerId, String name) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("customerId cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.customerId = customerId;
        this.name = name;
    }

    /**
     * 取得顧客編號
     * @return 顧客編號
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 取得顧客姓名
     * @return 顧客姓名
     */
    public String getName() {
        return name;
    }
}