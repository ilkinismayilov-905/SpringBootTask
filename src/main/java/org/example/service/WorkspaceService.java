package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Workspace;
import org.example.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkspaceService {

    private WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public List<Workspace> getAllSpaces(){
        List<Workspace> workspaceList = new ArrayList<>();
       for(Workspace workspace : workspaceRepository.findAll()){
           workspaceList.add(workspace);
       }
       return workspaceList;
    }

    public Workspace createWorkspace(Workspace workspace){
        return workspaceRepository.save(workspace);
    }

    public Optional<Workspace> getById(Long id){
        if(workspaceRepository.existsById(id)){
            return workspaceRepository.findById(id);
        }
        return null;
    }
}
