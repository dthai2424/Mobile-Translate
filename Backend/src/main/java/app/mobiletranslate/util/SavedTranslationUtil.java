package app.mobiletranslate.util;

import app.mobiletranslate.dto.LanguageDTO;
import app.mobiletranslate.dto.SavedTranslationDTO;
import app.mobiletranslate.dto.UserDTO;
import app.mobiletranslate.entity.Language;
import app.mobiletranslate.entity.SavedTranslation;
import app.mobiletranslate.entity.User;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Builder
public class SavedTranslationUtil {
    public SavedTranslationDTO entityToModel(SavedTranslation savedTranslation) {
        SavedTranslationDTO savedTranslationDTO = SavedTranslationDTO.builder().userId(savedTranslation.getUser().getUserId()).sourceLangId(savedTranslation.getSourceLanguage().getLanguageId()).sourceText(savedTranslation.getSourceText()).targetLangId(savedTranslation.getTargetLanguage().getLanguageId()).targetText(savedTranslation.getTargetText()).createdAt(savedTranslation.getCreatedAt()).savedTranslationId(savedTranslation.getSavedTranslationId()).build();
        return savedTranslationDTO;
    }
    public SavedTranslation modelToEntity(SavedTranslationDTO savedTranslationDTO, User user,Language source, Language target, boolean active) {
        SavedTranslation savedTranslation = SavedTranslation.builder().user(user).sourceLanguage(source).sourceText(savedTranslationDTO.getSourceText()).targetLanguage(target).targetText(savedTranslationDTO.getTargetText()).active(active).build();
        return savedTranslation;
    }
}
