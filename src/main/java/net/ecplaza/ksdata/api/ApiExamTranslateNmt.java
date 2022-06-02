package net.ecplaza.ksdata.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.ecplaza.ksdata.service.TranslationService;

// 네이버 기계번역 (Papago SMT) API 예제
@Controller
@RequiredArgsConstructor
public class ApiExamTranslateNmt {

	TranslationService translationService;
	net.ecplaza.ksdata.dto.TranslationDto translationdto;
	
	@GetMapping("/translation")
	public String translation(Model model) {
//		model.addAttribute("translationDto", new TranslationDto());
		return "dictionary/translation";
	}
	
	@GetMapping("/translation/result")
	public String translationResult(@RequestParam(value="korean", required=false, defaultValue="")String korean, Model model) {
		model.addAttribute("result", korean);
		return "dictionary/translation";
	}

	public static void main(String[] args) throws ParseException {

		String clientId = "tD340CxS_2_rtl1CUfYo";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "I7HhWfRR3Y";// 애플리케이션 클라이언트 시크릿값";

		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		String text;
		String korean = "번역할 내용 적는 자리";
		try {
			text = URLEncoder.encode(korean, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = post(apiURL, requestHeaders, text);
		System.out.println("1.확인-responseBody");
		System.out.println(responseBody);

//		JSONParser jsonParser = new JSONParser();
//
//		JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
//		System.out.println("1.확인-jsonObject");
//		System.out.println(jsonObject);
//
//		JSONObject objMessage = (JSONObject) jsonObject.get("message");
//		System.out.println("1.확인-objMessage");
//		System.out.println(objMessage);
//
//		JSONObject objResult = (JSONObject) objMessage.get("result");
//		System.out.println("1.확인-objResult");
//		System.out.println(objResult);
//
//		String translatedText = (String) objResult.get("translatedText");
//		System.out.println("1.확인-translatedText");
//		System.out.println(translatedText);
	}

	private static String post(String apiUrl, Map<String, String> requestHeaders, String text) {
		HttpURLConnection con = connect(apiUrl);
		String postParams = "source=ko&target=en&text=" + text; // 원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
		try {
			con.setRequestMethod("POST");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
				return readBody(con.getInputStream());
			} else { // 에러 응답
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}