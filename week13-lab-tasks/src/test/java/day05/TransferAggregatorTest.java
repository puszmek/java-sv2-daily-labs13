package day05;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferAggregatorTest {
    @Test
    void testEverything() throws IOException {
        TransferAggregator transferAggregator = new TransferAggregator();
        List<TransferPerClient> result = transferAggregator.readTransfers(Path.of("src/main/resources/transfers.csv"));
        assertEquals(20, result.size());

        assertEquals(2000, result.stream().map(TransferPerClient::getNumberOfTransactions).reduce(Integer::sum).orElse(-1));

        assertEquals(0, result.stream().map(TransferPerClient::getSum).reduce(Integer::sum).orElse(-1));

        Path out = Path.of("src/main/resources/transfers-sum.txt");
        transferAggregator.writeTransfers(out);

        List<String> content = Files.readAllLines(out);
        assertEquals(20, content.size());

        String[] parts = content.get(10).split("\s+");
        assertEquals(3, parts.length);
        assertEquals("9486c3d9-c6bc-48e6-9494-78e897215372", parts[0]);
        assertEquals("256 805", parts[1].replace('\u00A0', ' '));
        assertEquals("108", parts[2]);
    }
}