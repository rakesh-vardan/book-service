package com.epam.jpop.bookservice.repository;

import com.epam.jpop.bookservice.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
}
