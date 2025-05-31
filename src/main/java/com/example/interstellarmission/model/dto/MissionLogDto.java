package com.example.interstellarmission.model.dto;

import com.example.interstellarmission.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionLogDto {
    private Long id;
    private String title;
    private String content;
    private Status status;
}

