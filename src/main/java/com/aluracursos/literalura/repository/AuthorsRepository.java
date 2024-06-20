package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Authors, Long> {

    Authors searchByName (String name);

    List<Authors> searchByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int birthYear, int deathYear);
}
