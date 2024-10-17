package myaong.popolog.portfolioservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.dto.PortfolioContent;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PortfoliosService {

	private final PortfolioRepository portfolioRepository;

	public PortfoliosResponse getPortfolios() {

		Portfolio main = portfolioRepository.findByMemberIdAndIsMain(5L, true).get(0);

		PortfoliosResponse.Portfolio resMain = PortfoliosResponse.Portfolio.builder()
				.portfolioId(main.getId())
				.portfolioName(main.getTitle())
				.timestamp(main.getCreatedAt())
				.memo(main.getMemo())
				.build();

		List<Portfolio> resPortfolioList = portfolioRepository.findByMemberIdAndIsMain(5L, false);
		List<PortfoliosResponse.Portfolio> resPortfolios = new ArrayList<>();

		for (Portfolio p : resPortfolioList) {

			PortfoliosResponse.Portfolio portfolio = PortfoliosResponse.Portfolio.builder()
					.portfolioId(p.getId())
					.portfolioName(p.getTitle())
					.timestamp(p.getCreatedAt())
					.memo(p.getMemo())
					.build();

			resPortfolios.add(portfolio);
		}

		return PortfoliosResponse.builder()
				.main(resMain)
				.portfolios(resPortfolios)
				.build();
	}

	public PortfolioResponse getPortfolio(Long portfolioId) {

		Portfolio portfolio = portfolioRepository.findById(portfolioId).get();

		PortfolioContent portfolioContent;
		ObjectMapper mapper = new ObjectMapper();
		try {
			portfolioContent = mapper.readValue(portfolio.getContent(), PortfolioContent.class);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
			throw new ApiException(ApiCode.DB_ERROR);
		}

		return new PortfolioResponse(portfolio.getTitle(), portfolio.getPreferredJob(), portfolioContent);
	}
}
