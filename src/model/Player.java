package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

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
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

    // 旧构造方法（兼容）
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.baseAttr = new HashMap<>();
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

    // Getter/Setter
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Map<String, Integer> getBaseAttr() { return baseAttr; }
    public void setBaseAttr(Map<String, Integer> baseAttr) { this.baseAttr = baseAttr; }

    public void setStyleScore(String style, int score) {
        baseAttr.put(style, score);
    }

    public int getStyleScore(String style) {
        return baseAttr.getOrDefault(style, 0);
    }

    // 显示玩家信息
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