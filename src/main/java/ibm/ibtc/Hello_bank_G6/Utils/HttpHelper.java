package ibm.ibtc.Hello_bank_G6.Utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public final class HttpHelper {
    public Boolean ValidarCpf(String cpf){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("cpf", cpf);

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
        String url = "https://5ayjl94kkc.execute-api.us-east-1.amazonaws.com/default/validador_cpf";

        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
        return (response.getBody().equals("true")) ? true : false;
    }

}
