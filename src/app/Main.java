package app;

import controler.triadesController;
import javax.swing.JFrame;
import javax.swing.JLabel;
import visual.visu;

public class Main {

    public static void main(String[] args) {
        
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                exibirTe();
            }
        });
         
        new triadesController().criador();

    }
    
    public static void exibirTe() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new visu().setVisible(true);
            }
        });
    }

}
