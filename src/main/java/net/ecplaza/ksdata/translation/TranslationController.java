package net.ecplaza.ksdata.translation;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TranslationController {
	private TranslationService translationService;

	@GetMapping("/translation")
	public String home() {
//		아래 encode테스트
		String text;
		String korean = "안녕하세요, translation home입니다.";
		try {
			text = URLEncoder.encode(korean, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		System.out.println(korean);
		return "translation/home";
	}

//	전부 번역
	@GetMapping("/translation/all")
	public String allTranslationForm() {
		return "translation/all";
	}

	@GetMapping("/translation/all/result")
	public String allTranslationForm(
			@RequestParam(value = "keyword1", required = false, defaultValue = "") String keyword1,
			@RequestParam(value = "keyword2", required = false, defaultValue = "") String keyword2, Model model) {
		try {
			String result1 = translationService.getCompanyTranslation(keyword1);
			String result2 = translationService.getCeoTranslation(keyword2);
			if (result1.equals("")) {
				model.addAttribute("result1", "입력해주세요.");
			} else if (result2.equals("")) {
				model.addAttribute("result2", "입력해주세요.");
			} else {
				model.addAttribute("result1", result1);
				model.addAttribute("result2", result2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "translation/all";
	}

//	상호명 번역
	@GetMapping("/translation/company")
	public String companyTranslationForm() {
		return "translation/company";
	}

	@GetMapping("/translation/company/result")
	public String companyTranslationForm(
			@RequestParam(value = "keyword1", required = false, defaultValue = "") String keyword1, Model model) {
		try {
			String result = translationService.getCompanyTranslation(keyword1);
			if (result.equals("")) {
				model.addAttribute("result", "입력해주세요.");
			} else {
				model.addAttribute("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "translation/company";
	}

//	대표자명 번역
	@GetMapping("/translation/ceo")
	public String ceoTranslationForm() {
		return "translation/ceo";
	}

	// 잠시주석
	@GetMapping("/translation/ceo/result")
	public String ceoTranslationForm(
			@RequestParam(value = "keyword2", required = false, defaultValue = "") String keyword2, Model model) {
		try {
			String result = translationService.getCeoTranslation(keyword2);
			model.addAttribute("result", result);
		} catch (Exception e) {
			model.addAttribute("result", "입력해주세요.");
			e.printStackTrace();
		}
		return "translation/ceo";
	}

//	주소 번역 (경기->경기도 변경해야함)
	@GetMapping("/translation/address")
	public String addressTranslationForm() {
		return "translation/address";
	}

	@GetMapping("/translation/address/result")
	public String addressTranslationForm(
			@RequestParam(value = "keyword3", required = false, defaultValue = "") String keyword3, Model model) {
		try {
			String result = translationService.getAddressTranslation(keyword3);
			model.addAttribute("result", result);
		} catch (Exception e) {
			model.addAttribute("result", "입력해주세요.");
			e.printStackTrace();
		}
		return "translation/address";
	}
}
