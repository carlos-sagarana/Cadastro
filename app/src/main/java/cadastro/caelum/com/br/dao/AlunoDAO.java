package cadastro.caelum.com.br.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 11/07/15.
 */
public class AlunoDAO {

    private final DBHelper helper;
    private static final String TABELA = "Alunos";

    public AlunoDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void insere(Aluno aluno) {
        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("nota", aluno.getNota());
        valores.put("endereco", aluno.getEndereco());
        valores.put("site", aluno.getSite());
        valores.put("telefone", aluno.getTelefone());
        valores.put("foto", aluno.getCaminhoFoto());

        helper.getWritableDatabase().insert(TABELA, null, valores);
    }

    public List<Aluno> getAlunos() {
        String sql = "SELECT * FROM Alunos";
        Cursor cursor = helper.getReadableDatabase().rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<>();

        while(cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));

            alunos.add(aluno);
        }

        return alunos;
    }

    private ContentValues toValues(Aluno aluno) {
        ContentValues cv = new ContentValues();
        cv.put("nome", aluno.getNome());
        cv.put("endereco", aluno.getEndereco());
        cv.put("telefone", aluno.getTelefone());
        cv.put("site", aluno.getSite());
        cv.put("foto", aluno.getCaminhoFoto());
        cv.put("nota", aluno.getNota());

        return cv;
    }

    public void closeConnection() {
        helper.close();
    }

    public void deletar(Aluno aluno) {
        String[] args = {String.valueOf(aluno.getId())};
        helper.getWritableDatabase().delete(TABELA, "id=?", args);
    }

    public void atualizar(Aluno aluno) {
        ContentValues cv = toValues(aluno);
        String[] args = {String.valueOf(aluno.getId())};
        helper.getWritableDatabase().update(TABELA, cv, "id=?", args);
    }
}
