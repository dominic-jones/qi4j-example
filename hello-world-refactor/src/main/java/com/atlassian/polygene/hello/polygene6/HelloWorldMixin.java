package com.atlassian.polygene.hello.polygene6;

import org.qi4j.api.injection.scope.This;

public class HelloWorldMixin implements HelloWorldComposite {

    @This
    HelloWorldState state;

    @Override
    public String say() {
        return state.name().get() + " " + state.phrase().get();
    }
}
