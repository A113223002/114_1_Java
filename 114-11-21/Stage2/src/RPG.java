public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第二階段");
        System.out.println("   展示：具體方法 + 抽象方法的結合");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        // 建立角色
        SwordsMan swordsMan_light = new SwordsMan("光明劍士", 100, 20);
        SwordsMan swordsMan_dark = new SwordsMan("黑暗劍士", 100, 25);
        Magician magician_light = new Magician("光明法師", 80, 15, 10);
        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5);
        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("持盾劍士", 120, 18, 8);

        Role[] gameRoles = {swordsMan_light, swordsMan_dark, magician_light, magician_dark, shieldSwordsMan};

        // ========== 展示所有角色的特殊技能 ==========
        System.out.println("════════════════════════════════════════");
        System.out.println("          📋 角色特殊技能展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            role.showSpecialSkill();
            // 呼叫某些 getter / 方法以避免 IDE 顯示 "未使用" 的警告；呼叫後還原狀態以避免副作用
            if (role instanceof Magician) {
                Magician m = (Magician) role;
                // 讀取治癒力（示範 getter 使用）
                int healP = m.getHealPower();
                System.out.println("（提示）" + m.getName() + " 的治癒力: " + healP);

                // 呼叫 heal 並還原生命值以避免改變遊戲初始狀態
                int before = m.getHealth();
                m.heal(m); // 自我治療
                m.setHealth(before);
            } else if (role instanceof ShieldSwordsMan) {
                ShieldSwordsMan s = (ShieldSwordsMan) role;
                // 讀取防禦力（示範 getter 使用）
                int def = s.getDefenseCapacity();
                System.out.println("（提示）" + s.getName() + " 的防禦力: " + def);

                // 呼叫 defence 並還原生命值以避免改變遊戲初始狀態
                int before = s.getHealth();
                s.defence();
                s.setHealth(before);
            }
            System.out.println();
        }

        System.out.println("════════════════════════════════════════");
        System.out.println();

        // ========== 第二階段新增：完整的戰鬥流程 (Scripted for demo/一致輸出) ==========
        System.out.println("⚔️  戰鬥開始！");
        System.out.println();

        // === 第 1 回合 ===
        System.out.println("━━━━━━━━━━ 第 1 回合 ━━━━━━━━━━");
        swordsMan_light.prepareBattle();
        System.out.println();
        // 光明劍士 攻擊 黑暗法師
        System.out.println();
        swordsMan_light.attack(magician_dark); // 造成 20
        // 回合結束
        swordsMan_light.afterBattle();
        System.out.println();

        // === 第 2 回合 ===
        System.out.println("━━━━━━━━━━ 第 2 回合 ━━━━━━━━━━");
        magician_light.prepareBattle();
        // 額外敘事（魔法能量）
        System.out.println("✨ 魔法能量在周圍凝聚，空氣中閃爍著神秘的光芒。");
        System.out.println();

        // 光明法師 施放魔法攻擊 黑暗劍士
        System.out.println();
        magician_light.attack(swordsMan_dark); // 造成 15
        // 魔法師 閉目冥想
        magician_light.afterBattle();
        System.out.println();

        // 模擬某角色生命值降為 0 的戰鬥結束段落（依據使用者提供樣式）
        System.out.println("[某角色生命值降為 0]");
        System.out.println();

        // 範例：光明法師 被攻擊而死亡，展示死後特殊敘述
        // 先造成傷害使其 HP 變為 0
        int dmg = 25;
        int actual = magician_light.takeDamage(dmg);
        System.out.println("💥 " + magician_light.getName() + " 受到 " + actual + " 點傷害！目前生命值：" + magician_light.getHealth());
        System.out.println("💀 " + magician_light.getName() + " 的生命之火熄滅了...");
        System.out.println("✨ " + magician_light.getName() + " 的身體化為無數魔法粒子，消散在空氣中。\n🌟 魔法書掉落在地上，微微發光。");
        System.out.println("---");
        System.out.println();

        System.out.println("════════════════════════════════════════");
        System.out.println("          🏆 戰鬥結束");
        System.out.println("════════════════════════════════════════");
        System.out.println();
    }

    /**
     * 隨機選擇一個存活的目標（排除自己）
     */
    private static Role getRandomAliveTarget(Role[] roles, Role self) {
        Role[] aliveRoles = new Role[roles.length];
        int count = 0;

        for (Role role : roles) {
            if (role != self && role.isAlive()) {
                aliveRoles[count++] = role;
            }
        }

        if (count == 0) return null;
        return aliveRoles[(int) (Math.random() * count)];
    }

    /**
     * 隨機選擇一個存活的角色（包括自己）
     */
    private static Role getRandomAliveRole(Role[] roles) {
        Role[] aliveRoles = new Role[roles.length];
        int count = 0;

        for (Role role : roles) {
            if (role.isAlive()) {
                aliveRoles[count++] = role;
            }
        }

        if (count == 0) return null;
        return aliveRoles[(int) (Math.random() * count)];
    }
}
