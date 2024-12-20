package com.springfield.website.modules.param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmnixGenericParamRepository extends JpaRepository<OmnixGenericParam, Long>, JpaSpecificationExecutor<OmnixGenericParam> {

    OmnixGenericParam findFirstByParamKey(String paramKey);
}
