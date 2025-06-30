import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vladislav117.fsmanagement.directory.Directory;

public final class DirectoryTests {
    @Test
    public void testDirectory() {
        FSManagementTestUtils.checkTestDirectory();
        Directory directory = new Directory("test/directory");

        Assertions.assertFalse(directory.exists());

        Directory childDirectory = directory.getChildDirectory("child0/child1");

        Assertions.assertFalse(childDirectory.exists());

        childDirectory.create();

        Assertions.assertTrue(directory.exists());
        Assertions.assertTrue(childDirectory.exists());

        directory.delete();

        Assertions.assertFalse(directory.exists());
        Assertions.assertFalse(childDirectory.exists());
    }
}
