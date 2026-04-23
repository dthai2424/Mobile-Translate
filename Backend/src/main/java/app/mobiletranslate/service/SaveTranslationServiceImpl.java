package app.mobiletranslate.service;

import app.mobiletranslate.dto.SavedTranslationDTO;
import app.mobiletranslate.dto.SavedTranslationRequestDTO;
import app.mobiletranslate.dto.SavedTranslationResponseDTO;
import app.mobiletranslate.entity.Language;
import app.mobiletranslate.entity.SavedTranslation;
import app.mobiletranslate.entity.User;
import app.mobiletranslate.exception.NotFoundException;
import app.mobiletranslate.repository.LanguageRepository;
import app.mobiletranslate.repository.SavedTranslationRepository;
import app.mobiletranslate.repository.UserRepository;
import app.mobiletranslate.util.SavedTranslationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<SavedTranslationResponseDTO> getUserActiveSavedTranslation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return savedTranslationRepository
                .findAllByUser_UserIdAndActiveOrderByCreatedAtDesc(user.getUserId(), true)
                .stream()
                .map(savedTranslationUtil::entityToResponse)
                .toList();
    }

    @Override
    public List<SavedTranslationDTO> getAllSavedTranslation(boolean active) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        List<SavedTranslation> savedTranslationList = savedTranslationRepository
                .findAllByUser_UserIdAndActiveOrderByCreatedAtDesc(user.getUserId(),true);

        List<SavedTranslationDTO> list = new ArrayList<>();
        for(SavedTranslation savedTranslation : savedTranslationList){
            list.add(savedTranslationUtil.entityToModel(savedTranslation));
        }
        return list;
    }


    @Override
    public List<SavedTranslationDTO> getUserSavedTranslationBySourceLanguage(String sourceLang) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        List<SavedTranslation> savedTranslationList = savedTranslationRepository
                .findAllByUser_UserIdAndSourceLanguage_LanguageIdAndActiveOrderByCreatedAtDesc(user.getUserId(), sourceLang,true);

        List<SavedTranslationDTO> list = new ArrayList<>();
        for(SavedTranslation savedTranslation : savedTranslationList){
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
    @Override
    @Transactional
    public SavedTranslation save(SavedTranslationRequestDTO requestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Sử dụng username lấy từ token để tìm User
        System.out.println("sourcelang: "+requestDTO.getSourceLang());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Language sourceLang = languageRepository.findByLanguageIdAndActive(requestDTO.getSourceLang(),true)
                .orElseThrow(() -> new RuntimeException("Source language not found"));

        Language translateLang = languageRepository.findByLanguageIdAndActive(requestDTO.getTargetLang(),true)
                .orElseThrow(() -> new RuntimeException("Translate language not found"));

        SavedTranslation savedTranslation = new SavedTranslation();
        savedTranslation.setUser(user);
        savedTranslation.setSourceLanguage(sourceLang);
        savedTranslation.setSourceText(requestDTO.getSourceText());
        savedTranslation.setTargetLanguage(translateLang);
        savedTranslation.setTargetText(requestDTO.getTargetText());
        savedTranslation.setActive(true);

        return savedTranslationRepository.save(savedTranslation);
    }
}
