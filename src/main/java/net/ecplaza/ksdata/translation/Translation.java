package net.ecplaza.ksdata.translation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TranslationTest")
public class Translation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("상호")
	@Column(name = "company_name", columnDefinition = "varchar(100) default '' ")
	private String companyName;

	@Comment("상호(한글)")
	@Column(name = "company_name_kr", columnDefinition = "varchar(100) default '' ")
	private String companyNameKr;

	@Comment("대표자")
	@Column(name = "ceo", columnDefinition = "varchar(100) default '' ")
	private String ceo;

	@Comment("대표자(한글)")
	@Column(name = "ceo_kr", columnDefinition = "varchar(100) default '' ")
	private String ceoKr;

	@Comment("주소")
	@Column(name = "address", columnDefinition = "varchar(255) default '' ")
	private String address;

	@Comment("주소(한글)")
	@Column(name = "address_kr", columnDefinition = "varchar(255) default '' ")
	private String addressKr;

	@Comment("대표상품")
	@Lob
	private String product;

	@Comment("대표상품(한글)")
	@Lob
	private String productKr;
}
