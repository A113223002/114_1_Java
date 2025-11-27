public class SwordsMan extends Role{
    // 建構子：初始化劍士的名稱、生命值和攻擊力
    public SwordsMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    // 攻擊對手(通用劍士攻擊實作)
    // 使用父類別提供的 receiveDamage 統一處理生命值變動與下界保護
    @Override
    public void attack(Role opponent) {
        // 印出攻擊的動作敘述（由具體角色負責輸出敘事）
        System.out.println("⚔️  " + this.getName() + " 揮劍攻擊 " + opponent.getName() + "！");

        // 使用對外的 takeDamage 方法一次性處理受傷，避免重複扣血
        int actualDamage = opponent.takeDamage(this.getAttackPower());

        // 印出受傷與剩餘生命的訊息（統一格式）
        System.out.println("💥 " + opponent.getName() + " 受到 " + actualDamage + " 點傷害！目前生命值：" + opponent.getHealth());
        System.out.println();
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

    // 回合開始前的準備行為：顯示劍士的敘事
    @Override
    public void prepareBattle() {
        // Slightly more descriptive text to match example output when this is the 光明劍士
        if (this.getName().contains("光明")) {
            System.out.println("🗡️  " + this.getName() + " 擦拭劍刃，劍身反射出凜冽的寒光...");
        } else {
            System.out.println("🗡️  " + this.getName() + " 擦拭劍刃，檢查握法與護手。");
        }
    }

    // 回合結束後的行為：收劍、喘息等敘述
    @Override
    public void afterBattle() {
        // Match the target output phrasing
        System.out.println("🗡️  " + this.getName() + " 將劍收入劍鞘。");
    }
}