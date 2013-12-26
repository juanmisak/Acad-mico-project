package edu.project.academico;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.academico.R;

public class HorarioClase extends Activity{
	
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_clases);
        final EditText codigo = (EditText) findViewById(R.id.txtCodigo);
        final EditText paralelo = (EditText) findViewById(R.id.txtParalelo);
        //final EditText materia = (EditText) findViewById(R.id.tvMateria);
        Button consultar = (Button) findViewById(R.id.btnConsultar);
        consultar.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
           	 horario h1= new horario();
           	 h1.execute(codigo.getText().toString(),paralelo.getText().toString());
            }
         }); 
        
    }
    public class horario extends AsyncTask<String, Void, ArrayList<String>>{
    	//EditText materia = (EditText) findViewById(R.id.tvMateria);
    	/*EditText dia = (EditText) findViewById(R.id.tvDia);
    	EditText hinicio = (EditText) findViewById(R.id.tvHoraInicio);
    	editText hfin = (EditText) findViewById(R.id.tvHoraFin);
    	final EditText aula = (EditText) findViewById(R.id.tvAula);
    	final EditText bloque = (EditText) findViewById(R.id.tvBloque);*/
    	protected ArrayList<String> doInBackground(String... params) {
    		String NAMESPACE = "http://tempuri.org/";
    		String URL="https://ws.espol.edu.ec/saac/wsandroid.asmx";
    		String METHOD_NAME = "wsHorarioClases";
    		String SOAP_ACTION = "http://tempuri.org/wsHorarioClases";
    		HttpTransportSE httpTransport = new HttpTransportSE(URL);
    		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
    		request.addProperty("codigoMateria",params[0]);
    		request.addProperty("paralelo",Integer.parseInt(params[1]));
    		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
    		envelope.dotNet = true;
    		envelope.setOutputSoapObject(request);
    		SoapObject response;
    		try 
    		{
    			httpTransport.call(SOAP_ACTION,envelope);
    			response = (SoapObject) envelope.getResponse();	
    			response = (SoapObject) response.getProperty("diffgram");
    			response = (SoapObject) response.getProperty("NewDataSet");
    			response = (SoapObject) response.getProperty("HORARIOCLASES");
    			ArrayList<String> datos = new ArrayList<String>();
    			datos.add(response.getProperty("NOMBRE").toString());
    			datos.add(response.getProperty("NOMBREDIA").toString());
    			datos.add(response.getProperty("HORAINICIO").toString());
    			datos.add(response.getProperty("HORAFIN").toString());
    			datos.add(response.getProperty("AULA").toString());
    			datos.add(response.getProperty("BLOQUE").toString());
    			return datos;
    		} 
    		catch (Exception e) 
    		{
    			ArrayList<String>datos = new ArrayList<String>();
    			datos.add(e.getLocalizedMessage());
    			datos.add(e.getLocalizedMessage());
    			datos.add(e.getLocalizedMessage());
    			datos.add(e.getLocalizedMessage());
    			datos.add(e.getLocalizedMessage());
    			datos.add(e.getLocalizedMessage());
    			return datos;
    		}
    	}
    	
    	protected void onPostExecute(ArrayList<String> datos) {
    		Toast toast = Toast.makeText(getApplicationContext(), datos.get(0)+datos.get(1)+datos.get(2)+datos.get(3)+datos.get(4)+datos.get(5), Toast.LENGTH_SHORT);
    		toast.show();
    		//materia.setText(datos.get(0));
    	} 	
    }
    
}
