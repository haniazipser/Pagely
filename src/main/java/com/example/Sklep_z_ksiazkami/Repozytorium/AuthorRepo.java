package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    /*
    SELECT TOP 5 A.Imie_i_nazwisko, COUNT (O.Id) [Ilość sprzedanych książek]
FROM Autorzy A INNER JOIN Autorzy_ksiazek AK ON A.Id = AK.Id_autora INNER JOIN Ksiazki K ON AK.Id_ksiazki = K.Id INNER JOIN Nr_ISBN_ksiazek NI ON NI.Id_ksiazki = K.Id
INNER JOIN Oferty O ON  NI.ISBN = O.ISBN INNER JOIN Szczegoly_zamowienia SZ ON O.Id = SZ.Id_oferty INNER JOIN Zamowienia Z ON SZ.Id_zamowienia = Z.Id
WHERE Z.Status_zamowienia != 'W TRAKCIE'AND  YEAR (Z.Data_zamowienia)  = YEAR(GETDATE()) AND MONTH (Z.Data_zamowienia)  = MONTH(GETDATE())
GROUP BY A.Id, A.Imie_i_nazwisko
ORDER BY COUNT (O.Id) DESC
     */
    @Query("""
        SELECT a
        FROM Author a
        JOIN a.books b
        JOIN b.ISBN i
        JOIN i.offers o
        GROUP BY a
        ORDER BY COUNT(o.id) DESC
    """)
    List<Author> getPopularAuthors();
}
