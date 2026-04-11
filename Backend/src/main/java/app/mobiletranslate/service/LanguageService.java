package app.mobiletranslate.service;

import app.mobiletranslate.dto.LanguageDTO;
import app.mobiletranslate.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;


public interface LanguageService
{
    public LanguageDTO create(String languageId, String languageName);
    public LanguageDTO getLanguageById(String languageId,boolean active);
    public Language getEntityLanguageById(String languageId,boolean active);
    public LanguageDTO createLanguage(String languageId, String languageName);
    public LanguageDTO updateLanguage(String languageId, String languageName,boolean active);
}
