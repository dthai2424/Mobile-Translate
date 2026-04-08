package app.mobiletranslate.repository;

import app.mobiletranslate.entity.Language;
import app.mobiletranslate.entity.SavedTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SavedTranslationRepository extends JpaRepository<SavedTranslation, Integer> {
    Optional<SavedTranslation> findById(int id);
    List<SavedTranslation> findAllByUser_UserIdAndActiveOrderByCreatedAtAsc(Integer userId,boolean active);
    List<SavedTranslation> findAllByUser_UserIdAndActiveOrderByCreatedAtDesc(Integer userId,boolean active);
    List<SavedTranslation> findAllByUser_UserIdAndSourceLanguage_LanguageIdAndActiveOrderByCreatedAtAsc(Integer userId, String sourceLanguage,boolean active);
    List<SavedTranslation> findAllByUser_UserIdAndSourceLanguage_LanguageIdAndActiveOrderByCreatedAtDesc(Integer userId, String sourceLanguage,boolean active);
}
