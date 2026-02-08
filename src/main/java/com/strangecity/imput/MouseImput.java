package com.strangecity.input;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {
    private double lastX, lastY;
    private double displVecX, displVecY; // На сколько сдвинулась мышь

    public void init(long window) {
        // Скрываем курсор, чтобы он не вылетал за края окна
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void input(long window) {
        // Тут будет логика расчета дельты движения
    }
    
    public double getDisplVecX() { return displVecX; }
    public double getDisplVecY() { return displVecY; }
}
