package my.app.finalAtt.repository;

import my.app.finalAtt.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean deleteById(long id);

//    @Query("SELECT n FROM Commons n WHERE n.category=:category")
//    List<Commons> findByCategory(@Param("category") String category);
}
