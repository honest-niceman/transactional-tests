package jpa.buddy.transactionaltests;

import jpa.buddy.transactionaltests.repositories.RepositoryTransactional;
import jpa.buddy.transactionaltests.repositories.RepositoryTransactionalReadOnlyTrue;
import jpa.buddy.transactionaltests.repositories.RepositoryWithoutAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TransactionalTestsApplicationTests {
    @Autowired
    private RepositoryWithoutAnnotation withoutAnnotation;
    @Autowired
    private RepositoryTransactional transactional;
    @Autowired
    private RepositoryTransactionalReadOnlyTrue transactionalReadOnlyTrue;

    @Test
    void contextLoads() {
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void transactionalAndModifying_RepositoryWithoutAnnotationTest() {
        withoutAnnotation.deleteTransactionalAndModifying("test");
        assertThat(withoutAnnotation.count()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void modifying_RepositoryWithoutAnnotationTest() {
        withoutAnnotation.deleteModifying("test");
        assertThat(withoutAnnotation.count()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void transactionalAndModifying_RepositoryWithTransactionalAnnotationTest() {
        transactional.deleteTransactionalAndModifying("test");
        assertThat(transactional.count()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void modifying_RepositoryWithTransactionalAnnotationTest() {
        transactional.deleteModifying("test");
        assertThat(transactional.count()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void transactionalAndModifying_RepositoryWithTransactionalReadOnlyTrueAnnotationTest() {
        transactionalReadOnlyTrue.deleteTransactionalAndModifying("test");
        assertThat(transactionalReadOnlyTrue.count()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {"deleteAll.sql", "insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void modifying_RepositoryWithTransactionalReadOnlyTrueAnnotationTest() {
        transactionalReadOnlyTrue.deleteModifying("test");
        assertThat(transactionalReadOnlyTrue.count()).isEqualTo(0);
    }
}
