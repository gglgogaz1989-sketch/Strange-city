package com.strangecity.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    private Texture texture;
    private Rectangle bounds;

    public Button(String path, float x, float y, float size) {
        texture = new Texture(path);
        bounds = new Rectangle(x, y, size, size);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public boolean isPressed() {
        if (Gdx.input.isTouched()) {
            float tx = Gdx.input.getX();
            float ty = Gdx.graphics.getHeight() - Gdx.input.getY();
            return bounds.contains(tx, ty);
        }
        return false;
    }

    public void dispose() {
        texture.dispose();
    }
}

