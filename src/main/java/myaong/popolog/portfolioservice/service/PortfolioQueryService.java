package myaong.popolog.portfolioservice.service;

import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;

public interface PortfolioQueryService {

	Portfolio findByIdAndMemberId(Long portfolioId, Long memberId);

	PortfoliosResponse getPortfolios(Long memberId);

	PortfolioResponse getPortfolio(Long memberId, Long portfolioId);
}
