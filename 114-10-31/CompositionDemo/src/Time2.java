// 檔案路徑: c:\Users\m306\Desktop\114_1_Java\114-10-31\CompositionDemo\src\Time2.java
public class Time2 {
    private int hour; // 小時範圍 0 到 23
    private int minute; // 分鐘範圍 0 到 59
    private int second; // 秒數範圍 0 到 59

    // Time2 無參數建構子：
    // 將每個實例變數初始化為零
    public Time2() {
        this(0, 0, 0); // 呼叫帶三個參數的建構子
    }

    // Time2 建構子：提供小時，分鐘與秒數預設為 0
    public Time2(int hour) {
        this(hour, 0, 0); // 呼叫帶三個參數的建構子
    }

    // Time2 建構子：提供小時與分鐘，秒數預設為 0
    public Time2(int hour, int minute) {
        this(hour, minute, 0); // 呼叫帶三個參數的建構子
    }

    // Time2 建構子：提供小時、分鐘與秒數
    public Time2(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("hour must be 0-23");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("minute must be 0-59");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("second must be 0-59");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Time2 建構子：提供另一個 Time2 物件
    public Time2(Time2 time) {
        // 呼叫帶三個參數的建構子
        this(time.hour, time.minute, time.second);
    }

    // 設定方法
    // 使用萬用時間設定新的時間值；
    // 驗證資料
    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("hour must be 0-23");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("minute must be 0-59");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("second must be 0-59");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 驗證並設置小時
    public void setHour(int hour) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("hour must be 0-23");
        }

        this.hour = hour;
    }

    // 驗證並設置分鐘
    public void setMinute(int minute) {
        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("minute must be 0-59");
        }

        this.minute = minute;
    }

    // 驗證並設置秒數
    public void setSecond(int second) {
        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("second must be 0-59");
        }

        this.second = second;
    }

    // 取得方法
    // 取得小時值
    public int getHour() {return hour;}

    // 取得分鐘值
    public int getMinute() {return minute;}

    // 取得秒數值
    public int getSecond() {return second;}

    // 轉換為萬用時間格式的字串 (HH:MM:SS)
    public String toUniversalString() {
        return String.format(
                "%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    // 轉換為標準時間格式的字串 (H:MM:SS AM 或 PM)
    public String toString() {
        return String.format("%d:%02d:%02d %s",
                ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
                getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
    }
}