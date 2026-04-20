package app.mobiletranslate.service;

import app.mobiletranslate.dto.APITranslationRequest;
import app.mobiletranslate.dto.APITranslationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class LibreTranslateService {
        private static final String API_URL = "http://localhost:5000/translate";
        private final RestClient restClient;

        public LibreTranslateService(@Value("${libretranslate.api.url") String apiUrl){
                this.restClient=RestClient.builder().baseUrl(apiUrl).build();
        }
        public APITranslationResponse getTranslation(APITranslationRequest request) {
                APITranslationResponse response=restClient.post().uri("/translate").contentType(MediaType.APPLICATION_JSON).body(request).retrieve().body(APITranslationResponse.class);
                return response;
        }

}
