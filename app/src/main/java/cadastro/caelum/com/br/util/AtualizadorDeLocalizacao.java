package cadastro.caelum.com.br.util;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

import cadastro.caelum.com.br.fragment.MapaFragment;

/**
 * Created by Carlos Eduardo on 21/07/2015.
 */
public class AtualizadorDeLocalizacao implements LocationListener {

    private LocationClient cliente;
    private MapaFragment mapa;

    public AtualizadorDeLocalizacao(Context context, MapaFragment mapa) {
        this.mapa = mapa;

        Configurador configurador = new Configurador(this);
        this.cliente = new LocationClient(context, configurador, null);
        this.cliente.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        mapa.centralizaMapa(coordenada);
    }

    public void inicia(LocationRequest request) {
        cliente.requestLocationUpdates(request, this);
    }

    public void cancela() {
        cliente.removeLocationUpdates(this);
        cliente.disconnect();
    }
}
