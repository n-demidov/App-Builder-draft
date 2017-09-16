package com.sunshineapp.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppDto {

    @Getter
    @Setter
    private String appKey;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String statusDetails;

    @Getter
    @Setter
    private String filePath;

}
