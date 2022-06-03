package net.ecplaza.ksdata.translation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TranslationService {
	@Autowired
	private ApplicationYamlRead applicationYamlRead;

	public String getCompanyTranslation(String keyword1) throws IOException {
		System.out.println(applicationYamlRead.getPusanUrl());
		String url = applicationYamlRead.getPusanUrl() + keyword1;
		Document doc = Jsoup.connect(url).get();
		Elements contents = doc.select("table tbody tr td.td2 span#outputRMGoyu");
//        Elements contents = doc.select("div table.engname_popular tbody tr td");
//        String name = contents.select("a").get(0).text();
//        System.out.println("===1.확인 Ser==");
//        System.out.println("=contents.text()=");
//        System.out.println(name);
//        return name;
		System.out.println("===1.CompanyName확인 Ser==");
		System.out.println(contents.text());
		return contents.text();
	}

	public String getCeoTranslation(String keyword2) throws IOException {
		System.out.println(applicationYamlRead.getNaverUrl());
		String url = applicationYamlRead.getNaverUrl() + keyword2;
		Document doc = Jsoup.connect(url).get();
		Elements contents = doc.select("div table.engname_popular tbody tr td");
		String name = contents.select("a").get(0).text();
		System.out.println("===2.Name확인 Ser==");
		System.out.println(name);
		return name;
	}

	public String getAddressTranslation(String keyword3) throws IOException {
		System.out.println(applicationYamlRead.getNaverAddressUrl());
		String url = applicationYamlRead.getNaverAddressUrl() + keyword3 + "영문주소";
//		if 만약 반점이 있으면, 첫번째 반점 앞까지만 번역. 반점 뒤(keyword3Remain)는 파파고번역에서 가져오는걸로
//		일단 첫번째 반점 앞까지만 번역 앞까지만 번역. 반점 뒤는 그대로 출력되게.
		if (keyword3.contains(",")) {
			keyword3 = keyword3.substring(keyword3.indexOf(","));
			System.out.println(keyword3);
		} else if (keyword3.contains("")) {
			keyword3 = keyword3.replace("", "%20");
		}

		Document doc = Jsoup.connect(url).get();
		Elements contents = doc.select("body div div div div section div div div div div table tbody tr td dl dd"); // 첫번째
																													// dd는
																													// 한글,
																													// 두번째
																													// dd는
																													// 영문
		String address = contents.select("dd").get(1).text();
		System.out.println("===3.Address확인 Ser==");
		System.out.println(address);
		return address;
	}

}

////private EncodingTest encodingTest;
//// URL을 GET형식으로 가져온 문서를 모두 가져오는 코드
////private static String NAME_TO_ROMAN_URL = "https://dict.naver.com/name-to-roman/translation/?query=박인규&x=0&y=0&where=name";
//@Autowired
//private ApplicationYamlReadName applicationYamlReadName;
//
////@PostConstruct
////@PreDestroy
//public String getTranslations(String keyword) throws IOException {
//	System.out.println("== 1. URL확인 Serv ==");
//	System.out.println(applicationYamlReadName.getNaverUrl()); // https://dict.naver.com/name-to-roman/translation/?query=
////	String keyword = applicationYamRead.getName();
//	String naverurl = applicationYamlReadName.getNaverUrl() + keyword;
//	Document doc = Jsoup.connect(naverurl).get();
//	// 파싱 메소드인 select()를 이용
//	Elements contents = doc.select("div table.engname_popular tbody tr td");
////	String name = contents.select("a").get(0).text();
//	System.out.println("== 2. text확인 Serv ==");
//	System.out.println(contents.text()); //
//	return contents.text();
//}