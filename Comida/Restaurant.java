package Comida;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    //Recurso compartido entre el cliente y el cocinero
    private int capacidad;
    private ReentrantLock mutexAlmuerzo;
    private ReentrantLock mutexMerienda;
    private ReentrantLock mutexIngreso;

    private Condition hayAlmuerzos= mutexAlmuerzo.newCondition();
    private Condition hayMeriendas= mutexMerienda.newCondition();
    private Condition hayLugar= mutexIngreso.newCondition();

    private int cantAlmuerzosDisponibles, cantMeriendasDisponibles, cantPersonasAdentro;


    public Restaurant(int capacidad){
        this.capacidad=capacidad;
        mutexIngreso= new ReentrantLock();
        mutexAlmuerzo= new ReentrantLock();
        mutexMerienda= new ReentrantLock();
        cantPersonasAdentro=0;
        cantAlmuerzosDisponibles=0;
        cantMeriendasDisponibles=0;
    }
 
    public void ingresar(){
        mutexIngreso.lock();
   
        while (cantPersonasAdentro==capacidad){
            try {
                hayLugar.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        cantPersonasAdentro++;

        mutexIngreso.unlock();

    }
    public void irseDelRestaurant(){
        mutexIngreso.lock();
        cantPersonasAdentro--;
        hayLugar.signal();
        mutexIngreso.unlock();
    }
    public void comprarAlmuerzo(){
        mutexAlmuerzo.lock();
        while (cantAlmuerzosDisponibles==0){
            try {
                hayAlmuerzos.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        cantAlmuerzosDisponibles--;
        mutexAlmuerzo.unlock();
    }
    public void comprarMerienda(){
        mutexMerienda.lock();
        while (cantMeriendasDisponibles==0){
            try {
                hayMeriendas.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        mutexMerienda.unlock();
    }
    
    public void cocinarAlmuerzo(){
        mutexAlmuerzo.lock();
        cantAlmuerzosDisponibles++;
        hayAlmuerzos.signal();
        mutexAlmuerzo.unlock();
    }
       
    public void cocinarMerienda(){
        mutexMerienda.lock();
        cantMeriendasDisponibles++;
        hayMeriendas.signal();
        mutexMerienda.unlock();
    }



}
