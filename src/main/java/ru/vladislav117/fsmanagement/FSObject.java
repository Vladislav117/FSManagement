package ru.vladislav117.fsmanagement;

import ru.vladislav117.fsmanagement.directory.Directory;

/**
 * Объект файловой системы. Имеет своё расположение.
 */
public abstract class FSObject {
    protected java.io.File location;

    /**
     * Создание объекта файловой системы.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public FSObject(FSObject parentObject, String childPath) {
        location = new java.io.File(parentObject.location, childPath);
    }

    /**
     * Создание объекта файловой системы.
     *
     * @param object Объект
     */
    public FSObject(FSObject object) {
        location = new java.io.File(object.location.toString());
    }

    /**
     * Создание объекта файловой системы.
     *
     * @param parentObject Родительский объект
     * @param childPath    Путь к дочернему объекту
     */
    public FSObject(java.io.File parentObject, String childPath) {
        location = new java.io.File(parentObject, childPath);
    }

    /**
     * Создание объекта файловой системы.
     *
     * @param object Объект
     */
    public FSObject(java.io.File object) {
        location = new java.io.File(object.toString());
    }

    /**
     * Создание объекта файловой системы.
     *
     * @param parentPath Родительский путь
     * @param childPath  Путь к дочернему объекту
     */
    public FSObject(String parentPath, String childPath) {
        location = new java.io.File(parentPath, childPath);
    }


    /**
     * Создание объекта файловой системы.
     *
     * @param path Путь
     */
    public FSObject(String path) {
        location = new java.io.File(path);
    }

    /**
     * Получение оригинального объекта расположения объекта файловой системы.
     *
     * @return Объект расположения объекта файловой системы.
     */
    public java.io.File getLocation() {
        return location;
    }

    /**
     * Получение родительской директории.
     *
     * @return Родительская директория.
     */
    public Directory getParent() {
        String parentPath = location.getParent();
        if (parentPath == null) return new Directory(location.getAbsoluteFile().getParent());
        return new Directory(parentPath);
    }

    /**
     * Получение родительской директории.
     *
     * @param directoryType    Тип директории
     * @param <DirectoryClass> Класс директории
     * @return Родительская директория.
     */
    public <DirectoryClass extends Directory> DirectoryClass getParent(Class<DirectoryClass> directoryType) {
        return getParent().as(directoryType);
    }

    /**
     * Проверка объекта на существование.
     *
     * @return Существование объекта.
     */
    public boolean exists() {
        return location.exists();
    }

    /**
     * Получение имени объекта.
     *
     * @return Имя объекта.
     */
    public String getName() {
        return location.getName();
    }

    /**
     * Удаление объекта.
     * Если объекта не существует, ничего не произойдёт.
     *
     * @return Этот же объект.
     */
    @SuppressWarnings("UnusedReturnValue")
    public abstract FSObject delete();
}
