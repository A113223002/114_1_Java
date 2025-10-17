public class AccountTest {
    private int customerCount;  //客戶數量
    public static void main(String[] args) {
        Account[] customers = new Account[10]; // 儲存客戶帳戶的陣列
        Account acc1 = new Account("A001", "Alice", 5000);
        addCustomer(customers, acc1);
        Account acc2 = new Account("A002", "Bob", 3000);
        accCustomer(customers, acc2);
        Account acc3 = new Account("A003", "Charlie", -100);
        accCustomer(customers, acc3);
    }

    public static void addCustomer(Account[] customers, Account newAccount) {
        if (customerCount < customers.length) {
            customers[customerCount] = newAccount;
            customerCount++;
            System.out.println("成功新增客戶: " + newAccount.getAccountNumber());
            return;
            }
        }
        System.out.println("無法新增客戶，客戶數量已達上限");
    }

    public static void printCustomerAccounts(Account[] customers) {
        for (int i = 0; i < customerCount; i++) {
            printCustomerInfo(customers[i]);
        }
    }

    public static void printCustomerInfo(Account account) {
        System.out.printf("帳戶號碼： " + account.getAccountNumber() +
                ", 持所有者: " + account.getOwnerName() +
                ", 餘額: " + account.getBalance());
    }

