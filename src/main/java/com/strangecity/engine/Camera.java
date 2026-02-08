package com.strangecity.engine;

import org.joml.Vector3f;
import org.joml.Matrix4f;

public class Camera {
    public Vector3f position;
    public float pitch; // Наклон вверх-вниз
    public float yaw;   // Поворот влево-вправо

    public Camera(Vector3f position) {
        this.position = position;
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
            .rotateX((float)Math.toRadians(pitch))
            .rotateY((float)Math.toRadians(yaw))
            .translate(-position.x, -position.y, -position.z);
    }
}
