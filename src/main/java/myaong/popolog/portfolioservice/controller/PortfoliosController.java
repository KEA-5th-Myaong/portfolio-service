package myaong.popolog.portfolioservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiResponse;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.service.PortfoliosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public ResponseEntity<ApiResponse<PortfolioIdResponse>> createPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest) {

		PortfolioIdResponse res = portfoliosService.createPortfolio(portfolioRequest);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@PutMapping("/{portfolioId}/main")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMain(@PathVariable Long portfolioId) {

		portfoliosService.updatePortfolioMain(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@PostMapping("/{portfolioId}/memo")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMemo(@PathVariable Long portfolioId) {

		portfoliosService.updatePortfolioMemo(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@PutMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<Object>> updatePortfolio(@PathVariable Long portfolioId,
															   @Valid @RequestBody PortfolioRequest portfolioRequest) {

		portfoliosService.updatePortfolio(portfolioId, portfolioRequest);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@DeleteMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<Object>> deletePortfolio(@PathVariable Long portfolioId) {

		portfoliosService.deletePortfolio(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}
}
