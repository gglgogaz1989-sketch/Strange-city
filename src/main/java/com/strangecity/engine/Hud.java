package com.strangecity.engine;

import static org.lwjgl.opengl.GL11.*;

public class Hud {
    public void drawCursor() {
        // Переключаемся в режим 2D рисования
        glDisable(GL_DEPTH_TEST);
        
        // Тут должен быть код отрисовки твоего изображения cursor.png
        // Для начала просто нарисуем белую точку в центре, чтобы проверить
        glPointSize(5.0f);
        glBegin(GL_POINTS);
            glVertex2f(0, 0); // Центр экрана в нормализованных координатах
        glEnd();
        
        glEnable(GL_DEPTH_TEST);
    }
}

