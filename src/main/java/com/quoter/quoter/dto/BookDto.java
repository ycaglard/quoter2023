package com.quoter.quoter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quoter.quoter.model.Author;
import com.quoter.quoter.model.Subject;
import java.util.List;
import java.util.Map;
import com.quoter.quoter.model.Formats;

@JsonIgnoreProperties({ "translators", "bookshelves", "languages", "copyright", "media_type", "download_count",
        "formats" })
public class BookDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<Author> authors;
    @JsonProperty("subjects")
    private List<Subject> subjects;

    private String line;
    private String urlFormat;

    public BookDto() {
    }

    public BookDto(String id, String title, List<Author> authors, List<Subject> subjects) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.subjects = subjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String urlFormat() {
        return urlFormat;
    }

    public void setFormats(String urlFormat) {
        this.urlFormat = urlFormat;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getUrlFormat() {
        return urlFormat;
    }

    public void setUrlFormat(String urlFormat) {
        this.urlFormat = urlFormat;
    }

}
