package Note.Dashboard.repository;

import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b where b.categoryType = :categoryType")
    List<Board> findByCategory(@Param("categoryType") CategoryType categoryType);

    @Query("select b from Board b where b.categoryType = :categoryType")
    Page<Board> findByCategoryPage(@Param("categoryType") CategoryType categoryType, Pageable pageable);

    @Transactional
    @Modifying
    @Query("Update Board b  set b.categoryType = :categoryType where b.id = :id")
    int updateBoardCategoryType(@Param(value = "categoryType") CategoryType categoryType, @Param("id") Long id);
}
