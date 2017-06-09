package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene.HelloWorldComposite;
import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.common.Visibility;
import org.qi4j.api.structure.Application;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkCompletionException;
import org.qi4j.bootstrap.ApplicationAssembler;
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

        Application application = polygene.newApplication(assembler());
        installShutdownHook(application);
        application.activate();

        Module mHelloWorld = application.findModule("domain", "hello-world");

        createEntity(mHelloWorld);

        findEntity(mHelloWorld);
    }

    private static void createEntity(final Module mHelloWorld) {
        UnitOfWork first = mHelloWorld.newUnitOfWork();
        HelloWorldFactory helloWorldFactory = mHelloWorld.findService(HelloWorldFactory.class).get();
        HelloWorldComposite firstHw = helloWorldFactory.create();
        System.out.println("first " + firstHw);
        System.out.println("first " + firstHw.say());
        try {
            first.complete();
        } catch (UnitOfWorkCompletionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findEntity(final Module mHelloWorld) {
        UnitOfWork second = mHelloWorld.newUnitOfWork();
        HelloWorldService helloWorldService = mHelloWorld.findService(HelloWorldService.class).get();
        HelloWorldComposite secondHw = helloWorldService.find();
        System.out.println("second " + secondHw);
        System.out.println("second " + secondHw.say());
        try {
            second.complete();
        } catch (UnitOfWorkCompletionException e) {
            throw new RuntimeException(e);
        }
    }

    private static ApplicationAssembler assembler() {
        return factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            LayerAssembly lPersistence = persistenceLayer(assembly);

            LayerAssembly lDomain = lDomain(assembly);
            lPersistence.uses(lDomain);

            return assembly;
        };
    }

    private static LayerAssembly lDomain(final ApplicationAssembly assembly) throws AssemblyException {
        LayerAssembly layer = assembly.layer("domain");
        ModuleAssembly helloWorld = layer.module("hello-world");
        helloWorld.entities(HelloWorldComposite.class);
        helloWorld.services(
                HelloWorldFactory.class,
                HelloWorldService.class);

        return layer;
    }

    private static LayerAssembly persistenceLayer(final ApplicationAssembly assembly) throws AssemblyException {
        LayerAssembly layer = assembly.layer("domain");

        ModuleAssembly mMemory = layer.module("memory");
        new MemoryEntityStoreAssembler().visibleIn(Visibility.layer).assemble(mMemory);
        new JacksonValueSerializationAssembler().assemble(mMemory);
        new RdfMemoryStoreAssembler().assemble(mMemory);

        return layer;
    }

}
