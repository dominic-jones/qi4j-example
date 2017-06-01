package com.atlassian.polygene.hello.polygene4;

import org.qi4j.api.concern.Concerns;
import org.qi4j.api.mixin.Mixins;

@Concerns(HelloWorldBehaviourConcern.class)
@Mixins(HelloWorldBehaviourMixin.class)
public interface HelloWorldBehaviour {

    String say();

}
