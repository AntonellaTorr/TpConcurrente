import Gomones.*;
import IngresarParque.*;
import Shop.*;
import java.util.Random;

public class Cliente implements Runnable {
    private Rio rio;
    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;


    public Cliente (Rio r, Entrada e, ColectivoFolklorico c, CentroCompras shop){
        this.rio=r;
        this.e=e;
        this.c=c;
        this.shop=shop;
    }


    public void run (){
        Random r= new Random();

        if (/*(int)r.nextInt(2)==1*/ true){
            System.out.println(Thread.currentThread().getName()+ " va en colectivo");
            c.tomarAsiento();
            c.avisoLlegada();
            this.simula();
            c.bajarse();
            c.avisoLlegada();
        }
        else{
            System.out.println(Thread.currentThread().getName()+ " va por su cuenta ");

        }
        int nroMolinete= e.entrarAlParque();
        if (nroMolinete!=-1){
            e.entro(nroMolinete);
        }
        else{
            System.out.println(" EL PARQUE ESTA CERRADISIMMOOOO");
        }
        /*

        if ((int)r.nextInt(2)==0){
            System.out.println(Thread.currentThread().getName()+ " va a las actividades");

            //hacer un switch segun lo que quier
            this.realizarActividadGomon();





        }
        else{
            System.out.println(Thread.currentThread().getName()+ " eligiendo los souvenirs");
            this.simula();
            shop.irAPagar();
            System.out.println(Thread.currentThread().getName()+ " va a pagar");
            this.simula();
            shop.finalizarPago();
            System.out.println(Thread.currentThread().getName()+ " ya compro y se retira");
        }
    
    }
    */
    }

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    /*
    public void realizarActividadGomon(){
        Random r= new Random();

        if ((int)r.nextInt(2)==0){
            rio.entrarIndividual();
            //se esta tirandoo
            rio.salirIndividual();
          
        }
        else{
            rio.entrarDoble();
            //se esta tirando
            rio.salirDoble();
        }
        rio.dejarBolso();

    }*/


}
