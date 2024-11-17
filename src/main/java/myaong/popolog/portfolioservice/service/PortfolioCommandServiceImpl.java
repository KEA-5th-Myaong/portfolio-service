package myaong.popolog.portfolioservice.service;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.common.exception.ApiCode;
import myaong.popolog.portfolioservice.common.exception.ApiException;
import myaong.popolog.portfolioservice.converter.PortfolioConverter;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioCommandServiceImpl implements PortfolioCommandService {

	private final PortfolioRepository portfolioRepository;
	private final PortfolioConverter portfolioConverter;

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
}
