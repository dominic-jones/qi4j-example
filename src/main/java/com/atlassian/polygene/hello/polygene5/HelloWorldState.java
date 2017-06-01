package com.atlassian.polygene.hello.polygene5;

import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.property.Property;

@Mixins(HelloWorldStateMixin.class)
public interface HelloWorldState {

    Property<String> phrase();

    Property<String> name();
}
