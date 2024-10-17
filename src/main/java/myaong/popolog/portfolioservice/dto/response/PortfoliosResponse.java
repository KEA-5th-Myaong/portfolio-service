package myaong.popolog.portfolioservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PortfoliosResponse {

	private Portfolio main;
	private List<Portfolio> portfolios;

	@Builder
	@Getter
	public static class Portfolio {

		private Long portfolioId;
		private String portfolioName;
		private LocalDateTime timestamp;
		private String memo;
	}
}
