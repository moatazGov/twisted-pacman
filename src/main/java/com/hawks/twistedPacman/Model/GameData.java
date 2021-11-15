package com.hawks.twistedPacman.Model;


import org.json.simple.JSONObject;

import java.util.Objects;

public class GameData {
    private Integer id;
    private String nickName;
    private Integer score;

    public GameData(Integer id, String nickName, Integer score) {
        this.nickName = nickName;
        this.score = score;
    }

    public GameData() {
    }

    public GameData fromJson(JSONObject gameJson){

             id =    Integer.valueOf(gameJson.get("id").toString());
              nickName =   gameJson.get("nickName").toString();
              score =   Integer.valueOf(gameJson.get("score").toString());
              return  this;
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("score", score);
        obj.put("nickName", nickName);
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameData)) return false;
        GameData gameData = (GameData) o;
        return Objects.equals(getId(), gameData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
