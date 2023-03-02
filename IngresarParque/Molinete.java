package IngresarParque;
import java.util.concurrent.Semaphore;

public class Molinete {
    private Semaphore siendoUsado;

    public Molinete (){
        siendoUsado= new Semaphore(1);
    }

    public boolean molineteLibre (){
        return siendoUsado.tryAcquire();
    }
    public void librerarMolinete(){
        siendoUsado.release();
    }
    
    public void esperarPorMolinete(){
        try {
            siendoUsado.acquire();
        } catch (Exception e) {
        }
    }
}
