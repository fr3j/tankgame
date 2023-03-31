package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.scenes.StaticScene;

public class TitleScene extends StaticScene {

    @Override
    public void setupScene() {
        setBackgroundAudio("audio/8bit.mp3");
        setBackgroundImage("backgrounds/titlescreen.png");
    }

    @Override
    public void setupEntities() {

    }
}
