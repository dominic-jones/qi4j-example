package com.atlassian.polygene.hello.polygene3;

import org.qi4j.api.mixin.Mixins;

@Mixins(HelloWorldStateMixin.class)
public interface HelloWorldState {

    void setPhrase(String phrase);

    String getPhrase();

    void setName(String name);

    String getName();
}
