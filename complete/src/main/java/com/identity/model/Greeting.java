package com.identity.model;

/**
 * Created by Balachandar on 10-07-2017.
 */
public class Greeting {
    private final long id;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    private final String content;

    public Greeting(long id, String content) {
        this.id=id;
        this.content=content;
    }
}
