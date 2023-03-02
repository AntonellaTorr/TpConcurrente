package IngresarParque;
public class EmpleadoColectivoFolklorico {
        ColectivoFolklorico c;
        
        public EmpleadoColectivoFolklorico(ColectivoFolklorico c){
            this.c=c;
        }
        
        public void run(){
            while(true){
             c.iniciarViaje();
             System.out.println("Colectivo Folklorico: Comenzando viaje");
             this.simulaViaje();
             System.out.println("Colectivo Folklorico: LLevando los clientes al parquee");
             c.llegaADestino();
             c.iniciarViaje();
             System.out.println("Colectivo Folklorico: ya no hay mas clientes, vuelvo vacio");
             this.simulaViaje();
             System.out.println("Colectivo Folklorico: ya pueden subir el resto de clientes");
             c.terminoVuelta();
            }
        }
        
        private void simulaViaje(){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
        
    }

