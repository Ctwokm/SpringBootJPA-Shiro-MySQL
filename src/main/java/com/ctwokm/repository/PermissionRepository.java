package com.ctwokm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctwokm.pojo.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
