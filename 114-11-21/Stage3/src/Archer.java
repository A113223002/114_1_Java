public class Archer extends Role {
    private String attackType;
    private int range;
    private int energy;
    private int maxEnergy;
    private int arrows;
    private int maxArrows;

    public Archer(String name, int health, int attackPower, String attackType, int range, int maxEnergy, int maxArrows) {
        super(name, health, attackPower);
        this.attackType = attackType;
        this.range = range;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
        this.arrows = maxArrows;
        this.maxArrows = maxArrows;
    }

    public String getAttackType() { return attackType; }
    public int getRange() { return range; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }
    public int getArrows() { return arrows; }

    public void prepareShot() {
        System.out.println("🎯 " + this.getName() + " 準備 " + attackType + " 攻擊...");
        System.out.println("📊 能量值：" + energy + "/" + maxEnergy + "，射程：" + range);
        System.out.println("🏹 檢查弓弦的張力和箭矢的狀態...");
        System.out.println("🎯 調整呼吸，進入射擊姿態。");
        System.out.println();
    }

    @Override
    public void attack(Role opponent) {
        shoot(opponent);
    }

    public void shoot(Role target) {
        System.out.println("🏹 " + this.getName() + " 射出 " + attackType + " 攻擊 " + target.getName() + "！");
        // 消耗 10 能量
        this.energy = Math.max(0, this.energy - 10);
        System.out.println("💫 消耗 10 點能量，剩餘：" + this.energy + "/" + this.maxEnergy);
        // 減少箭矢
        this.arrows = Math.max(0, this.arrows - 1);
        System.out.println("📊 剩餘箭矢：" + this.arrows + "/" + this.maxArrows);
        // 攻擊目標（目標若為近戰會有護甲減免的覆寫）
        int actual = target.receiveDamage(this.getAttackPower());
        System.out.println("💥 " + target.getName() + " 受到 " + actual + " 點傷害！目前生命值：" + target.getHealth());
        System.out.println();
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.printf("║ %s 的特殊技能      ║%n", this.getName());
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：精準射擊          ║");
        System.out.println("║ 技能描述：提高命中率與傷害  ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.println();
    }

    @Override
    public void prepareBattle() {
        // 由於範例中有專用的 prepareShot 描述，這裡不輸出額外內容
    }

    @Override
    public void afterBattle() {
        // 可以恢復少量能量（留給外部呼叫模擬）
    }

    @Override
    public String toString() {
        return super.toString() + ", 攻擊類型=" + attackType + "，射程=" + range + "，能量=" + energy + "/" + maxEnergy;
    }
}

