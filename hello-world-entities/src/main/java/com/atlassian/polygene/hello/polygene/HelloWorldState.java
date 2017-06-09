package com.atlassian.polygene.hello.polygene;

import org.qi4j.api.property.Property;

public interface HelloWorldState {

    Property<String> phrase();

    Property<String> name();
}
