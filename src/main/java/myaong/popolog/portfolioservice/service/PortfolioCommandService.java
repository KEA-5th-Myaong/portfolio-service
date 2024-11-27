package myaong.popolog.portfolioservice.service;

import myaong.popolog.portfolioservice.dto.request.MemoRequest;
import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PicUrlResponse;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PortfolioCommandService {

	PortfolioIdResponse createPortfolio(Long memberId, PortfolioRequest portfolioRequest);

	PicUrlResponse storeImage(MultipartFile pic);

	void updatePortfolioMain(Long memberId, Long portfolioId);

	void updatePortfolioMemo(Long memberId, Long portfolioId, MemoRequest req);

	void updatePortfolio(Long memberId, Long portfolioId, PortfolioRequest req);

	void deletePortfolio(Long memberId, Long portfolioId);
}
