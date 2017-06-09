package com.atlassian.polygene.hello.polygene4;

import org.qi4j.api.constraint.Constraints;
import org.qi4j.api.mixin.Mixins;

@Constraints(NotEmptyConstraint.class)
@Mixins(HelloWorldStateMixin.class)
public interface HelloWorldState {

    void setPhrase(@NotEmpty String phrase);

    String getPhrase();

    void setName(@NotEmpty String name);

    String getName();
}
