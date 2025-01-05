package org.aguzman.springcloud.msvc.tipos.repositories;

import org.aguzman.springcloud.msvc.tipos.models.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

}
