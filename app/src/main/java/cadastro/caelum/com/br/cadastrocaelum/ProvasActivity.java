package cadastro.caelum.com.br.cadastrocaelum;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import cadastro.caelum.com.br.fragment.ListasProvasFragment;

/**
 * Created by carloseduardo on 19/07/15.
 */
public class ProvasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.provas_view, new ListasProvasFragment());
        transaction.commit();
    }
}
