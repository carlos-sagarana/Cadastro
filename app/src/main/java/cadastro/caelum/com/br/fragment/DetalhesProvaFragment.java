package cadastro.caelum.com.br.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cadastro.caelum.com.br.cadastrocaelum.R;

/**
 * Created by carloseduardo on 19/07/15.
 */
public class DetalhesProvaFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.detalhes_prova, container, false);

        return layout;
    }
}
