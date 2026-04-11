package app.mobiletranslate.service;

import app.mobiletranslate.dto.SavedTranslationDTO;
import app.mobiletranslate.entity.SavedTranslation;

import java.util.Date;
import java.util.List;

public interface SavedTranslationService {
    public List<SavedTranslationDTO> getUserActiveSavedTranslation(int userId);
    public List<SavedTranslationDTO> getAllSavedTranslation(boolean active);
    public List<SavedTranslationDTO> getUserSavedTranslationBySourceLanguage(int userId,String sourceLangId);

    public boolean saveTranslation(SavedTranslationDTO  savedTranslationDTO);
    public boolean deleteSavedTranslation(SavedTranslationDTO savedTranslationDTO);

}
