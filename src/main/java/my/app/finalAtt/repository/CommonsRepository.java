package my.app.finalAtt.repository;

import my.app.finalAtt.model.Commons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommonsRepository extends JpaRepository<Commons, Long> {
    boolean deleteById(long id);

    @Query("SELECT n FROM Commons n WHERE n.category=:category")
    List<Commons> findByCategory(@Param("category") String category);
}
