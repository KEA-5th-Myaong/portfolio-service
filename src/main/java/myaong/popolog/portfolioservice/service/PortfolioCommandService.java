package myaong.popolog.portfolioservice.service;

import myaong.popolog.portfolioservice.dto.request.PortfolioRequest;
import myaong.popolog.portfolioservice.dto.response.PicUrlResponse;
import myaong.popolog.portfolioservice.dto.response.PortfolioIdResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PortfolioCommandService {

	void validByIdAndMemberId(Long portfolioId, Long memberId);

	PortfolioIdResponse createPortfolio(Long memberId, PortfolioRequest portfolioRequest);

	PicUrlResponse storeImage(MultipartFile pic);

	void deleteImage(String picUrl);
}
