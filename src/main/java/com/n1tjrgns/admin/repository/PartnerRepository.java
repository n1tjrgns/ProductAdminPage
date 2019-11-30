package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.model.entity.Category;
import com.n1tjrgns.admin.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    List<Partner> findByCategory(Category category);
}
