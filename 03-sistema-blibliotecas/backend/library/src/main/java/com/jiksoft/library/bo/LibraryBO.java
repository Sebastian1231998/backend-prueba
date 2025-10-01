package com.jiksoft.library.bo;

import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LibraryBO extends AbstractFoundationBO {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
}