package NIKKIDressUp3;

import NIKKIDressUp3.model.Player;
import NIKKIDressUp3.model.NPC;
import NIKKIDressUp3.logic.SceneLogic;
import NIKKIDressUp3.util.FileUtil;
import java.util.*;

public class Main {
    private static Player currentPlayer;
    private static final String[] STYLES = {"帅气", "甜美", "性感", "典雅", "清新"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 注册/登录
        System.out.println("========== 《ShiningNIKKI》换装战斗游戏 ==========");
        System.out.println("1. 新用户注册");
        System.out.println("2. 老用户登录");
        System.out.print("请选择操作（1/2）：");

        int authChoice;
        try {
            authChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ 输入错误，退出游戏！");
            scanner.close();
            return;
        }

        if (authChoice == 1) {
            currentPlayer = register(scanner);
            if (currentPlayer == null) {
                System.out.println("❌ 注册失败，退出游戏！");
                scanner.close();
                return;
            }
        } else if (authChoice == 2) {
            currentPlayer = login(scanner);
            if (currentPlayer == null) {
                System.out.println("❌ 登录失败，退出游戏！");
                scanner.close();
                return;
            }
        } else {
            System.out.println("❌ 无效选择，退出游戏！");
            scanner.close();
            return;
        }

        // 登录成功
        System.out.println("\n🎉 欢迎回到《ShiningNIKKI》搭配师世界！");
        System.out.println("你当前的信息：");
        currentPlayer.showAttributes();

        // 初始化30个NPC（27普通+3BOSS）
        List<NPC> npcList = initNPCs();
        SceneLogic sceneLogic = new SceneLogic();

        // 主循环
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n======================================");
            System.out.println("               主菜单");
            System.out.println("======================================");
            System.out.println("1. 技能练习（提升单个风格熟练度）");
            System.out.println("2. 挑战NPC（根据场景剧情搭配穿搭）");
            System.out.println("3. 查看我的穿搭风格");
            System.out.println("4. 查看NPC对手信息");
            System.out.println("5. 退出游戏");
            System.out.println("======================================");
            System.out.print("请选择操作（输入1-5）：");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n❌ 错误：请输入1-5之间的数字！");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n=== 🌟 技能练习 🌟 ===");
                    System.out.println("可选风格：帅气、甜美、性感、典雅、清新");
                    System.out.print("请输入你要练习的风格：");
                    String targetStyle = scanner.nextLine().trim();
                    sceneLogic.practiceSkill(currentPlayer, targetStyle);
                    FileUtil.updatePlayer(currentPlayer);
                    break;

                case 2:
                    System.out.println("\n=== 🥊 挑战NPC 🥊 ===");
                    NPC selectedNPC = npcList.get(RANDOM.nextInt(npcList.size()));

                    // BOSS提醒机制
                    if (selectedNPC.getName().contains("BOSS")) {
                        System.out.println("⚠️  警告！你匹配到了强力BOSS「" + selectedNPC.getName() + "」！");
                        System.out.println("💀 BOSS属性远超普通NPC，获胜难度极高！建议先练习对应风格～");
                        System.out.print("是否继续挑战（Y/N）：");
                        String confirm = scanner.nextLine().trim().toUpperCase();
                        if (!confirm.equals("Y")) {
                            System.out.println("✅ 已取消挑战，返回主菜单～");
                            break;
                        }
                    } else {
                        System.out.println("🎯 本次对战的对手是：" + selectedNPC.getName() + "！");
                    }

                    // 随机场景+选择风格
                    SceneLogic.SceneInfo sceneInfo = sceneLogic.getRandomSceneInfo();
                    System.out.println("\n📜 对战场景剧情：");
                    System.out.println("【场景名称】" + sceneInfo.getSceneName());
                    System.out.println("【剧情描述】" + sceneInfo.getStory());

                    System.out.println("\n🎨 可选穿搭风格：帅气、甜美、性感、典雅、清新");
                    System.out.print("请根据场景剧情，选择你的主打穿搭风格：");
                    String mainStyle = scanner.nextLine().trim();
                    sceneLogic.battleWithNPC(currentPlayer, selectedNPC, mainStyle, sceneInfo);
                    FileUtil.updatePlayer(currentPlayer); // 对战后保存属性
                    break;

                case 3:
                    System.out.println("\n=== 📊 我的穿搭风格熟练度 ===");
                    currentPlayer.showAttributes();
                    break;

                case 4:
                    System.out.println("\n=== 📋 NPC对手风格信息 ===");
                    for (NPC npc : npcList) {
                        npc.showAttributes();
                        String goodAtStyle = getGoodAtStyle(npc);
                        System.out.println("💡 擅长风格：" + goodAtStyle);
                        if (npc.getName().contains("BOSS")) {
                            System.out.println("⚠️  类型：BOSS级NPC（属性超强）");
                        } else {
                            System.out.println("ℹ️  类型：普通NPC");
                        }
                        System.out.println("------------------------");
                    }
                    break;

                case 5:
                    boolean saveSuccess = FileUtil.updatePlayer(currentPlayer);
                    if (saveSuccess) {
                        System.out.println("\n✅ 玩家信息已自动保存！");
                    } else {
                        System.out.println("\n⚠️  玩家信息保存失败，请手动检查文件！");
                    }
                    System.out.println("🎈 感谢参与《ShiningNIKKI》换装战斗游戏！");
                    System.out.println("愿你永远保持对美的热爱，下次再见～");
                    isRunning = false;
                    break;

                default:
                    System.out.println("\n❌ 无效的选择！请输入1-5之间的数字，重新选择～");
            }
        }
        scanner.close();
    }

    // 注册流程
    private static Player register(Scanner scanner) {
        System.out.println("\n=== 📝 新用户注册 ===");
        System.out.print("请输入账号（长度≥4）：");
        String account = scanner.nextLine().trim();
        System.out.print("请输入密码（长度≥6）：");
        String password = scanner.nextLine().trim();
        System.out.print("请输入游戏昵称：");
        String nickname = scanner.nextLine().trim();

        if (account.length() < 4) {
            System.out.println("❌ 账号长度必须≥4！");
            return null;
        }
        if (password.length() < 6) {
            System.out.println("❌ 密码长度必须≥6！");
            return null;
        }
        if (nickname.isEmpty()) {
            System.out.println("❌ 昵称不能为空！");
            return null;
        }

        boolean registerSuccess = FileUtil.registerPlayer(account, password, nickname);
        if (registerSuccess) {
            System.out.println("✅ 注册成功！正在为你创建角色...");
            return FileUtil.login(account, password);
        } else {
            System.out.println("❌ 注册失败！该账号已存在～");
            return null;
        }
    }

    // 登录流程
    private static Player login(Scanner scanner) {
        System.out.println("\n=== 🔑 用户登录 ===");
        System.out.print("请输入账号：");
        String account = scanner.nextLine().trim();
        System.out.print("请输入密码：");
        String password = scanner.nextLine().trim();

        Player player = FileUtil.login(account, password);
        if (player != null) {
            System.out.println("✅ 登录成功！欢迎回来，" + player.getName() + "～");
            return player;
        } else {
            System.out.println("❌ 登录失败！账号不存在或密码错误～");
            return null;
        }
    }

    // 初始化30个NPC（27普通+3BOSS）
    private static List<NPC> initNPCs() {
        List<NPC> npcList = new ArrayList<>();

        // 1. 生成27个普通NPC（随机属性：基础70-120）
        String[] npcNames = {
                "莉莉", "阿明", "苏珊", "小美", "小杰", "娜娜", "阿凯", "菲菲", "阿杰",
                "乐乐", "洋洋", "朵朵", "轩轩", "妍妍", "涛涛", "萌萌", "浩浩", "倩倩",
                "斌斌", "丽丽", "超超", "莎莎", "明明", "静静", "强强", "婷婷", "龙龙"
        };
        for (String name : npcNames) {
            Map<String, Integer> attr = generateRandomAttr(70, 120);
            npcList.add(new NPC(name, attr));
        }

        // 2. 生成3个BOSS NPC（属性180-250，远超普通）
        Map<String, Integer> boss1Attr = generateRandomAttr(200, 250);
        npcList.add(new NPC("BOSS·闪耀女王", boss1Attr));

        Map<String, Integer> boss2Attr = generateRandomAttr(180, 230);
        npcList.add(new NPC("BOSS·穿搭教父", boss2Attr));

        Map<String, Integer> boss3Attr = generateRandomAttr(190, 240);
        npcList.add(new NPC("BOSS·风格之神", boss3Attr));

        System.out.println("\n📢 系统提示：已生成" + npcList.size() + "个NPC对手（含3个BOSS）！");
        return npcList;
    }

    // 生成随机属性（指定数值范围）
    private static Map<String, Integer> generateRandomAttr(int min, int max) {
        Map<String, Integer> attr = new HashMap<>();
        for (String style : STYLES) {
            attr.put(style, RANDOM.nextInt(max - min + 1) + min);
        }
        return attr;
    }

    // 获取NPC擅长风格
    private static String getGoodAtStyle(NPC npc) {
        Map<String, Integer> attrMap = npc.getNpcBaseAttr();
        String goodAtStyle = "";
        int maxScore = 0;
        for (Map.Entry<String, Integer> entry : attrMap.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                goodAtStyle = entry.getKey();
            }
        }
        return goodAtStyle;
    }
}