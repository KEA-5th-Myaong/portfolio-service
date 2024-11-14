package myaong.popolog.portfolioservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PortfoliosService {

	private final PortfolioRepository portfolioRepository;

	public PortfolioIdResponse createPortfolio(PortfolioRequest portfolioRequest) {

		return new PortfolioIdResponse(1L);
	}

	public void updatePortfolioMain(Long portfolioId) {
	}

	public void updatePortfolioMemo(Long portfolioId) {
	}

	public void updatePortfolio(Long portfolioId, PortfolioRequest portfolioRequest) {
	}

	public void deletePortfolio(Long portfolioId) {
	}
}
