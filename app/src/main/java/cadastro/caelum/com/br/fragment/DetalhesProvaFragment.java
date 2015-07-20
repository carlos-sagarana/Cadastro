package cadastro.caelum.com.br.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cadastro.caelum.com.br.cadastrocaelum.ProvasActivity;
import cadastro.caelum.com.br.cadastrocaelum.R;
import cadastro.caelum.com.br.modelo.Prova;

/**
 * Created by carloseduardo on 19/07/15.
 */
public class DetalhesProvaFragment extends Fragment{

    private Prova prova;
    private TextView materia;
    private TextView data;
    private ListView topicos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.detalhes_prova, container, false);

        if (getArguments() != null) {
            getViews(layout);
            populaViews();
        }

        return layout;
    }

    public void getViews(View layout) {
        materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaViews() {
        prova = (Prova) getArguments().getSerializable("prova");

        if (prova != null) {
            materia.setText(prova.getMateria());
            data.setText(prova.getData());

            ProvasActivity activity = (ProvasActivity) getActivity();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                    android.R.layout.simple_list_item_1, prova.getTopicos());

            topicos.setAdapter(adapter);
        }
    }
}
