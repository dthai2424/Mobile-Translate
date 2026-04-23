    package app.mobiletranslate.controller;

    import app.mobiletranslate.dto.SavedTranslationRequestDTO;
    import app.mobiletranslate.dto.SavedTranslationResponseDTO;
    import app.mobiletranslate.entity.SavedTranslation;
    import app.mobiletranslate.service.SavedTranslationService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/translations")
    @RequiredArgsConstructor
    public class SavedTranslationController {

        private final SavedTranslationService savedTranslationService;

        @PostMapping("/save")
        public ResponseEntity<SavedTranslation> saveTranslation(@RequestBody SavedTranslationRequestDTO requestDTO) {
            // Nếu dùng JWT: trích xuất userId từ token và set vào requestDTO tại đây
            SavedTranslation saved = savedTranslationService.save(requestDTO);
            return ResponseEntity.ok(saved);
        }
        @GetMapping("/my-translations")
        public ResponseEntity<List<SavedTranslationResponseDTO>> getMyTranslations(
                @RequestParam(defaultValue = "desc") String sortOrder) {

            List<SavedTranslationResponseDTO> result = savedTranslationService.getUserActiveSavedTranslation();
            return ResponseEntity.ok(result);
        }
    }