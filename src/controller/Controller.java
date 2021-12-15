package controller;

import model.*;

import java.util.ArrayList;

public class Controller {
    private SysData sysData = new SysData();
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private ArrayList<PacItem> pacItems;
    private ArrayList<BombItem> bombs;
    private ArrayList<Question> questions;
    private ArrayList<Wall> walls;

    // todo validate what is the field map
    private ArrayList map;

    private ArrayList<Pass> passes;
    private Integer score = 0 ;
    private Integer level = 0;


    public  boolean loadData(){
        return this.sysData.load();
    }

    public ArrayList getGames(){
        return this.sysData.loadGamesJson();
    }

}
