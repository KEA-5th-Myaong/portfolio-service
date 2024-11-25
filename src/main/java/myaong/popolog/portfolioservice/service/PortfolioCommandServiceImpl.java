package myaong.popolog.portfolioservice.service;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.Prefix;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.converter.PortfolioConverter;
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

	private final PortfolioRepository portfolioRepository;
	private final PortfolioConverter portfolioConverter;
	private final S3ApiService s3ApiService;

	@Override
	public void validByIdAndMemberId(Long portfolioId, Long memberId) {
		boolean canAccessPortfolio = portfolioRepository.existsByIdAndMemberId(portfolioId, memberId);
		if (!canAccessPortfolio) throw new ApiException(ApiCode.PORTFOLIO_NOT_FOUND);
	}

	@Override
	public PortfolioIdResponse createPortfolio(Long memberId, PortfolioRequest portfolioRequest) {

		// 이미 5개가 다 찼으면 오류 반환
		Long count = portfolioRepository.countByMemberId(memberId);
		if (count >= 5) throw new ApiException(ApiCode.PORTFOLIO_LIMIT_EXCEEDED);

		// 만약 기존 portfolio가 하나도 없다면 지금 추가하는 값이 main
		Boolean isMain = count.equals(0L);

		Portfolio portfolio = portfolioConverter.toPortfolio(memberId, portfolioRequest, isMain);

		// 생성된 포트폴리오의 ID를 반환
		Long portfolioId = portfolioRepository.save(portfolio).getId();
		return new PortfolioIdResponse(portfolioId);
	}

	@Override
	public PicUrlResponse storeImage(Long memberId, Long portfolioId, MultipartFile pic) {

		validByIdAndMemberId(portfolioId, memberId);

		String imageUrl = s3ApiService.uploadToTempStorage(Prefix.PORTFOLIO, pic);
		return new PicUrlResponse(imageUrl);
	}

	@Override
	public void deleteImage(Long memberId, Long portfolioId, String picUrl) {

		validByIdAndMemberId(portfolioId, memberId);

		s3ApiService.deleteFromPersistentStorage(picUrl);
	}
}
