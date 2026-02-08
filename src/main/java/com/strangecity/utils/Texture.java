package com.strangecity.utils;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture {
    public static ByteBuffer loadImage(String path, IntBuffer width, IntBuffer height, IntBuffer channels) {
        // STBImage загружает пиксели из файла в память
        ByteBuffer image = STBImage.stbi_load(path, width, height, channels, 4);
        if (image == null) {
            System.err.println("Could not load image: " + path);
        }
        return image;
    }
}

