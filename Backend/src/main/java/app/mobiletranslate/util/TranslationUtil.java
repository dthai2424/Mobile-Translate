package app.mobiletranslate.util;

import app.mobiletranslate.dto.APITranslationRequest;
import app.mobiletranslate.dto.APITranslationResponse;
import app.mobiletranslate.dto.TranslationRequestDTO;
import app.mobiletranslate.dto.TranslationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TranslationUtil {

    public boolean validate(TranslationRequestDTO request){
        if(request.getSourceLangId()==null||request.getSourceText()==null||request.getTargetLangId()==null)
            return false;
        return true;
    }
    public APITranslationRequest clientRequestToApiRequest(TranslationRequestDTO request){
        APITranslationRequest apiRequest=APITranslationRequest.builder().sourceLangId(request.getSourceLangId()).sourceText(request.getSourceText()).targetLangId(request.getTargetLangId()).api_key("").build();
        return apiRequest;
    }
    public TranslationResponseDTO apiResponseToClientResponse(APITranslationResponse response){
        TranslationResponseDTO clientResponse= TranslationResponseDTO.builder().targetText(response.getTargetText()).build();
        return clientResponse;
    }
}
