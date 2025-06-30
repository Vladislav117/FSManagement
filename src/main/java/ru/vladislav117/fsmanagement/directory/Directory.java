package ru.vladislav117.fsmanagement.directory;

import org.apache.commons.io.FileUtils;
import ru.vladislav117.fsmanagement.FSObject;
import ru.vladislav117.fsmanagement.file.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

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
        return asDirectory(directoryType);
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

    /**
     * Получение объектов файловой системы в директории, исключая поддиректории.
     *
     * @param root Корень, в котором будет произведён поиск
     * @return Найденные объекты файловой системы.
     */
    protected ArrayList<FSObject> entryFSObjectsAtTopLevel(FSObject root) {
        java.io.File[] files = root.getLocation().listFiles();
        if (files == null) return new ArrayList<>();
        ArrayList<FSObject> directoryEntries = new ArrayList<>();
        for (java.io.File file : files)
            directoryEntries.add(new FSObject(file) {
                @Override
                public FSObject delete() {
                    return this;
                }
            });
        return directoryEntries;
    }

    /**
     * Получение объектов файловой системы внутри директории, исключая поиск в поддиректориях.
     *
     * @param filter Фильтр объектов файловой системы
     * @return Найденные объекты файловой системы.
     */
    public ArrayList<FSObject> getEntryFSObjectsAtTopLevel(Predicate<FSObject> filter) {
        ArrayList<FSObject> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsAtTopLevel(this);
        for (FSObject fsObject : allFsObjects) {
            if (filter.test(fsObject)) fsObjects.add(fsObject);
        }
        return fsObjects;
    }

    /**
     * Получение объектов файловой системы внутри директории, исключая поиск в поддиректориях.
     *
     * @return Найденные объекты файловой системы.
     */
    public ArrayList<FSObject> getEntryFSObjectsAtTopLevel() {
        return entryFSObjectsAtTopLevel(this);
    }

    /**
     * Получение файлов внутри директории, исключая поиск в поддиректориях.
     *
     * @param filter Фильтр файлов
     * @return Найденные файлы.
     */
    public ArrayList<File> getEntryFilesAtTopLevel(Predicate<File> filter) {
        ArrayList<File> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsAtTopLevel(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isFile() && filter.test(fsObject.asFile())) fsObjects.add(fsObject.asFile());
        }
        return fsObjects;
    }

    /**
     * Получение файлов внутри директории, исключая поиск в поддиректориях.
     *
     * @return Найденные файлы.
     */
    public ArrayList<File> getEntryFilesAtTopLevel() {
        ArrayList<File> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsAtTopLevel(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isFile()) fsObjects.add(fsObject.asFile());
        }
        return fsObjects;
    }

    /**
     * Получение директорий внутри директории, исключая поиск в поддиректориях.
     *
     * @param filter Фильтр директорий
     * @return Найденные директории.
     */
    public ArrayList<Directory> getEntryDirectoriesAtTopLevel(Predicate<Directory> filter) {
        ArrayList<Directory> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsAtTopLevel(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isDirectory() && filter.test(fsObject.asDirectory())) fsObjects.add(fsObject.asDirectory());
        }
        return fsObjects;
    }

    /**
     * Получение директорий внутри директории, исключая поиск в поддиректориях.
     *
     * @return Найденные директории.
     */
    public ArrayList<Directory> getEntryDirectoriesAtTopLevel() {
        ArrayList<Directory> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsAtTopLevel(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isDirectory()) fsObjects.add(fsObject.asDirectory());
        }
        return fsObjects;
    }

    /**
     * Получение объектов файловой системы в директории, включая все поддиректории.
     * Директории не будут добавлены в результаты поиска.
     *
     * @param root Корень, в котором будет произведён поиск
     * @return Найденные объекты файловой системы.
     */
    static ArrayList<FSObject> entryFSObjectsRecursively(FSObject root) {
        java.io.File[] files = root.getLocation().listFiles();
        if (files == null) return new ArrayList<>();
        ArrayList<FSObject> fsObjects = new ArrayList<>();
        for (java.io.File file : files) {
            FSObject fsObject = new FSObject(file) {
                @Override
                public FSObject delete() {
                    return null;
                }
            };
            fsObjects.add(fsObject);
            if (fsObject.isDirectory()) fsObjects.addAll(entryFSObjectsRecursively(fsObject));
        }
        return fsObjects;
    }

    /**
     * Получение объектов файловой системы внутри директории, включая поиск в поддиректориях.
     *
     * @param filter Фильтр объектов файловой системы
     * @return Найденные объекты файловой системы.
     */
    public ArrayList<FSObject> getEntryFSObjects(Predicate<FSObject> filter) {
        ArrayList<FSObject> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsRecursively(this);
        for (FSObject fsObject : allFsObjects) {
            if (filter.test(fsObject)) fsObjects.add(fsObject);
        }
        return fsObjects;
    }

    /**
     * Получение объектов файловой системы внутри директории, включая поиск в поддиректориях.
     *
     * @return Найденные объекты файловой системы.
     */
    public ArrayList<FSObject> getEntryFSObjects() {
        return entryFSObjectsRecursively(this);
    }

    /**
     * Получение файлов внутри директории, включая поиск в поддиректориях.
     *
     * @param filter Фильтр файлов
     * @return Найденные файлы.
     */
    public ArrayList<File> getEntryFiles(Predicate<File> filter) {
        ArrayList<File> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsRecursively(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isFile() && filter.test(fsObject.asFile())) fsObjects.add(fsObject.asFile());
        }
        return fsObjects;
    }

    /**
     * Получение файлов внутри директории, включая поиск в поддиректориях.
     *
     * @return Найденные файлы.
     */
    public ArrayList<File> getEntryFiles() {
        ArrayList<File> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsRecursively(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isFile()) fsObjects.add(fsObject.asFile());
        }
        return fsObjects;
    }

    /**
     * Получение директорий внутри директории, включая поиск в поддиректориях.
     *
     * @param filter Фильтр директорий
     * @return Найденные директории.
     */
    public ArrayList<Directory> getEntryDirectories(Predicate<Directory> filter) {
        ArrayList<Directory> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsRecursively(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isDirectory() && filter.test(fsObject.asDirectory())) fsObjects.add(fsObject.asDirectory());
        }
        return fsObjects;
    }

    /**
     * Получение директорий внутри директории, включая поиск в поддиректориях.
     *
     * @return Найденные директории.
     */
    public ArrayList<Directory> getEntryDirectories() {
        ArrayList<Directory> fsObjects = new ArrayList<>();
        ArrayList<FSObject> allFsObjects = entryFSObjectsRecursively(this);
        for (FSObject fsObject : allFsObjects) {
            if (fsObject.isDirectory()) fsObjects.add(fsObject.asDirectory());
        }
        return fsObjects;
    }
}
