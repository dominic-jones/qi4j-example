package com.atlassian.polygene.example.bootstrap;

import com.atlassian.polygene.commons.MemoryAssembler;
import com.atlassian.polygene.example.account.Account;
import com.atlassian.polygene.example.account.AccountFactory;
import com.atlassian.polygene.example.user.User;
import com.atlassian.polygene.example.user.UserFactory;
import com.atlassian.polygene.name.Nameable;
import com.atlassian.polygene.name.NameableRepository;
import org.qi4j.api.activation.ActivationException;
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

import java.util.List;

import static com.atlassian.polygene.commons.Qi4jCommons.installShutdownHook;
import static java.util.stream.Collectors.toList;

public class Bootstrap {

    public static final String ACCOUNT = "account";

    public static void main(String[] args) throws AssemblyException, ActivationException, UnitOfWorkCompletionException {
        Energy4Java polygene = new Energy4Java();

        Application application = polygene.newApplication(assembler());
        installShutdownHook(application);
        application.activate();

        Module mAccount = application.findModule("domain", ACCOUNT);

        UnitOfWork unitOfWork = mAccount.newUnitOfWork();
        AccountFactory accountFactory = mAccount.findService(AccountFactory.class).get();
        Account account = accountFactory.create("Main");
        System.out.println(account);
        System.out.println(account.name());
        unitOfWork.complete();

        UnitOfWork unitOfWork2 = mAccount.newUnitOfWork();
        UserFactory userFactory = mAccount.findService(UserFactory.class).get();
        User user = userFactory.create("Sumir");
        System.out.println(user);
        System.out.println(user.name());
        unitOfWork2.complete();

        UnitOfWork unitOfWork1 = mAccount.newUnitOfWork();
        NameableRepository nameableRepository = mAccount.findService(NameableRepository.class).get();
        List<Nameable> main = nameableRepository.all();
        System.out.println(main);
        System.out.println(main.stream().map(Nameable::name).collect(toList()));
        unitOfWork1.close();
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
        new MemoryAssembler().assemble(layer, accountModule(layer));

        return layer;
    }

    private static ModuleAssembly accountModule(final LayerAssembly layer) {
        ModuleAssembly account = layer.module(ACCOUNT);

        account.entities(
                Account.class,
                User.class
        );
        account.services(
                AccountFactory.class,
                UserFactory.class,
                NameableRepository.class
        );

        return account;
    }

    private static LayerAssembly persistenceLayer(final ApplicationAssembly assembly) throws AssemblyException {
        LayerAssembly layer = assembly.layer("domain");

        new MemoryAssembler().assemble(layer, layer.module("memory"));

        return layer;
    }

}
