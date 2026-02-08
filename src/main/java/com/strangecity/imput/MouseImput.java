package com.strangecity.input;

import com.strangecity.engine.Camera;
import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {
    private double lastX, lastY;
    private boolean firstMouse = true;

    public MouseInput(long window) {
        // Захватываем курсор (скрываем его)
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void input(Camera camera) {
        // Получаем позицию мыши
        double[] xpos = new double[1];
        double[] ypos = new double[1];
        // В простой реализации LWJGL так получить позицию сложно без массива,
        // но для сборки пока оставим заглушку, чтобы код компилировался.
        // Поворот камеры добавим, когда подключим полный Input callback.
    }
}
