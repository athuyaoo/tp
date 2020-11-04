package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.studentgroup.StudentGroup;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final MatriculationNumber matriculationNumber;

    // Data fields
    private final Address address;
    private final Set<StudentGroup> studentGroups = new HashSet<>();
    private final Block block;
    private final Room room;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender, Set<StudentGroup> studentGroups,
                Block block, Room room, MatriculationNumber matriculationNumber) {
        requireAllNonNull(name, phone, email, address, gender, studentGroups, block, room, matriculationNumber);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studentGroups.addAll(studentGroups);
        this.block = block;
        this.room = room;
        this.gender = gender;
        this.matriculationNumber = matriculationNumber;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Block getBlock() {
        return block;
    }

    public Room getRoom() {
        return room;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Returns an immutable student group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<StudentGroup> getStudentGroups() {
        return Collections.unmodifiableSet(studentGroups);
    }

    public MatriculationNumber getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getMatriculationNumber().equals(getMatriculationNumber())
                || otherPerson.getName().equals(getName())
                    && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail())));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getStudentGroups().equals(getStudentGroups())
                && otherPerson.getMatriculationNumber().equals(getMatriculationNumber());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, studentGroups, matriculationNumber);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Gender: ")
                .append(getGender())
                .append(" Block: ")
                .append(getBlock())
                .append(" Room: ")
                .append(getRoom())
                .append(" Matriculation Number: ")
                .append(getMatriculationNumber())
                .append(" Student Groups: ");
        getStudentGroups().forEach(builder::append);
        return builder.toString();
    }

}
