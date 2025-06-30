package ru.vladislav117.fsmanagement.directory;

import org.apache.commons.io.FileUtils;
import ru.vladislav117.fsmanagement.FSObject;
import ru.vladislav117.fsmanagement.file.File;

import java.io.IOException;

/**
 * Директория.
 */
public class Directory extends FSObject {
    /**
     * Создание директории.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public Directory(FSObject parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание директории.
     *
     * @param object Объект
     */
    public Directory(FSObject object) {
        super(object);
    }

    /**
     * Создание директории.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public Directory(File parentObject, String childPath) {
        super(parentObject, childPath);
    }

    /**
     * Создание директории.
     *
     * @param object Объект
     */
    public Directory(File object) {
        super(object);
    }

    /**
     * Создание директории.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public Directory(String parentPath, String childPath) {
        super(parentPath, childPath);
    }

    /**
     * Создание директории.
     *
     * @param path Путь
     */
    public Directory(String path) {
        super(path);
    }

    /**
     * Приведение директории к другому типу.
     *
     * @param directoryType    Тип директории
     * @param <DirectoryClass> Класс директории
     * @return Директория другого типа.
     */
    public <DirectoryClass extends Directory> DirectoryClass as(Class<DirectoryClass> directoryType) {
        try {
            return directoryType.getConstructor(FSObject.class).newInstance(this);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Получение дочерней директории.
     *
     * @param childPath Путь к дочернему объекту
     * @return Дочерняя директория.
     */
    public Directory getChildDirectory(String childPath) {
        return new Directory(this, childPath);
    }

    /**
     * Получение дочерней директории.
     *
     * @param directoryType    Тип директории
     * @param childPath        Путь к дочернему объекту
     * @param <DirectoryClass> Класс директории
     * @return Дочерняя директория.
     */
    public <DirectoryClass extends Directory> DirectoryClass getChildDirectory(Class<DirectoryClass> directoryType, String childPath) {
        return getChildDirectory(childPath).as(directoryType);
    }

    /**
     * Получение дочернего файла.
     *
     * @param childPath Путь к дочернему объекту
     * @return Дочерний файл.
     */
    public File getChildFile(String childPath) {
        return new File(this, childPath);
    }

    /**
     * Получение дочернего файла.
     *
     * @param fileType    Тип файла
     * @param childPath   Путь к дочернему объекту
     * @param <FileClass> Класс файла
     * @return Дочерний файл.
     */
    public <FileClass extends File> FileClass getChildFile(Class<FileClass> fileType, String childPath) {
        return getChildFile(childPath).as(fileType);
    }

    /**
     * Создание директории.
     *
     * @return Эта же директория.
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue"})
    public Directory create() {
        location.mkdirs();
        return this;
    }

    @Override
    public Directory delete() {
        try {
            FileUtils.deleteDirectory(location);
        } catch (IOException ignored) {
        }
        return this;
    }
}
