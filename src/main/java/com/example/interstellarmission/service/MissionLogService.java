package com.example.interstellarmission.service;

import com.example.interstellarmission.exception.ResourceNotFoundException;
import com.example.interstellarmission.model.dto.MissionLogDto;
import com.example.interstellarmission.model.entity.MissionLog;
import com.example.interstellarmission.repository.MissionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionLogService {

    private final MissionLogRepository repository;

    public List<MissionLogDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MissionLogDto getById(Long id) {
        return toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with ID: " + id)));
    }

    public MissionLogDto create(MissionLogDto dto) {
        MissionLog log = toEntity(dto);
        return toDto(repository.save(log));
    }

    public MissionLogDto update(Long id, MissionLogDto dto) {
        MissionLog existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with ID: " + id));

        existing.setTitle(dto.getTitle());
        existing.setContent(dto.getContent());
        existing.setStatus(dto.getStatus());

        return toDto(repository.save(existing));
    }

    public void delete(Long id) {
        MissionLog log = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with ID: " + id));
        repository.delete(log);
    }

    public MissionLogDto getByIdAndTitle(Long id, String title) {
        return toDto(repository.findByIdAndTitle(id, title)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with ID & Title: " + id + ", " + title)));
    }

    public List<MissionLogDto> getByTitle(String title) {
        return repository.findByTitle(title).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private MissionLogDto toDto(MissionLog log) {
        MissionLogDto dto = new MissionLogDto();
        dto.setId(log.getId());
        dto.setTitle(log.getTitle());
        dto.setContent(log.getContent());
        dto.setStatus(log.getStatus());
        return dto;
    }

    private MissionLog toEntity(MissionLogDto dto) {
        MissionLog entity = new MissionLog();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        return entity;
    }

}
