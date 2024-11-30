package myaong.popolog.portfolioservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;

@Getter
public class PortfolioResponse extends PortfolioContentDTO {

	private final Long portfolioId;
	private final String title;
	private final String preferredJob;

	@Builder
	public PortfolioResponse(Long portfolioId, String title, String preferredJob, PortfolioContentDTO portfolioContent) {
		super(portfolioContent);
		this.portfolioId = portfolioId;
		this.title = title;
		this.preferredJob = preferredJob;
	}
}
