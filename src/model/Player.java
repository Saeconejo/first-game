package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String id;
    private String name;
    private Map<String, Integer> baseAttr;  // 存储五种风格的基础属性分

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.baseAttr = new HashMap<>();
        // 初始化五种风格，各100分
        String[] styles = {"帅气", "甜美", "性感", "典雅", "清新"};
        for (String style : styles) {
            baseAttr.put(style, 100);
        }
    }

    // Getter和Setter方法
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

    // 显示玩家属性
    public void showAttributes() {
        System.out.println("=== " + name + "的属性 ===");
        for (Map.Entry<String, Integer> entry : baseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
        }
        System.out.println("====================");
    }
}