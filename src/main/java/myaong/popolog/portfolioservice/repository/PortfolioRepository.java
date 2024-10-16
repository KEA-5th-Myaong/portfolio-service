package myaong.popolog.portfolioservice.repository;

import myaong.popolog.portfolioservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
