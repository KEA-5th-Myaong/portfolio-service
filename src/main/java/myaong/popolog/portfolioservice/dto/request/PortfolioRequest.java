package myaong.popolog.portfolioservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContent;

@Getter
public class PortfolioRequest extends PortfolioContent {

	@NotNull
	private String title;
	@NotNull
	private String preferredJob;
}
