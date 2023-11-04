package com.quoter.quoter.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Formats {
    private Map<String, String> formats;

    @JsonAnyGetter
    public Map<String, String> getFormats() {
        return formats;
    }

    @JsonAnySetter
    public void setFormats(String key, String value) {
        if (formats == null) {
            formats = new HashMap<>();
        }
        formats.put(key, value);
    }
}
