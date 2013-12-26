package edu.project.academico;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.academico.R;

public class Informacion extends Activity {

	ObtenerInformacion oi = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacion);
		//Bundle bundle=getIntent().getExtras();
		final EditText matricula = (EditText)findViewById(R.id.matricu);
		final EditText cedula = (EditText)findViewById(R.id.ced);
		final EditText nombre = (EditText)findViewById(R.id.nom);
		final EditText factorP = (EditText)findViewById(R.id.fac);
		final EditText grupo = (EditText)findViewById(R.id.gru);
		final EditText email = (EditText)findViewById(R.id.ema);
		final EditText promedio = (EditText)findViewById(R.id.pro);
		final EditText sancion = (EditText)findViewById(R.id.san);
		
		final String mat = MainActivity.mat;
		matricula.setText(mat);
		
		Button dialogButton;
		Button ButtonAvance;
		ImageButton ButtonCerrar;
		
        ButtonCerrar = (ImageButton)findViewById(R.id.ir);
        ButtonCerrar.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
        	 Informacion.this.finish();
         }
      });
		
		
        ButtonAvance = (Button) findViewById(R.id.btnAvance);
        ButtonAvance.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Informacion.this, Avance.class);
             startActivity(i);
         }
      });
		
		
		
        dialogButton = (Button) findViewById(R.id.btnHorario);
        dialogButton.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
        	 Intent cla = new Intent(Informacion.this, HorarioClase.class);
             startActivity(cla);
         }
      });

		
		oi=new ObtenerInformacion(new ObtenerInformacion.AsyncTaskListener() {
			
			
			@Override
			public void onProgressUpdate(Integer progress) {}
			
			@Override
			public void onInit() {}
			
			@Override
			public void onFinish(ArrayList<String> info) {
				matricula.setText(info.get(0));
				cedula.setText(info.get(1));
				nombre.setText(mat);
				email.setText(info.get(3));
				factorP.setText(info.get(4));
				grupo.setText(info.get(5));
				String sanc;
				if(info.get(6).equalsIgnoreCase("N"))
					sanc="No";
				else sanc="Si";
				sancion.setText(sanc);
				promedio.setText(info.get(7));
			}
			
			@Override
			public void onCancel() {}
		});
		oi.execute(mat);
		
		
		
	}

	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
	    if (keyCode == KeyEvent.KEYCODE_BACK) { 
	        moveTaskToBack(true); 
	        return true; 
	    } 
	    return super.onKeyDown(keyCode, event); 
	}  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacion, menu);
		return true;
	}




	       
	        
	    
	    private void mostrarAlertDialogo(){
	       AlertDialog.Builder dialog = new AlertDialog.Builder(this);
	       
	       dialog.setMessage("Ver");
	       dialog.setCancelable(false);
	       dialog.setPositiveButton("Horario de clases", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	        	 dialog.cancel();
	                Intent i = new Intent(Informacion.this, HorarioExamen.class);
	                startActivity(i);
	         }
	      });
	       dialog.setNegativeButton("Horario de exámenes", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
                Intent i = new Intent(Informacion.this, HorarioClase.class);
                startActivity(i);
	         }
	      });
	       dialog.show();
	    }}















