package cadastro.caelum.com.br.cadastrocaelum;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import cadastro.caelum.com.br.fragment.MapaFragment;

/**
 * Created by carloseduardo on 20/07/15.
 */
public class MostraAlunosActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        MapaFragment mapFragment = new MapaFragment();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mapa, mapFragment);
        tx.commit();
    }
}
