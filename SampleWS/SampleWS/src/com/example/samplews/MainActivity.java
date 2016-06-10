package com.example.samplews;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

/**
 * Accessing Web service in Android application 
 * @author Prabu
 * @version 1.0
 * @since SEP 10 2013
 */
public class MainActivity extends Activity {
	public final static String URL = "http://192.168.1.102/WebService/services/WebService?wsdl";
	public static final String NAMESPACE = "http://sample";
	public static final String SOAP_ACTION_PREFIX = "/";
	private static final String METHOD = "getName";
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.test);
		AsyncTaskRunner runner = new AsyncTaskRunner();
		runner.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Private class which runs the long operation.
	 * @author Prabu 
	 * 
	 */
	private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		private String resp;

		@Override
		protected String doInBackground(String... params) {
			publishProgress("Loading contents..."); // Calls onProgressUpdate()
			try {
				// SoapEnvelop.VER11 is SOAP Version 1.1 constant
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				SoapObject request = new SoapObject(NAMESPACE, METHOD);
				//bodyOut is the body object to be sent out with this envelope
				envelope.bodyOut = request;
				HttpTransportSE transport = new HttpTransportSE(URL);
				try {
					transport.call(NAMESPACE + SOAP_ACTION_PREFIX + METHOD, envelope);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
				//bodyIn is the body object received with this envelope
				if (envelope.bodyIn != null) {
					//getProperty() Returns a specific property at a certain index.
					SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn)
							.getProperty(0);
					resp=resultSOAP.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				resp = e.getMessage();
			}
			return resp;
		}

		/**
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			// In this example it is the return value from the web service
			textView.setText(result);
		}

		/**
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// Things to be done before execution of long running operation. For
			// example showing ProgessDialog
		}

		/**
		 * 
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(String... text) {
			textView.setText(text[0]);
			// Things to be done while execution of long running operation is in
			// progress. For example updating ProgessDialog
		}
	}
}
