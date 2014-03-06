
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.ByteBuffer;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.Future;

class AIOFileTest {
    public static void main(String[] args) throws Exception {
        byte[] content = Files.readAllBytes(Paths.get("../lipsum.txt"));

        Files.createDirectories(Paths.get("output"));

        long start = System.nanoTime();

        ArrayList<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for(int i = 0; i < 5000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(content);
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("output/lipsum-" + i + ".txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            Future<Integer> future = channel.write(byteBuffer, 0);
            list.add(future);
        }

        for(int i = 0; i < 5000; i++) {
            Future<Integer> f = list.get(i);
            while(!f.isDone());
            Integer g = f.get();
        }

        System.out.println("Total Time: " + (System.nanoTime() - start) / 1000000.0);
    }
}
