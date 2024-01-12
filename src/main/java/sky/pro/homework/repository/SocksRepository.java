package sky.pro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sky.pro.homework.model.Socks;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> findByColorIgnoreCaseAndCottonPart(String color, int cottonPart);
    List<Socks> findAllByColorIgnoreCaseAndCottonPartGreaterThan(String color, int cottonPart);
    List<Socks> findAllByColorIgnoreCaseAndCottonPartLessThan(String color, int cottonPart);
    List<Socks> findAllByColorIgnoreCaseAndCottonPart(String color, int cottonPart);
}
