package com.atlassian.polygene.hello.polygene;

import org.qi4j.api.concern.Concerns;
import org.qi4j.api.mixin.Mixins;

@Concerns(HelloWorldConcern.class)
@Mixins(HelloWorldMixin.class)
public interface HelloWorldComposite {

    String say();
}
