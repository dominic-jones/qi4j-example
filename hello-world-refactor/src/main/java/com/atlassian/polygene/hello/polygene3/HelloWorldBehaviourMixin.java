package com.atlassian.polygene.hello.polygene3;

import com.atlassian.polygene.hello.polygene2.HelloWorldState;
import org.qi4j.api.injection.scope.This;

public class HelloWorldBehaviourMixin implements HelloWorldBehaviour {

    @This
    HelloWorldState helloWorldState;

    @Override
    public String say() {
        return helloWorldState.getName() + " " + helloWorldState.getPhrase();
    }
}
