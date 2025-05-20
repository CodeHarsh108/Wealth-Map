package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.CompanyType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRegistrationRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private CompanyType type;

    @URL
    private String logoUrl;

    @Size(max = 1000)
    private String description;

    @Pattern(
            regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$",
            message = "Invalid website URL"
    )
    private String website;

    @Min(1)
    private Integer size;
}
