package cadastro.caelum.com.br.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import cadastro.caelum.com.br.converter.AlunoConverter;
import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.extra.Extras;
import cadastro.caelum.com.br.modelo.Aluno;
import cadastro.caelum.com.br.network.WebClient;

/**
 * Created by carloseduardo on 18/07/15.
 */
public class EnviaAlunosTask extends AsyncTask<Object, Object, String>{

    private Context context;
    private ProgressDialog progress;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Aguarde...", "Estamos enviando os dados para o servidor.");
    }

    @Override
    protected String doInBackground(Object[] params) {
        AlunoDAO dao = new AlunoDAO(new DBHelper(context));
        List<Aluno> alunos = dao.getAlunos();
        dao.closeConnection();

        String json = new AlunoConverter().toJSON(alunos);

        WebClient webClient = new WebClient(Extras.SERVIDOR);
        String resposta = webClient.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
