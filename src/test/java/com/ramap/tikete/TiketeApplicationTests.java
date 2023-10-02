package com.ramap.tikete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TiketeApplicationTests {

	@Test
	void contextLoads() {
//		Date datenow = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD h:mm:ss a");
//		String formattedDate = sdf.format(datenow);
//		Timestamp ts = new Timestamp(datenow.getTime());
//		LocalDateTime localDateTime = LocalDateTime.now();
//		LocalTime localTime = localDateTime.toLocalTime();
//		System.out.println("Toimestampnya 1 = "+ ts);
//		System.out.println("Toimestampnya 2 = "+ localDateTime);
//		System.out.println("Tomestampnya 3 = "+ datenow.getTime());
		
		String string = "1;123";
		String[] parts = string.split(";");
		List<String> lsStr = new ArrayList<String>();
		
		for(String data:parts) {
			System.out.println("data "+data);
			lsStr.add(data);
		}
	}

}
