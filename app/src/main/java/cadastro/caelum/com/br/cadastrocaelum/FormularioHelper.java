package cadastro.caelum.com.br.cadastrocaelum;

import android.widget.EditText;
import android.widget.SeekBar;

import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 11/07/15.
 */
public class FormularioHelper {

    private Aluno aluno;
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtSite;
    private SeekBar sbNota;

    public FormularioHelper(FormularioActivity activity) {
        edtNome = (EditText) activity.findViewById(R.id.nome);
        edtSite = (EditText) activity.findViewById(R.id.site);
        edtEndereco = (EditText) activity.findViewById(R.id.endereco);
        edtTelefone = (EditText) activity.findViewById(R.id.telefone);
        sbNota = (SeekBar) activity.findViewById(R.id.nota);
    }

    public Aluno getAluno() {
        aluno = new Aluno();
        aluno.setNome(edtNome.getText().toString());
        aluno.setSite(edtSite.getText().toString());
        aluno.setEndereco(edtEndereco.getText().toString());
        aluno.setTelefone(edtTelefone.getText().toString());
        aluno.setNota(sbNota.getProgress());

        return aluno;
    }

    public void setAlunoFormulario(Aluno aluno) {
        edtNome.setText(aluno.getNome());
        edtEndereco.setText(aluno.getEndereco());
        edtSite.setText(aluno.getSite());
        edtTelefone.setText(aluno.getTelefone());
        sbNota.setProgress((int) aluno.getNota());
    }

}
