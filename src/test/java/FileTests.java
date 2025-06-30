import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vladislav117.fsmanagement.file.BinaryFile;
import ru.vladislav117.fsmanagement.file.File;
import ru.vladislav117.fsmanagement.file.TextFile;

public final class FileTests {
    @Test
    public void testBinaryFile() {
        FSManagementTestUtils.checkTestDirectory();
        BinaryFile file = new BinaryFile("test/binary.bin");

        Assertions.assertFalse(file.exists());

        file.writeIfNotExists(new byte[]{1, 2, 3});

        Assertions.assertTrue(file.exists());

        byte[] content = file.read();

        Assertions.assertArrayEquals(new byte[]{1, 2, 3}, content);

        file.delete();

        Assertions.assertFalse(file.exists());
    }

    @Test
    public void testTextFile() {
        FSManagementTestUtils.checkTestDirectory();
        TextFile file = new TextFile("test/text.txt");

        Assertions.assertFalse(file.exists());

        file.writeIfNotExists("Hello, world!");

        Assertions.assertTrue(file.exists());

        String content = file.read();

        Assertions.assertEquals("Hello, world!", content);

        file.delete();

        Assertions.assertFalse(file.exists());
    }
}
