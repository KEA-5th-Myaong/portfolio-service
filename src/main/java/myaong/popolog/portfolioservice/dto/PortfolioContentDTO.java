package myaong.popolog.portfolioservice.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myaong.popolog.portfolioservice.dto.response.PortfolioResponse;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PortfolioContentDTO {

	private String tel;
	private String email;
	private List<PortfolioResponse.Education> educations;
	private List<PortfolioResponse.Experience> experiences;
	private String picUrl;
	private PortfolioResponse.Ps ps;
	private List<Link> links;
	private List<String> skills;
	private List<PortfolioResponse.Certification> certifications;
	private List<PortfolioResponse.ExtraActivity> extraActivities;

	@Getter
	public static class Education {

		private String name;
		private String major;
		private String graduation;
		private Double gpa;
	}

	@Getter
	public static class Experience {

		private String name;
		private String start;
		private String end;
		private String position;
		private String achievement;
	}

	@Getter
	public static class Ps {

		private String title;
		private String position;
		private String reason;
		private String content;
	}

	@Getter
	public static class Link {

		private String name;
		private String link;
	}

	@Getter
	public static class Certification {

		private String name;
		private String date;
	}

	@Getter
	public static class ExtraActivity {

		private String name;
		private String start;
		private String end;
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
