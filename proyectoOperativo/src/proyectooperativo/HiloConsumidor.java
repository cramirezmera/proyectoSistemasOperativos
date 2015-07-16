/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativo;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import static proyectooperativo.ProyectoOperativo.f;

/**
 *
 * @author User
 */
public class HiloConsumidor extends Thread  {
  
    private Buffer buffer;
  private boolean bandera = true;
  
  public HiloConsumidor(Buffer buffer) {
    this.buffer=buffer;
  }
  public void run(){
    List<String> bufferSync=new ArrayList<String>();
    while(bandera){
    System.out.printf("\n Inicio Consumidor");
    bufferSync=buffer.recoger();
    f.actualizarNoticias(bufferSync);
    System.out.printf("\n consumido %d",bufferSync.size());           
     try{ 
             sleep(40000);   
                
      }catch(InterruptedException ex){
          System.out.printf("\n consumidor fue interrumpido");
      }
    }
       System.out.printf("\n acabo consumidor exitosamente"); 
  }
  
   public  void actualizar(){
    List<String> bufferSync;
    
    
   
           
                bufferSync=buffer.recoger();
               
                f.actualizarNoticias(bufferSync);
       
       
  }
  
  public void stopHilo(){
        System.out.printf("\n Finalizo Consumidor");
        bandera=false;
    }
}
