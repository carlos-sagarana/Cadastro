package cadastro.caelum.com.br.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carloseduardo on 12/07/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA +
                "(id INTEGER PRIMARY KEY, " +
                "nome TEXT UNIQUE NOT NULL, " +
                "telefone TEXT, " +
                "endereco TEXT, " +
                "site TEXT, " +
                "nota REAL, " +
                "foto TEXT" +
                ");";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }
}
