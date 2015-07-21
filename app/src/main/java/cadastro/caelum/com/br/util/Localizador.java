package cadastro.caelum.com.br.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Carlos Eduardo on 20/07/2015.
 */
public class Localizador {

    private Geocoder geoCoder;

    public Localizador(Context context) {
        geoCoder = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco) {
        try {
            List<Address> listaEnderecos = geoCoder.getFromLocationName(endereco, 1);
            if (!listaEnderecos.isEmpty()) {
                Address address = listaEnderecos.get(0);
                return new LatLng(address.getLatitude(), address.getLongitude());
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
