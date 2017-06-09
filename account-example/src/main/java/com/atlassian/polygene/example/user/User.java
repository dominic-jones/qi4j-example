package com.atlassian.polygene.example.user;

import com.atlassian.polygene.name.Nameable;
import org.qi4j.api.mixin.Mixins;

@Mixins(User.UserMixin.class)
public interface User extends Nameable {
    abstract class UserMixin implements User {

    }

    interface UserState {

    }
}