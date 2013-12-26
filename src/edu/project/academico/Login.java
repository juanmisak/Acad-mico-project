package edu.project.academico;

import java.util.ArrayList;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;

import android.os.AsyncTask;

public class Login extends AsyncTask<String, Integer, ArrayList<String>>
{
	

	 public interface AsyncTaskListener{ void onInit(); void onProgressUpdate(Integer progress); void onCancel(); void onFinish(ArrayList<String> json);}
	 private AsyncTaskListener asyncTaskListener;
	 
	 
	 
	 public Login(AsyncTaskListener asyncTaskListener) {
		// TODO Auto-generated constructor stub
		  this.asyncTaskListener = asyncTaskListener;
	}

	@Override
	 protected ArrayList<String> doInBackground(String... urls) {
	  
	  return login(urls[0], urls[1]);
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
	 protected void onPostExecute(ArrayList<String> response){
	  asyncTaskListener.onFinish(response);
	 }
	 
	 @Override
	 protected void onCancelled(){
	  asyncTaskListener.onCancel();
	 }

	
	
	
	
	public static ArrayList<String> login(String usuario,String pass) 
	{
		String NAMESPACE = "http://academico.espol.edu.ec/webservices/";
		String URL="https://www.academico.espol.edu.ec/services/directorioEspol.asmx";
		String METHOD_NAME = "autenticacion";
		String SOAP_ACTION = "http://academico.espol.edu.ec/webservices/autenticacion";
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
		request.addProperty("varUser",usuario);
		request.addProperty("varContrasenia",pass);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		SoapPrimitive response;
		try 
		{
			ArrayList<String> datos=new ArrayList<String>();
			httpTransport.call(SOAP_ACTION,envelope);
			response = (SoapPrimitive) envelope.getResponse();
			String bool = response.toString();
			if(bool.equalsIgnoreCase("true"))
				datos=datos(usuario);
			else{
				datos.add("Tipo");
				datos.add("Matricula");
			}
			datos.add(bool);
			return datos;
		} 
		catch (Exception e) 
		{
			 //MainActivity.tipo.setText("asd");
			return null;
		}
	}
	
	//funcion añadida para recuperar el tipo de usuario y su matricula
	public static ArrayList<String> datos(String user)
	{
		String NAMESPACE = "http://tempuri.org/";
		String URL="https://ws.espol.edu.ec/saac/wsandroid.asmx";
		String METHOD_NAME = "wsInfoUsuario";
		String SOAP_ACTION = "http://tempuri.org/wsInfoUsuario";
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
		request.addProperty("usuario",user);
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
			response = (SoapObject) response.getProperty("INFORMACIONUSUARIO");
			
			ArrayList<String>datos = new ArrayList<String>();
			datos.add(response.getProperty("TIPO").toString());
			datos.add(response.getProperty("IDENTIFICACION").toString());
			return datos;
		} 
		catch (Exception e) 
		{

			 //MainActivity.tipo.setText("asd");
			return null;
			//MainActivity.tipo.setText(e.getLocalizedMessage());
		}
	}
	


	
}
