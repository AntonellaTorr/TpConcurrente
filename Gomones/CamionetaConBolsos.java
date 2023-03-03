package Gomones;

public class CamionetaConBolsos implements Runnable {

    private Rio r;

    public CamionetaConBolsos (Rio r){
        this.r=r;
    }
    
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