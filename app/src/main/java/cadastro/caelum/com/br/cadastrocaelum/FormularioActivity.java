package cadastro.caelum.com.br.cadastrocaelum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.extra.Extras;
import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 11/07/15.
 */
public class FormularioActivity extends Activity {

    private FormularioHelper formularioHelper;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        formularioHelper = new FormularioHelper(FormularioActivity.this);
        final Aluno alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECIONADO);
        final Button botao = (Button) findViewById(R.id.botao);

        ImageView foto = formularioHelper.getIbFoto();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localArquivoFoto)));
                startActivityForResult(intentFoto, Extras.ARQUIVO_FOTO);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Extras.ARQUIVO_FOTO:
                if (resultCode == RESULT_OK) {
                    formularioHelper.carregaImagem(localArquivoFoto);
                    formularioHelper.setLocalArquivo(localArquivoFoto);
                } else {
                    localArquivoFoto = null;
                }
        }
    }
}
