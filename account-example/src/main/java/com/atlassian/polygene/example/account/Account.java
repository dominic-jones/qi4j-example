package com.atlassian.polygene.example.account;

import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.injection.scope.This;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.property.Property;

import java.math.BigDecimal;

@Mixins(Account.AccountMixin.class)
public interface Account extends Nameable {

    BigDecimal currentBalance();

    abstract class AccountMixin implements Account {

        @This
        AccountState state;

        @Override
        public BigDecimal currentBalance() {
            return state.balance().get();
        }
    }

    interface AccountState {
        Property<BigDecimal> balance();
    }
}