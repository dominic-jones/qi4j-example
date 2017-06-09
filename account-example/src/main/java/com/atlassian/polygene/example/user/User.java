package com.atlassian.polygene.example.user;

import com.atlassian.polygene.example.account.Account;
import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.association.Association;
import org.qi4j.api.injection.scope.This;
import org.qi4j.api.mixin.Mixins;

@Mixins(User.UserMixin.class)
public interface User extends Nameable {

    String status();

    abstract class UserMixin implements User {

        @This
        UserState state;

        @Override
        public String status() {
            return state.name().get() + " has account " +
                    state.account().get().name() + " with balance " +
                    state.account().get().currentBalance();
        }
    }

    interface UserState extends NameableState {

        Association<Account> account();

    }
}