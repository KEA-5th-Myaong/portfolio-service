package myaong.popolog.portfolioservice.service;

import jakarta.validation.Valid;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;

public interface PortfolioCommandService {

	PortfolioIdResponse createPortfolio(Long memberId, @Valid PortfolioRequest portfolioRequest);
}
