package NIKKIDressUp3.model;

import java.util.HashMap;
import java.util.Map;

public class NPC {
    private String name;
    private Map<String, Integer> npcBaseAttr;

    // 无参构造（FastJSON序列化需要）
    public NPC() {}

    public NPC(String name, Map<String, Integer> baseAttr) {
        this.name = name;
        this.npcBaseAttr = new HashMap<>(baseAttr);
    }

    public String getName() { return name; }
    public Map<String, Integer> getNpcBaseAttr() { return npcBaseAttr; }
    public void setNpcBaseAttr(Map<String, Integer> npcBaseAttr) { this.npcBaseAttr = npcBaseAttr; }

    public int getStyleScore(String style) {
        return npcBaseAttr.getOrDefault(style, 0);
    }

    public void showAttributes() {
        System.out.println("=== NPC " + name + "的属性 ===");
        for (Map.Entry<String, Integer> entry : npcBaseAttr.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "分");
        }
        System.out.println("====================");
    }
}