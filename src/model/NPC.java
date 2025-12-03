package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

public class NPC {
    private String name;
    private Map<String, Integer> npcBaseAttr;  // NPC的五种风格基础分

    public NPC(String name, Map<String, Integer> baseAttr) {
        this.name = name;
        this.npcBaseAttr = new HashMap<>(baseAttr);
    }

    // Getter方法
    public String getName() { return name; }

    public Map<String, Integer> getNpcBaseAttr() { return npcBaseAttr; }

    public int getStyleScore(String style) {
        return npcBaseAttr.getOrDefault(style, 0);
    }

    // 显示NPC属性
    public void showAttributes() {
        System.out.println("=== NPC " + name + "的属性 ===");
        for (Map.Entry<String, Integer> entry : npcBaseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
        }
        System.out.println("====================");
    }
}