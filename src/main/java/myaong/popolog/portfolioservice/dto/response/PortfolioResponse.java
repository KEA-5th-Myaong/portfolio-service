package myaong.popolog.portfolioservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;

@Getter
public class PortfolioResponse extends PortfolioContentDTO {

	private final String title;
	private final String preferredJob;

	@Builder
	public PortfolioResponse(String title, String preferredJob, PortfolioContentDTO portfolioContent) {
		super(portfolioContent);
		this.title = title;
		this.preferredJob = preferredJob;
	}
}
