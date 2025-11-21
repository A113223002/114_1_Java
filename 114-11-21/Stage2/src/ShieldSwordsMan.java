public class ShieldSwordsMan extends SwordsMan{
    private int defenseCapacity;
    // 建構子：初始化持盾劍士的名稱、生命值和攻擊力
    public ShieldSwordsMan(String name, int health, int attackPower, int defenseCapacity) {
        super(name, health, attackPower);
        this.defenseCapacity = defenseCapacity;
    }

    // 持盾劍士的攻擊實作：攻擊力略遜於無盾劍士，但仍使用父類統一的受傷處理
    @Override
    public void attack(Role opponent) {
        int reducedDamage = Math.max(0, this.getAttackPower() - 5); // 持盾劍士攻擊力減少5點
        // 使用對外的 takeDamage 統一處理生命值，取得實際造成的傷害
        int actual = opponent.takeDamage(reducedDamage);
        System.out.println(this.getName() + " 揮劍攻擊 " + opponent.getName() + " 造成 " +
                actual + " 點傷害！目前生命值：" + opponent.getHealth());
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