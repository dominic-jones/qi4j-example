package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene.HelloWorldComposite;
import com.atlassian.polygene.hello.polygene.HelloWorldState;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

@Mixins(HelloWorldFactory.HelloWorldFactoryMixin.class)
public interface HelloWorldFactory {

    HelloWorldComposite create();

    class HelloWorldFactoryMixin implements HelloWorldFactory {

        @Structure
        UnitOfWorkFactory uowf;

        public HelloWorldComposite create() {

            UnitOfWork uow = uowf.currentUnitOfWork();

            EntityBuilder<HelloWorldComposite> builder = uow.newEntityBuilder(HelloWorldComposite.class);

            HelloWorldState helloWorldState = builder.instanceFor(HelloWorldState.class);
            helloWorldState.name().set("Dom");
            helloWorldState.phrase().set("stop");

            return builder.newInstance();
        }
    }
}
