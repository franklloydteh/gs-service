package com.ariba.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Lloyd Teh
 */
public class EnvelopeGreetingsDto {
    private Long id;

    private String name;

    private List<GreetingListDto> greetings = new ArrayList<>();

    public List<GreetingListDto> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<GreetingListDto> greetings) {
        this.greetings = greetings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
