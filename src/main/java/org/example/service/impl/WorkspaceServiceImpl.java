package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Workspace;
import org.example.enums.WorkspaceType;
import org.example.exceptions.EmptyListExcepption;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.WorkspaceRepository;
import org.example.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkspaceServiceImpl {

    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public List<Workspace> getAllSpaces(){
        List<Workspace> workspaceList = new ArrayList<>();
       for(Workspace workspace : workspaceRepository.findAll()){
           workspaceList.add(workspace);
       }
       if(workspaceList.isEmpty()){
           throw new EmptyListExcepption("Workspace List Is Empty");
       }
       return workspaceList;
    }

    public void createWorkspace(Workspace workspace){
         workspaceRepository.save(workspace);
    }

    public Optional<Workspace> getById(Long id){
        Optional<Workspace> workspace = workspaceRepository.findById(id);

        if(workspace.isEmpty()){
            throw new NotFoundByIdException();
        }
        return workspace;
    }

    public List<Workspace> findByType(WorkspaceType workspaceType){
        return workspaceRepository.findByType(workspaceType);
    }


//    @Override
//    public void deleteById(Object id) {
//        workspaceRepository.deleteById(id);
//    }
//
//    @Override
//    public Object save(Object entity) {
//        return workspaceRepository.save(entity);
//    }
//
//    @Override
//    public Optional<T> getById(Object id) {
//        Optional<T> workspace = workspaceRepository.findById(id);
//
//        if(workspace.isEmpty()){
//            throw new NotFoundByIdException();
//        }
//        return workspace;
//    }
//
//    @Override
//    public List<T> getAll() {
//        return workspaceRepository.findAll();
//    }
}
