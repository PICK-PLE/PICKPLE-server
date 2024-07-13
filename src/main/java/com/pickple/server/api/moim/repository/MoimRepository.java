package com.pickple.server.api.moim.repository;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    Optional<Moim> findMoimById(Long id);

    List<Moim> findMoimByHostId(Long hostId);

    List<Moim> findMoimByhostIdAndMoimState(Long hostId, String moimState);

    default Moim findMoimByIdOrThrow(Long id) {
        return findMoimById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MOIM_NOT_FOUND));
    }

    @Query(value = "SELECT * FROM moims WHERE EXISTS (" +
            "SELECT 1 FROM jsonb_each_text(category_list) AS categories " +
            "WHERE categories.value = :category)",
            nativeQuery = true)
    List<Moim> findMoimListByCategory(@Param("category") String category);
}
