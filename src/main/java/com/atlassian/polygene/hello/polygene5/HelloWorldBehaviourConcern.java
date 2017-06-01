package com.atlassian.polygene.hello.polygene5;

import org.qi4j.api.concern.ConcernOf;

public class HelloWorldBehaviourConcern extends ConcernOf<HelloWorldBehaviour>
        implements HelloWorldBehaviour {

    @Override
    public String say() {
        return "Simon says: " + next.say();
    }
}
