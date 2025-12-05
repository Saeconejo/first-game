package NikkiDressUp.model;

import java.util.HashMap;
import java.util.Map;

public class NPC {
    private String name;
<<<<<<< HEAD
    private Map<String, Integer> npcBaseAttr;

    // 无参构造（FastJSON序列化需要）
    public NPC() {}
=======
    private Map<String, Integer> npcBaseAttr;  // NPC的五种风格基础分
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642

    public NPC(String name, Map<String, Integer> baseAttr) {
        this.name = name;
        this.npcBaseAttr = new HashMap<>(baseAttr);
    }

<<<<<<< HEAD
    public String getName() { return name; }
    public Map<String, Integer> getNpcBaseAttr() { return npcBaseAttr; }
    public void setNpcBaseAttr(Map<String, Integer> npcBaseAttr) { this.npcBaseAttr = npcBaseAttr; }
=======
    // Getter方法
    public String getName() { return name; }

    public Map<String, Integer> getNpcBaseAttr() { return npcBaseAttr; }
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642

    public int getStyleScore(String style) {
        return npcBaseAttr.getOrDefault(style, 0);
    }

<<<<<<< HEAD
=======
    // 显示NPC属性
>>>>>>> e794eacaceb5477a8d4a3e3ae2b27960a7ac6642
    public void showAttributes() {
        System.out.println("=== NPC " + name + "的属性 ===");
        for (Map.Entry<String, Integer> entry : npcBaseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
        }
        System.out.println("====================");
    }
}