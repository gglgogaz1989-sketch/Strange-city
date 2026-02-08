package com.strangecity.engine;

import org.joml.Vector3f;

public class Camera {
    public Vector3f position;
    public float pitch; // Вверх-вниз
    public float yaw;   // Влево-вправо

    public Camera() {
        // Стартовая позиция игрока (чуть выше пола)
        position = new Vector3f(0, 2.0f, 0); 
    }
}
