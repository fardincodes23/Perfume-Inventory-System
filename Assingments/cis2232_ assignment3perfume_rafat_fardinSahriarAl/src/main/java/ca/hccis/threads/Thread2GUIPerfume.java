package ca.hccis.threads;

import ca.hccis.entity.Perfume;
import ca.hccis.services.PerfumeJournalService;

import javax.swing.*;

public class Thread2GUIPerfume implements Runnable {

    private final PerfumeJournalService service = new PerfumeJournalService();

    @Override
    public void run() {
        String option;
        do {
            option = JOptionPane.showInputDialog("--- GUI Perfume Menu ---\nA) Add Perfume\nV) View All\nX) Exit\nChoose option:");
            if (option == null) break; // if user cancels
            switch (option.toUpperCase()) {
                case "A":
                    Perfume perfume = new Perfume();
                    String name = JOptionPane.showInputDialog("Enter customer name:");
                    perfume.setCustomerName(name);

                    String perfumeName = JOptionPane.showInputDialog("Enter perfume name (Dior/Chanel/Gucci):");
                    perfume.setPerfumeName(perfumeName);

                    int size = Integer.parseInt(JOptionPane.showInputDialog("Enter size (1=90ml, 2=120ml):"));
                    perfume.setSize(size);

                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                    perfume.setQuantity(quantity);

                    perfume.calculateTotalPrice();
                    service.addPerfume(perfume);
                    JOptionPane.showMessageDialog(null, "Perfume added successfully!");
                    break;

                case "V":
                    service.viewPerfumesGUI();
                    break;

                case "X":
                    JOptionPane.showMessageDialog(null, "Exiting GUI thread...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option!");
            }
        } while (!option.equalsIgnoreCase("X"));
    }
}
