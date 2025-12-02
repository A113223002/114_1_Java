/**
 * Priest - 牧師類別
 *
 * 設計說明（繁體中文詳細註解）：
 * - 繼承：RangedRole（遠程角色）
 * - 實作：Healable（可治療）介面
 * - 不實作 Defendable（牧師不擅長防禦）
 *
 * 牧師特色：
 * 1. 以聖光治療盟友，擁有固定的治療力（healPower）。
 * 2. 使用能量（energy）來施放治療與攻擊技能，能量由 RangedRole 管理。
 * 3. 在戰前會進行祝禱準備，在戰後會進行短暫恢復（恢復能量）。
 * 4. 提供清楚的技能說明與狀態輸出，方便在 RPG 展示介面功能。
 */
public class Priest extends RangedRole implements Healable {
    // 牧師的治療力：每次治療可回復的生命值數量
    private int healPower;

    /**
     * 建構子：初始化牧師屬性
     * @param name 角色名稱
     * @param health 生命值
     * @param attackPower 攻擊力（聖光打擊的基礎傷害）
     * @param healPower 治療力（每次治療回復量）
     * @param range 射程（遠程攻擊的距離）
     * @param maxEnergy 最大能量值（用於施放技能）
     */
    public Priest(String name, int health, int attackPower, int healPower,
                  int range, int maxEnergy) {
        super(name, health, attackPower, range, maxEnergy);
        this.healPower = healPower;
    }

    // =============================================
    // 實作 Role 抽象方法（攻擊、技能顯示、死亡、戰前、戰後）
    // =============================================

    /**
     * 攻擊方法：牧師使用聖光打擊敵人，會消耗能量。
     * 若能量不足，攻擊會失敗並顯示提示。
     */
    @Override
    public void attack(Role opponent) {
        // 攻擊能量消耗（牧師攻擊較為省能）
        int cost = 12;
        if (!consumeEnergy(cost)) {
            System.out.println("❌ " + getName() + " 能量不足，無法釋放聖光攻擊！");
            return;
        }

        System.out.println("🔆 " + getName() + " 以聖光攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    /**
     * 顯示牧師的特殊技能資訊（包含治療與攻擊描述）
     */
    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：神聖祝禱          ║");
        System.out.println("║ 技能描述：以神聖之光治療盟友或造成光屬性傷害║");
        System.out.println("║ 技能效果：回復盟友生命值 " + healPower + " 點    ║");
        System.out.println("║ 能量消耗：治療 " + 15 + " / 攻擊 " + 12 + "         ║");
        System.out.println("║ 射程：" + getRange() + " 米                ║");
        System.out.println("╚═════════════════════════════╝");
    }

    /**
     * 當角色死亡時的描述（文字化呈現）
     */
    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 被黑暗吞噬，聖光散去...");
        System.out.println("🙏 聖歌回蕩，信徒低頭哀悼。");
    }

    /**
     * 戰前準備：牧師會低聲祈禱以強化祝福，並說明目前能量狀態。
     */
    @Override
    public void prepareBattle() {
        System.out.println("🙏 " + getName() + " 低聲祈禱，準備施放祝福與聖光。");
        System.out.println("📊 能量值：" + getEnergy() + "/" + getMaxEnergy() + "，射程：" + getRange());
        onRangedPrepare();
    }

    /**
     * 戰後行為：牧師會靜心恢復，並呼叫遠程角色的恢復鉤子。
     */
    @Override
    public void afterBattle() {
        // 牧師恢復較多能量（比預設略高）
        restoreEnergy(15);
        onRangedRecover();
    }

    // =============================================
    // 實作 RangedRole 抽象方法
    // =============================================

    @Override
    public String getRangedAttackType() {
        return "聖光";
    }

    @Override
    protected void onRangedPrepare() {
        System.out.println("✨ 聖光在手中凝聚，牧師為盟友祝福。");
    }

    @Override
    protected void onRangedRecover() {
        System.out.println("😌 " + getName() + " 低聲祈禱，恢復精神與能量。");
    }

    // =============================================
    // 實作 Healable 介面方法
    // =============================================

    /**
     * 實作 heal：牧師使用聖療治療目標，會消耗能量並回復生命值。
     * 若能量不足則無法施放。
     */
    @Override
    public void heal(Role target) {
        int cost = 15; // 治療能量消耗
        if (!consumeEnergy(cost)) {
            System.out.println("❌ " + getName() + " 能量不足，無法施放治療！");
            return;
        }

        int oldHealth = target.getHealth();
        target.setHealth(target.getHealth() + this.healPower);
        System.out.println("💚 " + getName() + " 施放聖療，治療 " + target.getName() + "。");
        System.out.println("✨ 恢復 " + healPower + " 點生命值 (" + oldHealth + " → " + target.getHealth() + ")");
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力: " + healPower;
    }
}
