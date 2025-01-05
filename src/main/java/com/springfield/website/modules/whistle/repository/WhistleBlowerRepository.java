package com.springfield.website.modules.whistle.repository;

import com.springfield.website.modules.whistle.model.WhistleBlower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhistleBlowerRepository extends JpaRepository<WhistleBlower, Long> {
}
