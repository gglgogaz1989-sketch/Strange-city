package com.strangecity.core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import com.strangecity.engine.Renderer; // Импортируем наш рендерер
import com.strangecity.input.MouseInput; // Импортируем управление

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    private long window;
    private Renderer renderer;
    private MouseInput mouseInput;

    public void run() {
        init();
        loop();

        // Освобождаем память при выходе
        glfwTerminate();
    }

    private void init() {
        if (!glfwInit()) throw new IllegalStateException("Не удалось запустить GLFW");

        // Создаем окно
        window = glfwCreateWindow(1280, 720, "Strange City - First Person", 0, 0);
        if (window == 0) throw new RuntimeException("Ошибка создания окна");

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Инициализируем наши модули из других папок
        renderer = new Renderer();
        renderer.init();

        mouseInput = new MouseInput();
        mouseInput.init(window);
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            // 1. Ввод (Клавиатура/Мышь)
            mouseInput.input(window);

            // 2. Обновление логики (Strange City оживает здесь)

            // 3. Рисование
            renderer.clear();
            
            // В будущем здесь будет вызов renderer.render(scene);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
