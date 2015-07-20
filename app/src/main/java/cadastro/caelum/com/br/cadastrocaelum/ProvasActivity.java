package cadastro.caelum.com.br.cadastrocaelum;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import cadastro.caelum.com.br.fragment.DetalhesProvaFragment;
import cadastro.caelum.com.br.fragment.ListasProvasFragment;
import cadastro.caelum.com.br.modelo.Prova;

/**
 * Created by carloseduardo on 19/07/15.
 */
public class ProvasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isTablet()) {
            transaction.replace(R.id.provas_lista, new ListasProvasFragment())
                .replace(R.id.provas_detalhe, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListasProvasFragment());
        }
        transaction.commit();
    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionaProva(Prova prova) {
        Bundle argumentos = new Bundle();
        argumentos.putSerializable("prova", prova);

        DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
        detalhesProva.setArguments(argumentos);

        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();

        if (isTablet()) {
            transaction.replace(R.id.provas_detalhe, detalhesProva);
        } else {
            transaction.replace(R.id.provas_view, detalhesProva);
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
