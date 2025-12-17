package com.example.literalura.repository;

import com.example.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNomeIgnoreCase(String nome);

    List<Author> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(
            Integer anoNascimento,
            Integer anoFalecimento
    );

    List<Author> findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(Integer ano);

}