package IngresarParque;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hora {
    private Entrada entrada;

    public Hora(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public void run(){
       while(true){
           try {
               Thread.sleep(2000);
               entrada.pasarHora();
           } catch (InterruptedException ex) {
               Logger.getLogger(Hora.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
}
