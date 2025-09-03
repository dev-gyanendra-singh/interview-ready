package LLD.TelemetryCollector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

public class TelemetryCollector {

    // Represents a single telemetry data point
    static class TelemetryData {
        String name;
        double value;
        long timestamp;

        TelemetryData(String name, double value, long timestamp) {
            this.name = name;
            this.value = value;
            this.timestamp = timestamp;
        }

        String serialize() {
            return name + "|" + value + "|" + timestamp;
        }
    }

    // Exporter interface for sending telemetry data
    interface Exporter {
        void export(List<TelemetryData> data);
    }

    // Exporter that writes telemetry to console
    class ConsoleExporter implements Exporter {
        public void export(List<TelemetryData> data) {
            data.forEach(d -> System.out.println("Console: " + d.serialize()));
        }
    }

    // Exporter that writes telemetry to a file
    class FileExporter implements Exporter {
        private PrintWriter writer;

        FileExporter(String filename) throws IOException {
            writer = new PrintWriter(new FileWriter(filename, true), true);
        }

        public void export(List<TelemetryData> data) {
            data.forEach(d -> writer.println("File: " + d.serialize()));
        }
    }

    private final BlockingQueue<TelemetryData> queue = new LinkedBlockingQueue<>();
    private final List<Exporter> exporters = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public TelemetryCollector() {
        // Flush data every 5 seconds
        scheduler.scheduleAtFixedRate(this::flush, 5, 5, TimeUnit.SECONDS);
    }

    public void registerExporter(Exporter exporter) {
        exporters.add(exporter);
    }

    public void collect(String name, double value) {
        queue.offer(new TelemetryData(name, value, System.currentTimeMillis()));
    }

    private void flush() {
        List<TelemetryData> buffer = new ArrayList<>();
        queue.drainTo(buffer);
        if (!buffer.isEmpty()) {
            exporters.forEach(e -> {
                try {
                    e.export(buffer);
                } catch (Exception ex) {
                    System.err.println("Export failed: " + ex.getMessage());
                }
            });
        }
    }

    public void shutdown() {
        scheduler.shutdown();
        flush();
    }

    // Demo usage
    public static void main(String[] args) throws Exception {
        TelemetryCollector collector = new TelemetryCollector();
        collector.registerExporter(collector.new ConsoleExporter());
        collector.registerExporter(collector.new FileExporter("telemetry.log"));

        for (int i = 0; i < 10; i++) {
            collector.collect("cpu", Math.random() * 100);
            Thread.sleep(500);
        }

        collector.shutdown();
    }
}

