package com.atlassian.polygene.example.account;

import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

@Mixins(AccountFactory.AccountFactoryMixin.class)
public interface AccountFactory {

    Account create(String name);

    abstract class AccountFactoryMixin implements AccountFactory {

        @Structure
        UnitOfWorkFactory uowf;

        public Account create(String name) {
            UnitOfWork uow = uowf.currentUnitOfWork();
            EntityBuilder<Account> builder = uow.newEntityBuilder(Account.class);

            Nameable.NameableState accountState = builder.instanceFor(Nameable.NameableState.class);
            accountState.name().set(name);

            return builder.newInstance();
        }
    }
}
