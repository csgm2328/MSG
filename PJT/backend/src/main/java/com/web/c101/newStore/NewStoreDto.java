package com.web.c101.newStore;


import lombok.*;

import javax.persistence.Id;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStoreDto {

    @Id
    private Long sid;

    private String name;
    private String area;
}
