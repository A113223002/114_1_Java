public abstract class Role {
    // 角色名稱
    private final String name;
    // 生命值
    private int health;
    // 攻擊力
    private int attackPower;

    // 建構子：初始化角色的名稱、生命值和攻擊力
    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // 取得角色名稱
    public String getName() {
        return name;
    }

    // 取得生命值
    public int getHealth() {
        return health;
    }

    // 設定生命值 (內部使用)
    public void setHealth(int health) {
        this.health = health;
    }

    // 取得攻擊力
    public int getAttackPower() {
        return attackPower;
    }

    // 設定攻擊力
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    // 檢查角色是否存活
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * 統一處理受傷邏輯：將生命值扣到最低為 0，回傳實際造成的傷害量。
     * 這樣子類就不用直接操作 setHealth，可以統一維護負值保護等邏輯。
     */
    public int receiveDamage(int damage) {
        if (damage < 0) damage = 0; // 保護性檢查
        int prev = this.health;
        int newHp = Math.max(0, prev - damage);
        this.health = newHp;
        return prev - newHp; // 實際造成的傷害
    }

    // 每個角色需實作的行為：攻擊另一個角色
    public abstract void attack(Role opponent);

    /**
     * 展示角色的特殊技能（抽象方法）
     * 設計成抽象：要求每個具體角色實作自己的技能展示邏輯
     */
    public abstract void showSpecialSkill();

    /**
     * 戰前準備：在回合開始時角色可以輸出描述或做初始化（由子類實作）
     */
    public abstract void prepareBattle();

    /**
    @SuppressWarnings("unused")
    @SuppressWarnings("unused")
     * 戰後行為：在回合結束時角色可以做恢復或狀態處理（由子類實作）
     */
    public abstract void afterBattle();

    @Override
    public String toString() {
        return "角色名稱: " + name + ", 生命值: " + health + ", 攻擊力: " + attackPower;
}