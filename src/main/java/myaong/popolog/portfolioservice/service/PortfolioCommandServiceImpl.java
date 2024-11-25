package myaong.popolog.portfolioservice.service;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.Prefix;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.converter.PortfolioConverter;
import myaong.popolog.portfolioservice.dto.request.MemoRequest;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PicUrlResponse;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioCommandServiceImpl implements PortfolioCommandService {

	private final PortfolioQueryServiceImpl portfolioQueryService;
	private final PortfolioRepository portfolioRepository;
	private final PortfolioConverter portfolioConverter;
	private final S3ApiService s3ApiService;

	@Override
	public PortfolioIdResponse createPortfolio(Long memberId, PortfolioRequest portfolioRequest) {

		// 이미 5개가 다 찼으면 오류 반환
		Long count = portfolioRepository.countByMemberId(memberId);
		if (count >= 5) {
			throw new ApiException(ApiCode.PORTFOLIO_LIMIT_EXCEEDED);
		}

		// 이미지 URL 변경
		String tempPicUrl = portfolioRequest.getPicUrl();
		if (tempPicUrl != null) {
			// 이미지를 영구 저장소로 이동
			String persistentPicUrl = s3ApiService.moveToPersistentStorage(tempPicUrl);
			// 임시 URL을 영구 저장소의 URL로 변경
			portfolioRequest.setPicUrl(persistentPicUrl);
		}

		// 만약 기존 portfolio가 하나도 없다면 지금 추가하는 값이 main
		Boolean isMain = count.equals(0L);

		Portfolio portfolio = portfolioConverter.toPortfolio(memberId, portfolioRequest, isMain);

		// 생성된 포트폴리오의 ID를 반환
		Long portfolioId = portfolioRepository.save(portfolio).getId();
		return new PortfolioIdResponse(portfolioId);
	}

	@Override
	public PicUrlResponse storeImage(MultipartFile pic) {

		String imageUrl = s3ApiService.uploadToTempStorage(Prefix.PORTFOLIO, pic);
		return new PicUrlResponse(imageUrl);
	}

	@Override
	public void deleteImage(String picUrl) {

		s3ApiService.deleteFromPersistentStorage(picUrl);
	}

	@Override
	public void updatePortfolioMain(Long memberId, Long portfolioId) {

		// 기존 메인 포트폴리오는 일반 포트폴리오로 변경
		Portfolio mainPortfolio = portfolioRepository.findMainByMemberId(memberId);
		mainPortfolio.updateMain(false);

		Portfolio portfolio = portfolioQueryService.findByIdAndMemberId(portfolioId, memberId);
		portfolio.updateMain(true);
	}

	@Override
	public void updatePortfolioMemo(Long memberId, Long portfolioId, MemoRequest req) {

		Portfolio portfolio = portfolioQueryService.findByIdAndMemberId(portfolioId, memberId);

		String memo = req.getMemo();

		if (memo == null || memo.isEmpty()) {
			portfolio.initializeMemo();
		} else {
			portfolio.updateMemo(req.getMemo());
		}
	}

	@Override
	public void updatePortfolio(Long memberId, Long portfolioId, PortfolioRequest req) {

		Portfolio portfolio = portfolioQueryService.findByIdAndMemberId(portfolioId, memberId);

		// 기존 이미지 URL이 있다면 이미지 삭제
		String existedPicUrl = portfolioConverter.toPortfolioContentDTO(portfolio).getPicUrl();
		if (existedPicUrl != null) {
			s3ApiService.deleteFromPersistentStorage(existedPicUrl);
		}

		// 이미지 URL 변경
		String tempPicUrl = req.getPicUrl();
		if (tempPicUrl != null) {
			// 이미지를 영구 저장소로 이동
			String persistentPicUrl = s3ApiService.moveToPersistentStorage(tempPicUrl);
			// 임시 URL을 영구 저장소의 URL로 변경
			req.setPicUrl(persistentPicUrl);
		}

		// 포트폴리오 수정
		String content = portfolioConverter.toPortfolio_Content(req);
		portfolio.updatePortfolio(req.getTitle(), req.getPreferredJob(), content);

		// 수정된 포트폴리오 저장
		portfolioRepository.save(portfolio);
	}
}
