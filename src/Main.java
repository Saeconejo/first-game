package NikkiDressUp;

import  NikkiDressUp.model.Player;
import  NikkiDressUp.model.NPC;
import  NikkiDressUp.logic.SceneLogic;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("========== 《ShiningNIKKI》换装战斗游戏 ==========");
        System.out.println("欢迎来到搭配师的世界！");

        // 1. 初始化玩家
        System.out.print("请输入你的昵称：");
        String playerName = scanner.nextLine();
        Player player = new Player("P001", playerName);
        System.out.println("欢迎，" + playerName + "！初始属性已生成。");
        player.showAttributes();

        // 2. 初始化NPC
        List<NPC> npcList = new ArrayList<>();

        // NPC1：莉莉（甜美风格）
        Map<String, Integer> lilyAttr = new HashMap<>();
        lilyAttr.put("帅气", 80);
        lilyAttr.put("甜美", 120);
        lilyAttr.put("性感", 90);
        lilyAttr.put("典雅", 85);
        lilyAttr.put("清新", 95);
        npcList.add(new NPC("莉莉", lilyAttr));

        // NPC2：阿明（帅气风格）
        Map<String, Integer> mingAttr = new HashMap<>();
        mingAttr.put("帅气", 130);
        mingAttr.put("甜美", 75);
        mingAttr.put("性感", 85);
        mingAttr.put("典雅", 90);
        mingAttr.put("清新", 80);
        npcList.add(new NPC("阿明", mingAttr));

        // NPC3：苏珊（性感风格）
        Map<String, Integer> susanAttr = new HashMap<>();
        susanAttr.put("帅气", 85);
        susanAttr.put("甜美", 90);
        susanAttr.put("性感", 125);
        susanAttr.put("典雅", 95);
        susanAttr.put("清新", 80);
        npcList.add(new NPC("苏珊", susanAttr));

        System.out.println("已生成" + npcList.size() + "个NPC对手！");

        // 3. 初始化场景逻辑
        SceneLogic sceneLogic = new SceneLogic();

        // 4. 游戏主循环
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========== 主菜单 ==========");
            System.out.println("1. 练习技能（提升风格属性）");
            System.out.println("2. 挑战NPC");
            System.out.println("3. 查看属性");
            System.out.println("4. 查看NPC信息");
            System.out.println("5. 退出游戏");
            System.out.print("请选择操作（1-5）：");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // 清除换行符
            } catch (InputMismatchException e) {
                System.out.println("请输入数字！");
                scanner.nextLine();  // 清除无效输入
                continue;
            }

            switch (choice) {
                case 1:  // 练习技能
                    System.out.println("\n=== 技能练习 ===");
                    System.out.println("可选风格：帅气、甜美、性感、典雅、清新");
                    System.out.print("请输入要练习的风格：");
                    String targetStyle = scanner.nextLine();
                    sceneLogic.practiceSkill(player, targetStyle);
                    break;

                case 2:  // 挑战NPC
                    System.out.println("\n=== 挑战NPC ===");

                    // 随机选择一个NPC
                    NPC selectedNPC = npcList.get(random.nextInt(npcList.size()));
                    System.out.println("你的对手是：" + selectedNPC.getName());
                    selectedNPC.showAttributes();

                    System.out.println("可选风格：帅气、甜美、性感、典雅、清新");
                    System.out.print("请输入你的主打风格：");
                    String mainStyle = scanner.nextLine();

                    // 开始战斗
                    sceneLogic.battleWithNPC(player, selectedNPC, mainStyle);
                    break;

                case 3:  // 查看属性
                    player.showAttributes();
                    break;

                case 4:  // 查看NPC信息
                    System.out.println("\n=== NPC信息 ===");
                    for (NPC npc : npcList) {
                        npc.showAttributes();
                    }
                    break;

                case 5:  // 退出游戏
                    System.out.println("感谢游玩！再见！");
                    isRunning = false;
                    break;

                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }

        scanner.close();
    }
}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
