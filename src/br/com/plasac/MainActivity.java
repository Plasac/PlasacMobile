package br.com.plasac;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import br.com.plasac.converter.CredenciadoConverter;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> credenciados = new ArrayList<String>();
		
        CredenciadoConverter CredenciadoConverter = new CredenciadoConverter("http://172.16.8.115/get_dados.php");
        
        // Obtêm a resposta do webservice
        String resultado = CredenciadoConverter.pegaCredenciados();
        
        try {
            // Utiliza a classe JSONArray para obter as
            // informações e separar o campo que será
            // utilizado no listview
            JSONArray json = new JSONArray(resultado);
            
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObj = json.getJSONObject(i);
                
                // Insere o estado na lista de estados
                credenciados.add(jsonObj.getString("NOME_CRE"));
            }
            
        } catch (JSONException e) {
            Log.e("CredenciadoConverter", e.toString());
        }
        finally{
           // setListAdapter(new ArrayAdapter<String>(this, R.id.spinner1, credenciados));
        }
		
        
		ArrayAdapter<String> mCredenciados = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, credenciados);
		Spinner spnCredenciados = (Spinner) findViewById(R.id.spinner1);
		spnCredenciados.setAdapter(mCredenciados);

	}

}
