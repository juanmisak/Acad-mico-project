package edu.project.academico;

import java.util.ArrayList;

import com.project.academico.R;



//import edu.project.academico.Login.AsyncTaskListener;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity 
{
	//static TextView test;
	//static TextView matricula;
	//static TextView tipo;
	Login log = null;
	static String mat;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.pass);
		ImageButton testws = (ImageButton) findViewById(R.id.ir);
		final TextView test = (TextView) findViewById(R.id.textView3);
		final TextView tipo = (TextView) findViewById(R.id.tipo);//a�adida
		final TextView matricula = (TextView) findViewById(R.id.matricula);//a�adida
		
		
        ImageButton ButtonCerrar = (ImageButton) findViewById(R.id.salir);
        ButtonCerrar.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
        	 MainActivity.this.finish();
         }
      }); 
		
		testws.setOnClickListener(new View.OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{

				//String bool = Log.login(user.getText().toString(),pass.getText().toString());
				log = new Login(new Login.AsyncTaskListener() {
					
					@Override
					public void onProgressUpdate(Integer progress) {}
					
					@Override
					public void onInit() {}
					
					@Override
					public void onFinish(ArrayList<String> datos) {
						 tipo.setText(datos.get(0));
						 matricula.setText(datos.get(1));
						 mat=datos.get(1);
						 test.setText(datos.get(2));
						if(datos.get(2).equalsIgnoreCase("true")){
							Intent informacion= new Intent(MainActivity.this,MenuPrincipal.class);
							informacion.putExtra("matricula", datos.get(1));
							startActivity(informacion);
						}
					}
					
					@Override
					public void onCancel() {}
				});
				log.execute(user.getText().toString(),pass.getText().toString());
				
				
				
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
