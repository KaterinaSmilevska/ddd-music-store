package com.example.sharedkernel.domain.base;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.NonNull;
import java.util.UUID;

@MappedSuperclass
@Embeddable
@Getter
public class DomainObjectId implements Serializable {

    private String id;

    public DomainObjectId() {

    }

    @JsonCreator
    protected DomainObjectId(@NonNull String uuid) {
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }


    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }


}
