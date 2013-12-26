package edu.project.academico;

//import com.project.academico.R;

import com.project.academico.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


public class Malla extends Activity {
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);	
		Uri uri = Uri.parse("https://www.academico.espol.edu.ec/imgMallas/LI_RED_v6.png");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
        setContentView(R.layout.activity_malla);

}
}