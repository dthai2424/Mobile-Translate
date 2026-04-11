package app.mobiletranslate.service;

import app.mobiletranslate.dto.LanguageDTO;
import app.mobiletranslate.entity.Language;
import app.mobiletranslate.exception.AlreadyExistException;
import app.mobiletranslate.exception.InvalidFormatException;
import app.mobiletranslate.exception.NotFoundException;
import app.mobiletranslate.repository.LanguageRepository;
import app.mobiletranslate.util.LanguageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService{
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageUtil languageUtil;
    @Override
    public LanguageDTO create(String languageId, String languageName) {
        return null;
    }



    @Override
    public LanguageDTO getLanguageById(String languageId,boolean active) {
        Optional<Language> language=languageRepository.findByLanguageIdAndActive(languageId,active);
        if(language.isEmpty()) throw new NotFoundException("Khong tim thay ngon ngu nay!");
        LanguageDTO languageDTO= languageUtil.entityToModel(language.get());
        return languageDTO;
    }
    @Override
    public Language getEntityLanguageById(String languageId,boolean active) {
        Optional<Language> language=languageRepository.findByLanguageIdAndActive(languageId,active);
        if(language.isEmpty()) throw new NotFoundException("Khong tim thay ngon ngu nay!");
        return language.get();
    }

    @Override
    public LanguageDTO createLanguage(String languageId, String languageName) {
        if(!languageUtil.validate(languageId,languageName)) throw new InvalidFormatException("Du lieu khong hop le!");
        if(languageRepository.existsByLanguageIdAndActive(languageId,true)) throw new AlreadyExistException("Ngon ngu da ton tai!");
        LanguageDTO languageDTO=LanguageDTO.builder().languageId(languageId).languageName(languageName).active(true).build();
        Language language=languageUtil.modelToEntity(languageDTO);
        languageRepository.save(language);
        return languageDTO;
    }

    @Override
    public LanguageDTO updateLanguage(String languageId, String languageName, boolean active) {
        if(!languageRepository.existsByLanguageId(languageId)) throw new NotFoundException("Ngon ngu da ton tai!");
        if(!languageUtil.validate(languageId,languageName)) throw new InvalidFormatException("Du lieu khong hop le!");
        Language language=languageRepository.findByLanguageIdAndActive(languageId,true).get();
        language.setLanguageName(languageName);
        language.setActive(active);
        languageRepository.save(language);
        LanguageDTO languageDTO= languageUtil.entityToModel(language);
        return languageDTO;

    }
}
