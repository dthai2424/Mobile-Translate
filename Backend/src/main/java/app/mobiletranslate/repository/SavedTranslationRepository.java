package app.mobiletranslate.repository;

import app.mobiletranslate.entity.Language;
import app.mobiletranslate.entity.SavedTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedTranslationRepository extends JpaRepository<SavedTranslation, Integer> {
    Optional<SavedTranslation> findById(int id);
    List<SavedTranslation> findAllByUser_UserIdAndActiveOrderByCreatedAtAsc(Integer userId,boolean active);
    List<SavedTranslation> findAllByUser_UserIdAndActiveOrderByCreatedAtDesc(Integer userId,boolean active);
    List<SavedTranslation> findAllByUser_UserIdAndSourceLanguage_LangugageIdAndActiveOrderByCreatedAtAsc(Integer userId, Integer sourceLanguage);
    List<SavedTranslation> findAllByUser_UserIdAndSourceLanguage_LangugageIdAndActiveOrderByCreatedAtDesc(Integer userId, Integer sourceLanguage);
}
