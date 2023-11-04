package com.quoter.quoter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "translators", "bookshelves", "languages", "copyright", "media_type", "download_count",
        "results" })
public class ExtApiResponse {
    @JsonProperty("count")
    String count;
    @JsonProperty("next")
    String next;
    @JsonProperty("previous")
    String previous;

    public ExtApiResponse() {
    }

    public ExtApiResponse(String count, String next, String previous) {
        this.count = count;
        this.next = next;
        this.previous = previous;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

}
