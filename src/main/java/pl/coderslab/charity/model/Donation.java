package pl.coderslab.charity.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "donations")
public class Donation extends AbstractEntity {

    private int quantity;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donation")
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private LocalDateTime pickUpDate;
    private LocalDateTime pickUpTime;
    private String pickUpComment;

    public Donation(int quantity, List<Category> categories, Institution institution, String street, String city, String zipCode, LocalDateTime pickUpDate, LocalDateTime pickUpTime, String pickUpComment) {
        this.quantity = quantity;
        this.categories = categories;
        this.institution = institution;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpComment = pickUpComment;
    }

    public Donation() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Donation donation = (Donation) o;
        return quantity == donation.quantity &&
                Objects.equals(categories, donation.categories) &&
                Objects.equals(institution, donation.institution) &&
                Objects.equals(street, donation.street) &&
                Objects.equals(city, donation.city) &&
                Objects.equals(zipCode, donation.zipCode) &&
                Objects.equals(pickUpDate, donation.pickUpDate) &&
                Objects.equals(pickUpTime, donation.pickUpTime) &&
                Objects.equals(pickUpComment, donation.pickUpComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, categories, institution, street, city, zipCode, pickUpDate, pickUpTime, pickUpComment);
    }
}
