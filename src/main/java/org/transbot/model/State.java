package org.transbot.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Erwin on 15-Mar-17.
 */
@GeneratePojoBuilder
@Entity
@Table(name = "state")
public class State {
    @javax.persistence.Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            generator = "system-uuid"
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid2"
    )
    @org.springframework.data.annotation.Id
    private String id;

    @Column(name = "userId", unique = true)
    private String userId;

    @Column(name = "state")
    private short state;

    @Column(name = "address_suggestion", columnDefinition = "TEXT")
    private String address;

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", state=" + state +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state1 = (State) o;

        if (state != state1.state) return false;
        if (id != null ? !id.equals(state1.id) : state1.id != null) return false;
        if (userId != null ? !userId.equals(state1.userId) : state1.userId != null) return false;
        return address != null ? address.equals(state1.address) : state1.address == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (int) state;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
