package com.appsynth.workshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;
}