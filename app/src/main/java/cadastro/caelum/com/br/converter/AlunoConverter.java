package cadastro.caelum.com.br.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 18/07/15.
 */
public class AlunoConverter {

    public String toJSON(List<Aluno> alunos) {
        try {
            JSONStringer jsonStringer = new JSONStringer();

            jsonStringer.object().key("list").array().
                    object().key("aluno").array();

            for (Aluno aluno : alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("nota").value(aluno.getNota())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .endObject();
            }

            return jsonStringer.endArray().endObject()
                    .endArray().endObject().toString();
        } catch (JSONException e) {
            new RuntimeException(e);
            return null;
        }
    }

}
