package com.strangecity.phys;

import org.joml.Vector3f;

public class AABB {
    public Vector3f min, max; // Нижний и верхний углы куба

    public AABB(Vector3f min, Vector3f max) {
        this.min = min;
        this.max = max;
    }

    // Проверка столкновения с другим кубом
    public boolean intersects(AABB other) {
        return (min.x <= other.max.x && max.x >= other.min.x) &&
               (min.y <= other.max.y && max.y >= other.min.y) &&
               (min.z <= other.max.z && max.z >= other.min.z);
    }
}
