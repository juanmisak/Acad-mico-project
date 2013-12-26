package edu.project.academico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


import com.project.academico.R;

public class Deudas extends Activity{
    protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deudas);
        setTitle("Deudas");
     
        Button buttonLaunchDeudaGen = (Button)findViewById(R.id.btnDeudaGen);
        Button buttonLaunchDeudaSal = (Button)findViewById(R.id.btnSaldo);
        
        
		
		
        ImageButton ButtonCerrar = (ImageButton) findViewById(R.id.ir);
        ButtonCerrar.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
        	 Deudas.this.finish();
         }
      }); 
        
        
        buttonLaunchDeudaGen.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(Deudas.this, DeudaGeneral.class);
                startActivity(i);
            }
        });
        buttonLaunchDeudaSal.setOnClickListener(new View.OnClickListener()
        {	
            public void onClick(View v)
            {
                Intent i = new Intent(Deudas.this, SaldoFavor.class);
                startActivity(i);
            }
        });
        
			}
		
}

