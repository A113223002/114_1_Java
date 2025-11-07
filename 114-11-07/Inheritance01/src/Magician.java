public class Magician extends Role {

    //治癒力
    private int healPoewr;

    //建構子：初始化魔法師的名稱、生命值和攻擊力
    public Magician(String name, int health, int healPoewr) {
        super(name, health, attackPower);
        this.healPoewr = healPoewr;
    }

    //取得治癒力
    public int getHealthPower() {
        return healPoewr;
    }

    //攻擊對手
    public void attack(Magician opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻擊 " + opponent.getName() + " 造成 " + this.getAttackPower() + " 點傷害。");
    }

    //治療劍客
    public void heal(SwordsMan ally) {
        ally.setHealth(ally.getHealth() + this.healPoewr);
        System.out.println(this.getName() + " 治療了 " + ally.getName() + " 回復" + healPoewr + " 點生命值。");
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力：" + healPoewr;
    }
}
