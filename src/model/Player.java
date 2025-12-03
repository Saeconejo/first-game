package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

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
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

    // 旧构造方法（保留，兼容原有逻辑）
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.baseAttr = new HashMap<>();
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

    // Getter和Setter（新增账号、密码的get/set）
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Map<String, Integer> getBaseAttr() { return baseAttr; }

    public void setStyleScore(String style, int score) {
        baseAttr.put(style, score);
    }

    public int getStyleScore(String style) {
        return baseAttr.getOrDefault(style, 0);
    }

    // 显示玩家信息（隐藏密码，只显示账号、昵称、熟练度）
    public void showAttributes() {
        System.out.println("📌 账号：" + account);
        System.out.println("📌 昵称：" + name);
        System.out.println("=== 穿搭风格熟练度 ===");
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "级");
        }
        System.out.println("======================");
    }
}