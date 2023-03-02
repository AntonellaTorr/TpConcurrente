package Gomones;

import java.util.concurrent.Semaphore;
public class CamionetaConBolsos {
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


    /// no se usa
    public CamionetaConBolsos(int cantLugares){
        mutex= new Semaphore(1);
        bolsitos= new String[cantLugares];
    }
    
    public int guardarBolso(){
        try {
          mutex.acquire();
           int i=0;
          while(i<bolsitos.length){
           if(bolsitos[i]==null){
               //ese bolso esta libre
               bolsoCliente=i;
               i=bolsitos.length;
           }
          }
        }catch(Exception e){}
        mutex.release();
        return bolsoCliente;
      }
    
    
    
    public void sacarBolso(int numeroBolso){
        try {
            mutex.acquire();
            bolsitos[numeroBolso]=null;
            mutex.release();
        } catch (Exception e) {
        }
    }
    
  
}