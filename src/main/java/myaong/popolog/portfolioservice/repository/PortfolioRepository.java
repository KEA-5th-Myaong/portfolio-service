package myaong.popolog.portfolioservice.repository;

import myaong.popolog.portfolioservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

	Optional<Portfolio> findByIdAndMemberId(Long portfolioId, Long memberId);

	List<Portfolio> findByMemberIdAndIsMain(Long memberId, Boolean isMain);
	default Portfolio findMainByMemberId(Long memberId) {
		return findByMemberIdAndIsMain(memberId, true).get(0);
	}
	default List<Portfolio> findNormalByMemberId(Long memberId) {
		return findByMemberIdAndIsMain(memberId, false);
	}

	Long countByMemberId(Long memberId);
}
