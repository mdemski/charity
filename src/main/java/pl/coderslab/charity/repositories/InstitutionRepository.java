package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.AbstractEntity;
import pl.coderslab.charity.model.Institution;

import java.util.List;

public interface InstitutionRepository<T extends AbstractEntity, L extends Number> extends JpaRepository<Institution, Long> {
    @Query("SELECT DISTINCT i FROM Institution i")
    List<Institution> getAllSupportedInstitutions();
}
