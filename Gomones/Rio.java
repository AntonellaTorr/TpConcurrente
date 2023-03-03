package Gomones;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rio {
    // recurso compartido
    private int cantTotalGomonesInd, cantTotalGomonesDobles, cantGomonesQueSePuedenTirar, cantGomonesQueSeTiraron;
    private Semaphore gomonesIndividuales, gomonesDobles, puedeSalir, puedeSalirCamioneta, puedenTirarse, mutex,
            espaciosCamioneta, camionetaVacia;
    CyclicBarrier barrera;


    public Rio(int cantGomonesInd, int cantGomonesDobles, int cantGomonesQueSePuedenTirar) {
        this.cantTotalGomonesInd = cantGomonesInd;
        this.cantTotalGomonesDobles = cantGomonesDobles;
        this.cantGomonesQueSePuedenTirar = cantGomonesQueSePuedenTirar;
        this.cantGomonesQueSeTiraron = 0;

        gomonesIndividuales = new Semaphore(this.cantTotalGomonesInd);
        gomonesDobles = new Semaphore(this.cantTotalGomonesDobles);
        puedenTirarse = new Semaphore(this.cantGomonesQueSePuedenTirar);
        mutex = new Semaphore(1);

        barrera = new CyclicBarrier(cantGomonesQueSePuedenTirar);

        // parte camioneta

       
        espaciosCamioneta = new Semaphore(cantGomonesQueSePuedenTirar);
        camionetaVacia = new Semaphore(0);
        puedeSalir = new Semaphore(0);
        puedeSalirCamioneta = new Semaphore(0);

    }

    public void entrarIndividual() {
        try {
            // agarra un gomon individual, sino se queda bloqueado
            System.out.println(Thread.currentThread().getName() +" intenta ingresar individual");
            gomonesIndividuales.acquire();
            
            // se fija si todavia hay espacio para tirarse
            puedenTirarse.acquire();
            System.out.println(Thread.currentThread().getName() +" ENTRA SOLO");
            // espera a que el resto de gomones esten listos
            barrera.await();
            System.out.println(Thread.currentThread().getName() +" SE TIRAN LOS INDIVIDUALES");

            // avisa que se tiro
            mutex.acquire();
            cantGomonesQueSeTiraron++;
            puedeSalirCamioneta.release();

            // si soy el ultimo hilo que se podia tirar, reseteo la barrera
            if (cantGomonesQueSeTiraron == this.cantGomonesQueSePuedenTirar) {
                barrera.reset();
                puedenTirarse.release(this.cantGomonesQueSePuedenTirar);
                System.out.println(Thread.currentThread().getName() +" SE RESETEA LA BARRERA");
            }
          

            mutex.release();

        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void entrarDoble() {
        try {
            // agarra un gomon individual, sino se queda bloqueado
            System.out.println(Thread.currentThread().getName() +" intenta ingresar doble");
            gomonesDobles.acquire();


            // se fija si todavia hay espacio para tirarse
            puedenTirarse.acquire();
            System.out.println(Thread.currentThread().getName() +" ENTRA DOBLE");

            // espera que el resto de gomones esten listos
            
            
            barrera.await();
            System.out.println(Thread.currentThread().getName() +" SE TIRAN LOS DOBLES");

            // avisa que se tiro
            mutex.acquire();
            cantGomonesQueSeTiraron++;
            puedeSalirCamioneta.release();

            // si soy el ultimo hilo que se podia tirar, reseteo la barrera
            if (cantGomonesQueSeTiraron == this.cantGomonesQueSePuedenTirar) {
                barrera.reset();
                puedenTirarse.release(this.cantGomonesQueSePuedenTirar);
                System.out.println(" SE RESETEA LA BARRERA");
            }
          
            mutex.release();

        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarBolso() {
        //ocupa un espacio en la camioneta
        try {
            System.out.println(Thread.currentThread().getName() + " intenta dejar el bolso");
            espaciosCamioneta.acquire();
           
        } catch (Exception e) {}
    }

    public void retirarBolso() {
        //avisa que hay espacio
        camionetaVacia.release();
        System.out.println(Thread.currentThread().getName() + " RECUPERA SU BOLSO");
    }

    public void camionetaBaja() {

        try {
            //una vez que todos se tiraron la camioneta baja
            puedeSalirCamioneta.acquire(cantGomonesQueSePuedenTirar);
            System.out.println( "CAMIONETA BAJA");
        } catch (Exception e) {
        }
    }

    public void camionetaSube() {
        try {
            //una vez que todos tomaron sus pertenencias la camioneta sube 
            camionetaVacia.acquire(cantGomonesQueSePuedenTirar);
            System.out.println( "CAMIONTA VUELVE");
            espaciosCamioneta.release(cantGomonesQueSePuedenTirar);

        } catch (Exception e) {
        }

    }

    public void salirDoble() {
        gomonesDobles.release();
        System.out.println(Thread.currentThread().getName() +" SALE DOBLE" + " CANTIDAD PERMISOS GOMONES dobles" + gomonesDobles.availablePermits()) ;
    }

    public void salirIndividual() {
        gomonesIndividuales.release();
        System.out.println(Thread.currentThread().getName() +" SALE SOLO" + " CANTIDAD PERMISOS GOMONES simples" + gomonesIndividuales .availablePermits());
    }

}