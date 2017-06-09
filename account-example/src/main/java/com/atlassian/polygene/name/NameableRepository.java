package com.atlassian.polygene.name;

import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.query.QueryBuilder;
import org.qi4j.api.query.QueryBuilderFactory;
import org.qi4j.api.query.QueryExpressions;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Mixins(NameableRepository.NameableRepositoryMixin.class)
public interface NameableRepository {

    List<Nameable> all();

    Nameable findByName(String name);

    abstract class NameableRepositoryMixin implements NameableRepository {

        @Structure
        UnitOfWorkFactory uowf;

        @Structure
        Module module;

        @Structure
        QueryBuilderFactory qbf;

        public List<Nameable> all() {
            Nameable.NameableState tNameable = QueryExpressions.templateFor(Nameable.NameableState.class);

            QueryBuilder<Nameable> builder = qbf.newQueryBuilder(Nameable.class);

            return newArrayList(
                    uowf.currentUnitOfWork()
                            .newQuery(builder)
            );
        }

        public Nameable findByName(final String name) {
            Nameable.NameableState tNameable = QueryExpressions.templateFor(Nameable.NameableState.class);

            QueryBuilder<Nameable> builder = qbf.newQueryBuilder(Nameable.class)
                    .where(QueryExpressions.eq(tNameable.name(), name));

            return uowf.currentUnitOfWork()
                    .newQuery(builder)
                    .find();
        }

    }
}