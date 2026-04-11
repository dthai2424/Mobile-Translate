package app.mobiletranslate.service;

import app.mobiletranslate.dto.LanguageDTO;
import app.mobiletranslate.dto.SavedTranslationDTO;
import app.mobiletranslate.entity.Language;
import app.mobiletranslate.entity.SavedTranslation;
import app.mobiletranslate.entity.User;
import app.mobiletranslate.exception.NotFoundException;
import app.mobiletranslate.repository.LanguageRepository;
import app.mobiletranslate.repository.SavedTranslationRepository;
import app.mobiletranslate.repository.UserRepository;
import app.mobiletranslate.util.SavedTranslationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaveTranslationServiceImpl implements SavedTranslationService
{
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LanguageServiceImpl languageService;
    @Autowired
    private SavedTranslationRepository savedTranslationRepository;
    @Autowired
    private SavedTranslationUtil savedTranslationUtil;


    @Override
    public List<SavedTranslationDTO> getUserActiveSavedTranslation(int userId) {
        List<SavedTranslation> savedTranslationList=savedTranslationRepository.findAllByUser_UserIdAndActiveOrderByCreatedAtDesc(userId,true);
        List<SavedTranslationDTO> list=new ArrayList<>();
        for(SavedTranslation savedTranslation:savedTranslationList){
            list.add(savedTranslationUtil.entityToModel(savedTranslation));
        }
        return list;
    }

    @Override
    public List<SavedTranslationDTO> getAllSavedTranslation(boolean active) {
        return List.of();
    }


    @Override
    public List<SavedTranslationDTO> getUserSavedTranslationBySourceLanguage(int userId, String sourceLangId) {
        List<SavedTranslation> savedTranslationList=savedTranslationRepository.findAllByUser_UserIdAndSourceLanguage_LanguageIdAndActiveOrderByCreatedAtDesc(userId,sourceLangId,true);
        List<SavedTranslationDTO> list=new ArrayList<>();
        for(SavedTranslation savedTranslation:savedTranslationList){
            list.add(savedTranslationUtil.entityToModel(savedTranslation));
        }
        return list;
    }


    @Override
    public boolean saveTranslation(SavedTranslationDTO savedTranslationDTO) {
        User user=userService.getUserEntityById(savedTranslationDTO.getUserId(),true);

        Language sourceLang=languageService.getEntityLanguageById(savedTranslationDTO.getSourceLangId(),true);

        Language  targetLang=languageService.getEntityLanguageById(savedTranslationDTO.getTargetLangId(),true);

        SavedTranslation savedTranslation=savedTranslationUtil.modelToEntity(savedTranslationDTO,user,sourceLang,targetLang,true);
        savedTranslationRepository.save(savedTranslation);
        return true;

    }

    @Override
    public boolean deleteSavedTranslation(SavedTranslationDTO savedTranslationDTO) {
        Optional<SavedTranslation> savedTranslation=savedTranslationRepository.findById(savedTranslationDTO.getSavedTranslationId());
        if(savedTranslation.isEmpty()) throw new NotFoundException("Khong tim thay SavedTranslation nay");
        savedTranslation.get().setActive(false);
        savedTranslationRepository.save(savedTranslation.get());

        return true;
    }
}
