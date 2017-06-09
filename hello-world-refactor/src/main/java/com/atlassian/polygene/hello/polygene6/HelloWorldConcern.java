package com.atlassian.polygene.hello.polygene6;

import org.qi4j.api.concern.ConcernOf;

public class HelloWorldConcern extends ConcernOf<HelloWorldComposite>
        implements HelloWorldComposite {

    @Override
    public String say() {
        return "Simon says: " + next.say();
    }
}
