package cadastro.caelum.com.br.fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.modelo.Aluno;
import cadastro.caelum.com.br.util.AtualizadorDeLocalizacao;
import cadastro.caelum.com.br.util.Localizador;

/**
 * Created by carloseduardo on 20/07/15.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();
        AlunoDAO dao = new AlunoDAO(new DBHelper(getActivity()));
        List<Aluno> alunos = dao.getAlunos();
        dao.closeConnection();

        Localizador localizador = new Localizador(getActivity());
        new AtualizadorDeLocalizacao(getActivity(), this);

        for (Aluno aluno : alunos) {
            MarkerOptions marker = new MarkerOptions();
            LatLng coordenada = localizador.getCoordenada(aluno.getEndereco());
            if (coordenada != null) {
                marker.title(aluno.getNome()).position(localizador.getCoordenada(aluno.getEndereco()));
                getMap().addMarker(marker);
            }
        }
    }

    public void centralizaMapa(LatLng local) {
        GoogleMap map = getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
    }
}
