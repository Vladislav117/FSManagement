import ru.vladislav117.fsmanagement.directory.Directory;

public final class FSManagementTestUtils {
    public static void checkTestDirectory() {
        Directory directory = new Directory("test");
        directory.create();
    }
}
