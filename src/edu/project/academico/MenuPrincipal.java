package edu.project.academico;

import com.project.academico.R;


	import android.app.TabActivity;
	import android.content.Intent;
	import android.content.res.Resources;
	import android.os.Bundle;
	import android.widget.TabHost;

	@SuppressWarnings("deprecation")
	public class MenuPrincipal extends TabActivity {
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_menu);
		
			Resources res = getResources(); // Resource object to get Drawables
			TabHost tabHost = getTabHost(); // The activity TabHost
			TabHost.TabSpec spec; // Reusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab

			// Create an Intent to launch an Activity for the tab (to be reused)
			intent = new Intent().setClass(this, Informacion.class);
			spec = tabHost.newTabSpec("Información")
					.setIndicator("INFORMACIÓN", res.getDrawable(R.drawable.blueconsejerias))
					.setContent(intent);
			tabHost.addTab(spec);

			// Do the same for the other tabs

			intent = new Intent().setClass(this, Materias.class);
			spec = tabHost.newTabSpec("Materias")
					.setIndicator("MATERIAS", res.getDrawable(R.drawable.blueafavor))
					.setContent(intent);
			tabHost.addTab(spec);
			
			 
			intent = new Intent().setClass(this, Profesores.class);
			spec = tabHost
					.newTabSpec("Profesores")
					.setIndicator("PROFESORES",
							res.getDrawable(R.drawable.blueavance))
					.setContent(intent);
			
			tabHost.addTab(spec);
			
			intent = new Intent().setClass(this, Deudas.class);
			spec = tabHost
					.newTabSpec("DEUDAS")
					.setIndicator("DEUDAS",
							res.getDrawable(R.drawable.blueavance))
					.setContent(intent);
			tabHost.addTab(spec);
			
			intent = new Intent().setClass(this, Malla.class);
			spec = tabHost
					.newTabSpec("MALLA")
					.setIndicator("MALLA",
							res.getDrawable(R.drawable.blueavance))
					.setContent(intent);
			tabHost.addTab(spec);
			
			//set tab which one you want open first time 0 or 1 or 2
			tabHost.setCurrentTab(0);
			
			
		}
		
	}