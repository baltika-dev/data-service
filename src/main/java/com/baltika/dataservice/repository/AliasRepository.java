package com.baltika.dataservice.repository;

import com.baltika.dataservice.model.db.Ally;
import com.baltika.dataservice.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliasRepository extends JpaRepository<Ally, User> {
}
