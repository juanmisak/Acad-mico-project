package edu.project.academico;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;

import android.os.AsyncTask;

public class ObtenerDisponibles extends AsyncTask<String, Integer, ArrayList<ArrayList<String>>>
{
	

	 public interface AsyncTaskListener{ void onInit(); void onProgressUpdate(Integer progress); void onCancel(); void onFinish(ArrayList<ArrayList<String>> json);}
	 private AsyncTaskListener asyncTaskListener;
	 
	 
	 
	 public ObtenerDisponibles(AsyncTaskListener asyncTaskListener) {
		// TODO Auto-generated constructor stub
		  this.asyncTaskListener = asyncTaskListener;
	}

	@Override
	 protected ArrayList<ArrayList<String>> doInBackground(String... urls) {
	  
	  return obtener();
	 }
	 
	 
	 @Override
	 protected void onPreExecute(){
	  asyncTaskListener.onInit();
	  //MainActivity.pb.setVisibility(0);
	 }
	 
	 @Override
	 protected void onProgressUpdate(Integer... progress){
	  asyncTaskListener.onProgressUpdate(progress[0]);
	 }
	 
	 @Override
	 protected void onPostExecute(ArrayList<ArrayList<String>> response){
	  asyncTaskListener.onFinish(response);
	 }
	 
	 @Override
	 protected void onCancelled(){
	  asyncTaskListener.onCancel();
	 }

	
	
	
	
	public static ArrayList<ArrayList<String>> obtener() 
	{
		String NAMESPACE = "http://tempuri.org/";
		String URL="https://ws.espol.edu.ec/saac/wsandroid.asmx";
		String METHOD_NAME = "wsMateriasDisponibles";
		String SOAP_ACTION = "http://tempuri.org/wsMateriasDisponibles";
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
		request.addProperty("codigoestudiante",MainActivity.mat);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		SoapObject response;
		SoapObject data;
		try 
		{
			httpTransport.call(SOAP_ACTION,envelope);
			response = (SoapObject) envelope.getResponse();	
			response = (SoapObject) response.getProperty("diffgram");
			response = (SoapObject) response.getProperty("NewDataSet");
			int numero = response.getPropertyCount();
			ArrayList<String>  materia;
			ArrayList<ArrayList<String>> materias = new ArrayList<ArrayList<String>>();
			for(int i =0; i<numero;i++){
				data= (SoapObject) response.getProperty(i);
				materia =new ArrayList<String>();
				materia.add(data.getProperty("COD_MATERIA_ACAD").toString());
				materia.add(data.getProperty("NOMBRE_MATERIA").toString());
				//materia.add(data.getProperty("NOMBRE").toString()+" "+data.getProperty("PARALELO").toString());
				//materia.add(data.getProperty("PARALELO").toString());
				materias.add(materia);
			}
			/*
			ArrayList<String>datos = new ArrayList<String>();
			datos.add(response.getProperty("COD_MATERIA_ACAD").toString());
			datos.add(response.getProperty("NOMBRE").toString());
			datos.add(response.getProperty("PARALELO").toString());*/
			
			return materias;
		} 
		catch (Exception e) 
		{ ArrayList<String> error = new ArrayList<String>();
		error.add("error");
		ArrayList<ArrayList<String>> errors = new ArrayList<ArrayList<String>>();
		errors.add(error);
			return errors;
			//MainActivity.tipo.setText(e.getLocalizedMessage());
		}
	}
	
	


	
}
