package com.example.swagger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Base64;
import java.util.Date;


public class UsageQuotaTest {
    private static final Log log = LogFactory.getLog(UsageQuotaTest.class);

    public static void main(String[] args) throws InterruptedException {
        getNewBearer();
    }

    private static void getNewBearer() throws InterruptedException {

        int i = 0;
        for (i = 0; i < 1; i++) {

//            Testing tenant application ibx-order-api
//            curl --request POST \
//            --url https://budbee-testing.eu.auth0.com/oauth/token \
//            --header 'content-type: application/json' \
//            --data '{"client_id":"RIN0w9o9X00ecYzpJukIQvUXWvAZi7ga","client_secret":"Ds7x0l9e_yAzj8esai2R2u__ij9dG3FD3nKOSmV1qaxwJxQSkEmaRE4yacWRUlnI","audience":"https://webapi.budbee.com/m2m","grant_type":"client_credentials"}'
            HttpResponse<String> response = Unirest.post("https://budbee-testing.eu.auth0.com/oauth/token")
                    .header("content-type", "application/json")
                    .body("{\"client_id\":\"RIN0w9o9X00ecYzpJukIQvUXWvAZi7ga\",\"client_secret\":\"Ds7x0l9e_yAzj8esai2R2u__ij9dG3FD3nKOSmV1qaxwJxQSkEmaRE4yacWRUlnI\",\"audience\":\"https://webapi.budbee.com/m2m\",\"grant_type\":\"client_credentials\"}")
                    .asString();


//            Dev tenant: Dev-gonxo3aktwr5sfpc
//            HttpResponse<String> response = Unirest.post("https://dev-gonxo3aktwr5sfpc.us.auth0.com/oauth/token")
//                    .header("content-type", "application/json")
//                    .body("{\"client_id\":\"INvXECZp3H0ajwAl7yaozgw5pQSObhg6\",\"client_secret\":\"vAbxMVgGAcFSmxaEL0CqfqRW1pBXlO9VSIFimpwpCsxNof1xdfaBwrke-hNMLfsy\",\"audience\":\"http://localhost:1110\",\"grant_type\":\"client_credentials\"}")
//                    .asString();

            JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
            String token = jsonObject.get("access_token").getAsString();
            log.error("Token: " + token);
            String[] chunks = token.split("\\.");
            String payloadEncoded = chunks[1];
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(payloadEncoded));
//        System.out.println(payload);
            jsonObject = new JsonParser().parse(payload).getAsJsonObject();
            String exp = jsonObject.get("exp").getAsString();
//            System.out.println("Token expiry: " + new Date(Long.parseLong(exp) * 1000));
            log.error("Attempt: " + i + ",   Token expiry: " + new Date(Long.parseLong(exp) * 1000));
            Thread.sleep(1000);
        }

    }
}
