package com.atlassian.polygene.example.account;

import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.mixin.Mixins;

@Mixins(Account.AccountMixin.class)
public interface Account extends Nameable {

    abstract class AccountMixin implements Account {

    }
}