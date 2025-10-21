/**
 * About: This class is responsible to handle writing and reading perfume objects to/from a JSON file.
 *        This class is used by both the console and GUI threads.
 *
 *
 * @author fsar
 * @since 20251017
 * **/


package ca.hccis.services;

import ca.hccis.entity.Perfume;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PerfumeJournalService {

    private static final String PATH = "c:\\CIS2232\\perfume_data_rafat_fardinsahriaral.json";
    private static final AtomicInteger perfumeCount = new AtomicInteger(0);
    private final Gson gson = new Gson();

    public synchronized void addPerfume(Perfume perfume) {
        try (FileWriter writer = new FileWriter(PATH, true)) {
            String json = gson.toJson(perfume);
            writer.write(json + System.lineSeparator());
            perfumeCount.incrementAndGet();
            System.out.println("Perfume saved! Total count: " + perfumeCount.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewPerfumesConsole() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH));
            if (lines.isEmpty()) {
                System.out.println("No perfumes found.");
                return;
            }
            for (String current : lines) {
                Perfume perfume = gson.fromJson(current, Perfume.class);
                System.out.println(perfume.toString());
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public void viewPerfumesGUI() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH));
            if (lines.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No perfumes found.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (String current : lines) {
                Perfume perfume = gson.fromJson(current, Perfume.class);
                sb.append(perfume.toString()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file.");
        }
    }
}
