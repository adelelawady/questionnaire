package app.questionaire.org;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("app.questionaire.org");

        noClasses()
            .that()
                .resideInAnyPackage("app.questionaire.org.service..")
            .or()
                .resideInAnyPackage("app.questionaire.org.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..app.questionaire.org.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
