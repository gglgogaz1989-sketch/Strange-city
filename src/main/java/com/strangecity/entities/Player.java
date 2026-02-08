package com.strangecity.entities;

import com.strangecity.engine.Camera;
import static org.lwjgl.glfw.GLFW.*;

public class Player {
    private Camera camera;
    private float speed = 0.1f;

    public Player(Camera camera) {
        this.camera = camera;
    }

    public void update(long window) {
        // Простое движение WASD
        // В реальной игре здесь нужна математика векторов, чтобы ходить "куда смотришь"
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            camera.position.z -= speed;
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            camera.position.z += speed;
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            camera.position.x -= speed;
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            camera.position.x += speed;
        }
        
        // Простая гравитация (чтобы не провалиться под землю y=0)
        if (camera.position.y > 2.0f) {
            camera.position.y -= 0.05f;
        } else {
            camera.position.y = 2.0f;
        }
    }
}
