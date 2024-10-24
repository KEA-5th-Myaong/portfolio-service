package myaong.popolog.portfolioservice.controller;

import io.swagger.v3.oas.annotations.Operation;
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

	@Operation(summary = "API 명세서 v0.3 line 72", description = "포트폴리오 목록 조회")
	@GetMapping
	public ResponseEntity<ApiResponse<PortfoliosResponse>> getPortfolios() {

		PortfoliosResponse res = portfoliosService.getPortfolios();

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.3 line 73", description = "포트폴리오 조회")
	@GetMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<PortfolioResponse>> getPortfolio(@PathVariable Long portfolioId) {

		PortfolioResponse res = portfoliosService.getPortfolio(1L);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.3 line 74", description = "포트폴리오 작성")
	@PostMapping
	public ResponseEntity<ApiResponse<PortfolioIdResponse>> createPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest) {

		PortfolioIdResponse res = portfoliosService.createPortfolio(portfolioRequest);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.3 line 77", description = "대표 포트폴리오 설정")
	@PutMapping("/{portfolioId}/main")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMain(@PathVariable Long portfolioId) {

		portfoliosService.updatePortfolioMain(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@Operation(summary = "API 명세서 v0.3 line 78", description = "포트폴리오 메모 등록")
	@PostMapping("/{portfolioId}/memo")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMemo(@PathVariable Long portfolioId) {

		portfoliosService.updatePortfolioMemo(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@Operation(summary = "API 명세서 v0.3 line 79", description = "포트폴리오 수정")
	@PutMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<Object>> updatePortfolio(@PathVariable Long portfolioId,
															   @Valid @RequestBody PortfolioRequest portfolioRequest) {

		portfoliosService.updatePortfolio(portfolioId, portfolioRequest);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@Operation(summary = "API 명세서 v0.3 line 80", description = "포트폴리오 삭제")
	@DeleteMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<Object>> deletePortfolio(@PathVariable Long portfolioId) {

		portfoliosService.deletePortfolio(portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}
}
