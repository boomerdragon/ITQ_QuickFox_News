package com.itq.noticiasapp.dto;

import androidx.annotation.Keep;

/**
 * Clase DTO que representa un artículo de noticia en el JSON recibido del servidor.
 * La anotación @Keep previene que el ofuscador R8/ProGuard elimine estos campos en compilación Release.
 */
@Keep
public class ArticleDto {

    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String sourceName;

    // Constructor vacío
    public ArticleDto() {
    }

    // Constructor completo
    public ArticleDto(String title, String description, String url, String urlToImage, String publishedAt, String sourceName) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.sourceName = sourceName;
    }

    // Getters y Setters
    public String getTitle() {
        return title != null ? title : "Sin título disponible";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description != null && !description.equals("null") ? description : "Sin descripción disponible para este artículo.";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt != null ? publishedAt : "Fecha no especificada";
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSourceName() {
        return sourceName != null ? sourceName : "Fuente Noticiosa";
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}