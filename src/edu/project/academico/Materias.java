package edu.project.academico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.project.academico.R;

public class Materias extends Activity{

     
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_materias);

            Button buttonLaunchHistorial = (Button)findViewById(R.id.btnAvance);
            Button buttonLaunchMateriasDisp = (Button)findViewById(R.id.btnMateriasDisponibles);
            Button buttonLaunchMateriasReg = (Button)findViewById(R.id.btnMateriasRegistradas);
            Button buttonLaunchProbAvance = (Button)findViewById(R.id.btnProbDeAvance);
            
            ImageButton ButtonCerrar = (ImageButton)findViewById(R.id.ir);
            ButtonCerrar.setOnClickListener(new OnClickListener() {
             
             @Override
             public void onClick(View v) {
            	 Materias.this.finish();
             }
          });
            buttonLaunchHistorial.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(Materias.this, Historial.class);
                    startActivity(i);
                }
            });
            
            
            buttonLaunchMateriasDisp.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(Materias.this, MateriasDisponibles.class);
                    startActivity(i);
                }
            });
            
            
            buttonLaunchMateriasReg.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(Materias.this, MateriasRegistradas.class);
                    startActivity(i);
                }
            });
            
            
            buttonLaunchProbAvance.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(Materias.this, ProbabilidadAvance.class);
                    startActivity(i);
                }
            });
            
        }
        
        
       
}