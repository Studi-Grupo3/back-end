package sptech.school.adapters.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sptech.school.domain.entity.Content;
import sptech.school.applitcation.usecase.ContentRepositoryUseCase;

import java.util.Optional;

@Repository
public class ContentRepositoryAdapter implements ContentRepositoryUseCase {

    @Autowired
    private JpaContentRepository jpaContentRepository;

    @Override
    public Content save(Content content) {
        content.setId(null);
        return jpaContentRepository.save(content);
    }

    @Override
    public Optional<Content> findById(Long id) {
        return jpaContentRepository.findById(id);
    }
}