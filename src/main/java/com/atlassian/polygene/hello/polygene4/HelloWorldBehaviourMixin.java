package com.atlassian.polygene.hello.polygene4;

import org.qi4j.api.injection.scope.This;

public class HelloWorldBehaviourMixin implements HelloWorldBehaviour {

    @This
    HelloWorldState helloWorldState;

    @Override
    public String say() {
        return helloWorldState.getName() + " " + helloWorldState.getPhrase();
    }
}
