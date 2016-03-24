package com.ariba.dto;

/**
 * @author Frank Lloyd Teh
 */
public class GreetingDto {

    private Long id;
    private String title;
    private String content;
    private Long envelopeId;

    public GreetingDto() {
    }

    public GreetingDto(Long id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public Long getEnvelopeId() {
        return envelopeId;
    }

    public void setEnvelopeId(Long envelopeId) {
        this.envelopeId = envelopeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
