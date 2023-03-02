package Gomones;

import java.util.concurrent.Semaphore;
public class CamionetaConBolsos implements Runnable {
    private Semaphore mutex;
    private String [] bolsitos;
    private int bolsoCliente;
    private Rio r;
    
    public void run (){

        while (true){
            r.camionetaBaja();
            this.simular();
            r.camionetaSube();
        }

    }

    public void simular (){
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


  
    
  
}