package myaong.popolog.portfolioservice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioConverter {

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

	public PortfolioContentDTO toPortfolioContentDTO(Portfolio portfolio) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(portfolio.getContent(), PortfolioContentDTO.class);
		} catch (JsonProcessingException e) {
			throw new ApiException(ApiCode.DB_ERROR);
		}
	}
}
