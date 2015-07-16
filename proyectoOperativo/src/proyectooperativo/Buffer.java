/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Buffer {
  //private StringBuffer contenido = new StringBuffer ();
    private final List<String> contenido=new ArrayList<String>();
  private boolean disponible=false;
 public Buffer() {
  }

  public synchronized List<String> recoger(){
    while(!disponible){
        System.out.printf("\n esperando para hacer pull ...");
        try{
            wait(); 
        }catch(InterruptedException ex){}
    }
    disponible=false;
  
    notify();
    return contenido;
  }

    public List<String> getContenido() {
        return contenido;
    }

  public synchronized void poner( List<String> rss,int id){
     //comprobar si buffer esta disponible, sino lo esta esperar que termine hilo que lo ocupa
     while(disponible){
         System.out.printf("\n esperando para hacer push hilo %d..",id);
        try{
            wait();
           
        }catch(InterruptedException ex){
        }
    }
    
    //seccion critica bloquea disponibilidad al entrar
    disponible=false;
    //se filtra feeds nuevos
    
    for(int i=0;i<rss.size();i++){
        if(!contenido.contains(rss.get(i))){
            contenido.add(rss.get(i));
        }
     
   
    }
   
    System.out.printf("\n tamaño de rss de hilo %d tomado %d => tamaño buffer %d \n",id,rss.size(),contenido.size());
     //libera disponibilidad al terminar seccion critica
    disponible=true;
    notify();
     
  }
  
}
