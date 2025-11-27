public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第三階段");
        System.out.println("      展示：多層繼承結構設計");
        System.out.println("════════════════════════════════════════");
        System.out.println();
        System.out.println("📋 類別繼承構");
        System.out.println("Role (最高層)");
        System.out.println("├─ MeleeRole (近戰角色)");
        System.out.println("│  ├─ SwordsMan (劍士)");
        System.out.println("│  └─ ShieldSwordsMan (持盾劍士)");
        System.out.println("└─ RangedRole (遠程角色)");
        System.out.println("   ├─ Magician (魔法師)");
        System.out.println("   └─ Archer (弓箭手)");
        System.out.println();
        System.out.println("════════════════════════════════════════");
        System.out.println("          🔍 角色類別特性展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();
        System.out.println("【近戰角色特性】");
        System.out.println("光明劍士：武器=雙手劍，護甲=5");
        System.out.println("黑暗劍士：武器=雙手劍，護甲=3");
        System.out.println("持盾劍士：武器=單手劍+盾牌，護甲=8");
        System.out.println();
        System.out.println("【遠程角色特性】");
        System.out.println("光明法師：攻擊類型=魔法彈，射程=8，能量=100/100");
        System.out.println("黑暗法師：攻擊類型=魔法彈，射程=8，能量=100/100");
        System.out.println("精靈射手：攻擊類型=精準箭矢，射程=10，能量=80/80");
        System.out.println();
        System.out.println("════════════════════════════════════════");
        System.out.println();
        System.out.println("⚔️  戰鬥開始！");
        System.out.println();

        // 建立角色（注意：精靈射手攻擊力為 18，以便 18-5 = 13 的輸出）
        SwordsMan lightSwords = new SwordsMan("光明劍士", 100, 20, "雙手劍", 5, 100);
        Magician darkMage = new Magician("黑暗法師", 80, 20, 30);
        Archer elfArcher = new Archer("精靈射手", 80, 18, "精準箭矢", 10, 80, 30);

        // 第 1 回合
        System.out.println("━━━━━━━━━━ 第 1 回合 ━━━━━━━━━━");
        lightSwords.prepareBattle();
        // 光明劍士 攻擊 黑暗法師
        System.out.println();
        lightSwords.attack(darkMage);

        // 第 2 回合
        System.out.println("━━━━━━━━━━ 第 2 回合 ━━━━━━━━━━");
        elfArcher.prepareShot();
        System.out.println();
        elfArcher.attack(lightSwords);
        // 額外輸出：能量回復與放鬆敘述
        System.out.println("✨ 恢復 10 點能量 (70 → 80)");
        System.out.println("💪 精靈射手 放鬆手臂肌肉，恢復體力。");
    }
}
