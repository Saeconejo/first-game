package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
public class Player {
    private String account;
    private String password;
    private String id;
    private String name;
    private Map<String, Integer> baseAttr;

    // 无参构造（必须，FastJSON序列化需要）
    public Player() {}

    // 注册构造方法
    public Player(String account, String password, String nickname) {
        this.account = account;
        this.password = password;
        this.id = "P_" + System.currentTimeMillis();
        this.name = nickname;
        this.baseAttr = new HashMap<>();
=======
<<<<<<< HEAD
/**
 * 玩家实体类（含账号、密码、昵称、风格熟练度）
 */
public class Player {
    private String account; // 登录账号（新增）
    private String password; // 登录密码（新增）
    private String id;
    private String name; // 游戏昵称
    private Map<String, Integer> baseAttr; // 五种风格熟练度

    // 注册时使用的构造方法（新增）
    public Player(String account, String password, String nickname) {
        this.account = account;
        this.password = password;
        this.id = "P_" + System.currentTimeMillis(); // 生成唯一ID（时间戳）
        this.name = nickname;
        this.baseAttr = new HashMap<>();
        // 初始化五种风格，各100熟练度
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

<<<<<<< HEAD
    // 旧构造方法（兼容）
=======
    // 旧构造方法（保留，兼容原有逻辑）
=======
public class Player {
    private String id;
    private String name;
    private Map<String, Integer> baseAttr;  // 存储五种风格的基础属性分

>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.baseAttr = new HashMap<>();
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
        // 初始化五种风格，各100分
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

<<<<<<< HEAD
    // Getter/Setter
=======
<<<<<<< HEAD
    // Getter和Setter（新增账号、密码的get/set）
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
<<<<<<< HEAD
    public Map<String, Integer> getBaseAttr() { return baseAttr; }
    public void setBaseAttr(Map<String, Integer> baseAttr) { this.baseAttr = baseAttr; }
=======
=======
    // Getter和Setter方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
    public Map<String, Integer> getBaseAttr() { return baseAttr; }
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642

    public void setStyleScore(String style, int score) {
        baseAttr.put(style, score);
    }

    public int getStyleScore(String style) {
        return baseAttr.getOrDefault(style, 0);
    }

<<<<<<< HEAD
    // 显示玩家信息
=======
<<<<<<< HEAD
    // 显示玩家信息（隐藏密码，只显示账号、昵称、熟练度）
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    public void showAttributes() {
        System.out.println("📌 账号：" + account);
        System.out.println("📌 昵称：" + name);
        System.out.println("=== 穿搭风格熟练度 ===");
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "级");
        }
        System.out.println("======================");
<<<<<<< HEAD
=======
=======
    // 显示玩家属性
    public void showAttributes() {
        System.out.println("=== " + name + "的属性 ===");
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
        }
        System.out.println("====================");
>>>>>>> d66f3c8a65079faae4b32a24324d16e9fb8ccf91
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    }
}