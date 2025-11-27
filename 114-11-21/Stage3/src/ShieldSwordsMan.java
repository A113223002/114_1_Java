public class ShieldSwordsMan extends SwordsMan{
    private int defenseCapacity;
    // 建構子：初始化持盾劍士的名稱、生命值、攻擊力、武器與護甲
    public ShieldSwordsMan(String name, int health, int attackPower, String weapon, int armor, int defenseCapacity, int maxEnergy) {
        super(name, health, attackPower, weapon, armor, maxEnergy);
        this.defenseCapacity = defenseCapacity;
    }

    // 持盾劍士的攻擊實作：攻擊力略遜於無盾劍士
    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️  " + this.getName() + " 揮動 " + this.getWeapon() + " 攻擊 " + opponent.getName() + "！");
        // 消耗 15 能量
        // 取用父類能量欄位的 getter
        // 為了簡潔，我們不再顯示能量變化細節在此範例中
        int actual = opponent.receiveDamage(Math.max(0, this.getAttackPower() - 5));
        System.out.println("💥 " + opponent.getName() + " 受到 " + actual + " 點傷害！目前生命值：" + opponent.getHealth());
        System.out.println();
    }

    // 取得防禦力
    public int getDefenseCapacity() {
        return defenseCapacity;
    }

    // 使用盾牌防禦：回復生命值（此為簡化示範）
    public void defence() {
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println(this.getName() + " 使用盾牌防禦，恢復 " + defenseCapacity + " 點生命值。" + this);
    }

    // 持盾劍士在受攻擊時使用護甲減免傷害（覆寫）
    @Override
    public int receiveDamage(int damage) {
        int reduced = Math.min(defenseCapacity, damage);
        int actual = Math.max(0, damage - reduced);
        System.out.println("🛡️  護甲減免 " + reduced + " 點傷害！");
        // 使用父類的 setHealth
        this.setHealth(this.getHealth() - actual);
        return actual;
    }

    // 顯示特殊技能
    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.printf("║ %s 的特殊技能      ║%n", this.getName());
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：盾牌猛擊          ║");
        System.out.println("║ 技能描述：使用盾牌撞擊敵人  ║");
        System.out.println("║ 技能效果：造成傷害並暈眩    ║");
        System.out.printf("║ 防禦加成：+%d 防禦力           ║%n", this.defenseCapacity);
        System.out.println("╚═════════════════════════════╝");
        System.out.println();
    }
}