package com.atlassian.polygene.hello;

public class HelloWorld {

    private String phrase;
    private String name;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        if (phrase == null) {
            throw new IllegalArgumentException("Phrase may not be null ");
        }

        this.phrase = phrase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null ");
        }

        this.name = name;
    }

    public String say() {
        return phrase + " " + name;
    }
}