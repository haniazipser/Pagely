package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto;
import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface  BookRepo extends JpaRepository<Book, Integer> {
    @Query("""
        SELECT new com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto(k.id, k.title, k.description, k.category.categoryName, AVG(r.stars)) 
        FROM Book k 
        JOIN k.reviews r 
        GROUP BY k.id, k.title, k.description, k.category.categoryName
        ORDER BY AVG(r.stars) DESC
    """)
    List<BestsellerDto> findBestsellers();
    @Query("""
        SELECT new com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto(k.id, k.title, k.description, k.category.categoryName, AVG(r.stars)) 
        FROM Book k 
        JOIN k.reviews r 
        WHERE k.category.id = ?1
        GROUP BY k.id, k.title, k.description, k.category.categoryName
        ORDER BY AVG(r.stars) DESC
    """
    )
    List<BestsellerDto> findByCategoryId(Integer categoryId);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findByTitle(String title);
}
