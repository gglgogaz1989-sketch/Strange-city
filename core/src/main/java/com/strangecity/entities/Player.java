package com.strangecity.entities;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    public float x = 150, y = 150;
    public float velocityY = 0;
    private Texture texture;

    public Player() {
        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fill(); // Сплошной белый квадрат
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void update() {
        velocityY -= 0.8f; // Гравитация
        y += velocityY;
        if (y < 0) { // Простая проверка пола
            y = 0;
            velocityY = 0;
        }
    }

    public void jump() {
        if (y <= 0) velocityY = 15;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
    
    public void dispose() {
        texture.dispose();
    }
}

