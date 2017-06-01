package com.atlassian.polygene.hello.polygene5;

import org.qi4j.api.injection.scope.This;

public class HelloWorldBehaviourMixin implements HelloWorldBehaviour {

    @This
    HelloWorldState state;

    @Override
    public String say() {
        return state.name().get() + " " + state.phrase().get();
    }
}
