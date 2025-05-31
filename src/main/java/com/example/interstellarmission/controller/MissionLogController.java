package com.example.interstellarmission.controller;

import com.example.interstellarmission.model.dto.MissionLogDto;
import com.example.interstellarmission.service.MissionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class MissionLogController {

    private final MissionLogService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<MissionLogDto>> getAllLogs() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MissionLogDto> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MissionLogDto> createLog(@RequestBody MissionLogDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MissionLogDto> updateLog(@PathVariable Long id, @RequestBody MissionLogDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByIdAndTitle")
    public ResponseEntity<MissionLogDto> getByIdAndTitle(@RequestParam Long id, @RequestParam String title) {
        return ResponseEntity.ok(service.getByIdAndTitle(id, title));
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<List<MissionLogDto>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.getByTitle(title));
    }
}
