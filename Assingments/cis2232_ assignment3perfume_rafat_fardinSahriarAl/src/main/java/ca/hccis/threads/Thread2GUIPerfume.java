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
            option = JOptionPane.showInputDialog("--- GUI Perfume Menu ---\n A) Add Perfume\nV) View All\nX) Exit\nChoose option:");
            if (option == null) break; // if user cancels
            switch (option.toUpperCase()) {
                case "A":
                    Perfume perfume = new Perfume();
                    String name = JOptionPane.showInputDialog("Enter customer name:");
                    perfume.setCustomerName(name);

                   try{
                       int perfumeChoice = Integer.parseInt(JOptionPane.showInputDialog("Enter perfume choice (1 = Dior/ 2 = Chanel/ 3 = Gucci):"));
                       perfume.setPerfumeChoice(perfumeChoice);

                       switch (perfumeChoice) {
                           case 1:
                               perfume.setPerfumeName("Dior");
                               break;
                           case 2:
                               perfume.setPerfumeName("Chanel");
                               break;
                           case 3:
                               perfume.setPerfumeName("Gucci");
                               break;

                       }

                       int size = Integer.parseInt(JOptionPane.showInputDialog("Enter size (1=90ml, 2=120ml):"));
                       perfume.setSize(size);
                       if (size == 1) {
                           perfume.setPricePerBottle(Perfume.MIDDLE_SIZE_BOTTLE_RATE);
                       } else {
                           perfume.setPricePerBottle(Perfume.LARGE_SIZE_BOTTLE_RATE);
                       }

                       int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                       perfume.setQuantity(quantity);


                   }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Invalid input!");
                   }

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
