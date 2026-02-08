package com.strangecity.blocks;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform {
    private Texture tileTexture;
    private final int gridSize = 10;
    private final int tileSize = 32;

    public Platform() {
        // Создаем белый квадрат с рамкой программно
        Pixmap pixmap = new Pixmap(tileSize, tileSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1); // Белый
        pixmap.drawRectangle(0, 0, tileSize, tileSize); // Рисуем контур
        tileTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                batch.draw(tileTexture, i * tileSize, j * tileSize);
            }
        }
    }
    
    public void dispose() {
        tileTexture.dispose();
    }
}

