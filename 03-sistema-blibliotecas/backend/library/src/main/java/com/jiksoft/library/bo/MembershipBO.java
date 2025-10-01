package com.jiksoft.library.bo;

import java.io.Serial;
import java.time.LocalDate;
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
public class MembershipBO extends AbstractFoundationBO {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private LibraryBO library;

    private String type;

    private LocalDate startDate;

    private LocalDate endDate;
}
