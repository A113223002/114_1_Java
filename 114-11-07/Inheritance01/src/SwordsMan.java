// 此類別代表一名持劍戰士，包含名稱、生命值與攻擊力等屬性
public class SwordsMan extends Role {

    // 建構子：初始化名稱、生命值與攻擊力
    public SwordsMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    // 攻擊對手
    public void attack(SwordsMan opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻擊 " + opponent.getName() + " 造成了 " + this.getAttackPower() + " 點傷害。" + opponent.toString());
    }
}
