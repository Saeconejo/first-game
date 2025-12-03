package NikkiDressUp.logic;

import NikkiDressUp.model.Player;
import NikkiDressUp.model.NPC;
import java.util.*;

public class SceneLogic {
    // 五种核心风格
    private static final String[] STYLES = {"帅气", "甜美", "性感", "典雅", "清新"};

    // 预设战斗场景
    private static final String[] SCENES = {"甜美派对", "帅气赛场", "性感晚宴", "典雅茶会", "清新森林"};

    // 场景对应的核心风格要求
    private static final Map<String, String> SCENE_REQUIREMENTS = new HashMap<>();
    static {
        SCENE_REQUIREMENTS.put("甜美派对", "甜美");
        SCENE_REQUIREMENTS.put("帅气赛场", "帅气");
        SCENE_REQUIREMENTS.put("性感晚宴", "性感");
        SCENE_REQUIREMENTS.put("典雅茶会", "典雅");
        SCENE_REQUIREMENTS.put("清新森林", "清新");
    }

    /**
     * 技能练习：提升指定风格基础分
     */
    public boolean practiceSkill(Player player, String targetStyle) {
        // 校验目标风格是否有效
        if (!Arrays.asList(STYLES).contains(targetStyle)) {
            System.out.println("错误：无效的风格！");
            return false;
        }

        // 提升对应风格基础分
        int currentScore = player.getStyleScore(targetStyle);
        player.setStyleScore(targetStyle, currentScore + 10);

        System.out.println("练习成功！" + targetStyle + "风格基础分 +10");
        System.out.println("当前" + targetStyle + "分数：" + (currentScore + 10));
        return true;
    }

    /**
     * 随机获取战斗场景
     */
    public String getRandomScene() {
        Random rand = new Random();
        int index = rand.nextInt(SCENES.length);
        return SCENES[index];
    }

    /**
     * 计算玩家战斗总得分
     */
    public double calculatePlayerScore(Player player, String targetStyle,
                                       String playerChooseStyle) {
        Map<String, Integer> baseAttr = player.getBaseAttr();

        // 1. 基础分加权计算
        double baseWeightedScore = 0;
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();

            if (style.equals(targetStyle)) {
                baseWeightedScore += score * 1.1;  // 符合场景风格×1.1
            } else {
                baseWeightedScore += score;        // 其他风格直接相加
            }
        }

        // 2. 决策加权
        double decisionWeight;
        if (playerChooseStyle.equals(targetStyle)) {
            decisionWeight = 1.2;  // 选择正确×1.2
            System.out.println("选择正确！获得决策加权×1.2");
        } else {
            decisionWeight = 0.8;  // 选择错误×0.8
            System.out.println("选择错误！获得决策加权×0.8");
        }

        // 3. 总得分
        double finalScore = baseWeightedScore * decisionWeight;
        System.out.println("玩家基础加权分：" + String.format("%.1f", baseWeightedScore));
        System.out.println("玩家最终得分：" + String.format("%.1f", finalScore));

        return finalScore;
    }

    /**
     * 计算NPC战斗总得分
     */
    public double calculateNpcScore(NPC npc, String targetStyle) {
        Map<String, Integer> baseAttr = npc.getNpcBaseAttr();

        // 1. 基础分加权计算（与玩家规则相同）
        double baseWeightedScore = 0;
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();

            if (style.equals(targetStyle)) {
                baseWeightedScore += score * 1.1;
            } else {
                baseWeightedScore += score;
            }
        }

        // 2. 随机波动±5%
        Random rand = new Random();
        double fluctuation = (rand.nextDouble() * 0.1) - 0.05;  // -0.05到+0.05
        double finalScore = baseWeightedScore * (1 + fluctuation);

        System.out.println("NPC基础加权分：" + String.format("%.1f", baseWeightedScore));
        System.out.println("NPC最终得分：" + String.format("%.1f", finalScore));

        return finalScore;
    }

    /**
     * 与NPC进行战斗
     */
    public boolean battleWithNPC(Player player, NPC npc, String playerChooseStyle) {
        System.out.println("\n=== 战斗开始 ===");

        // 1. 随机获取战斗场景
        String scene = getRandomScene();
        String targetStyle = SCENE_REQUIREMENTS.get(scene);

        System.out.println("战斗场景：" + scene);
        System.out.println("场景要求风格：" + targetStyle);
        System.out.println("你选择的主打风格：" + playerChooseStyle);

        // 2. 计算双方得分
        double playerScore = calculatePlayerScore(player, targetStyle, playerChooseStyle);
        double npcScore = calculateNpcScore(npc, targetStyle);

        System.out.println("\n=== 战斗结果 ===");

        // 3. 判定胜负
        if (playerScore > npcScore) {
            // 胜利：对应风格基础分+5
            int currentScore = player.getStyleScore(targetStyle);
            player.setStyleScore(targetStyle, currentScore + 5);

            System.out.println("恭喜！你战胜了" + npc.getName() + "！");
            System.out.println(targetStyle + "风格基础分 +5");
            System.out.println("当前" + targetStyle + "分数：" + (currentScore + 5));
            return true;
        } else {
            System.out.println("很遗憾，你输给了" + npc.getName() + "！");
            System.out.println("失败无惩罚，请继续努力！");
            return false;
        }
    }
}