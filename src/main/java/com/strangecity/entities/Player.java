package com.strangecity.entities;

import org.joml.Vector3f;

public class Player {
    public Vector3f position;
    public Vector3f velocity; // Вектор скорости
    
    private final float GRAVITY = -0.005f; // Сила притяжения
    private final float JUMP_FORCE = 0.15f;
    private boolean onGround = false;

    public Player(Vector3f startPos) {
        this.position = startPos;
        this.velocity = new Vector3f(0, 0, 0);
    }

    public void update() {
        // 1. Применяем гравитацию, если мы не на земле
        if (!onGround) {
            velocity.y += GRAVITY;
        }

        // 2. Движение (упрощенно)
        position.add(velocity);

        // 3. Фейковая проверка пола (пока нет коллизии с объектами)
        if (position.y < 0) { 
            position.y = 0;
            velocity.y = 0;
            onGround = true;
        } else {
            onGround = false;
        }
    }

    public void jump() {
        if (onGround) {
            velocity.y = JUMP_FORCE;
            onGround = false;
        }
    }
}
