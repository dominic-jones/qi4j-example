package com.atlassian.polygene.hello.polygene;

import org.qi4j.api.concern.ConcernOf;

public class HelloWorldConcern extends ConcernOf<HelloWorldComposite>
        implements HelloWorldComposite {

    @Override
    public String say() {
        return "Simon says: " + next.say();
    }
}
