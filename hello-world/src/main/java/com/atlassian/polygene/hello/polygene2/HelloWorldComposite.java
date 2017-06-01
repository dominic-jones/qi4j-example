package com.atlassian.polygene.hello.polygene2;

import com.atlassian.polygene.hello.polygene.HelloWorldMixin;
import org.qi4j.api.mixin.Mixins;

@Mixins(HelloWorldMixin.class)
public interface HelloWorldComposite extends HelloWorld {
}
