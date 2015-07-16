/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectooperativo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 *
 * @author User
 */

public class HiloProductor extends Thread 
{
   private boolean bandera = true;
   final String url;
   final int num;
   private Buffer buffer;
   
    public HiloProductor(String url, int num,Buffer buffer) {
        this.url = url;
        this.num = num;
        this.buffer=buffer;
    }
    
           

   public void run() {
 
    while(bandera=true){
    
    
        List<String> rss=new ArrayList<String>();  
         //se parsea el contenido descargado a xml
         xmlReader xmlParseado = new xmlReader(url);
    
      
        
    
        for (ExtraerInfoXML message : xmlParseado.readFeed().getMessages()) {
           
            rss.add(message.toString());
     
        }
         
        System.out.printf("\n Ejecutandose hilo %d \n"+url,num);
       
        xmlParseado=null;
        buffer.poner(rss,num);
         try{ 
             System.out.printf("\n sleep \n");
             
             sleep(5000);
         }catch(InterruptedException ex){
            System.out.printf("\n hilo %d fue interrumpido",num);
         }
    }
    
     System.out.printf("\n salio del while");
    } 
 

    public void stopHilo(int id){
        if(id==num){
            this.stop();
            //bandera=false;
            System.out.printf("\n salio por stop");
        }
    }

}
