package br.com.plasac;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.plasac.converter.CredenciadoConverter;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<String> credenciados = new ArrayList<String>();
		
        CredenciadoConverter CredenciadoConverter = new CredenciadoConverter("http://172.16.8.115/get_dados.php");
        
        // Obtêm a resposta do webservice
        String resultado = CredenciadoConverter.getCredenciados();
        
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
            setListAdapter(new ArrayAdapter<String>(this, R.id.spinner1, credenciados));
        }
		
		/*
		mCredenciados = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, credenciados);
		spnCredenciados = (Spinner) findViewById(R.id.spinner1);
		spnCredenciados.setAdapter(mCredenciados);*/

	}

}
