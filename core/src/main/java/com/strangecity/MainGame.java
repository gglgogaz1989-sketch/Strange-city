package com.strangecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strangecity.utils.Button;

public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    Button jumpButton;
    float playerY = 100;
    float velocityY = 0;
    boolean isGrounded = true;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Просто имя файла, так как android/assets — это корень для ресурсов
        jumpButton = new Button("1000013987.png", 50, 50, 180);
    }

    @Override
    public void render() {
        if (jumpButton.isPressed() && isGrounded) {
            velocityY = 15;
            isGrounded = false;
        }

        velocityY -= 0.8f;
        playerY += velocityY;
        if (playerY <= 100) { 
            playerY = 100; 
            isGrounded = true; 
        }

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        jumpButton.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        jumpButton.dispose();
    }
}

