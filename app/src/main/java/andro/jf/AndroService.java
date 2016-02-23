package andro.jf;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class AndroService extends Service {

  /* For binding this service */
  //La donnée à transmettre à l'activité
  private int infoOfService = 0; 
  //L'implémentation de l'objet de binding
  private final IBinder ib = new MonServiceBinder(); 
  private class MonServiceBinder extends Binder 
         implements AndroServiceInterface {
    // Cette classe qui hérite de Binder 
    // implémente une méthode définie dans l'interface
    public int getInfo() {
      return infoOfService;
    }   
  }

  public IBinder onBind(Intent arg0) {
    return ib;
  }

  /* Service stuff */
  public void onCreate() {
    // Création du service
    Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
  }
  public void onDestroy() {
    // Destruction du service
  }
  public int onStartCommand(Intent intent, int flags, int startId) {
    // Démarrage du service
    Thread t = new Thread(new Runnable() {

      @Override
      public void run() {

        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        infoOfService = 12; 
      }
    });
    t.start();

    return START_STICKY;
  }

}