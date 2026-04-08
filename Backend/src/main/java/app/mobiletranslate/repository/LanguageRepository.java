package app.mobiletranslate.repository;

import app.mobiletranslate.entity.Language;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface LanguageRepository extends JpaRepository<Language,String> {
    boolean existsByLanguageIdAndActive(String languageId,boolean active);
    Optional<Language> findByLanguageIdAndActive(String languageId,boolean active);
    List<Language> getAllByActiveIsTrue();

}
