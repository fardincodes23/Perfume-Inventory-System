
package ca.hccis;

import ca.hccis.services.PerfumeJournalService;
import ca.hccis.threads.Thread1ConsolePerfume;
import ca.hccis.threads.Thread2GUIPerfume;

/**
 * Controller class to start the perfume sales application in two threads:
 * one for console input and one for GUI (JOptionPane) input.
 *
 * @author Fardin Sahriar Al Rafat
 * @since 20251019
 */
public class Controller {

    public static void main(String[] args) {

        System.out.println("Starting Perfume Sales Application...");

        PerfumeJournalService sharedService = new PerfumeJournalService();

        // Create two threads: one console, one GUI
        Thread threadConsole = new Thread(new Thread1ConsolePerfume(sharedService));
        Thread threadGUI = new Thread(new Thread2GUIPerfume(sharedService));

        // Start both threads
        threadConsole.start();
        threadGUI.start();

         //Wait for both to finish before ending the program
        try {
            threadConsole.join();
            threadGUI.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nBoth threads finished. Goodbye!");
    }
}
