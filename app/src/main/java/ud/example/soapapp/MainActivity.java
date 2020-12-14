package ud.example.soapapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.AsynchronousChannelGroup;

import Clases.WebService;


public class MainActivity extends AppCompatActivity {

    private Button miboton;
    private TextView salida;
    private TextView titulo;
    private TextView valoreuro;
    private EditText entrada;
    private RadioButton cambioP;
    private String editText;
    private String displayText;
    private String displayEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cambioP = findViewById(R.id.radioButton);
        cambioP.setChecked(true);
        entrada = findViewById(R.id.editTextNumberDecimal);
        valoreuro = findViewById(R.id.textView2);
        salida = findViewById(R.id.textView3);
        titulo  = findViewById(R.id.textView);
        miboton = findViewById(R.id.button);
        miboton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                if (entrada.getText().length() != 0 && entrada.getText().toString() != "")
                {
                    editText = entrada.getText().toString();
                    CambioEuroWS task = new CambioEuroWS();
                    task.execute();
                    salida.setVisibility(view.VISIBLE);
                }
            }
        });

        LLamadoInicialWS capini = new LLamadoInicialWS();
        capini.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LLamadoInicialWS extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings){
            double temp = WebService.CaptureEurosWS("getEuro");
            int covt = (int) temp;
            displayEuro = String.valueOf(covt);


            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            valoreuro.setText(displayEuro);
        }

        @Override
        protected void onPreExecute() {
            valoreuro.setText("Comenzo");
        }
    }

    private class CambioEuroWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            double temp;
            String Tipomoneda ="";
            //String tmp = null; //an
            if (cambioP.isChecked()) {
                temp = WebService.CambioEuroWS("Peso2Euro", editText);
                Tipomoneda = "€";
            } else {
                temp = WebService.CambioEuroWS("Euro2Peso", editText);
                Tipomoneda = "$"; //an
            }
            displayText = String.valueOf(temp)+ " " +Tipomoneda;
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            salida.setText(displayText);
        }

        @Override
        protected void onPreExecute() {
            salida.setText("comienzo");
        }
    }


    private class AsyncCallWS extends  AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params){
            displayText = WebService.HolaMundoWS(editText, "hello");
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            titulo.setText(displayText);
        }
        @Override
        protected void onPreExecute(){

        }
        @Override
        protected  void onProgressUpdate(Void... values){

        }

    }

}
