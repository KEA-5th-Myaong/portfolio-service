package myaong.popolog.portfolioservice.repository;

import myaong.popolog.portfolioservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

	Optional<Portfolio> findByIdAndMemberId(Long portfolioId, Long memberId);

	List<Portfolio> findByMemberIdAndIsMain(Long memberId, Boolean isMain);

	Long countByMemberId(Long memberId);
}
