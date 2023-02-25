package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact c) {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();
        System.out.println(fa.getUserData() + ", " + fb.getUserData());
    }

    @Override
    public void endContact(Contact c) {
        System.out.println("End Contact");
    }

    @Override
    public void preSolve(Contact c, Manifold m) {

    }

    @Override
    public void postSolve(Contact c, ContactImpulse ci) {

    }
}
