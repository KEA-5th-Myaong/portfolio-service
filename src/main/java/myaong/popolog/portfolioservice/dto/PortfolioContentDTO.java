package myaong.popolog.portfolioservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PortfolioContentDTO {

	@NotBlank(message = "전화번호를 입력해주세요.")
	private String tel;
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	@Size(min = 1, message = "최소 1개 이상의 학력을 입력해주세요.")
	private List<PortfolioResponse.Education> educations;
	@Valid
	private List<PortfolioResponse.Experience> experiences;
	private String picUrl;
	@Valid
	private PortfolioResponse.Ps ps;
	@Valid
	private List<Link> links;
	private List<String> skills;
	@Valid
	private List<PortfolioResponse.Certification> certifications;
	@Valid
	private List<PortfolioResponse.ExtraActivity> extraActivities;

	@Getter
	public static class Education {

		@NotBlank(message = "학교 이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "학과를 입력해주세요. 고등학교인 경우 계열을 입력해주세요.")
		private String major;
		@NotBlank(message = "졸업 일자 혹은 졸업 예정 일자를 입력해주세요.")
		private String graduation;
		private Double gpa;
	}

	@Getter
	public static class Experience {

		@NotBlank(message = "회사 이름을 입력해주세요.")
		private String name;
		private String start;
		private String end;
		@NotBlank(message = "직책을 입력해주세요.")
		private String position;
		private String achievement;
	}

	@Getter
	public static class Ps {

		@NotBlank(message = "자기소개서 제목이 누락되었습니다.")
		private String title;
		@NotBlank(message = "자기소개서 지원 직무가 누락되었습니다.")
		private String position;
		@NotBlank(message = "자기소개서 지원 사유가 누락되었습니다.")
		private String reason;
		@NotBlank(message = "자기소개서 내용이 누락되었습니다.")
		private String content;
	}

	@Getter
	public static class Link {

		@NotBlank(message = "링크 이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "사이트 URL을 입력해주세요.")
		private String link;
	}

	@Getter
	public static class Certification {

		@NotBlank(message = "자격증 이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "자격증 취득일자를 입력해주세요.")
		private String date;
	}

	@Getter
	public static class ExtraActivity {

		@NotBlank(message = "교육|대외활동 이름을 입력해주세요.")
		private String name;
		@NotBlank(message = "교육|대외활동 시작 일자를 입력해주세요.")
		private String start;
		private String end;
		@NotBlank(message = "교육|대외활동 기관을 입력해주세요.")
		private String institution;
		private String description;
	}

	protected PortfolioContentDTO(PortfolioContentDTO portfolioContentDTO) {

		this.tel = portfolioContentDTO.getTel();
		this.email = portfolioContentDTO.getEmail();
		this.educations = portfolioContentDTO.getEducations();
		this.experiences = portfolioContentDTO.getExperiences();
		this.picUrl = portfolioContentDTO.getPicUrl();
		this.ps = portfolioContentDTO.getPs();
		this.links = portfolioContentDTO.getLinks();
		this.skills = portfolioContentDTO.getSkills();
		this.certifications = portfolioContentDTO.getCertifications();
		this.extraActivities = portfolioContentDTO.getExtraActivities();
	}
}
