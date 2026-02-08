package com.strangecity.entities;

import com.strangecity.phys.AABB;
import org.joml.Vector3f;

public class Platform {
    public Vector3f position;
    public Vector3f size; // Ширина, высота, длина
    public AABB hitbox;

    public Platform(Vector3f pos, Vector3f size) {
        this.position = pos;
        this.size = size;
        
        // Создаем границы для физики: от центра во все стороны
        this.hitbox = new AABB(
            new Vector3f(pos.x - size.x/2, pos.y - size.y/2, pos.z - size.z/2),
            new Vector3f(pos.x + size.x/2, pos.y + size.y/2, pos.z + size.z/2)
        );
    }
}

