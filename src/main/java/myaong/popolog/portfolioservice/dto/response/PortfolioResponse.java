package myaong.popolog.portfolioservice.dto.response;

import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContent;

@Getter
public class PortfolioResponse extends PortfolioContent {

	private String title;
	private String preferredJob;

	public PortfolioResponse(String title, String preferredJob, PortfolioContent portfolioContent) {
		super(portfolioContent);
		this.title = title;
		this.preferredJob = preferredJob;
	}
}
