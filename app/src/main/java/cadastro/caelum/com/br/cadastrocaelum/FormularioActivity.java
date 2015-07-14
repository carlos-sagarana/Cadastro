package cadastro.caelum.com.br.cadastrocaelum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.extra.Extras;
import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 11/07/15.
 */
public class FormularioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        final FormularioHelper formularioHelper = new FormularioHelper(FormularioActivity.this);
        final Aluno alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECIONADO);
        final Button botao = (Button) findViewById(R.id.botao);

        if (alunoParaSerAlterado != null) {
            botao.setText("Alterar");
            formularioHelper.setAlunoFormulario(alunoParaSerAlterado);
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = formularioHelper.getAluno();
                AlunoDAO dao = new AlunoDAO(new DBHelper(FormularioActivity.this));

                if (alunoParaSerAlterado != null) {
                    aluno.setId(alunoParaSerAlterado.getId());
                    dao.atualizar(aluno);
                } else {
                    dao.insere(aluno);
                }

                dao.closeConnection();
                finish();
            }
        });
    }
}
