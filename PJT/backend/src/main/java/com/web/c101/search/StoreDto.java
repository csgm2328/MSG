package com.web.c101.search;


import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private String name;
    private String area;
    private String address;
    private float latittude;
    private float longitude;

}
