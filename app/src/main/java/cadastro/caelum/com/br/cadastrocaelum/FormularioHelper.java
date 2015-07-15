package cadastro.caelum.com.br.cadastrocaelum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 11/07/15.
 */
public class FormularioHelper {

    private Aluno aluno = new Aluno();
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtSite;
    private SeekBar sbNota;
    private ImageView ibFoto;

    public FormularioHelper(FormularioActivity activity) {
        edtNome = (EditText) activity.findViewById(R.id.nome);
        edtSite = (EditText) activity.findViewById(R.id.site);
        edtEndereco = (EditText) activity.findViewById(R.id.endereco);
        edtTelefone = (EditText) activity.findViewById(R.id.telefone);
        sbNota = (SeekBar) activity.findViewById(R.id.nota);
        ibFoto = (ImageView) activity.findViewById(R.id.foto);
    }

    public ImageView getIbFoto() {
        return ibFoto;
    }

    public Aluno getAluno() {
        aluno.setNome(edtNome.getText().toString());
        aluno.setSite(edtSite.getText().toString());
        aluno.setEndereco(edtEndereco.getText().toString());
        aluno.setTelefone(edtTelefone.getText().toString());
        aluno.setNota(sbNota.getProgress());

        return aluno;
    }

    public void setAlunoFormulario(Aluno aluno) {
        this.aluno = aluno;
        edtNome.setText(aluno.getNome());
        edtEndereco.setText(aluno.getEndereco());
        edtSite.setText(aluno.getSite());
        edtTelefone.setText(aluno.getTelefone());
        sbNota.setProgress((int) aluno.getNota());

        if (aluno.getCaminhoFoto() != null) {
            carregaImagem(aluno.getCaminhoFoto());
        }
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
        ibFoto.setImageBitmap(imagemReduzida);
    }

    public void setLocalArquivo(String localArquivo) {
        aluno.setCaminhoFoto(localArquivo);
    }
}
