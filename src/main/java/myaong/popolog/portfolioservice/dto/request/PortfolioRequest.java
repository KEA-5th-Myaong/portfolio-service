package myaong.popolog.portfolioservice.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import myaong.popolog.portfolioservice.dto.PortfolioContentDTO;

@Getter
@JsonSerialize(as = PortfolioContentDTO.class)
public class PortfolioRequest extends PortfolioContentDTO {

	@NotBlank(message = "포트폴리오 제목을 입력해주세요.")
	private String title;
	@NotBlank(message = "포트폴리오 관심 직무를 입력해주세요.")
	private String preferredJob;
}
