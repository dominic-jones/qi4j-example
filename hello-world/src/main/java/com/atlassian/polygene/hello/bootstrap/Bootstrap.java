package com.atlassian.polygene.hello.bootstrap;

import com.atlassian.polygene.hello.polygene6.HelloWorldComposite;
import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.service.ServiceReference;
import org.qi4j.api.structure.Application;
import org.qi4j.api.structure.Module;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.Energy4Java;
import org.qi4j.bootstrap.LayerAssembly;
import org.qi4j.bootstrap.ModuleAssembly;

public class Bootstrap {

    public static void main(String[] args) throws AssemblyException, ActivationException {
        Energy4Java polygene = new Energy4Java();

        Application application = polygene.newApplication(factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            LayerAssembly domain = assembly.layer("domain");
            LayerAssembly persistence = assembly.layer("persistence");

            domain.uses(persistence);
            ModuleAssembly helloWorld = domain.module("hello-world");
            helloWorld.transients(HelloWorldComposite.class);
            helloWorld.services(HelloWorldFactory.class);

            return assembly;
        });

        installShutdownHook(application);

        Module domain = application.findModule("domain", "hello-world");
        ServiceReference<HelloWorldFactory> service = domain.findService(HelloWorldFactory.class);
        HelloWorldFactory helloWorldFactory = service.get();
        HelloWorldComposite helloWorld = helloWorldFactory.create();
        System.out.println(helloWorld.say());

        application.activate();
    }

    private static void installShutdownHook(final Application application) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (application != null) {
                try {
                    application.passivate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
