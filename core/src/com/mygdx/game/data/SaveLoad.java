package com.mygdx.game.data;

import com.mygdx.game.states.Play;

import java.io.*;

public class SaveLoad {
    private Play play;
    private DataStorage this_ds;

    public SaveLoad(Play play) {
        this.play = play;
    }

    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();
            ds.playerPosX = play.getPlayer().getPosition();
            ds.save = play.save;
            //ds.playerPosY = play.getPlayer().getPosition().y;

            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("can't save");
        }
    }

    public void load(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage) ois.readObject();

            play.getPlayer().getPosition().set(ds.playerPosX);
            play.save = ds.save;
            //play.getPlayer().getPosition().y = ds.playerPosY;
            this_ds = ds;
            System.out.println(ds.playerPosX);

        } catch (Exception e) {
            System.out.println("can't load");
        }
    }

    public DataStorage getDs() {
        return this_ds;
    }
}
