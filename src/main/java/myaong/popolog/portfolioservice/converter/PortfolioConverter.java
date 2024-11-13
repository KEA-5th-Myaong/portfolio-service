package myaong.popolog.portfolioservice.converter;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioConverter {

	private final PortfolioRepository portfolioRepository;

	public PortfoliosResponse toPortfoliosResponse(Portfolio mainPortfolio, List<Portfolio> subPortfolios) {

		PortfoliosResponse.Portfolio main = PortfoliosResponse.Portfolio.builder()
				.portfolioId(mainPortfolio.getId())
				.portfolioName(mainPortfolio.getTitle())
				.timestamp(mainPortfolio.getCreatedAt())
				.memo(mainPortfolio.getMemo())
				.build();

		List<PortfoliosResponse.Portfolio> portfolios = new ArrayList<>();
		for (Portfolio p : subPortfolios) {

			PortfoliosResponse.Portfolio portfolio = PortfoliosResponse.Portfolio.builder()
					.portfolioId(p.getId())
					.portfolioName(p.getTitle())
					.timestamp(p.getCreatedAt())
					.memo(p.getMemo())
					.build();

			portfolios.add(portfolio);
		}

		return PortfoliosResponse.builder()
				.main(main)
				.portfolios(portfolios)
				.build();
	}
}
