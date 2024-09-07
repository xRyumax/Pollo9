package pgm.logicasp_pgm;

import javax.swing.SwingUtilities;

public class Bebidas  {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BebidasGUI();
            }
        });
    }  
}

