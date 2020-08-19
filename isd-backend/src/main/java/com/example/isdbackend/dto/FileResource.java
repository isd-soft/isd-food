package com.example.isdbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileResource {

    String fileName;
    byte[] bytes;

}
