package andro.jf;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AndroServiceBindActivity extends Activity {
  /** Called when the activity is first created. */

  private int infoFromService = 0;
  /* Code disponible lorsque l'activité sera connectée au service */
  private ServiceConnection maConnexion = new ServiceConnection() {

    /* Code exécuté lorsque la connexion est établie: */
    public void onServiceConnected(ComponentName name, IBinder service) {
      AndroServiceInterface myBinder = (AndroServiceInterface)service;
      // stockage de l'information provevant du service
      infoFromService = myBinder.getInfo(); 

    }
    public void onServiceDisconnected(ComponentName name) {
    }
  };


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Intent serv = new Intent(this,AndroService.class);
    startService(serv);

    Button b = (Button)findViewById(R.id.button1);
    b.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        /* Intent pour la connexion au service */
        Intent intentAssociation = 
            new Intent(AndroServiceBindActivity.this, AndroService.class);
        /* Bind au service: à refaire à chaque 
         * appui sur le bouton pour rafraichir la valeur */
        bindService(intentAssociation, maConnexion, 
            Context.BIND_AUTO_CREATE);
        Toast.makeText(getApplicationContext(), 
            "Info lue dans le service: "  + infoFromService, 
            Toast.LENGTH_SHORT).show();
        /* On se déconnecte du service */
        unbindService(maConnexion);

      }
    });



  }
}