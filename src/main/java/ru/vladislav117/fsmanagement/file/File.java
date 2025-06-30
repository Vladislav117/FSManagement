package ru.vladislav117.fsmanagement.file;

import org.apache.commons.io.FileUtils;
import ru.vladislav117.fsmanagement.FSObject;

import java.io.IOException;

/**
 * Файл.
 */
public class File extends FSObject {
    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public File(FSObject parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public File(FSObject object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public File(java.io.File parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание файла.
     *
     * @param object Объект
     */
    public File(java.io.File object) {
        super(object);
    }

    /**
     * Создание файла.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public File(String parentPath, String childPath) {
        super(parentPath, childPath);
    }

    /**
     * Создание файла.
     *
     * @param path Путь
     */
    public File(String path) {
        super(path);
    }

    /**
     * Приведение файла к другому типу.
     *
     * @param fileType    Тип файла
     * @param <FileClass> Класс файла
     * @return Файл другого типа.
     */
    public <FileClass extends File> FileClass as(Class<FileClass> fileType) {
        try {
            return fileType.getConstructor(FSObject.class).newInstance(this);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Получение имени файла без расширения.
     *
     * @return Имя файла без расширения.
     */
    public String getNameWithoutExtension() {
        int dotIndex = location.getName().lastIndexOf(".");
        return (dotIndex == -1) ? location.getName() : location.getName().substring(0, dotIndex);
    }

    /**
     * Получение расширения файла.
     *
     * @return Расширение файла.
     */
    public String getExtension() {
        int dotIndex = location.getName().lastIndexOf(".");
        if (dotIndex == location.getName().length() - 1) return "";
        return (dotIndex == -1) ? "" : location.getName().substring(dotIndex + 1);
    }

    /**
     * Создание родительской директории.
     *
     * @return Этот же файл.
     */
    public File createParentDirectory() {
        getParent().create();
        return this;
    }

    @Override
    public File delete() {
        try {
            FileUtils.delete(location);
        } catch (IOException ignored) {
        }
        return this;
    }
}
