package Comida;

import java.util.Random;

public class Cocinero {
    private Restaurant r;

    public Cocinero (Restaurant r){
        this.r=r;

    }

    public void run (){
        Random ran= new Random();
        while (true){
            if (ran.nextInt(2)==0){
                r.cocinarAlmuerzo();
            }
            else{
                r.cocinarMerienda();
            }
           

        }
    }
}

