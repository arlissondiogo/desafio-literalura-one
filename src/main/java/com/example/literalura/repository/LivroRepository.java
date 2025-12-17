package com.example.literalura.repository;

import com.example.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@SuppressWarnings("unused")
public interface LivroRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleIgnoreCase(String title);
    long countByLanguagesContainingIgnoreCase(String language);

}
