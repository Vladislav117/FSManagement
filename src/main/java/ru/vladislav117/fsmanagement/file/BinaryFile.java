package ru.vladislav117.fsmanagement.file;

import org.apache.commons.io.FileUtils;
import ru.vladislav117.fsmanagement.FSObject;

import java.io.File;
import java.io.IOException;

/**
 * Бинарный файл.
 */
public class BinaryFile extends ContentFile<byte[]> {
    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public BinaryFile(FSObject parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public BinaryFile(FSObject object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public BinaryFile(File parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public BinaryFile(File object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public BinaryFile(String parentPath, String childPath) {
        super(parentPath, childPath);
    }

    /**
     * Создание файла.
     *
     * @param path Путь
     */
    public BinaryFile(String path) {
        super(path);
    }

    @Override
    protected byte[] readUnsafe() throws IOException {
        return FileUtils.readFileToByteArray(location);
    }

    @Override
    protected void writeUnsafe(byte[] content) throws IOException {
        FileUtils.writeByteArrayToFile(location, content);
    }
}
