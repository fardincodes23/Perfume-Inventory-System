/**
 * About:Represents GUI based perfume sales process.
 *
 *
 * @author fsar
 * @since 20251017
 * **/


package ca.hccis.threads;

import ca.hccis.entity.Perfume;
import ca.hccis.services.PerfumeJournalService;
import ca.hccis.util.CisUtility;

import javax.swing.*;



public class Thread2GUIPerfume implements Runnable {

    private final PerfumeJournalService service;
    CisUtility cisUtility = new CisUtility();

    public Thread2GUIPerfume(PerfumeJournalService service) {
        this.service = service;
        cisUtility.setIsGUI(true);
    }

    @Override
    public void run() {

        String option;
        do {
            option = cisUtility.getInputString("Perfume Shop (Console Mod4):\n " +
                    "\n A) Add Perfume" +
                    "\n V) View All Perfumes" +
                    "\n X) Exit" +
                    "\n Coose an option: ");
            switch (option.toUpperCase()) {
                case "A":
                    Perfume perfume = new Perfume();
                    perfume.getInformation(cisUtility);
                    service.addPerfume(perfume);
                    break;

                case "V":
                    service.viewPerfumesGUI();
                    break;
                case "X":
                    cisUtility.display("Exiting from the GUI mode");
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }while(!option.equalsIgnoreCase("x"));

    }
}
