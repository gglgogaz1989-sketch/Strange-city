package com.strangecity.core;

import com.strangecity.engine.Renderer;
import com.strangecity.engine.Camera;
import com.strangecity.entities.Player;
import com.strangecity.input.MouseInput;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    private long window;
    private Renderer renderer;
    private Camera camera;
    private Player player;
    private MouseInput mouseInput;

    public void run() {
        init();
        loop();
        glfwTerminate();
    }

    private void init() {
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1280, 720, "Strange City 3D", 0, 0);
        
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);

        // Инициализация модулей
        camera = new Camera();
        mouseInput = new MouseInput(window);
        player = new Player(camera);
        renderer = new Renderer();
        renderer.init();
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            // 1. Ввод
            mouseInput.input(camera);

            // 2. Логика (Физика)
            player.update(window);

            // 3. Рендеринг (Рисование)
            renderer.render(camera);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
