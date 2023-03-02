import IngresarParque.ColectivoFolklorico;

import IngresarParque.*;
import Gomones.*;
import Shop.*;


public class Main {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Entrada e= new Entrada(2);
        CentroCompras centro= new CentroCompras();
        Rio r= new Rio(3, 3, 2);
        ColectivoFolklorico cole= new ColectivoFolklorico();


        Thread camio= new Thread(new CamionetaConBolsos());
        Thread []c= new Thread[30];
        Thread empCole= new Thread(new EmpleadoColectivoFolklorico(cole));

        Thread reloj= new Thread(new Hora(e));
        reloj.start();
       
        empCole.start();

        for (int i=0; i<c.length;i++){
            c[i]= new Thread (new Cliente(r, e, cole, centro), "Cliente "+i );

        }
        for (int i=0; i<c.length;i++){
            c[i].start();

        }
       
        

    }

}
