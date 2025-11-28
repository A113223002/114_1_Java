public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第二階段");
        System.out.println("   展示：具體方法與抽象方法結合");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        // ==========================================
        // 1. 建立角色
        // ==========================================
        System.out.println("【創建角色】");
        System.out.println("─────────────────────────────────────");

        SwordsMan swordsMan_light = new SwordsMan("光明劍士", 100, 20);
        System.out.println("✅ " + swordsMan_light);

        SwordsMan swordsMan_dark = new SwordsMan("黑暗劍士", 100, 25);
        System.out.println("✅ " + swordsMan_dark);

        Magician magician_light = new Magician("光明法師", 80, 15, 10);
        System.out.println("✅ " + magician_light);

        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5);
        System.out.println("✅ " + magician_dark);

        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("持盾劍士", 120, 18, 8);
        System.out.println("✅ " + shieldSwordsMan);

        System.out.println();

        // ==========================================
        // 2. 將所有角色放入陣列
        // ==========================================
        Role[] gameRoles = {swordsMan_light, swordsMan_dark, magician_light,
                magician_dark, shieldSwordsMan};

        // ==========================================
        // 3. 顯示所有角色的特殊技能
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          ⚔️  特殊技能展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            role.showSpecialSkill();
            System.out.println();
        }

        // ==========================================
        // 4. 戰鬥前準備
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🛡️  戰鬥前準備");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            role.prepareBattle();
            System.out.println();
        }

        // ==========================================
        // 5. 戰鬥測試：takeDamage 與 onDeath
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("        ⚔️  戰鬥測試 (受傷與死亡)");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【測試 1：受傷但未死亡】");
        System.out.println("光明劍士受到 30 點傷害：");
        swordsMan_light.takeDamage(30);
        System.out.println("目前狀態：" + swordsMan_light);
        System.out.println();

        System.out.println("【測試 2：受到致命傷害】");
        System.out.println("光明劍士受到 80 點傷害（致命）：");
        swordsMan_light.takeDamage(80);
        System.out.println();

        // ==========================================
        // 6. 角色互相攻擊測試
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          ⚔️  角色互相攻擊");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【戰鬥 1：黑暗劍士 vs 光明法師】");
        swordsMan_dark.attack(magician_light);
        System.out.println();

        System.out.println("【戰鬥 2：光明法師反擊】");
        magician_light.attack(swordsMan_dark);
        System.out.println();

        // ==========================================
        // 7. 魔法師治療測試
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          💚 治療測試");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【光明法師治療黑暗劍士】");
        magician_light.heal(swordsMan_dark);
        System.out.println();

        // ==========================================
        // 8. 持盾劍士特殊能力測試
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("        🛡️  持盾劍士防禦測試");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【持盾劍士受到傷害】");
        shieldSwordsMan.takeDamage(25);
        System.out.println();

        System.out.println("【持盾劍士使用防禦技能】");
        shieldSwordsMan.defence();
        System.out.println();

        // ==========================================
        // 9. 戰鬥後行為
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🌙 戰鬥結束");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            if (role.isAlive()) {
                role.afterBattle();
                System.out.println();
            }
        }

        // ==========================================
        // 10. 最終狀態報告
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          📊 最終狀態報告");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        int aliveCount = 0;
        int deadCount = 0;

        for (Role role : gameRoles) {
            String status = role.isAlive() ? "✅ 存活" : "💀 陣亡";
            System.out.println(status + " | " + role);

            if (role.isAlive()) {
                aliveCount++;
            } else {
                deadCount++;
            }
        }

        System.out.println();
        System.out.println("─────────────────────────────────────");
        System.out.println("存活角色：" + aliveCount + " 名");
        System.out.println("陣亡角色：" + deadCount + " 名");
        System.out.println("─────────────────────────────────────");
        System.out.println();
        System.out.println("🎮 遊戲結束！");
    }
}
