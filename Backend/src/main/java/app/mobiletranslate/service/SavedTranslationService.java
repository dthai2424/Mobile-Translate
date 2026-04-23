package app.mobiletranslate.service;

import app.mobiletranslate.dto.SavedTranslationDTO;
import app.mobiletranslate.dto.SavedTranslationRequestDTO;
import app.mobiletranslate.dto.SavedTranslationResponseDTO;
import app.mobiletranslate.entity.SavedTranslation;

import java.util.Date;
import java.util.List;

public interface SavedTranslationService {
    public List<SavedTranslationResponseDTO> getUserActiveSavedTranslation();
    public List<SavedTranslationDTO> getAllSavedTranslation(boolean active);
    public List<SavedTranslationDTO> getUserSavedTranslationBySourceLanguage(String sourceLangId);
    public SavedTranslation save(SavedTranslationRequestDTO requestDTO);
    public boolean saveTranslation(SavedTranslationDTO  savedTranslationDTO);
    public boolean deleteSavedTranslation(SavedTranslationDTO savedTranslationDTO);

}
