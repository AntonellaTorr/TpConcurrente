package IngresarParque;
import java.util.concurrent.Semaphore;

public class ColectivoFolklorico {
    private Semaphore asientos, avisarEmpleado, avisarClientes;

  

    public ColectivoFolklorico(){

        asientos= new Semaphore(25, true);
        avisarEmpleado= new Semaphore(0);
        avisarClientes= new Semaphore(0);
    }
    
    public void iniciarViaje(){
        //empleado
        try {
            avisarEmpleado.acquire(25);
            System.out.println(" COLECTIVERO INICIANDO VIAJESULI");
        } catch (Exception e) {
        }
    }
    
    public void llegaADestino(){
        //empleado
        avisarClientes.release(25);
    }
    
    public void terminoVuelta(){
        //empleado
        asientos.release(25);
    }
    
    public void tomarAsiento(){
        try {
            asientos.acquire();
            System.out.println(Thread.currentThread().getName() + " consiguio asiento en el cole ");
        } catch (Exception e) {
        }
    }
    
    public void avisoLlegada(){
        avisarEmpleado.release();
      
    }
    
    public void avisoSalida(){
        //metodo que usa el cliente para avisarle al empleado que salio
        //esto es para que el colectivero no se pueda ir hasta que no salgan todos los clientes
        avisarEmpleado.release();
    }
    
    public void bajarse(){
        //metodo que usa el cliente para intentar bajarse
        //los que suban van a quedarse bloqueados esperando a que el cliente libere los permisos cuando llege a destino
        try {
            avisarClientes.acquire();
            System.out.println(Thread.currentThread().getName() + " se baja ");
        } catch (Exception e) {
        }
    }
    
}
