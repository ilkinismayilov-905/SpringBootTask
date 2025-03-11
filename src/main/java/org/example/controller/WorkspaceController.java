package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.User;
import org.example.entity.Workspace;
import org.example.enums.WorkspaceType;
import org.example.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Workspace>> getAll(){
        return ResponseEntity.ok(workspaceService.getAllSpaces());
    }

    @PostMapping("/add")
    public ResponseEntity<Workspace> createWorkspace(@Valid @RequestBody Workspace workspace){
        workspaceService.createWorkspace(workspace);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getUserById(@PathVariable Long id) {
        Optional<Workspace> workspace = workspaceService.getById(id);
        return workspace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/types")
    public ResponseEntity<List<Workspace>> getWorkspaceByType(@RequestParam("types")WorkspaceType type){
        return ResponseEntity.ok(workspaceService.findByType(type));
    }
}
