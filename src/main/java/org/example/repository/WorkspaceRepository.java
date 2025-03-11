package org.example.repository;

import org.example.entity.Workspace;
import org.example.enums.WorkspaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {
    public List<Workspace> findByType(WorkspaceType type);
}
