package com.strangecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera; // Добавили камеру
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strangecity.blocks.Platform;
import com.strangecity.entities.Player;
import com.strangecity.utils.Button;

public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera; // Объект камеры
    private Player player;
    private Platform platform;
    private Button jumpButton;

    @Override
    public void create() {
        batch = new SpriteBatch();
        
        // Создаем камеру и настраиваем её на размер экрана
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); // false = ось Y направлена вверх
        
        player = new Player();
        platform = new Platform();
        jumpButton = new Button("1000013987.png", 50, 50, 150);
    }

    @Override
    public void render() {
        // 1. ЛОГИКА
        player.update();
        if (jumpButton.isPressed()) player.jump();

        // ПРИМЕР ПОВОРОТА: Поворачиваем камеру на 1 градус каждый кадр
        // camera.rotate(1f); 
        
        // Или можно установить фиксированный угол (например, 45 градусов):
        // camera.up.set(0, 1, 0); // Сброс вектора "верх"
        // camera.direction.set(0, 0, -1);
        // camera.rotate(45);

        camera.update(); // Обязательно обновляем матрицу камеры

        // 2. ОТРИСОВКА МИРА (с учетом камеры)
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Привязываем batch к камере
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        platform.draw(batch);
        player.draw(batch);
        batch.end();

        // 3. ОТРИСОВКА ИНТЕРФЕЙСА (кнопка не должна крутиться вместе с миром)
        // Сбрасываем матрицу в стандартное положение (без поворотов)
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.begin();
        jumpButton.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        platform.dispose();
        jumpButton.dispose();
    }
}
