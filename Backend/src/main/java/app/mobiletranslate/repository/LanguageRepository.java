package app.mobiletranslate.repository;

import app.mobiletranslate.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByLanguageIdAndActive(Integer languageId,boolean active);
    Optional<Language> findByLanguageIdAndActive(Integer languageId,boolean active);
    List<Language> getAllByActiveIsTrue();

}
