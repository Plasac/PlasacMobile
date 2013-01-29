// http://172.16.8.115/get_dados.php

package br.com.plasac.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class CredenciadoConverter {
	
	private static final int TIMEOUT_CONEXAO = 20000; // 20 segundos
    private static final int TIMEOUT_SOCKET = 30000; // 30 segundos
    private static final int TAM_MAX_BUFFER = 10240; // 10Kbytes
    private String url;

    public CredenciadoConverter(String url) {
        this.url = url;
    }
    public String pegaCredenciados() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				// Toast.makeText(JSONADS.this,
				// "Não há conexão com a Internet ", Toast.LENGTH_SHORT).show();
				Log.e(CredenciadoConverter.class.toString(),
						"Nao ha conexao com a Internet ou com o servidor informado");

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
    
    public String getCredenciados(){
        String parserbuilder = "";
        
        try{
            HttpParams httpParameters = new BasicHttpParams();
            
            // Configura o timeout da conexão em milisegundos até que a conexão seja estabelecida
            HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONEXAO);
            
            // Configura o timeout do socket em milisegundos do tempo  que será utilizado para aguardar os dados
            HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);   
            
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url + "/GetCredenciados");
    
            HttpResponse response = httpclient.execute(httppost);
            
            BufferedReader reader = new BufferedReader( new InputStreamReader(response.getEntity().getContent(), "UTF-8"), TAM_MAX_BUFFER);
            
            StringBuilder builder = new StringBuilder();
            
            for (String line = null ; (line = reader.readLine())!= null;) {
                builder.append(line).append("\n");
            }
            
            parserbuilder = builder.toString();
            
            // Retira a string <?xml version="1.0" encoding="utf-8" ?> <string xmlns="http://tempuri.org/"> e a tag </string> 
            // para obter o resultado em Json, já que o webservice está retornando uma string
            Integer firstTagString = parserbuilder.indexOf("<string");
            Integer posXml = parserbuilder.indexOf(">", firstTagString);
            Integer posTagString = parserbuilder.indexOf("</string>");
            parserbuilder = parserbuilder.substring(posXml + 1, posTagString + 1);
        
        }catch(ClientProtocolException e){
            Log.e("CredenciadoConverter", e.toString());
        }
        
        catch(IOException e){
            Log.e("CredenciadoConverter", e.toString());
        }
        
        return parserbuilder;    
    }
    
}
