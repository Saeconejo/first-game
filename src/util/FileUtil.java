package NikkiDressUp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import NikkiDressUp.model.Player;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
    private static final String PLAYER_FILE_PATH = "playerData.json";

    // 读取所有玩家信息
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
            return JSON.parseObject(json.toString(), new TypeReference<Map<String, Player>>() {});
        } catch (Exception e) {
            System.out.println("⚠️  读取玩家数据失败，返回空数据！");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // 保存所有玩家信息
    public static boolean saveAllPlayers(Map<String, Player> players) {
        try (FileWriter writer = new FileWriter(PLAYER_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(writer)) {
            String json = JSON.toJSONString(players, true);
            bw.write(json);
            return true;
        } catch (Exception e) {
            System.out.println("❌ 保存玩家数据失败！");
            e.printStackTrace();
            return false;
        }
    }

    // 注册新玩家
    public static boolean registerPlayer(String account, String password, String nickname) {
        Map<String, Player> players = readAllPlayers();
        if (players.containsKey(account)) {
            return false;
        }
        Player newPlayer = new Player(account, password, nickname);
        players.put(account, newPlayer);
        return saveAllPlayers(players);
    }

    // 登录验证
    public static Player login(String account, String password) {
        Map<String, Player> players = readAllPlayers();
        Player player = players.get(account);
        if (player != null && player.getPassword().equals(password)) {
            return player;
        }
        return null;
    }

    // 更新单个玩家信息
    public static boolean updatePlayer(Player player) {
        Map<String, Player> players = readAllPlayers();
        players.put(player.getAccount(), player);
        return saveAllPlayers(players);
    }
}