package myaong.popolog.portfolioservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiResponse;
import myaong.popolog.portfolioservice.dto.request.MemoRequest;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.*;
import myaong.popolog.portfolioservice.service.PortfolioCommandService;
import myaong.popolog.portfolioservice.service.PortfolioQueryService;
import myaong.popolog.portfolioservice.service.PortfoliosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfoliosController {

	private final PortfolioQueryService portfolioQueryService;
	private final PortfolioCommandService portfolioCommandService;
	private final PortfoliosService portfoliosService;

	@Operation(summary = "API 명세서 v0.3 line 72", description = "포트폴리오 목록 조회")
	@GetMapping
	public ResponseEntity<ApiResponse<PortfoliosResponse>> getPortfolios(@RequestHeader(name = "memberId") Long memberId) {

		PortfoliosResponse res = portfolioQueryService.getPortfolios(memberId);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.4 line 74", description = "포트폴리오 조회")
	@GetMapping("/{portfolioId}")
	public ResponseEntity<ApiResponse<PortfolioResponse>> getPortfolio(@RequestHeader(name = "memberId") Long memberId,
																	   @PathVariable Long portfolioId) {

		PortfolioResponse res = portfolioQueryService.getPortfolio(memberId, portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.4 line 75", description = "포트폴리오 작성")
	@PostMapping
	public ResponseEntity<ApiResponse<PortfolioIdResponse>> createPortfolio(@RequestHeader(name = "memberId") Long memberId,
																			@Valid @RequestBody PortfolioRequest portfolioRequest) {

		PortfolioIdResponse res = portfolioCommandService.createPortfolio(memberId, portfolioRequest);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.4 line 76", description = "포트폴리오 이미지 등록")
	@PostMapping("/pic")
	public ResponseEntity<ApiResponse<PicUrlResponse>> storePicture(@RequestParam(value = "pic") MultipartFile pic) {

		PicUrlResponse res = portfolioCommandService.storeImage(pic);

		return ResponseEntity.ok(ApiResponse.onSuccess(res));
	}

	@Operation(summary = "API 명세서 v0.4 line 77", description = "포트폴리오 이미지 삭제")
	@DeleteMapping("/pic")
	public ResponseEntity<ApiResponse<Object>> deletePicture(@RequestParam(value = "picUrl") String picUrl) {

		portfolioCommandService.deleteImage(picUrl);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@Operation(summary = "API 명세서 v0.3 line 77", description = "대표 포트폴리오 설정")
	@PutMapping("/{portfolioId}/main")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMain(@RequestHeader(name = "memberId") Long memberId,
																   @PathVariable Long portfolioId) {

		portfolioCommandService.updatePortfolioMain(memberId, portfolioId);

		return ResponseEntity.ok(ApiResponse.onSuccess(null));
	}

	@Operation(summary = "API 명세서 v0.3 line 78", description = "포트폴리오 메모 등록")
	@PostMapping("/{portfolioId}/memo")
	public ResponseEntity<ApiResponse<Object>> updatePortfolioMemo(@RequestHeader(name = "memberId") Long memberId,
																   @PathVariable Long portfolioId,
																   @RequestBody MemoRequest req) {

		portfolioCommandService.updatePortfolioMemo(memberId, portfolioId, req);

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
