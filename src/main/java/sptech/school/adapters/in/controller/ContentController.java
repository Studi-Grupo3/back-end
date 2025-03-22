package sptech.school.adapters.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.domain.dto.ContentDTO;
import sptech.school.domain.entity.Content;
import sptech.school.applitcation.mappers.ContentMapper;
import sptech.school.applitcation.service.ContentService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class ContentController {
    @Autowired
    private ContentService service;
    @Autowired
    private ContentMapper mapper;

    @PostMapping
    public ResponseEntity<ContentDTO> uploadArquivo(@RequestParam("file") MultipartFile file) throws IOException {
            Content content = service.saveFile(file);
            return ResponseEntity.ok(mapper.toResponse(content));
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ContentDTO> getMetadata(@PathVariable Long id) {
        return service.getMetadataById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> downloadArquivo(@PathVariable Long id) throws IOException {
        Optional<Content> contentOpt = service.getMetadataById(id);
        if (contentOpt.isEmpty()) return ResponseEntity.notFound().build();
        Content content = contentOpt.get();

            Optional<InputStream> streamOpt = service.findFileById(id);
            if (streamOpt.isEmpty()) return ResponseEntity.notFound().build();

            InputStreamResource resource = new InputStreamResource(streamOpt.get());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(content.getFileSize())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + content.getFileName() + "\"")
                    .body(resource);

    }
}