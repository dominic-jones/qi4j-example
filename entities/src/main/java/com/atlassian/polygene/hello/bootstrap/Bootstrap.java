package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene.HelloWorldComposite;
import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.common.Visibility;
import org.qi4j.api.structure.Application;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkCompletionException;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.Energy4Java;
import org.qi4j.bootstrap.LayerAssembly;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.entitystore.memory.MemoryEntityStoreAssembler;
import org.qi4j.index.rdf.assembly.RdfMemoryStoreAssembler;
import org.qi4j.valueserialization.jackson.JacksonValueSerializationAssembler;

import static com.atlassian.polygene.commons.Qi4jCommons.installShutdownHook;

public class Bootstrap {

    public static void main(String[] args) throws AssemblyException, ActivationException {
        Energy4Java polygene = new Energy4Java();

        Application application = polygene.newApplication(factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            LayerAssembly domain = assembly.layer("domain");
            LayerAssembly persistence = assembly.layer("persistence");

            domain.uses(persistence);
            ModuleAssembly helloWorld = domain.module("hello-world");
            helloWorld.entities(HelloWorldComposite.class);
            helloWorld.services(
                    HelloWorldFactory.class,
                    HelloWorldService.class).visibleIn(Visibility.application);

            new MemoryEntityStoreAssembler().assemble(helloWorld);
            new JacksonValueSerializationAssembler().assemble(helloWorld);
            new RdfMemoryStoreAssembler().assemble(helloWorld);

            return assembly;
        });

        installShutdownHook(application);

        Module mHelloWorld = application.findModule("domain", "hello-world");

        UnitOfWork first = mHelloWorld.newUnitOfWork();
        HelloWorldFactory helloWorldFactory = mHelloWorld.findService(HelloWorldFactory.class).get();
        HelloWorldComposite helloWorld = helloWorldFactory.create();
        System.out.println(helloWorld.say());
        try {
            first.complete();
        } catch (UnitOfWorkCompletionException e) {
            throw new RuntimeException(e);
        }

        UnitOfWork second = mHelloWorld.newUnitOfWork();
        HelloWorldService helloWorldService = mHelloWorld.findService(HelloWorldService.class).get();
        HelloWorldComposite helloWorldComposite = helloWorldService.find();
        System.out.println(helloWorldComposite);
        try {
            second.complete();
        } catch (UnitOfWorkCompletionException e) {
            throw new RuntimeException(e);
        }

        application.activate();
    }

}
