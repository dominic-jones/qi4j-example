package com.atlassian.polygene.hello.polygene3;

import org.qi4j.api.mixin.Mixins;

@Mixins(HelloWorldBehaviourMixin.class)
public interface HelloWorldBehaviour {

    String say();

}
