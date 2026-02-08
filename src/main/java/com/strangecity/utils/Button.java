package com.strangecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture jumpBtnTex;
    Texture cursorTex;
    
    // Переменные игрока
    Vector2 playerPos = new Vector2(200, 100);
    float velocityY = 0;
    float gravity = -0.8f; // Сила притяжения
    float jumpForce = 18f; // Сила прыжка
    boolean isGrounded = true;

    // Кнопка прыжка
    Rectangle jumpButtonBounds;
    float btnSize = 180f; // Размер кнопки

    @Override
    public void create() {
        batch = new SpriteBatch();
        
        // Загружаем твои арты (файлы должны лежать в android/assets/)
        jumpBtnTex = new Texture("1000013987.png"); // Твоя голубая стрелка
        cursorTex = new Texture("cursor.png");     // Твой прицел

        // Устанавливаем кнопку в НИЖНИЙ ЛЕВЫЙ угол (отступ 50 пикселей)
        jumpButtonBounds = new Rectangle(50, 50, btnSize, btnSize);
    }

    @Override
    public void render() {
        update(); // Обновляем логику

        // Очистка экрана (темно-синий цвет города)
        Gdx.gl.glClearColor(0.05f, 0.05f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        
        // Рисуем кнопку прыжка (внизу слева)
        batch.draw(jumpBtnTex, jumpButtonBounds.x, jumpButtonBounds.y, jumpButtonBounds.width, jumpButtonBounds.height);
        
        // Рисуем прицел (курсор) по центру экрана
        batch.draw(cursorTex, 
                   Gdx.graphics.getWidth() / 2 - 32, 
                   Gdx.graphics.getHeight() / 2 - 32, 
                   64, 64);
        
        batch.end();
    }

    private void update() {
        // 1. Применяем гравитацию
        velocityY += gravity;
        playerPos.y += velocityY;

        // 2. Проверка приземления (на воображаемый пол y=100)
        if (playerPos.y <= 100) {
            playerPos.y = 100;
            velocityY = 0;
            isGrounded = true;
        }

        // 3. Обработка нажатий (Touch для телефона и Пробел для ПК)
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            // Инвертируем Y, так как у тачскрина 0 вверху, а у LibGDX 0 внизу
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (jumpButtonBounds.contains(touchX, touchY) && isGrounded) {
                jump();
            }
        }
        
        // Прыжок на пробел (для ПК версии)
        if (Gdx.input.isKeyJustPressed(62) && isGrounded) {
            jump();
        }
    }

    private void jump() {
        velocityY = jumpForce;
        isGrounded = false;
    }

    @Override
    public void dispose() {
        batch.dispose();
        jumpBtnTex.dispose();
        cursorTex.dispose();
    }
              }

