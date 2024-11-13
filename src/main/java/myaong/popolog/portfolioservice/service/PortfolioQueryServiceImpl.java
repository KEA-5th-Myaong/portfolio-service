package myaong.popolog.portfolioservice.service;

import lombok.RequiredArgsConstructor;
import myaong.popolog.portfolioservice.converter.PortfolioConverter;
import myaong.popolog.portfolioservice.dto.response.PortfoliosResponse;
import myaong.popolog.portfolioservice.entity.Portfolio;
import myaong.popolog.portfolioservice.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioQueryServiceImpl implements PortfolioQueryService {

	private final PortfolioRepository portfolioRepository;
	private final PortfolioConverter portfolioConverter;

	@Override
	public PortfoliosResponse getPortfolios(Long memberId) {

		Portfolio main = portfolioRepository.findByMemberIdAndIsMain(memberId, true).get(0);
		List<Portfolio> sub = portfolioRepository.findByMemberIdAndIsMain(memberId, false);

		return portfolioConverter.toPortfoliosResponse(main, sub);
	}
}
