package com.strangecity.core;

import com.strangecity.engine.Renderer;
import com.strangecity.engine.Camera;
import com.strangecity.entities.Player;
import com.strangecity.input.MouseInput;
// Не забудь добавить новые импорты в самом верху файла!
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

// ... внутри класса Main ...

    private void init() {
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1280, 720, "Strange City 3D", 0, 0);
        
        // --- ВСТАВЛЯЕМ ЗАГРУЗКУ ИКОНКИ ---
        setIcon(window, "src/main/resources/textures/icon.png"); 
        // ---------------------------------

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);

        camera = new Camera();
        mouseInput = new MouseInput(window);
        player = new Player(camera);
        renderer = new Renderer();
        renderer.init();
    }

    // Новый метод для установки иконки
    private void setIcon(long window, String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            // Загружаем картинку
            ByteBuffer iconData = STBImage.stbi_load(path, w, h, comp, 4);
            if (iconData != null) {
                GLFWImage.Buffer iconBuffer = GLFWImage.malloc(1);
                iconBuffer.position(0);
                iconBuffer.width(w.get(0));
                iconBuffer.height(h.get(0));
                iconBuffer.pixels(iconData);

                glfwSetWindowIcon(window, iconBuffer);
                
                STBImage.stbi_image_free(iconData); // Чистим память
                iconBuffer.free();
            } else {
                System.err.println("Не удалось загрузить иконку: " + path);
            }
        }
    }
