package ru.vladislav117.fsmanagement.file;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.fsmanagement.FSObject;

import java.io.IOException;

/**
 * Файл, содержащий некоторый контент, который может быть прочитан или записан.
 *
 * @param <Content> Тип контента
 */
public abstract class ContentFile<Content> extends File {
    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public ContentFile(FSObject parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public ContentFile(FSObject object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public ContentFile(java.io.File parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public ContentFile(java.io.File object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public ContentFile(String parentPath, String childPath) {
        super(parentPath, childPath);
    }

    /**
     * Создание файла.
     *
     * @param path Путь
     */
    public ContentFile(String path) {
        super(path);
    }

    /**
     * Чтение из файла. Может произойти ошибка ввода-вывода.
     *
     * @return Прочитанная информация.
     */
    protected abstract Content readUnsafe() throws IOException;

    /**
     * Чтение из файла. Может произойти ошибка ввода-вывода.
     *
     * @return Прочитанная информация.
     */
    public Content read() {
        try {
            return readUnsafe();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Чтение из файла. Если произойдёт ошибка ввода-вывода, будет возвращено null.
     *
     * @return Прочитанная информация или null.
     */
    public @Nullable Content readOrNull() {
        try {
            return read();
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Чтение из файла. Если произойдёт ошибка ввода-вывода, будет возвращено значение по умолчанию.
     *
     * @param defaultValue Значение по умолчанию
     * @return Прочитанная информация или значение по умолчанию.
     */
    public Content readOrDefault(Content defaultValue) {
        try {
            return read();
        } catch (Exception exception) {
            return defaultValue;
        }
    }

    /**
     * Запись в файл. Может произойти ошибка ввода-вывода.
     *
     * @param content Контент, который будет записан
     */
    protected abstract void writeUnsafe(Content content) throws IOException;

    /**
     * Запись в файл.
     *
     * @param content Контент, который будет записан
     * @return Этот же файл.
     */
    @SuppressWarnings("UnusedReturnValue")
    public ContentFile<Content> write(Content content) {
        try {
            writeUnsafe(content);
        } catch (IOException ignored) {
        }
        return this;
    }

    /**
     * Запись в файл, если такового не существует.
     *
     * @param content Контент, который будет записан
     * @return Этот же файл.
     */
    public ContentFile<Content> writeIfNotExists(Content content) {
        if (!exists()) write(content);
        return this;
    }
}
