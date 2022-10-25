package no.kristiania.library;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(TestEntityManager.Implementation.class)
public @interface TestEntityManager {
    public class Implementation implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return parameterContext.getParameter().getType() == EntityManager.class;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
            var entityManager = Persistence.createEntityManagerFactory("library").createEntityManager();
            context.getStore(ExtensionContext.Namespace.GLOBAL).put("entityManager", entityManager);
            return entityManager;
        }

        @Override
        public void beforeEach(ExtensionContext context){
            var entityManager = context.getStore(ExtensionContext.Namespace.GLOBAL).get("entityManager", EntityManager.class);
            entityManager.getTransaction().begin();
        }

        @Override
        public void afterEach(ExtensionContext context){
            var entityManager = context.getStore(ExtensionContext.Namespace.GLOBAL).get("entityManager", EntityManager.class);
            entityManager.getTransaction().rollback();
        }


    }
}