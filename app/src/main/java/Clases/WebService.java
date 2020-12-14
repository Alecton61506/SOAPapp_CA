package Clases;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebService {

    private static final String NAMESPACE =  "http://com.example.ud/";
    private static final String URL = "http://c3b2e2b9098b.ngrok.io/ServicioSOAP/MiServidorWeb?WSDL";
    private static  String SOAP_ACTION = "http://com.example.ud/";

    public  static double CaptureEurosWS(String webMathName){
        double resTxt = 0;
        SoapObject request = new SoapObject(NAMESPACE, webMathName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMathName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = Double.parseDouble(response.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
        return resTxt;
    }

    public static double CambioEuroWS(String webMathName, String valord){
        double resTxt = 0;
        SoapObject request = new SoapObject(NAMESPACE, webMathName);
        request.addProperty("valor", valord);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMathName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = Double.parseDouble(response.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
        return resTxt;
    }

    public  static String HolaMundoWS(String name, String webMathName){
        String resTxt;
        SoapObject request = new SoapObject(NAMESPACE, webMathName);
        request.addProperty("name", name);
        SoapSerializationEnvelope envelope = new
                SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMathName,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch(Exception e){
            e.printStackTrace();
            resTxt = e.getMessage();
        }
        return resTxt;
    }
}


