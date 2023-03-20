package com.mygdx.game.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.mygdx.game.handlers.B2DVars.PPM;

public class BoundedCamera extends OrthographicCamera {
    private float xmin;
    private float xmax;
    private float ymin;
    private float ymax;

    public BoundedCamera() {
        this(0, 0, 0, 0);
    }

    public BoundedCamera(float xmin, float xmax, float ymin, float ymax) {
        super();
        setBounds(xmin, xmax, ymin, ymax);
    }

    public void setBounds(float xmin, float xmax, float ymin, float ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public void setPosition(float x, float y) {
        setPosition(x, y, 0);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
        fixBounds();
    }

    private void fixBounds() {
        if(position.x < xmin + viewportWidth / 2) {
            position.x = xmin + viewportWidth / 2;
        }
        if(position.x > xmax - viewportWidth / 70) {
            System.out.println(" MAX X");
            position.x = xmax - viewportWidth / 70;
        }
        if(position.y < ymin + viewportHeight / 35 ) {
            //System.out.println(ymin + viewportHeight / 35 + " MIN Y");
            position.y = ymin + viewportHeight / 35;
        }
        if(position.y > ymax - viewportHeight / 35) {
            //System.out.println(ymax - viewportHeight / 35 + " MAX Y");
            position.y = ymax - viewportHeight / 35;
        }
    }
}
