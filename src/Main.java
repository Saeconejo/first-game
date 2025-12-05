package NikkiDressUp;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
import NikkiDressUp.model.Player;
import NikkiDressUp.model.NPC;
import NikkiDressUp.logic.SceneLogic;
import NikkiDressUp.util.FileUtil;
import java.util.*;

public class Main {
<<<<<<< HEAD
    private static Player currentPlayer;
    private static final String[] STYLES = {"帅气", "甜美", "性感", "典雅", "清新"};
    private static final Random RANDOM = new Random();
=======
    private static Player currentPlayer; // 当前登录玩家（全局可用）
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

<<<<<<< HEAD
        // 注册/登录
=======
        // 第一步：注册/登录选择
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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

<<<<<<< HEAD
        if (authChoice == 1) {
=======
        // 处理注册/登录逻辑
        if (authChoice == 1) {
            // 注册流程
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
            currentPlayer = register(scanner);
            if (currentPlayer == null) {
                System.out.println("❌ 注册失败，退出游戏！");
                scanner.close();
                return;
            }
        } else if (authChoice == 2) {
<<<<<<< HEAD
=======
            // 登录流程
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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

<<<<<<< HEAD
        // 登录成功
=======
        // 登录/注册成功，进入游戏
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
        System.out.println("\n🎉 欢迎回到《ShiningNIKKI》搭配师世界！");
        System.out.println("你当前的信息：");
        currentPlayer.showAttributes();

<<<<<<< HEAD
        // 初始化30个NPC（27普通+3BOSS）
        List<NPC> npcList = initNPCs();
        SceneLogic sceneLogic = new SceneLogic();

        // 主循环
=======
        // 初始化NPC和场景逻辑
        List<NPC> npcList = initNPCs();
        SceneLogic sceneLogic = new SceneLogic();

        // 游戏主循环
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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
<<<<<<< HEAD
                    FileUtil.updatePlayer(currentPlayer);
=======
                    FileUtil.updatePlayer(currentPlayer); // 练习后保存（新增）
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
                    break;

                case 2:
                    System.out.println("\n=== 🥊 挑战NPC 🥊 ===");
<<<<<<< HEAD
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
=======
                    NPC selectedNPC = npcList.get(new Random().nextInt(npcList.size()));
                    System.out.println("🎯 本次对战的对手是：" + selectedNPC.getName() + "！");
                    System.out.println("💡 提示：" + selectedNPC.getName() + "的擅长风格已在「查看NPC信息」中显示，记得参考～");

>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
                    SceneLogic.SceneInfo sceneInfo = sceneLogic.getRandomSceneInfo();
                    System.out.println("\n📜 对战场景剧情：");
                    System.out.println("【场景名称】" + sceneInfo.getSceneName());
                    System.out.println("【剧情描述】" + sceneInfo.getStory());

                    System.out.println("\n🎨 可选穿搭风格：帅气、甜美、性感、典雅、清新");
                    System.out.print("请根据场景剧情，选择你的主打穿搭风格：");
                    String mainStyle = scanner.nextLine().trim();
                    sceneLogic.battleWithNPC(currentPlayer, selectedNPC, mainStyle, sceneInfo);
<<<<<<< HEAD
                    FileUtil.updatePlayer(currentPlayer); // 对战后保存属性
=======
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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
<<<<<<< HEAD
                        if (npc.getName().contains("BOSS")) {
                            System.out.println("⚠️  类型：BOSS级NPC（属性超强）");
                        } else {
                            System.out.println("ℹ️  类型：普通NPC");
                        }
=======
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
                        System.out.println("------------------------");
                    }
                    break;

                case 5:
<<<<<<< HEAD
=======
                    // 退出游戏：保存玩家信息
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
                    boolean saveSuccess = FileUtil.updatePlayer(currentPlayer);
                    if (saveSuccess) {
                        System.out.println("\n✅ 玩家信息已自动保存！");
                    } else {
                        System.out.println("\n⚠️  玩家信息保存失败，请手动检查文件！");
                    }
                    System.out.println("🎈 感谢游玩《ShiningNIKKI》换装战斗游戏！");
                    System.out.println("愿你永远保持对美的热爱，下次再见～");
                    isRunning = false;
                    break;

                default:
                    System.out.println("\n❌ 无效的选择！请输入1-5之间的数字，重新选择～");
            }
        }
<<<<<<< HEAD
        scanner.close();
    }

    // 注册流程
=======

        scanner.close();
    }

    /**
     * 注册流程：输入账号、密码、昵称，验证账号唯一性
     */
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    private static Player register(Scanner scanner) {
        System.out.println("\n=== 📝 新用户注册 ===");
        System.out.print("请输入账号（长度≥4）：");
        String account = scanner.nextLine().trim();
        System.out.print("请输入密码（长度≥6）：");
        String password = scanner.nextLine().trim();
        System.out.print("请输入游戏昵称：");
        String nickname = scanner.nextLine().trim();

<<<<<<< HEAD
=======
        // 校验输入合法性
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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

<<<<<<< HEAD
        boolean registerSuccess = FileUtil.registerPlayer(account, password, nickname);
        if (registerSuccess) {
            System.out.println("✅ 注册成功！正在为你创建角色...");
            return FileUtil.login(account, password);
=======
        // 注册并保存
        boolean registerSuccess = FileUtil.registerPlayer(account, password, nickname);
        if (registerSuccess) {
            System.out.println("✅ 注册成功！正在为你创建角色...");
            return FileUtil.login(account, password); // 注册后自动登录
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
        } else {
            System.out.println("❌ 注册失败！该账号已存在～");
            return null;
        }
    }

<<<<<<< HEAD
    // 登录流程
=======
    /**
     * 登录流程：输入账号密码，验证通过返回玩家对象
     */
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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

<<<<<<< HEAD
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
=======
    /**
     * 初始化NPC对手（3位不同风格擅长者）
     */
    private static List<NPC> initNPCs() {
        List<NPC> npcList = new ArrayList<>();

        // NPC1：莉莉（甜美风格擅长者）
=======
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
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
        Map<String, Integer> lilyAttr = new HashMap<>();
        lilyAttr.put("帅气", 80);
        lilyAttr.put("甜美", 120);
        lilyAttr.put("性感", 90);
        lilyAttr.put("典雅", 85);
        lilyAttr.put("清新", 95);
        npcList.add(new NPC("莉莉", lilyAttr));

<<<<<<< HEAD
        // NPC2：阿明（帅气风格擅长者）
=======
        // NPC2：阿明（帅气风格）
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
        Map<String, Integer> mingAttr = new HashMap<>();
        mingAttr.put("帅气", 130);
        mingAttr.put("甜美", 75);
        mingAttr.put("性感", 85);
        mingAttr.put("典雅", 90);
        mingAttr.put("清新", 80);
        npcList.add(new NPC("阿明", mingAttr));

<<<<<<< HEAD
        // NPC3：苏珊（性感风格擅长者）
=======
        // NPC3：苏珊（性感风格）
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
        Map<String, Integer> susanAttr = new HashMap<>();
        susanAttr.put("帅气", 85);
        susanAttr.put("甜美", 90);
        susanAttr.put("性感", 125);
        susanAttr.put("典雅", 95);
        susanAttr.put("清新", 80);
        npcList.add(new NPC("苏珊", susanAttr));

<<<<<<< HEAD
        System.out.println("\n📢 系统提示：已生成" + npcList.size() + "个NPC对手！");
        return npcList;
    }

    /**
     * 辅助方法：判断NPC的擅长风格
     */
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
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
<<<<<<< HEAD
}
=======
}
=======
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
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
