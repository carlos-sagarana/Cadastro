package cadastro.caelum.com.br.network;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by carloseduardo on 18/07/15.
 */
public class WebClient {

    private String url;

    public WebClient(String url) {
        this.url = url;
    }

    public String post(String json) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost(url);

            post.setEntity(new StringEntity(json));
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(post);
            String jsonResposta = EntityUtils.toString(response.getEntity());
            return jsonResposta;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
