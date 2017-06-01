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
        this.phrase = phrase;
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public void setName(final String name) {

    }

    @Override
    public String getName() {
        return null;
    }
}
