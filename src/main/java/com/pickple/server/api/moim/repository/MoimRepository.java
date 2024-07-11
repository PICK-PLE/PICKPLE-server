package com.pickple.server.api.moim.repository;

import com.pickple.server.api.moim.domain.Moim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimRepository extends JpaRepository<Moim, Long> {

}
