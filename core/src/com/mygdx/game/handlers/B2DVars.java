package com.mygdx.game.handlers;

import com.mygdx.game.MyGdxGame;

public class B2DVars {
    public static class UI{
        public static class Buttons{
            public static final int BTN_WIDTH_DEF = 96;
            public static final int BTN_HEIGHT_DEF = 33;
            public static final int BTN_WIDTH = BTN_WIDTH_DEF * 4;
            public static final int BTN_HEIGHT = BTN_HEIGHT_DEF * 4;
        }
    }
    public static final float PPM = 10;

    public static final short BIT_NOTHING = 2;
    public static final short BIT_PLAYER = 4;
    public static final short BIT_TROPA = 8;
    public static final short BIT_PENEK = 16;
}
