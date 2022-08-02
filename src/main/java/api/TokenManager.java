package api;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashMap;

import static api.RestResource.postAccount;

@Slf4j
public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                log.info("Renewing the token...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds);
            }else {
                log.info("Token is good to use");
            }
        }catch (Exception e){
            throw new RuntimeException("Abort ! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken() {
        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("client_id","7a8a61a0cd8e4b00b2480eb032b7d30e");
        formParams.put("client_secret","6286ef4895464d989dc367e96faa72e8");
        formParams.put("refresh_token","AQAhIbj_gzuJDMMIdzSlxBdklHJiOdaO_tx7DFdx0b2HS5YI1ugZhV2drLZi5OZ3_fyve485jTHIe3tj7BBHAbYZiTPmzQns88LtC3p1sMzVYlyhKNZ1RrSVF87AXUAg77g");
        formParams.put("grant_type","refresh_token");
        Response response = postAccount(formParams);
        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT ! Renew token failed");
        }
        return response;
    }
}
