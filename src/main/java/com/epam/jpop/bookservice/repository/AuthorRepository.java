package com.epam.jpop.bookservice.repository;

import com.epam.jpop.bookservice.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
}
