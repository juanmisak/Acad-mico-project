package edu.project.academico;

import com.project.academico.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Desarrolladores extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desarrolladores);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.desarrolladores, menu);
		return true;
	}

}
