package com.strangecity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strangecity.blocks.Platform;
import com.strangecity.entities.Player;
import com.strangecity.utils.Button;

public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Player player;
    private Platform platform;
    private Button jumpButton;
    private Texture cursorTex;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        player = new Player();
        platform = new Platform();
        jumpButton = new Button("1000013987.png", 50, 50, 150);

        // Создаем курсор (белый пиксель)
        Pixmap p = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
        p.setColor(1, 1, 1, 1);
        p.fill();
        cursorTex = new Texture(p);
        p.dispose();
    }

    @Override
    public void render() {
        if (jumpButton.isPressed()) player.jump();
        player.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        platform.draw(batch);
        player.draw(batch);
        batch.end();

        // Отрисовка интерфейса (кнопка и курсор)
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.begin();
        jumpButton.draw(batch);
        // Рисуем курсор в позиции касания/мыши
        batch.draw(cursorTex, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        platform.dispose();
        jumpButton.dispose();
        cursorTex.dispose();
    }
}
