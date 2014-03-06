
import java.nio.file.Files;
import java.nio.file.Paths;

class OIOFileTest {
    public static void main(String[] args) throws Exception {
        byte[] content = Files.readAllBytes(Paths.get("../lipsum.txt"));

        Files.createDirectories(Paths.get("output"));

        long start = System.nanoTime();

        for(int i = 0; i < 5000; i++) {
            Files.write(Paths.get("output/lipsum-" + i + ".txt"), content);
        }

        System.out.println("Total Time: " + (System.nanoTime() - start) / 1000000.0);

    }
}
