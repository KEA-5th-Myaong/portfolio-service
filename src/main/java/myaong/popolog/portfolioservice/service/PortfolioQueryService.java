package myaong.popolog.portfolioservice.service;

import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;

public interface PortfolioQueryService {

	PortfoliosResponse getPortfolios(Long memberId);
}
