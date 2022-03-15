package jpa.buddy.transactionaltests.repositories;

import jpa.buddy.transactionaltests.entities.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface RepositoryWithoutAnnotation extends JpaRepository<NewEntity, UUID> {

    @Query("delete from NewEntity n where n.name = ?1")
    @Transactional
    @Modifying
    void deleteTransactionalAndModifying(String name);

    @Query("delete from NewEntity n where n.name = ?1")
    @Modifying
    void deleteModifying(String name);
}