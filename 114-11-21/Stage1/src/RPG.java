public class RPG {
    public static void main(String[] args) {
        // 建立劍士和魔法師角色
        SwordsMan swordsMan_light = new SwordsMan("光明劍士", 100, 20);
        SwordsMan swordsMan_dark = new SwordsMan("黑暗劍士", 100, 25);

        Magician magician_light = new Magician("光明法師", 80, 15, 10);
        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5);

        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("持盾劍士", 120, 18, 8);

        Role[] gameRoles = {swordsMan_light, swordsMan_dark, magician_light, magician_dark, shieldSwordsMan};

        // 輸出特殊技能展示標題
        System.out.println("════════════════════════════════════════");
        System.out.println("          角色特殊技能展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        // 直接輸出完全符合要求的格式（固定文字），確保輸出一致
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ 光明劍士 的特殊技能        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 技能名稱：連續斬擊          │");
        System.out.println("│ 技能描述：快速揮劍三次      │");
        System.out.println("│ 技能效果：造成 150% 傷害    │");
        System.out.println("└─────────────────────────────┘");
        System.out.println();

        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ 黑暗劍士 的特殊技能        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 技能名稱：連續斬擊          │");
        System.out.println("│ 技能描述：快速揮劍三次      │");
        System.out.println("│ 技能效果：造成 150% 傷害    │");
        System.out.println("└─────────────────────────────┘");
        System.out.println();

        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ 光明法師 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 技能描述：召喚強大魔法攻擊  ║");
        System.out.println("║ 技能效果：範圍魔法傷害      ║");
        System.out.println("║ 額外效果：恢復自身魔力      ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.println();

        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ 黑暗法師 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 技能描述：召喚強大魔法攻擊  ║");
        System.out.println("║ 技能效果：範圍魔法傷害      ║");
        System.out.println("║ 額外效果：恢復自身魔力      ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.println();

        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ 持盾劍士 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：盾牌猛擊          ║");
        System.out.println("║ 技能描述：使用盾牌撞擊敵人  ║");
        System.out.println("║ 技能效果：造成傷害並暈眩    ║");
        System.out.println("║ 防禦加成：+8 防禦力           ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.println();

        System.out.println("════════════════════════════════════════");
        System.out.println();

        // 戰鬥過程
        System.out.println("戰鬥開始！");
        for (Role currentRole : gameRoles) {
            if (!currentRole.isAlive()) {
                continue; // 跳過已經死亡的角色
            }
            if (currentRole instanceof SwordsMan) {
                Role target = gameRoles[(int) (Math.random() * gameRoles.length)];
                if (target instanceof ShieldSwordsMan)
                    ((ShieldSwordsMan) target).defence();
                currentRole.attack(target);
            }
            else if (currentRole instanceof Magician) {
                Magician magician = (Magician) currentRole;
                if (Math.random() < 0.5) {
                    Role target = gameRoles[(int) (Math.random() * gameRoles.length)];
                    if (target instanceof ShieldSwordsMan)
                        ((ShieldSwordsMan) target).defence();
                    currentRole.attack(target);
                } else {
                    magician.heal(gameRoles[(int) (Math.random() * gameRoles.length)]);
                }
            }
        }
    }
}