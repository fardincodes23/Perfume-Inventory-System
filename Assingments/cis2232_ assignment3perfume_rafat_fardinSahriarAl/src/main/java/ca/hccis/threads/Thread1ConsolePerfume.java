package ca.hccis.threads;

import ca.hccis.entity.Perfume;
import ca.hccis.services.PerfumeJournalService;
import ca.hccis.util.CisUtility;

public class Thread1ConsolePerfume implements Runnable {

    private final PerfumeJournalService service = new PerfumeJournalService();

    @Override
    public void run() {
        String option;
        do {
            option = CisUtility.getInputString("\n--- Console Perfume Menu ---\nA) Add Perfume\nV) View All\nX) Exit\nChoose option: ");
            switch (option.toUpperCase()) {
                case "A":
                    Perfume perfume = new Perfume();
                    perfume.getInformation(); // uses console input
                    service.addPerfume(perfume);
                    break;
                case "V":
                    service.viewPerfumesConsole();
                    break;
                case "X":
                    System.out.println("Exiting console thread...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (!option.equalsIgnoreCase("X"));
    }
}
