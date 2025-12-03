package NikkiDressUp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference; // 新增这行
import NikkiDressUp.model.Player;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件读写工具类：负责玩家信息的保存、读取、账号密码验证
 */
public class FileUtil {
    // 玩家信息存储文件路径（项目根目录下的 playerData.json）
    private static final String PLAYER_FILE_PATH = "playerData.json";

    /**
     * 读取所有玩家信息（账号→Player对象）
     */
    public static Map<String, Player> readAllPlayers() {
        File file = new File(PLAYER_FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            // 用TypeReference指定泛型，这是fastjson支持的写法
            return JSON.parseObject(json.toString(), new TypeReference<Map<String, Player>>() {});
        } catch (Exception e) {
            System.out.println("⚠️  读取玩家数据失败，返回空数据！");
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    /**
     * 保存所有玩家信息到文件
     */
    public static boolean saveAllPlayers(Map<String, Player> players) {
        try (FileWriter writer = new FileWriter(PLAYER_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(writer)) {
            // JSON序列化（格式化输出，便于查看）
            String json = JSON.toJSONString(players, true);
            bw.write(json);
            return true;
        } catch (Exception e) {
            System.out.println("❌ 保存玩家数据失败！");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 注册新玩家（检查账号是否已存在，不存在则添加）
     */
    public static boolean registerPlayer(String account, String password, String nickname) {
        Map<String, Player> players = readAllPlayers();
        if (players.containsKey(account)) {
            return false; // 账号已存在，注册失败
        }
        // 创建新玩家（初始风格熟练度均为100）
        Player newPlayer = new Player(account, password, nickname);
        players.put(account, newPlayer);
        return saveAllPlayers(players); // 保存到文件
    }

    /**
     * 登录验证（账号密码匹配返回Player，否则返回null）
     */
    public static Player login(String account, String password) {
        Map<String, Player> players = readAllPlayers();
        Player player = players.get(account);
        if (player != null && player.getPassword().equals(password)) {
            return player; // 账号密码匹配，返回玩家对象
        }
        return null; // 账号不存在或密码错误
    }

    /**
     * 更新单个玩家信息（如熟练度变化后保存）
     */
    public static boolean updatePlayer(Player player) {
        Map<String, Player> players = readAllPlayers();
        players.put(player.getAccount(), player); // 覆盖旧数据
        return saveAllPlayers(players);
    }
}