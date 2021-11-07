package Model;

import org.codehaus.groovy.vmplugin.v8.IndyInterface;

import java.util.Objects;

public class GameData {
    private Integer id;
    private String nickName;
    private Integer score;

    public GameData(String nickName, Integer score) {
        this.nickName = nickName;
        this.score = score;
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
