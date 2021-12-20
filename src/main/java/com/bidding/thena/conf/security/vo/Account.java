package com.bidding.thena.conf.security.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {

    @NonNull
    Object corporation;

    @NonNull
    Object userId;

    @NonNull
    Object userName;

    @NonNull
    Object userType;

    @NonNull
    Object userRole;

}
