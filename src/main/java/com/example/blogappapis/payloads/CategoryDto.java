package com.example.blogappapis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto
{
    private Integer categoryId;

    @NotBlank(message = "Please enter the Category Title")
    @Size(min = 4, message = "min size of category title is 4 !!")
    private String categoryTitle;

    @NotBlank
    @Size(min =10, max = 150, message = "Min size of category disc 10 and max is 150..!!")
    private String categoryDiscription;
}
