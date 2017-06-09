package com.atlassian.polygene.name;

import org.qi4j.api.injection.scope.This;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.property.Property;

@Mixins(Nameable.NameableMixin.class)
public interface Nameable {

    String name();

    abstract class NameableMixin implements Nameable {
        @This
        NameableState state;

        @Override
        public String name() {
            return state.name().get();
        }
    }

    interface NameableState {
        Property<String> name();
    }
}