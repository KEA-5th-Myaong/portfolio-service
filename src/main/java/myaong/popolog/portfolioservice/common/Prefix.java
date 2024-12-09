package myaong.popolog.portfolioservice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Prefix {

	PORTFOLIO("portfolio");

	private final String name;

	@Override
	public String toString() {
		return name;
	}
}
