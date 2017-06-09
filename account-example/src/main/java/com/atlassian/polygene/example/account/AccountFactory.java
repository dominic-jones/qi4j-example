package com.atlassian.polygene.example.account;

import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

import java.math.BigDecimal;

@Mixins(AccountFactory.AccountFactoryMixin.class)
public interface AccountFactory {

    Account create(String name, final BigDecimal balance);

    abstract class AccountFactoryMixin implements AccountFactory {

        @Structure
        UnitOfWorkFactory uowf;

        public Account create(String name, BigDecimal balance) {
            UnitOfWork uow = uowf.currentUnitOfWork();
            EntityBuilder<Account> builder = uow.newEntityBuilder(Account.class);

            Nameable.NameableState nameableState = builder.instanceFor(Nameable.NameableState.class);
            nameableState.name().set(name);

            Account.AccountState accountState = builder.instanceFor(Account.AccountState.class);
            accountState.balance().set(balance);

            return builder.newInstance();
        }
    }
}
