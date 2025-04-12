package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Workspace;
import org.example.enums.WorkspaceType;
import org.example.service.impl.WorkspaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private WorkspaceServiceImpl workspaceServiceImpl;

    @Autowired
    public WorkspaceController(WorkspaceServiceImpl workspaceServiceImpl) {
        this.workspaceServiceImpl = workspaceServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Workspace>> getAll(){
        return ResponseEntity.ok(workspaceServiceImpl.getAll());
    }

    @PostMapping("/add")

    public ResponseEntity<Workspace> createWorkspace(@Valid @RequestBody Workspace workspace){
        workspaceServiceImpl.save(workspace);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id) {
        Optional<Workspace> workspace = workspaceServiceImpl.getById(id);
        return workspace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Workspace>> getWorkspaceByType(@PathVariable WorkspaceType type){
        return ResponseEntity.ok(workspaceServiceImpl.findByType(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Workspace> deleteWorkspaceById(@PathVariable Long id){
        workspaceServiceImpl.deleteWorkspaceById(id);
        return ResponseEntity.ok().build();
    }
}
