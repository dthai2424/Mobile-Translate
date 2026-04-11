package app.mobiletranslate.util;

import app.mobiletranslate.dto.LanguageDTO;
import app.mobiletranslate.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageUtil {
    public LanguageDTO entityToModel(Language language){
        LanguageDTO languageDTO= LanguageDTO.builder().languageId(language.getLanguageId()).languageName(language.getLanguageName()).active(language.isActive()).build();
        return languageDTO;
    }
    public Language modelToEntity(LanguageDTO languageDTO){
        Language language=Language.builder().languageId(languageDTO.getLanguageId()).languageName(languageDTO.getLanguageName()).active(languageDTO.isActive()).build();
        return language;
    }
    public boolean validate(String languageId, String languageName){
        if(languageId==null || languageName==null) return false;
        if(languageId.isEmpty() || languageName.isEmpty()) return false;
        return true;
    }
}
