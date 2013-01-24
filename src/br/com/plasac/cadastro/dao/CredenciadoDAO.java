package br.com.plasac.cadastro.dao;

import br.com.plasac.cadastro.modelo.Credenciado;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CredenciadoDAO extends SQLiteOpenHelper{

	private static final String TABELA = "Plasac";
	private static final String DATABASE = "Credenciados";	
	
	public CredenciadoDAO(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		String sql = " CREATE TABLE "+TABELA+" ("+
				" id INTEGER PRIMARY KEY, "+
				" localAtendimento TEXT, "+
				" prioridade INTEGER, "+
				" nomeCredenciado TEXT, "+
				" codCredenciado INTEGER, "+
				" telefone TEXT, "+
				" local INTEGER, "+
				" endereco TEXT, "+
				" nomeBairro TEXT, "+
				" codBairro INTEGER, "+
				" nomeCidade TEXT, "+
				" codCidade INTEGER, "+
				" nomeEstado TEXT, "+
				" zona TEXT, "+
				" codZona INTEGER, "+
				" tipoCre INTEGER, "+
				" especialidade TEXT, "+
				" cep TEXT, "+
				" endCod INTEGER );";
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = " DROP TABLE IF EXISTIS "+TABELA;
		database.execSQL(sql);
		onCreate(database);
	}
	
	public void insere(Credenciado credenciado){
		ContentValues values = new ContentValues();
		values.put("localAtendimento", credenciado.getLocalAtendimento() );
		values.put("nomeCredenciado", credenciado.getNomeCredenciado() );
		values.put("codCredenciado", credenciado.getCodCredenciado() );
		values.put("telefone", credenciado.getTelefone() );
		values.put("local", credenciado.getLocal() );
		values.put("endereco", credenciado.getEndereco() );
		values.put("nomeBairro", credenciado.getNomeBairro() );
		values.put("codBairro", credenciado.getCodBairro() );
		values.put("nomeCidade", credenciado.getNomeCidade() );
		values.put("codCidade", credenciado.getCodCidade() );
		values.put("nomeEstado", credenciado.getNomeEstado() );
		values.put("zona", credenciado.getZona() );
		values.put("codZona", credenciado.getCodZona() );
		values.put("tipoCre", credenciado.getTipoCre() );
		values.put("especialidade", credenciado.getEspecialidade() );
		values.put("cep", credenciado.getCep() );
		values.put("endCod", credenciado.getEndCod() );
		getWritableDatabase().insert(TABELA, null, values);
	}
	
}
