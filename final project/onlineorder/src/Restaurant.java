import java.util.ArrayList;

/**
 * 檔名：Restaurant.java

 * 【用途說明】
 * 表示線上訂餐系統中的餐廳，
 * 餐廳負責管理自己的菜單項目。

 * 【對應系統規格書】
 * 類別：Restaurant（餐廳）

 * 【設計重點】
 * 1. 餐廳名稱不可為空
 * 2. 菜單中不可出現名稱重複的餐點
 */
public class Restaurant {

    private String name;
    private ArrayList<MenuItem> menu;

    // 驗證餐廳名稱不可為 null 或空字串
    public Restaurant(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be empty");
        }
        this.name = name;
        menu = new ArrayList<>();
    }

    /**
     * 新增餐點到菜單
     * @param item 菜單項目
     * @throws IllegalArgumentException 若菜單已存在相同名稱餐點
     */
    public void addMenuItem(MenuItem item) {
        for (MenuItem m : menu) {
            if (m.name.equals(item.name)) {
                throw new IllegalArgumentException("Menu item already exists");
            }
        }
        menu.add(item);
    }

    /**
     * 方法：getMenu
     *
     * 功能：
     * 回傳餐廳目前的菜單
     */
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
}