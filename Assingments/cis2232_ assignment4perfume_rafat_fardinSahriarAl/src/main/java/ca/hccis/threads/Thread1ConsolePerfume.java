/**
 * About:Represents console based perfume sales process
 *
 *
 * @author fsar
 * @since 20251017
 * **/


package ca.hccis.threads;

import ca.hccis.entity.Perfume;
import ca.hccis.services.PerfumeJournalService;
import ca.hccis.util.CisUtility;


public class Thread1ConsolePerfume implements Runnable {

    private final PerfumeJournalService service ;
    CisUtility cisUtility = new CisUtility();

    public Thread1ConsolePerfume(PerfumeJournalService service) {
        this.service = service;
        cisUtility.setIsGUI(false); // console mode

    }

    @Override
    public void run() {

        String option;
        do {
            option = cisUtility.getInputString("Perfume Shop (Console Mode):\n " +
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
                    service.viewPerfumesConsole();
                    break;
                case "X":
                    System.out.println("Exiting from the Console mode");
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }while(!option.equalsIgnoreCase("X"));

    }
}
