public class Account {
    // 帳戶號碼，唯一識別每個帳戶
    private String accountNumber;
    // 帳戶餘額，儲存目前帳戶內的金額
    private double balance;

    /**
     * 帳戶建構子，初始化帳戶號碼與初始餘額
     * @param accountNumber 帳戶號碼
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, double initialBalance) {
        this.setAccountNumber(accountNumber); // 設定帳戶號碼
        try{
            this.setBalance(initialBalance);      // 設定初始餘額
        } catch (IllegalArgumentException e) {
            System.out.println("初始餘額錯誤: " + e.getMessage() + "，將餘額設為0");
        }
    }

    public Account() {
        this("000000"); // 預設帳戶號碼為"000000"
    }

    public Account(String accountNumber) {
        this(accountNumber, 0.0); // 預設餘額為0.0
    }

    /**
     * 取得帳戶號碼
     * @return 帳戶號碼
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
     * 驗證金額輸入的通用方法
     * @param value 初始金額
     * @param min 最小值
     * @param max 最大值（若無上限則傳入Double.MAX_VALUE）
     * @param errorMsg 錯誤提示訊息
     * @return 驗證後的金額
     * @throws IllegalArgumentException 若三次輸入皆不合法
     */
    private double validateAmount(double value, double min, double max, String errorMsg) {
        int attempts = 0;
        while ((value < min || value > max) && attempts < 3) {
            System.out.println(errorMsg + " 請重新輸入：");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            value = scanner.nextDouble();
            attempts++;
        }
        if (value >= min && value <= max) {
            return value;
        } else {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /**
     * 設定帳戶餘額
     * @param balance 欲設定的帳戶餘額，必須大於等於0
     * @throws IllegalArgumentException 如果餘額小於0則拋出例外
     */
    public void setBalance(double balance) {
        this.balance = validateAmount(balance, 0, Double.MAX_VALUE, "帳戶餘額必須為正數");
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
        double validAmount = validateAmount(amount, 0.01, Double.MAX_VALUE, "存款金額必須為正數");
        balance += validAmount;
    }

    public void deposit(double amount, String currencyType) {
        // 假設這裡有一個貨幣轉換的邏輯
        double exchangeRate = 1.0; // 假設匯率為1:1
        switch (currencyType) {
            case "USD":
                exchangeRate = 30.0; // 假設1 USD = 30 TWD
                break;
            case "EUR":
                exchangeRate = 35.0; // 假設1 EUR = 35 TWD
                break;
            case "JPN":
                exchangeRate = 0.28; // 假設1 JPN = 0.28 TWD
                break;
            default:
                throw new IllegalArgumentException("不支援的貨幣類型");
        }
        double convertedAmount = amount * exchangeRate;
        deposit(convertedAmount);
    }

    public void deposit(double... amounts) {
        double totalAmount = 0.0;
        for (double amount : amounts) {
            totalAmount += amount;
        }
        double validAmount = validateAmount(
                totalAmount, 0.01, Double.MAX_VALUE, "存款金額必須為正數");
        balance += validAmount;
    }
    /**
     while (amount <= 0 && attempts < 2) {
     * @param amount 提款金額，必須大於0且小於等於餘額
     * @throws IllegalArgumentException 如果提款金額不合法
     */
    public void withdraw(double amount) {
        double validAmount = validateAmount(amount, 0.01, balance, "提款金額不合法");
        balance -= validAmount;
    }
}

