package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene6.HelloWorldComposite;
import com.atlassian.polygene.hello.polygene6.HelloWorldState;
import org.qi4j.api.composite.TransientBuilder;
import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;

@Mixins(HelloWorldFactory.HelloWorldFactoryMixin.class)
public interface HelloWorldFactory {

    HelloWorldComposite create();

    class HelloWorldFactoryMixin implements HelloWorldFactory {

        @Structure
        TransientBuilderFactory tbf;

        public HelloWorldComposite create() {
            TransientBuilder<HelloWorldComposite> builder = tbf.newTransientBuilder(HelloWorldComposite.class);

            HelloWorldState helloWorldState = builder.prototypeFor(HelloWorldState.class);
            helloWorldState.name().set("Dom");
            helloWorldState.phrase().set("stop");

            return builder.newInstance();
        }
    }
}
