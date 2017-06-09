package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene.HelloWorldComposite;
import com.atlassian.polygene.hello.polygene.HelloWorldState;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.query.Query;
import org.qi4j.api.query.QueryBuilder;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

import static org.qi4j.api.query.QueryExpressions.eq;
import static org.qi4j.api.query.QueryExpressions.templateFor;

@Mixins(HelloWorldService.HelloWorldServiceMixin.class)
public interface HelloWorldService {

    HelloWorldComposite find();

    class HelloWorldServiceMixin implements HelloWorldService {

        @Structure
        UnitOfWorkFactory uofw;

        @Structure
        Module module;

        @Override
        public HelloWorldComposite find() {

            HelloWorldState helloWorldState = templateFor(HelloWorldState.class);

            QueryBuilder<HelloWorldComposite> qb = module.newQueryBuilder(HelloWorldComposite.class)
                    .where(eq(helloWorldState.name(), "Dom"));

            UnitOfWork uow = uofw.currentUnitOfWork();
            Query<HelloWorldComposite> query = uow.newQuery(qb);

            return query.find();
        }
    }
}
