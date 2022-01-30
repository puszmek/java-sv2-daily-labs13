package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TransferAggregator {
    private List<TransferPerClient> transfers;

    public List<TransferPerClient> readTransfers(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            Map<String, TransferPerClient> data = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String sourceId = parts[0];
                String targetId = parts[1];
                int amount = Integer.parseInt(parts[2]);
                TransferPerClient source = data.computeIfAbsent(sourceId, TransferPerClient::new);
                TransferPerClient target = data.computeIfAbsent(targetId, TransferPerClient::new);
                source.decrease(amount);
                target.increase(amount);
            }
            transfers = new ArrayList<>(data.values());
            transfers.sort(Comparator.comparing(TransferPerClient::getClientId));
            return transfers;
        } catch (IOException e) {
            throw new IllegalStateException("File not found", e);
        }
    }

    public void writeTransfers(Path path) {
        try {
            List<String> transfersSum = new ArrayList<>();
            for (TransferPerClient transfer : transfers)
                transfersSum.add("%s %,12d %5d".formatted(transfer.getClientId(), transfer.getSum(), transfer.getNumberOfTransactions()));
            Files.write(path, transfersSum);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write file", e);
        }
    }
}
