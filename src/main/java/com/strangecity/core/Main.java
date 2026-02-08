package com.strangecity.core;

import com.strangecity.engine.Renderer;
import com.strangecity.engine.Camera;
import com.strangecity.entities.Player;
import com.strangecity.input.MouseInput; // Убедись, что папка называется input
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

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
        
        // Освобождаем память и закрываем окно
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1280, 720, "Strange City 3D", 0, 0);
        if (window == 0) throw new RuntimeException("Failed to create the GLFW window");
        
        // --- ЗАГРУЗКА ИКОНКИ ---
        // Путь должен быть относительным корня проекта при запуске
        setIcon(window, "src/main/resources/textures/icon.png"); 

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);

        // Инициализация игры
        camera = new Camera();
        mouseInput = new MouseInput(window);
        player = new Player(camera);
        renderer = new Renderer();
        renderer.init();
    }

    // Метод загрузки иконки (ВНУТРИ КЛАССА)
    private void setIcon(long window, String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            // Загружаем картинку через STB
            ByteBuffer iconData = STBImage.stbi_load(path, w, h, comp, 4);
            if (iconData != null) {
                GLFWImage.Buffer iconBuffer = GLFWImage.malloc(1);
                iconBuffer.position(0);
                iconBuffer.width(w.get(0));
                iconBuffer.height(h.get(0));
                iconBuffer.pixels(iconData);

                glfwSetWindowIcon(window, iconBuffer);
                
                STBImage.stbi_image_free(iconData);
                iconBuffer.free();
            } else {
                // Если картинки нет, просто выводим предупреждение, но не крашим игру
                System.out.println("Warning: Icon not found at " + path);
            }
        }
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            // 1. Ввод
            mouseInput.input(camera);

            // 2. Логика
            player.update(window);

            // 3. Рендеринг
            renderer.render(camera);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    // Вспомогательный метод для очистки колбэков
    private void glfwFreeCallbacks(long window) {
        glfwSetWindowCloseCallback(window, null).free();
        glfwSetKeyCallback(window, null).free();
        glfwSetCursorPosCallback(window, null).free();
        glfwSetMouseButtonCallback(window, null).free();
        glfwSetScrollCallback(window, null).free();
    }

    public static void main(String[] args) {
        new Main().run();
    }

} // <--- ВОТ ЭТА СКОБКА ОЧЕНЬ ВАЖНА, ОНА ЗАКРЫВАЕТ ВЕСЬ КЛАСС
