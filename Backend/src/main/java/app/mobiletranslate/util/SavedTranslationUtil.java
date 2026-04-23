package app.mobiletranslate.util;

import app.mobiletranslate.dto.*;
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
    public SavedTranslationResponseDTO entityToResponse(SavedTranslation entity) {
        return SavedTranslationResponseDTO.builder()
                .savedTranslationId(entity.getSavedTranslationId())
                .sourceLang(entity.getSourceLanguage().getLanguageId())
                .sourceText(entity.getSourceText())
                .targetLang(entity.getTargetLanguage().getLanguageId())
                .targetText(entity.getTargetText())
                .createdAt(entity.getCreatedAt())
                .build();
    }


    public SavedTranslation requestToEntity(SavedTranslationRequestDTO dto,
                                            User user,
                                            Language sourceLang,
                                            Language targetLang) {

        return SavedTranslation.builder()
                .user(user)
                .sourceLanguage(sourceLang)
                .sourceText(dto.getSourceText())
                .targetLanguage(targetLang)
                .targetText(dto.getTargetText())
                .active(true)
                .build();
    }
}
