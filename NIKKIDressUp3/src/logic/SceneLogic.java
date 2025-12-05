package NIKKIDressUp3.logic;

import NIKKIDressUp3.model.Player;
import NIKKIDressUp3.model.NPC;
import java.util.*;

public class SceneLogic {
    private static final String[] STYLES = {"帅气", "甜美", "性感", "典雅", "清新"};

    public static class SceneInfo {
        private String sceneName;
        private String story;
        private String targetStyle;

        public SceneInfo(String sceneName, String story, String targetStyle) {
            this.sceneName = sceneName;
            this.story = story;
            this.targetStyle = targetStyle;
        }

        public String getSceneName() { return sceneName; }
        public String getStory() { return story; }
        private String getTargetStyle() { return targetStyle; }
    }

    // 扩展至30个场景（每个风格6个场景）
    private static final Map<String, SceneInfo> SCENE_DETAILS = new HashMap<>();
    static {
        // 甜美风格（6个）
        SCENE_DETAILS.put("sweet1", new SceneInfo("草莓园甜美派对",
                "阳光洒满郊外的草莓园，闺蜜们精心布置了粉色气球、蕾丝桌布和奶油草莓蛋糕。到场的人都穿着蓬蓬裙、带着蝴蝶结发饰，连饮品都插着小雏菊～ 主办方说，最契合主题的穿搭会获得“草莓甜心”称号和限定礼品！", "甜美"));
        SCENE_DETAILS.put("sweet2", new SceneInfo("洛丽塔茶话会",
                "复古咖啡馆举办洛丽塔主题茶话会，蕾丝、荷叶边、缎带是核心元素，搭配精致的茶点和英式红茶，大家都穿着不同风格的洛丽塔裙装，比拼谁的搭配更有少女感～", "甜美"));
        SCENE_DETAILS.put("sweet3", new SceneInfo("游乐园旋转木马",
                "周末的游乐园充满欢声笑语，旋转木马、棉花糖、彩色气球构成梦幻场景，适合穿浅色系、泡泡袖、百褶裙，打造元气满满的甜美少女感～", "甜美"));
        SCENE_DETAILS.put("sweet4", new SceneInfo("樱花季野餐",
                "樱花树下的野餐派对，粉白花瓣飘落，搭配格纹野餐垫、草莓三明治和樱花汽水，穿搭要清新又甜美，浅粉、米白为主色调，点缀樱花元素～", "甜美"));
        SCENE_DETAILS.put("sweet5", new SceneInfo("童话主题生日会",
                "以爱丽丝梦游仙境为主题的生日会，现场有兔子先生、扑克牌装饰，穿搭需要充满童话感，蓬蓬裙、蝴蝶结发箍、玛丽珍鞋是必备～", "甜美"));
        SCENE_DETAILS.put("sweet6", new SceneInfo("猫咪咖啡馆打卡",
                "网红猫咪咖啡馆，整体装修是奶油色系，满墙的猫咪周边，适合穿软糯的针织衫、背带裙，搭配猫爪形状的配饰，和猫咪互动更有氛围感～", "甜美"));

        // 帅气风格（6个）
        SCENE_DETAILS.put("cool1", new SceneInfo("极限山地赛车挑战赛",
                "城市周边的山地赛道开放了挑战赛，赛道布满陡坡和急弯，既考验车技也考验气场。其他车手都穿着利落工装、马丁靴，搭配简约护具和墨镜，全程散发着“速度与激情”的帅气感～", "帅气"));
        SCENE_DETAILS.put("cool2", new SceneInfo("街头滑板大赛",
                "城市广场的街头滑板大赛，选手们穿着oversize卫衣、工装裤、板鞋，搭配棒球帽和项链，既要方便做动作，又要展现街头酷感～", "帅气"));
        SCENE_DETAILS.put("cool3", new SceneInfo("机车俱乐部聚会",
                "复古机车俱乐部的周末聚会，大家骑着哈雷、复古机车，穿搭以皮质夹克、破洞牛仔裤、马丁靴为主，打造硬朗的机车风～", "帅气"));
        SCENE_DETAILS.put("cool4", new SceneInfo("电竞职业联赛现场",
                "电竞职业联赛的线下观赛现场，选手和观众都穿着战队队服、机能风外套，搭配发光耳机、电竞手套，充满科技感和酷飒感～", "帅气"));
        SCENE_DETAILS.put("cool5", new SceneInfo("沙漠越野探险",
                "沙漠越野探险活动，需要穿防风沙的工装套装、高帮沙漠靴，搭配护目镜和战术马甲，既实用又充满冒险的帅气～", "帅气"));
        SCENE_DETAILS.put("cool6", new SceneInfo("摇滚演唱会现场",
                "摇滚乐队的现场演唱会，氛围热烈，穿搭以黑色系为主，皮夹克、铆钉配饰、破洞裤，搭配马丁靴，展现叛逆又帅气的风格～", "帅气"));

        // 性感风格（6个）
        SCENE_DETAILS.put("sexy1", new SceneInfo("海滨星空晚宴",
                "海滨度假酒店的露天露台举办星空主题晚宴，海风轻拂，星光与灯光交相辉映。女士们多穿露肩长裙、搭配闪亮首饰和细高跟，整体氛围浪漫又迷人～", "性感"));
        SCENE_DETAILS.put("sexy2", new SceneInfo("复古歌舞厅舞会",
                "80年代复古歌舞厅的主题舞会，迪斯科灯光、复古舞曲，穿搭以亮片、丝绒、紧身裙为主，搭配夸张的耳环和高跟鞋，展现复古性感～", "性感"));
        SCENE_DETAILS.put("sexy3", new SceneInfo("红酒品鉴晚宴",
                "高端红酒品鉴晚宴，现场氛围优雅又性感，女士穿修身鱼尾裙、细吊带长裙，搭配珍珠项链和细高跟，男士穿修身西装，展现成熟魅力～", "性感"));
        SCENE_DETAILS.put("sexy4", new SceneInfo("泳池派对",
                "夏日泳池派对，阳光、泳池、鸡尾酒，穿搭以性感泳衣外搭薄纱罩衫，搭配墨镜和金属配饰，展现热辣的夏日性感～", "性感"));
        SCENE_DETAILS.put("sexy5", new SceneInfo("红毯颁奖典礼",
                "时尚颁奖典礼的红毯环节，明星们穿着高定礼服，露背、高开叉、修身剪裁是主流，搭配奢华珠宝，展现红毯性感～", "性感"));
        SCENE_DETAILS.put("sexy6", new SceneInfo("爵士酒吧演出",
                "爵士酒吧的驻唱演出，昏暗的灯光、慵懒的音乐，穿搭以丝绒吊带、修身长裤，搭配细闪眼影和红唇，展现慵懒又性感的风格～", "性感"));

        // 典雅风格（6个）
        SCENE_DETAILS.put("elegant1", new SceneInfo("古典美术馆百年特展",
                "市中心美术馆举办百年名画特展，邀请了各界名流和艺术爱好者。现场要求着装庄重得体，丝绒、绸缎、刺绣等材质的服饰格外受欢迎～", "典雅"));
        SCENE_DETAILS.put("elegant2", new SceneInfo("欧式宫廷下午茶",
                "欧式宫廷风格的下午茶，现场有复古吊灯、蕾丝桌布、骨瓷茶具，穿搭以复古长裙、珍珠配饰、低跟玛丽珍鞋，展现优雅的宫廷风～", "典雅"));
        SCENE_DETAILS.put("elegant3", new SceneInfo("交响音乐会现场",
                "城市音乐厅的交响音乐会，着装要求正式且典雅，女士穿长款礼服裙、男士穿燕尾服，搭配精致的首饰和领结，符合音乐会的庄重氛围～", "典雅"));
        SCENE_DETAILS.put("elegant4", new SceneInfo("古董珠宝展",
                "高端古董珠宝展，现场布满珍贵的珠宝藏品，穿搭以简约的丝绒长裙、纯色西装为主，突出珠宝的精致，整体风格典雅大气～", "典雅"));
        SCENE_DETAILS.put("elegant5", new SceneInfo("英式庄园晚宴",
                "英式庄园的传统晚宴，复古的建筑、精致的餐具，穿搭以复古格纹、羊毛材质的服饰为主，搭配礼帽和手套，展现英伦典雅～", "典雅"));
        SCENE_DETAILS.put("elegant6", new SceneInfo("书法绘画雅集",
                "文人墨客的书法绘画雅集，现场笔墨纸砚齐全，穿搭以新中式风格为主，真丝旗袍、亚麻长衫，搭配玉饰，展现东方典雅～", "典雅"));

        // 清新风格（6个）
        SCENE_DETAILS.put("fresh1", new SceneInfo("森林徒步露营会",
                "和朋友组队参加郊外森林露营，活动包含徒步登山、溪边野餐和星空露营。穿搭要舒适透气、方便活动，棉麻服饰、运动鞋是首选～", "清新"));
        SCENE_DETAILS.put("fresh2", new SceneInfo("田园农场采摘",
                "有机田园农场的采摘活动，满田的蔬果，穿搭以浅色系棉麻衬衫、背带裤，搭配草帽和帆布鞋，清新又实用～", "fresh"));
        SCENE_DETAILS.put("fresh3", new SceneInfo("湖边垂钓野餐",
                "静谧的湖边垂钓野餐，微风拂面，穿搭以宽松的亚麻长裤、白T恤，搭配渔夫帽和帆布包，打造慵懒的清新感～", "清新"));
        SCENE_DETAILS.put("fresh4", new SceneInfo("校园毕业季拍照",
                "毕业季的校园拍照，梧桐大道、图书馆、操场，穿搭以白衬衫、百褶裙、小白鞋，搭配帆布包，展现青春清新感～", "清新"));
        SCENE_DETAILS.put("fresh5", new SceneInfo("茶园采茶体验",
                "高山茶园的采茶体验，满眼的绿色，穿搭以浅绿、白色系的棉麻服饰，搭配竹编帽，融入茶园的清新氛围～", "清新"));
        SCENE_DETAILS.put("fresh6", new SceneInfo("海边日出骑行",
                "清晨的海边骑行看日出，海风清新，穿搭以速干运动套装、帆布鞋，搭配棒球帽，展现活力清新感～", "清新"));
    }

    // 技能练习
    public boolean practiceSkill(Player player, String targetStyle) {
        if (!Arrays.asList(STYLES).contains(targetStyle)) {
            System.out.println("❌ 错误：没有「" + targetStyle + "」这种穿搭风格，请从可选风格中选择～");
            return false;
        }
        int currentScore = player.getStyleScore(targetStyle);
        player.setStyleScore(targetStyle, currentScore + 10);
        System.out.println("✅ 练习成功！");
        System.out.println("「" + targetStyle + "」风格穿搭熟练度+10～");
        System.out.println("当前「" + targetStyle + "」风格表现：更贴合主题场景啦！");
        return true;
    }

    // 获取随机场景
    public SceneInfo getRandomSceneInfo() {
        List<String> sceneKeys = new ArrayList<>(SCENE_DETAILS.keySet());
        Random rand = new Random();
        String randomKey = sceneKeys.get(rand.nextInt(sceneKeys.size()));
        return SCENE_DETAILS.get(randomKey);
    }

    // 计算玩家得分
    private double calculatePlayerScore(Player player, String targetStyle, String playerChooseStyle) {
        Map<String, Integer> styleScores = player.getBaseAttr();
        double baseScore = 0;
        for (Map.Entry<String, Integer> entry : styleScores.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();
            baseScore += style.equals(targetStyle) ? score * 1.1 : score;
        }
        double fitFactor = playerChooseStyle.equals(targetStyle) ? 1.2 : 0.8;
        if (playerChooseStyle.equals(targetStyle)) {
            System.out.println("✨ 穿搭风格与场景高度契合！获得额外适配加分～");
        } else {
            System.out.println("⚠️  穿搭风格与场景适配度一般，加分较少哦～");
        }
        return baseScore * fitFactor;
    }

    // 计算NPC得分
    private double calculateNpcScore(NPC npc, String targetStyle) {
        Map<String, Integer> styleScores = npc.getNpcBaseAttr();
        double baseScore = 0;
        for (Map.Entry<String, Integer> entry : styleScores.entrySet()) {
            String style = entry.getKey();
            int score = entry.getValue();
            baseScore += style.equals(targetStyle) ? score * 1.1 : score;
        }
        Random rand = new Random();
        double fluctuation = (rand.nextDouble() * 0.15) - 0.075; // 扩大波动至±7.5%
        double finalScore = baseScore * (1 + fluctuation);
        return finalScore;
    }

    // 对战逻辑
    public boolean battleWithNPC(Player player, NPC npc, String playerChooseStyle, SceneInfo sceneInfo) {
        System.out.println("\n=== 🎉 对战正式开始 🎉 ===");
        System.out.println("【对战场景】" + sceneInfo.getSceneName());
        System.out.println("【你的穿搭】" + playerChooseStyle + "风格");
        System.out.println("【对手穿搭】" + npc.getName() + "的场景适配搭配");
        System.out.println("\n=== ⏳ 穿搭评审进行中... ⏳ ===");

        String targetStyle = sceneInfo.getTargetStyle();
        double playerScore = calculatePlayerScore(player, targetStyle, playerChooseStyle);
        double npcScore = calculateNpcScore(npc, targetStyle);

        System.out.println("\n=== 📊 评审结果 ===");
        System.out.println("你的最终表现得分：" + String.format("%.1f", playerScore));
        System.out.println(npc.getName() + "的最终表现得分：" + String.format("%.1f", npcScore));

        System.out.println("\n=== 🏆 对战结果 🏆 ===");
        if (playerScore > npcScore) {
            int currentScore = player.getStyleScore(targetStyle);
            player.setStyleScore(targetStyle, currentScore + 5);
            System.out.println("🎉 恭喜你！战胜了" + npc.getName() + "！");
            System.out.println("你的「" + playerChooseStyle + "」风格穿搭在「" + sceneInfo.getSceneName() + "」中脱颖而出～");
            System.out.println("💪 「" + targetStyle + "」风格穿搭熟练度+5！");
            return true;
        } else if (playerScore == npcScore) {
            System.out.println("🤝 平局！你和" + npc.getName() + "的穿搭表现不相上下～");
            return false;
        } else {
            System.out.println("😥 很遗憾，你没能战胜" + npc.getName() + "～");
            System.out.println(npc.getName() + "的「" + targetStyle + "」风格穿搭更契合场景氛围，表现略胜一筹～");
            System.out.println("❌ 失败无惩罚！多练习「" + targetStyle + "」风格，下次一定能赢回来！");
            return false;
        }
    }
}