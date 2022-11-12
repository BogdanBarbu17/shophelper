package com.learning.shophelper.data.access.repository;

import com.learning.shophelper.data.access.model.RoleEntity;
import com.learning.shophelper.data.access.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
