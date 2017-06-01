package com.atlassian.polygene.hello.polygene;

public class HelloWorldMixin implements HelloWorld {

    String phrase;
    String name;

    @Override
    public String say() {
        return getPhrase() + " " + getName();
    }

    @Override
    public void setPhrase(final String phrase) {

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
    public void setName(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("Name may not be null ");
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
