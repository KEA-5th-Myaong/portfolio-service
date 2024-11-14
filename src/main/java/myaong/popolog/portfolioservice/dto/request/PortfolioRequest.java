package myaong.popolog.portfolioservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;

@Getter
public class PortfolioRequest extends PortfolioContentDTO {

	@NotNull
	private String title;
	@NotNull
	private String preferredJob;
}
