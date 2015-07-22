package cadastro.caelum.com.br.util;

import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by Carlos Eduardo on 21/07/2015.
 */
public class Configurador implements GooglePlayServicesClient.ConnectionCallbacks {

    private AtualizadorDeLocalizacao atualizadorDeLocalizacao;

    public Configurador(AtualizadorDeLocalizacao atualizadorDeLocalizacao) {
        this.atualizadorDeLocalizacao = atualizadorDeLocalizacao;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50);

        atualizadorDeLocalizacao.inicia(request);
    }

    @Override
    public void onDisconnected() {

    }
}
