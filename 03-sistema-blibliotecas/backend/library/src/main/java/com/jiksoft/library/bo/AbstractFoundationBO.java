package com.jiksoft.library.bo;

import java.io.Serial;
import java.io.Serializable;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractFoundationBO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4146737620741357625L;

    protected String id;

}

