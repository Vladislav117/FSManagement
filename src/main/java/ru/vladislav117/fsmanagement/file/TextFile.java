package ru.vladislav117.fsmanagement.file;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.fsmanagement.FSObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Текстовый файл.
 */
public class TextFile extends ContentFile<String> {
    protected Charset charset = StandardCharsets.UTF_8;

    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public TextFile(FSObject parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public TextFile(FSObject object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public TextFile(File parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public TextFile(File object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public TextFile(String parentPath, String childPath) {
        super(parentPath, childPath);
    }

    /**
     * Создание файла.
     *
     * @param path Путь
     */
    public TextFile(String path) {
        super(path);
    }

    /**
     * Получение кодировки файла.
     *
     * @return Кодировка файла.
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * Установка кодировки файла.
     *
     * @param charset Кодировка
     * @return Этот же файл.
     */
    public TextFile setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    @Override
    protected String readUnsafe() throws IOException {
        return FileUtils.readFileToString(location, charset);
    }

    @Override
    protected void writeUnsafe(String content) throws IOException {
        FileUtils.writeStringToFile(location, content, charset);
    }
}
