public class Date {
    private int month; // 月份 1-12
    private int day; // 根據月份的日 1-31
    private int year; // 年份，可為任意年

    private static final int[] daysPerMonth =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 建構子：確認在給定年份下月份與日期的正確值
    public Date(int month, int day, int year) {
        // 檢查月份是否在範圍內
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException(
                    "month (" + month + ") must be 1-12");
        }

        // 檢查該月的日是否在範圍內
        if (day <= 0 ||
                (day > daysPerMonth[month] && !(month == 2 && day == 29))) {
            throw new IllegalArgumentException("day (" + day +
                    ") out-of-range for the specified month and year");
        }

        // 如果是二月且日為29，檢查是否為閏年
        if (month == 2 && day == 29 && !(year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0))) {
            throw new IllegalArgumentException("day (" + day +
                    ") out-of-range for the specified month and year");
        }

        this.month = month;
        this.day = day;
        this.year = year;

        // System.out.printf("Date object constructor for date %s%n", this);
    }

    // 回傳形式為 month/day/year 的字串
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}