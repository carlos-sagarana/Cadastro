package cadastro.caelum.com.br.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cadastro.caelum.com.br.cadastrocaelum.R;
import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by Carlos Eduardo on 15/07/2015.
 */
public class AlunoAdapter extends BaseAdapter {

    List<Aluno> alunos;
    Activity activity;

    public AlunoAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = activity.getLayoutInflater().inflate(R.layout.list_aluno_item, null);

        if (position % 2 == 0) {
            linha.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }

        Aluno aluno = (Aluno) getItem(position);

        TextView nome = (TextView) linha.findViewById(R.id.nomeItem);
        nome.setText(aluno.getNome());
        ImageView imagem = (ImageView) linha.findViewById(R.id.fotoItem);
        if (aluno.getCaminhoFoto() != null) {
            Bitmap foto = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            Bitmap fotoReduzida = Bitmap.createScaledBitmap(foto, 100, 100, true);
            imagem.setImageBitmap(fotoReduzida);
        } else {
            imagem.setImageResource(R.drawable.ic_no_image);
        }

        TextView telefone = (TextView) linha.findViewById(R.id.telefoneItem);
        TextView site = (TextView) linha.findViewById(R.id.siteItem);

        if (telefone != null) {
            if (aluno.getTelefone() != null) {
                telefone.setText(aluno.getTelefone());
            }

            if (aluno.getSite() != null) {
                site.setText(aluno.getSite());
            }
        }

        return linha;
    }
}
