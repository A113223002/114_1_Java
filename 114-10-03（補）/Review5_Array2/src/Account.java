public class Account {
    private static int accountCount = 0; // 帳戶數量統計

    // 帳戶號碼，唯一識別每個帳戶
    private String accountNumber;
    // 帳戶擁有者名稱
    private String ownerName;
    // 帳戶餘額，儲存目前帳戶內的金額
    private double balance;

    /**
     * 帳戶建構子，初始化帳戶號碼與初始餘額
     * @param accountNumber 帳戶號碼
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber,String ownerName, double initialBalance) {
        this.setAccountNumber(accountNumber); // 設定帳戶號碼
        this.ownerName = ownerName;
        try{
            this.setBalance(initialBalance);      // 設定初始餘額
        } catch (IllegalArgumentException e) {
            System.out.println("初始餘額錯誤: " + e.getMessage() + "，將餘額設為0");
        }
        accountCount++;  //帳戶數量+1
    }

    public Account(String accountNumber, double initialBalance) {
        this(accountNumber, "未知", 0);
    }

    public Account() {
        this("未知", "未知", 0);
    }

    public Account(String accountNumber) {
        this(accountNumber, "未知", 0);
    }

    /**
     * 取得帳戶餘額
     * @return 帳戶餘額
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 取得帳戶餘額
     * @return 帳戶餘額
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 取得帳戶擁有者名稱
     * @return 帳戶擁有者名稱
     */
    public String getOwnerName(){
        return ownerName;
    }

    /**
     * 設定帳戶餘額
     * @param balance 欲設定的帳戶餘額，必須為正數
     * @throws IllegalArgumentException 如果餘額非整數則拋出例外
     */
    public void setBalance(double balance){
        if (balance >= 0) {
            this.balance = balance;  // 設定新的帳戶餘額
        } else {
            throw new IllegalArgumentException("帳戶餘額必須為正數");
        }
    }

    /**
     * 設定帳戶號碼
     * @param accountNumber 欲設定的帳戶號碼
     */
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber; // 設定帳戶號碼
    }

    /**
     * 存款方法，將指定金額存入帳戶
     * @param amount 存款金額，必須大於0
     * @throws IllegalArgumentException 如果存款金額小於等於0
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // 增加帳戶餘額
        } else {
            throw new IllegalArgumentException("存款金額必須為正數");
        }
    }

    /**
     * 提款方法，從帳戶提取指定金額
     * @param amount 提款金額，必須正數且不得超過餘額
     * @throws IllegalArgumentException 如果提款金額不合法則拋出例外
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // 減少帳戶餘額
        } else {
            throw new IllegalArgumentException("提款金額不合法");
        }
    }
}