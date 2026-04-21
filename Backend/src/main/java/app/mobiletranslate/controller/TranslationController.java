package app.mobiletranslate.controller;

import app.mobiletranslate.dto.APITranslationRequest;
import app.mobiletranslate.dto.APITranslationResponse;
import app.mobiletranslate.dto.TranslationRequestDTO;
import app.mobiletranslate.dto.TranslationResponseDTO;
import app.mobiletranslate.exception.InvalidFormatException;
import app.mobiletranslate.service.LibreTranslateService;
import app.mobiletranslate.util.TranslationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/translate")
public class TranslationController {
    @Autowired
    LibreTranslateService libreTranslateService;
    @Autowired
    TranslationUtil translationUtil;
    @PostMapping
    public ResponseEntity<?> translate(@RequestBody TranslationRequestDTO clientRequest){
        if(!translationUtil.validate(clientRequest)) throw new InvalidFormatException("Chon lai ngon ngu hoac viet lai van ban can dich");
        APITranslationRequest request= translationUtil.clientRequestToApiRequest(clientRequest);
        APITranslationResponse response=libreTranslateService.getTranslation(request);
        TranslationResponseDTO clientResponse=translationUtil.apiResponseToClientResponse(response);
        return ResponseEntity.ok(clientResponse);
    }

}
