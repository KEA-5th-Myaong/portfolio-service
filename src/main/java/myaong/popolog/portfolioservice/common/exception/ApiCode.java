package myaong.popolog.portfolioservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiCode {

	OK(HttpStatus.OK, "COMMON_2000", "OK"),
	INVALID_DATA(HttpStatus.BAD_REQUEST, "COMMON_4000", "Request data missing or invalid"),
	READ_ONLY_ACCESS(HttpStatus.FORBIDDEN, "COMMON_4031", "You can only read"),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_4050", "Method not allowed"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5000", "Internal Server Error"),
	DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5001", "DB Error"),

	KEY_INVALID(HttpStatus.FORBIDDEN, "PORT_4030", " 잘못된 키입니다. URL을 다시 확인해주세요."),
	PORTFOLIO_NOT_FOUND(HttpStatus.NOT_FOUND, "PORT_4040", "존재하지 않는 포트폴리오입니다."),
	PORTFOLIO_LIMIT_EXCEEDED(HttpStatus.CONFLICT, "PORT_4090", "You cannot add more then 5 portfolios."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
