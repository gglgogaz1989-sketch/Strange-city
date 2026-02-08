public void input(long window, Camera camera) {
    // В 2026 году мы используем дельту перемещения
    double xpos, ypos;
    // ... получаем координаты курсора через glfwGetCursorPos ...
    
    float mouseSensitivity = 0.15f;
    camera.yaw += (xpos - lastX) * mouseSensitivity;
    camera.pitch += (ypos - lastY) * mouseSensitivity;
    
    // Ограничение, чтобы не "сломать шею" (не смотреть за спину через верх)
    if (camera.pitch > 89.0f) camera.pitch = 89.0f;
    if (camera.pitch < -89.0f) camera.pitch = -89.0f;
}
