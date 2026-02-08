package com.strangecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture jumpBtnTex;
    Texture cursorTex;
    
    // Физика
    Vector2 position = new Vector2(100, 100);
    float velocityY = 0;
    float gravity = -0.5f;
    boolean onGround = true;

    // Зона кнопки прыжка (для тача)
    Rectangle jumpButtonBounds;

    @Override
    public void create() {
        batch = new SpriteBatch();
        jumpBtnTex = new Texture("jump_icon.png"); // Твой арт со стрелкой
        cursorTex = new Texture("cursor.png");     // Твой прицел
        jumpButtonBounds = new Rectangle(Gdx.graphics.getWidth() - 200, 50, 150, 150);
    }

    @Override
    public void render() {
        update();
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(16384); // Очистка экрана

        batch.begin();
        // Рисуем кнопку прыжка (только если мы на телефоне)
        batch.draw(jumpBtnTex, jumpButtonBounds.x, jumpButtonBounds.y, jumpButtonBounds.width, jumpButtonBounds.height);
        
        // Рисуем курсор по центру
        batch.draw(cursorTex, Gdx.graphics.getWidth()/2 - 32, Gdx.graphics.getHeight()/2 - 32, 64, 64);
        batch.end();
    }

    private void update() {
        // Гравитация
        velocityY += gravity;
        position.y += velocityY;

        if (position.y <= 100) {
            position.y = 100;
            velocityY = 0;
            onGround = true;
        }

        // Проверка прыжка (Клавиатура ИЛИ Тач)
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            
            if (jumpButtonBounds.contains(touchX, touchY) && onGround) {
                jump();
            }
        }
        
        if (Gdx.input.isKeyJustPressed(62) && onGround) { // 62 = Space
            jump();
        }
    }

    private void jump() {
        velocityY = 15;
        onGround = false;
    }
}

