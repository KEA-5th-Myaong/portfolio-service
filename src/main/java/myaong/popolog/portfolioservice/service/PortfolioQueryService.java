package myaong.popolog.portfolioservice.service;

import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;

public interface PortfolioQueryService {

	Portfolio findById(Long portfolioId);
	PortfoliosResponse getPortfolios(Long memberId);
	PortfolioResponse getPortfolio(Long memberId, Long portfolioId);
}
