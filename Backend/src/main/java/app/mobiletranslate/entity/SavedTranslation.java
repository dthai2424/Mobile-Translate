package app.mobiletranslate.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="SavedTranslation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavedTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer savedTranslationId;

    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="sourceLanguageId",nullable = false)
    private Language sourceLanguage;

    private String sourceText;

    @ManyToOne
    @JoinColumn(name="targetLanguageId",nullable = false)
    private Language targetLanguage;

    private String targetText;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    private boolean active;

}
