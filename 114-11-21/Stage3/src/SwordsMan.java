public class SwordsMan extends Role{
    // 武器名稱
    private String weapon;
    // 護甲值，用來減免傷害
    private int armor;
    // 能量與最大能量
    private int energy;
    private int maxEnergy;

    // 建構子：加入武器、護甲與能量參數
    public SwordsMan(String name, int health, int attackPower, String weapon, int armor, int maxEnergy) {
        super(name, health, attackPower);
        this.weapon = weapon;
        this.armor = armor;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
    }

    public String getWeapon() { return weapon; }
    public int getArmor() { return armor; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }

    // 回合開始時檢查武器狀態（對應輸出範例）
    @Override
    public void prepareBattle() {
        System.out.println("⚔️  " + this.getName() + " 檢查 " + weapon + " 的狀態...");
        System.out.println("🛡️  目前護甲值：" + armor);
        System.out.println("✨ 擦拭劍刃，劍身反射出凜冽的寒光...");
        System.out.println();
    }

    // 攻擊行為：消耗 15 能量、造成攻擊力傷害，並印出收劍敘述
    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️  " + this.getName() + " 揮動 " + weapon + " 攻擊 " + opponent.getName() + "！");
        // 消耗能量 15
        this.energy = Math.max(0, this.energy - 15);
        System.out.println("💫 消耗 15 點能量，剩餘：" + this.energy + "/" + this.maxEnergy);

        // 若敵方為近戰且擁有護甲，我們希望護甲可以減傷；Role 的 receiveDamage 為直接扣血，
        // 所以在此先處理護甲減免：若 opponent 為 SwordsMan 或 ShieldSwordsMan，而且擁有 getArmor，
        // 我們應由對方類別自行處理護甲（因此改為呼叫 opponent.receiveDamage 並讓對方 override），
        // 但為了確保護甲訊息出現在輸出中，我們在受傷端 implement 护甲訊息。

        int actualDamage = opponent.receiveDamage(this.getAttackPower());
        System.out.println("💥 " + opponent.getName() + " 受到 " + actualDamage + " 點傷害！目前生命值：" + opponent.getHealth());
        System.out.println();
        System.out.println("🗡️  " + this.getName() + " 將 " + weapon + " 收入劍鞘。\n");
    }

    // 顯示特殊技能（示範抽象方法的實作）
    @Override
    public void showSpecialSkill() {
        System.out.println("┌─────────────────────────────┐");
        System.out.printf("│ %s 的特殊技能        │%n", this.getName());
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 技能名稱：連續斬擊          │");
        System.out.println("│ 技能描述：快速揮劍三次      │");
        System.out.println("│ 技能效果：造成 150% 傷害    │");
        System.out.println("└─────────────────────────────┘");
        System.out.println();
    }

    // 回合結束後的行為：收劍、喘息等敘述（保留）
    @Override
    public void afterBattle() {
        // 本例將主要的收劍描述放在 attack 中，這裡提供較短的結尾敘述
        System.out.println("\n");
    }

    // 近戰角色受傷會先被護甲減免（若護甲存在）
    @Override
    public int receiveDamage(int damage) {
        int reduced = Math.min(this.armor, Math.max(0, damage));
        int actual = Math.max(0, damage - reduced);
        System.out.println("🛡️  護甲減免 " + reduced + " 點傷害！");
        this.setHealth(this.getHealth() - actual);
        return actual;
    }
}