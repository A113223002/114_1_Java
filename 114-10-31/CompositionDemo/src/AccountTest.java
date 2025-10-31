// 檔案路徑: c:\Users\m306\Desktop\114_1_Java\114-10-31\CompositionDemo\src\AccountTest.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) {
        List<Account> customers = new ArrayList<>(); // 儲存客戶帳戶的List
        Account acc1 = new Account("A001", "Alice","A12345678", 5000);
        addCustomer(customers, acc1);
        Account acc2 = new Account("A002", "Bob","00000000", 3000);
        addCustomer(customers, acc2);
        Account acc3 = new Account("A003", "Charlie","00000000", -100);
        addCustomer(customers,  acc3);

        operation(customers);
        // 顯示所有客戶帳戶資訊
//        System.out.println("\n所有位客戶帳戶資訊:");
//        printCustomerAccounts(customers);
    }

    public static void operation(List<Account> customers) {
        Scanner scanner = new Scanner(System.in);
        Account selectedAccount = null;
        while (true) {
            menu();
            System.out.print("請選擇功能(1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除換行符號
            switch (choice) {
                case 1:
                    System.out.print("輸入帳戶號碼: ");
                    String accNum = scanner.nextLine();
                    System.out.print("輸入持有人名稱: ");
                    String ownerName = scanner.nextLine();
                    System.out.print("輸入初始餘額: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // 清除換行
                    Account newAccount = new Account(accNum, ownerName, initialBalance);
                    addCustomer(customers, newAccount);
                    break;
                case 2: // 存款
                    System.out.print("輸入要存款的帳戶號碼: ");
                    String depAcc = scanner.nextLine();
                    Account depAccount = customerInAction(customers, depAcc);
                    if (depAccount == null) break;
                    System.out.print("輸入存款金額: ");
                    double depAmt = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        depAccount.deposit(depAmt);
                        System.out.println("存款成功，帳戶餘額: " + depAccount.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("存款失敗: " + e.getMessage());
                    }
                    break;
                case 3: // 提款
                    System.out.print("輸入要提款的帳戶號碼: ");
                    String witAcc = scanner.nextLine();
                    Account witAccount = customerInAction(customers, witAcc);
                    if (witAccount == null) break;
                    System.out.print("輸入提款金額: ");
                    double witAmt = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        witAccount.withdraw(witAmt);
                        System.out.println("提款成功，帳戶餘額: " + witAccount.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("提款失敗: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("輸入要查詢的帳戶號碼: ");
                    String searchAccNum = scanner.nextLine();
                    selectedAccount = customerInAction(customers, searchAccNum);
                    printCustomerInfo(selectedAccount);

                    break;

                case 5:
                    System.out.println("\n所有位客戶帳戶資訊:");
                    printCustomerAccounts(customers);
                    break;
                case 6:
                    System.out.print("輸入要刪除的帳戶號碼: ");
                    String deleteAccNum = scanner.nextLine();
                    deleteCustomer(customers, deleteAccNum);
                    break;
                case 7:
                    System.out.println("離開系統，謝謝使用!");
                    return;
                default:
                    System.out.println("無效的選擇，請重新輸入");
            }
        }
    }

    public static Account customerInAction(List<Account> customers, String accountNumber) {
        for (Account account : customers) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("找不到指定的帳戶號碼: " + accountNumber);
        return null;
    }

    public static void addCustomer(List<Account> customers, Account newAccount) {
        customers.add(newAccount);
        System.out.println("新增客戶成功: " + newAccount.getAccountNumber());
    }

    public static void deleteCustomer(List<Account> customers, String accountNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountNumber().equals(accountNumber)) {
                Account removedAccount = customers.remove(i);
                System.out.println("刪除客戶成功: " + removedAccount.getAccountNumber() + " (" + removedAccount.getOwnerName() + ")");
                return;
            }
        }
        System.out.println("找不到指定的帳戶號碼: " + accountNumber);
    }

    public static void printCustomerAccounts(List<Account> customers) {
        for (Account customer : customers) {
            printCustomerInfo(customer);
        }
    }

    public static void printCustomerInfo(Account account) {
        if (account == null) {
            System.out.println("無法列印帳戶資訊，帳戶不存在");
            return;
        }
        System.out.println(account.toString());
    }

    // 功能選單 (1) 新增客戶 (2) 存款 (3) 提款 (4) 列印指定客戶帳戶資訊 (5) 顯示所有客戶帳戶資訊 (6) 刪除客戶帳戶 (7) 離開
    public static void menu() {
        System.out.println("功能選單:");
        System.out.println("1. 新增客戶");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("4. 列印指定客戶帳戶資訊");
        System.out.println("5. 顯示所有客戶帳戶資訊");
        System.out.println("6. 刪除客戶帳戶");
        System.out.println("7. 離開");
    }
}