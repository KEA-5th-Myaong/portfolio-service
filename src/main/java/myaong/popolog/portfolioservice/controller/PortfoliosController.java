package myaong.popolog.portfolioservice.controller;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiResponse;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.service.PortfoliosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfoliosController {

	private final PortfoliosService portfoliosService;

	@GetMapping
	public ResponseEntity<ApiResponse<PortfoliosResponse>> getPortfolios() {

		PortfoliosResponse res = portfoliosService.getPortfolios();

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@GetMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<PortfolioResponse>> getPortfolio(@PathVariable Long portfolioId) {

		PortfolioResponse res = portfoliosService.getPortfolio(1L);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}
}
