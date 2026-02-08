package com.strangecity.engine;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    
    public void init() {
        glEnable(GL_DEPTH_TEST); // Включаем глубину (чтобы стены не просвечивали)
    }

    public void render(Camera camera) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.1f, 0.1f, 0.2f, 1.0f); // Цвет неба (Темно-синий)

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // Создаем перспективу (угол обзора 70 градусов)
        float aspect = 1280.0f / 720.0f;
        gluPerspective(70.0f, aspect, 0.1f, 1000.0f);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
        // Поворачиваем мир вокруг игрока (имитация камеры)
        glRotatef(camera.pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(camera.yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(-camera.position.x, -camera.position.y, -camera.position.z);

        // Рисуем землю (Платформу)
        glBegin(GL_QUADS);
        glColor3f(0.5f, 0.5f, 0.5f); // Серый цвет
        glVertex3f(-10, 0, -10);
        glVertex3f(-10, 0, 10);
        glVertex3f(10, 0, 10);
        glVertex3f(10, 0, -10);
        glEnd();
        
        // Тут можно добавить код для отрисовки курсора (HUD)
    }
    
    // Вспомогательный метод для перспективы (так как gluPerspective устарел, пишем аналог)
    private void gluPerspective(float fovy, float aspect, float zNear, float zFar) {
        float f = 1.0f / (float)Math.tan(Math.toRadians(fovy) / 2.0f);
        float m00 = f / aspect;
        float m11 = f;
        float m22 = (zFar + zNear) / (zNear - zFar);
        float m23 = -1.0f;
        float m32 = (2.0f * zFar * zNear) / (zNear - zFar);
        
        glMultMatrixf(new float[]{
            m00, 0, 0, 0,
            0, m11, 0, 0,
            0, 0, m22, -1,
            0, 0, m32, 0
        });
    }
}
