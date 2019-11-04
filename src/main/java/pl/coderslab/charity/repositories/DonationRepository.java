package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.AbstractEntity;
import pl.coderslab.charity.model.Donation;

public interface DonationRepository<T extends AbstractEntity, L extends Number> extends JpaRepository<Donation, Long> {
    @Query(value = "SELECT SUM(quantity) FROM donations", nativeQuery = true)
    int sumAllBags();
}
