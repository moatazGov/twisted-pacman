package model;



import org.json.simple.JSONObject;

import java.util.Objects;

public class GameData {
    private String nickName;
    private Integer score;

    public GameData(Integer id, String nickName, Integer score) {
        this.nickName = nickName;
        this.score = score;
    }

    public GameData() {
    }

    public GameData fromJson(JSONObject gameJson){
         nickName =   gameJson.get("nickName").toString();
         score =   Integer.valueOf(gameJson.get("score").toString());
         return  this;
    }



    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("score", score);
        obj.put("nickName", nickName);
        return obj;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
