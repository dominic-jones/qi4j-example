package com.atlassian.polygene.hello.polygene4;

public class HelloWorldStateMixin implements HelloWorldState {

    String phrase;
    String name;

    @Override
    public void setPhrase(String phrase)
            throws IllegalArgumentException {
        if (phrase == null) {
            throw new IllegalArgumentException("Phrase may not be null");
        }

        this.phrase = phrase;
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public void setName(String name)
            throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}