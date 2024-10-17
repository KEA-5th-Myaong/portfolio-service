package myaong.popolog.portfolioservice.repository;

import myaong.popolog.portfolioservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

	List<Portfolio> findByMemberIdAndIsMain(Long memberId, Boolean isMain);
}
