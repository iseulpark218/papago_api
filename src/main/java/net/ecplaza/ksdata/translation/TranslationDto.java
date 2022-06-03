package net.ecplaza.ksdata.translation;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TranslationDto {

	private Long id;

	private String companyName;
	private String companyNameKr;
	private String ceo;
	private String ceoKr;
	private String address;
	private String addressKr;
	private String product;
	private String productKr;
}
