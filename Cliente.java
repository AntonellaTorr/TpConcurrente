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
        System.out.println(Thread.currentThread().getName()+ " iniciando");
        Random r= new Random();
        //(int)r.nextInt(2)==1
        /*/
        if (true){
            System.out.println(Thread.currentThread().getName()+ " va en colectivo");
            c.tomarAsiento();
            c.avisoLlegada();
            this.simula();
            c.bajarse();
            c.avisoLlegada();
        }
        else{
            System.out.println(Thread.currentThread().getName()+ " va por su cuenta ");

        }*/
        int nroMolinete= e.entrarAlParque();
        if (nroMolinete!=-1){
            e.entro(nroMolinete);
            System.out.println(Thread.currentThread().getName()+ " entro al parque ");

            this.pasearPorElParque();

        }
        else{
            System.out.println(" El parque esta cerrado " +Thread.currentThread().getName()+ " se va");
        }
        

       
    
    
    }
    public void pasearPorElParque(){
        Random r= new Random();
        if (true){
            System.out.println("------------------------"+ Thread.currentThread().getName()+ " VA AL GOMON");

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

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    
    public void realizarActividadGomon(){
        Random r= new Random();

        rio.dejarBolso();
        System.out.println(Thread.currentThread().getName() + " DEJA EL BOLSO");

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

        rio.retirarBolso();
        System.out.println(Thread.currentThread().getName() +" RETIRA EL BOLSO");

    }


}
