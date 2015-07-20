package cadastro.caelum.com.br.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cadastro.caelum.com.br.cadastrocaelum.R;
import cadastro.caelum.com.br.modelo.Prova;

/**
 * Created by carloseduardo on 19/07/15.
 */
public class ListasProvasFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.provas_lista, container, false);

        ListView listProvas = (ListView) layout.findViewById(R.id.lista_provas);

        Prova prova1 = new Prova("17/08/2015", "Matemática");
        prova1.setTopicos(Arrays.asList("Algebra Linear", "Pagressões Geométricas", "Progressões Aritméticas"));

        Prova prova2 = new Prova("28/08/2015", "Português");
        prova1.setTopicos(Arrays.asList("Análise Sintática", "Redação"));

        List<Prova> provas = Arrays.asList(prova1, prova2);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, provas);

        listProvas.setAdapter(adapter);

        listProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);

                Toast.makeText(getActivity(), prova.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return layout;
    }
}
