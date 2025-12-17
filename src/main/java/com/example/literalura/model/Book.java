package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@SuppressWarnings("unused")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("download_count")
    private Integer downloadCount;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    @Transient
    @JsonProperty("authors")
    private List<AuthorApiDTO> authorsApi;

    public List<AuthorApiDTO> getAuthorsApi() {
        return authorsApi;
    }

    public void setAuthorsApi(List<AuthorApiDTO> authorsApi) {
        this.authorsApi = authorsApi;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    @Override
    public String toString() {
        return """
               -------------------------
               Título: %s
               Idioma: %s
               Downloads: %d
               -------------------------
               """.formatted(
                title,
                languages != null && !languages.isEmpty() ? languages.get(0) : "N/A",
                downloadCount
        );
    }
}
