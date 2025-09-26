import java.security.SecureRandom;  // 匯入安全隨機數產生器

public class RollDice {
    public static void main(String[] args) {
        SecureRandom randomNumbers = new SecureRandom();  // 建立 SecureRandom 物件，用於產生隨機數

        int[] frequency = new int[7]; // 建立一個長度為 7 的整數陣列，用來記錄每個點數出現的次數（索引 0 不使用）

        // 進行 6,000,000 次擲骰子
        for (int roll = 1; roll <= 6000000; roll++) {
            int face = 1 + randomNumbers.nextInt(6); // 產生 1~6 的隨機點數
            frequency[face]++; // 將對應點數的次數加一
        }

        System.out.printf("%s%10s%n", "點數", "出現次數");  //%10s對齊用，留10個空格的空間
        for (int face = 1; face < frequency.length; face++) {
            System.out.printf("%4d%10d%n", face, frequency[face]);  //%10s對齊用，留10個空格的空間
        }
    }
}
