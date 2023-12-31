package com.blog.payload;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Title should be at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 4,message = "Description should be al least 4 characters")
    private String description;
    @NotEmpty
    @Size(min = 4,message = "content should be al least 4 characters")
    private  String content;

}
