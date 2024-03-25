package com.itwill.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * Internationalization(i18n) 국제화
 * 
 * locale(로케일)
 * 	- 로케일은 사용자 인터페이스에서 사용되는 언어, 지역 설정, 출력 형식 등을 정의하는 문자열입니다.
 */
public class InternationalizationMain {

	public static void main(String[] args) {

		Locale currentLocale = Locale.getDefault();// os locale
		System.out.println(currentLocale.getLanguage());
		System.out.println(currentLocale.getCountry());

		Locale usLocale = new Locale("en", "US");
		Locale jpLocale = new Locale("ja", "JP");
		Locale krLocale = new Locale("ko", "KR");
		Locale cnLocale = new Locale("zh", "CN");
		Locale esLocale = new Locale("es", "ES");

		Locale locale = jpLocale;

		/*
		  Locale정보를 이용해서 guest_xx_YY.properties 중하나를 읽어서 
		 	xx--> 언어정보(en,ko,ja) 
		 	YY--> 국가정보(US,KR,JP)
		 
		 ResourceBundle객체생성
		 */
		ResourceBundle resBundle1 = ResourceBundle.getBundle("com/itwill/i18n/guest", locale);
		System.out.println(locale.getLanguage() + "_" + locale.getCountry());

		System.out.println(resBundle1.getString("title.main"));
		System.out.println(resBundle1.getString("title.list"));
		System.out.println(resBundle1.getString("title.view"));
		System.out.println(resBundle1.getString("title.write"));
		System.out.println(resBundle1.getString("write.label.name"));
		System.out.println(resBundle1.getString("write.label.email"));
		System.out.println(resBundle1.getString("write.label.homepage"));
		System.out.println(resBundle1.getString("write.label.title"));

	}

}
