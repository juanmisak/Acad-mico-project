package edu.project.academico;
//import android.os.AsyncTask;

import java.util.ArrayList;
//import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;

import android.os.AsyncTask;

public class ObtenerInformacion extends AsyncTask<String, Integer, ArrayList<String>>
{
	

	 public interface AsyncTaskListener{ void onInit(); void onProgressUpdate(Integer progress); void onCancel(); void onFinish(ArrayList<String> json);}
	 
	 private AsyncTaskListener asyncTaskListener;
	 
	 
	 
	 public ObtenerInformacion(AsyncTaskListener asyncTaskListener) {
		// TODO Auto-generated constructor stub
		  this.asyncTaskListener = asyncTaskListener;
	}

	@Override
	 protected ArrayList<String> doInBackground(String... urls) {
	  
	  return informacion(urls[0]);
	 }
	 
	 
	 @Override
	 protected void onPreExecute(){
	  asyncTaskListener.onInit();
	 }
	 
	 @Override
	 protected void onProgressUpdate(Integer... progress){
	  asyncTaskListener.onProgressUpdate(progress[0]);
	 }
	 
	 @Override
	 protected void onPostExecute(ArrayList<String> response){
	  asyncTaskListener.onFinish(response);
	 }
	 
	 @Override
	 protected void onCancelled(){
	  asyncTaskListener.onCancel();
	 }

	
	
	
	
	
	
	//funcion a�adida para recuperar el tipo de usuario y su matricula
	public static ArrayList<String> informacion(String matricula)
	{
		String NAMESPACE = "http://tempuri.org/";
		String URL="https://ws.espol.edu.ec/saac/wsandroid.asmx";
		String METHOD_NAME = "wsInfoEstudiante";
		String SOAP_ACTION = "http://tempuri.org/wsInfoEstudiante";
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
		request.addProperty("codigoEstudiante",matricula);
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
			response = (SoapObject) response.getProperty("INFOESTUDIANTE");
			
			ArrayList<String>datos = new ArrayList<String>();
			datos.add(response.getProperty("COD_ESTUDIANTE").toString());
			datos.add(response.getProperty("IDENTIFICACION").toString());
			datos.add(response.getProperty("NOMBRECOMPLETO").toString());
			datos.add(response.getProperty("EMAIL").toString());
			datos.add(response.getProperty("FACTORP").toString());
			datos.add(response.getProperty("GRUPOETNICO").toString());
			datos.add(response.getProperty("SANCION").toString());
			datos.add(response.getProperty("PROMEDIOGENERAL").toString());
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
			datos.add(e.getLocalizedMessage());
			datos.add(e.getLocalizedMessage());

			 //MainActivity.tipo.setText("asd");
			return datos;
			//MainActivity.tipo.setText(e.getLocalizedMessage());
		}
	}
	


	
}
