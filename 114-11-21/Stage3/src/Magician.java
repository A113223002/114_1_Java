public class Magician extends Role{
    // 治癒力（用於治療盟友），建構後不變所以標為 final
    private final int healPower;

    // 建構子：初始化魔法師的名稱、生命值、攻擊力與治癒力
    public Magician(String name, int health, int attackPower, int healPower) {
        super(name, health, attackPower);
        this.healPower = healPower;
    }

    // 取得治癒力
    public int getHealPower() {
        return healPower;
    }

    // 魔法攻擊：使用父類別的 receiveDamage 統一處理生命值變動
    // 並輸出符合敘述的魔法施放與受傷訊息
    @Override
    public void attack(Role opponent) {
        // 魔法施放的敘述由呼叫者(或角色)負責，這裡只印出攻擊行為
        System.out.println("✨ " + this.getName() + " 施放魔法攻擊 " + opponent.getName() + "！");

        // 實際造成的傷害
        int actualDamage = opponent.receiveDamage(this.getAttackPower());

        // 印出受傷與剩餘生命
        System.out.println("💥 " + opponent.getName() + " 受到 " + actualDamage + " 點傷害！目前生命值：" + opponent.getHealth());
        System.out.println();
    }

    // 治療隊友：增加生命值（不超過某上限此範例不限制）
    public void heal(Role ally) {
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println(this.getName() + " 治療 " + ally.getName() + " 回復 " + healPower + " 點生命值。" + ally);
    }

    // 顯示特殊技能（魔法師的技能樣板）
    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.printf("║ %s 的特殊技能        ║%n", this.getName());
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 技能描述：召喚強大魔法攻擊  ║");
        System.out.println("║ 技能效果：範圍魔法傷害      ║");
        System.out.println("║ 額外效果：恢復自身魔力      ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.println();
    }

    // 回合開始前的準備：施法者吟唱或翻書的敘述
    @Override
    public void prepareBattle() {
        System.out.println("📖 " + this.getName() + " 翻開魔法書，開始吟唱古老的咒語...");
    }

    // 回合結束後的行為：閉目冥想恢復魔力等敘述
    @Override
    public void afterBattle() {
        System.out.println("🧘 " + this.getName() + " 閉目冥想，恢復消耗的魔力。");
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力: " + healPower;
    }

}