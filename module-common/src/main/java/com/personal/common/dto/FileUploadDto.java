package com.personal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUploadDto {
    private String fileName;
    private String convertFileName;
    private Long fileSize;
    private String contentType;
}