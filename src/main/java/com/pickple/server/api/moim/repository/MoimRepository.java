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

    default Moim findMoimByIdOrThrow(Long id) {
        return findMoimById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MOIM_NOT_FOUND));
    }

    int countByHostId(Long hostId);

    List<Moim> findMoimByhostIdAndMoimState(Long hostId, String moimState);

    @Query(value = "SELECT * FROM moims WHERE EXISTS (" +
            "SELECT 1 FROM jsonb_each_text(category_list) AS categories " +
            "WHERE categories.value = :category)",
            nativeQuery = true)
    List<Moim> findMoimListByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM moims WHERE date_list->>'date' = :date AND moim_state = 'ongoing'", nativeQuery = true)
    List<Moim> findByDate(String date);

    @Query("SELECT m FROM Moim m WHERE m.host.id = :hostId AND m.moimState = 'completed'")
    List<Moim> findCompletedMoimsByHostId(@Param("hostId") Long hostId);

    @Query("SELECT COUNT(m) FROM Moim m WHERE m.host.id = :hostId AND m.moimState = 'completed'")
    int CompletedMoimNumber(Long hostId);

    List<Moim> findMoimByHostId(Long hostId);

}
