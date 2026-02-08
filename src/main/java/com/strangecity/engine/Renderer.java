package com.strangecity.engine;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    public void init() {
        // Настройка параметров OpenGL (глубина, туман и т.д.)
        glEnable(GL_DEPTH_TEST); 
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.1f, 0.1f, 0.2f, 1.0f); // Тёмно-синее небо города
    }
}
