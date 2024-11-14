package myaong.popolog.portfolioservice.service;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.converter.PortfolioConverter;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioQueryServiceImpl implements PortfolioQueryService {

	private final PortfolioRepository portfolioRepository;
	private final PortfolioConverter portfolioConverter;

	@Override
	public Portfolio findById(Long portfolioId) {
		return portfolioRepository.findById(portfolioId)
				.orElseThrow(() -> new ApiException(ApiCode.PORTFOLIO_NOT_FOUND));
	}

	@Override
	public PortfoliosResponse getPortfolios(Long memberId) {

		Portfolio main = portfolioRepository.findByMemberIdAndIsMain(memberId, true).get(0);
		List<Portfolio> sub = portfolioRepository.findByMemberIdAndIsMain(memberId, false);

		return portfolioConverter.toPortfoliosResponse(main, sub);
	}

	@Override
	public PortfolioResponse getPortfolio(Long memberId, Long portfolioId) {

		Portfolio portfolio = findById(portfolioId);

		PortfolioContentDTO portfolioContent = portfolioConverter.toPortfolioContentDTO(portfolio);

		return PortfolioResponse.builder()
				.title(portfolio.getTitle())
				.preferredJob(portfolio.getPreferredJob())
				.portfolioContent(portfolioContent)
				.build();
	}
}
