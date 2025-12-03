package NikkiDressUp.logic;

import NikkiDressUp.model.Player;
import NikkiDressUp.model.NPC;
import java.util.*;

public class SceneLogic {
    // 五种核心穿搭风格（仅内部逻辑使用，不对外暴露“属性”概念）
    private static final String[] STYLES = {"帅气", "甜美", "性感", "典雅", "清新"};

    // 场景信息封装类：存储场景名、剧情描述、适配风格（内部关联，不对外显示）
    public static class SceneInfo {
        private String sceneName;   // 场景名称
        private String story;       // 场景剧情描述
        private String targetStyle; // 场景适配的核心风格（隐藏逻辑）

        // 构造方法：初始化场景信息
        public SceneInfo(String sceneName, String story, String targetStyle) {
            this.sceneName = sceneName;
            this.story = story;
            this.targetStyle = targetStyle;
        }

        // Getter方法：给Main类提供场景名和剧情（不暴露targetStyle）
        public String getSceneName() {
            return sceneName;
        }

        public String getStory() {
            return story;
        }

        // 内部使用：获取场景适配风格（不对外开放）
        private String getTargetStyle() {
            return targetStyle;
        }
    }

    // 初始化带剧情的对战场景（5个场景，每个对应1种风格，剧情暗示适配方向）
    private static final Map<String, SceneInfo> SCENE_DETAILS = new HashMap<>();
    static {
        // 场景1：甜美风格适配
        SCENE_DETAILS.put("scene_sweet", new SceneInfo(
                "草莓园甜美派对",
                "阳光洒满郊外的草莓园，闺蜜们精心布置了粉色气球、蕾丝桌布和奶油草莓蛋糕。到场的人都穿着蓬蓬裙、带着蝴蝶结发饰，连饮品都插着小雏菊～ 主办方说，最契合主题的穿搭会获得“草莓甜心”称号和限定礼品！",
                "甜美"
        ));

        // 场景2：帅气风格适配
        SCENE_DETAILS.put("scene_cool", new SceneInfo(
                "极限山地赛车挑战赛",
                "城市周边的山地赛道开放了挑战赛，赛道布满陡坡和急弯，既考验车技也考验气场。其他车手都穿着利落工装、马丁靴，搭配简约护具和墨镜，全程散发着“速度与激情”的帅气感，毕竟赛道上的风采和成绩同样重要～",
                "帅气"
        ));

        // 场景3：性感风格适配
        SCENE_DETAILS.put("scene_sexy", new SceneInfo(
                "海滨星空晚宴",
                "海滨度假酒店的露天露台举办星空主题晚宴，海风轻拂，星光与灯光交相辉映。女士们多穿露肩长裙、搭配闪亮首饰和细高跟，男士则是修身西装配领结，整体氛围浪漫又迷人，需要用穿搭展现独特的魅力气场～",
                "性感"
        ));

        // 场景4：典雅风格适配
        SCENE_DETAILS.put("scene_elegant", new SceneInfo(
                "古典美术馆百年特展",
                "市中心美术馆举办百年名画特展，邀请了各界名流和艺术爱好者。现场要求着装庄重得体，丝绒、绸缎、刺绣等材质的服饰格外受欢迎，整体风格偏向复古优雅，既要体现文化底蕴，又不能过于随意轻浮～",
                "典雅"
        ));

        // 场景5：清新风格适配
        SCENE_DETAILS.put("scene_fresh", new SceneInfo(
                "森林徒步露营会",
                "和朋友组队参加郊外森林露营，活动包含徒步登山、溪边野餐和星空露营。这里空气清新、绿意盎然，大家都穿着轻便的棉麻服饰、运动鞋，搭配草帽或帆布包，重点是舒适透气、方便活动，还能融入自然环境～",
                "清新"
        ));
    }

    /**
     * 技能练习：提升指定风格的熟练度（隐藏“属性分”逻辑）
     * @param player 玩家对象
     * @param targetStyle 要练习的风格
     * @return 练习是否成功
     */
    public boolean practiceSkill(Player player, String targetStyle) {
        // 校验风格是否有效
        if (!Arrays.asList(STYLES).contains(targetStyle)) {
            System.out.println("❌ 错误：没有「" + targetStyle + "」这种穿搭风格，请从可选风格中选择～");
            return false;
        }

        // 提升对应风格熟练度（底层是属性分+10，对外说“熟练度”）
        int currentScore = player.getStyleScore(targetStyle);
        player.setStyleScore(targetStyle, currentScore + 10);

        System.out.println("✅ 练习成功！");
        System.out.println("「" + targetStyle + "」风格穿搭熟练度+10～");
        System.out.println("当前「" + targetStyle + "」风格表现：更贴合主题场景啦！");
        return true;
    }

    /**
     * 获取随机场景信息（给Main类调用，用于显示剧情）
     * @return 随机的SceneInfo对象（含场景名、剧情）
     */
    public SceneInfo getRandomSceneInfo() {
        List<String> sceneKeys = new ArrayList<>(SCENE_DETAILS.keySet());
        Random rand = new Random();
        String randomKey = sceneKeys.get(rand.nextInt(sceneKeys.size()));
        return SCENE_DETAILS.get(randomKey);
    }

    /**
     * 计算玩家穿搭表现得分（内部逻辑，不对外暴露计算规则）
     * @param player 玩家对象
     * @param targetStyle 场景适配风格（内部传入）
     * @param playerChooseStyle 玩家选择的风格
     * @return 玩家最终表现得分
     */
    private double calculatePlayerScore(Player player, String targetStyle, String playerChooseStyle) {
        Map<String, Integer> styleScores = player.getBaseAttr();

        // 1. 基础表现分：适配风格加权1.1，其他风格正常计算
        double baseScore = 0;
        for (Map.Entry<String, Integer> entry : styleScores.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();
            baseScore += style.equals(targetStyle) ? score * 1.1 : score;
        }

        // 2. 决策适配分：选择与场景适配的风格，加权1.2；否则0.8
        double fitFactor;
        if (playerChooseStyle.equals(targetStyle)) {
            fitFactor = 1.2;
            System.out.println("✨ 穿搭风格与场景高度契合！获得额外适配加分～");
        } else {
            fitFactor = 0.8;
            System.out.println("⚠️  穿搭风格与场景适配度一般，加分较少哦～");
        }

        // 3. 最终得分：基础分×适配分
        double finalScore = baseScore * fitFactor;
        return finalScore;
    }

    /**
     * 计算NPC穿搭表现得分（与玩家规则一致，增加随机波动）
     * @param npc NPC对象
     * @param targetStyle 场景适配风格（内部传入）
     * @return NPC最终表现得分
     */
    private double calculateNpcScore(NPC npc, String targetStyle) {
        Map<String, Integer> styleScores = npc.getNpcBaseAttr();

        // 1. 基础表现分（与玩家计算规则一致）
        double baseScore = 0;
        for (Map.Entry<String, Integer> entry : styleScores.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();
            baseScore += style.equals(targetStyle) ? score * 1.1 : score;
        }

        // 2. 随机波动：±5%（模拟NPC穿搭的细微差异）
        Random rand = new Random();
        double fluctuation = (rand.nextDouble() * 0.1) - 0.05; // -0.05~+0.05
        double finalScore = baseScore * (1 + fluctuation);

        return finalScore;
    }

    /**
     * 与NPC对战的核心逻辑（接收Main类传入的场景信息和玩家选择）
     * @param player 玩家对象
     * @param npc 对战NPC
     * @param playerChooseStyle 玩家选择的风格
     * @param sceneInfo 场景信息（含场景名、剧情）
     * @return 对战是否胜利
     */
    public boolean battleWithNPC(Player player, NPC npc, String playerChooseStyle, SceneInfo sceneInfo) {
        System.out.println("\n=== 🎉 对战正式开始 🎉 ===");
        System.out.println("【对战场景】" + sceneInfo.getSceneName());
        System.out.println("【你的穿搭】" + playerChooseStyle + "风格");
        System.out.println("【对手穿搭】" + npc.getName() + "的场景适配搭配");
        System.out.println("\n=== ⏳ 穿搭评审进行中... ⏳ ===");

        // 内部获取场景适配风格（不对外显示）
        String targetStyle = sceneInfo.getTargetStyle();

        // 计算双方表现得分
        double playerScore = calculatePlayerScore(player, targetStyle, playerChooseStyle);
        double npcScore = calculateNpcScore(npc, targetStyle);

        // 显示得分结果
        System.out.println("\n=== 📊 评审结果 ===");
        System.out.println("你的最终表现得分：" + String.format("%.1f", playerScore));
        System.out.println(npc.getName() + "的最终表现得分：" + String.format("%.1f", npcScore));

        // 判定胜负并反馈结果
        System.out.println("\n=== 🏆 对战结果 🏆 ===");
        if (playerScore > npcScore) {
            // 胜利：提升场景适配风格的熟练度（+5）
            int currentScore = player.getStyleScore(targetStyle);
            player.setStyleScore(targetStyle, currentScore + 5);

            System.out.println("🎉 恭喜你！战胜了" + npc.getName() + "！");
            System.out.println("你的「" + playerChooseStyle + "」风格穿搭在「" + sceneInfo.getSceneName() + "」中脱颖而出～");
            System.out.println("💪 「" + targetStyle + "」风格穿搭熟练度+5！");
            System.out.println("后续再遇到类似场景，你的穿搭会更契合主题哦～");
            return true;
        } else if (playerScore == npcScore) {
            // 平局：无奖惩，鼓励再试
            System.out.println("🤝 平局！你和" + npc.getName() + "的穿搭表现不相上下～");
            System.out.println("双方风格适配度和表现力都很出色，再来一局说不定能分出胜负！");
            return false;
        } else {
            // 失败：无惩罚，给出提示
            System.out.println("😥 很遗憾，你没能战胜" + npc.getName() + "～");
            System.out.println(npc.getName() + "的「" + targetStyle + "」风格穿搭更契合场景氛围，表现略胜一筹～");
            System.out.println("❌ 失败无惩罚！多练习「" + targetStyle + "」风格，下次一定能赢回来！");
            return false;
        }
    }
}