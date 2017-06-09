package com.atlassian.polygene.example.user;

import com.atlassian.polygene.example.account.Account;
import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

@Mixins(UserFactory.UserFactoryMixin.class)
public interface UserFactory {

    User create(final String name, Account account);

    abstract class UserFactoryMixin implements UserFactory {

        @Structure
        UnitOfWorkFactory unitOfWorkFactory;

        @Override
        public User create(final String name, Account account) {
            UnitOfWork uow = unitOfWorkFactory.currentUnitOfWork();
            EntityBuilder<User> builder = uow.newEntityBuilder(User.class);

            Nameable.NameableState nameableState = builder.instanceFor(Nameable.NameableState.class);
            nameableState.name().set(name);

            User.UserState userState = builder.instanceFor(User.UserState.class);
            userState.account().set(account);

            return builder.newInstance();
        }
    }
}
