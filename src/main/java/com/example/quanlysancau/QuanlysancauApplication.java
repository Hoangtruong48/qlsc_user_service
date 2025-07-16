package com.example.quanlysancau;

import com.example.quanlysancau.config.XmlConfigManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuanlysancauApplication {

	public static void main(String[] args) {
		XmlConfigManager config = XmlConfigManager.getInstance("E:\\quanlysancau\\quanlysancau\\config\\config.xml");
//		// Sử dụng trong toàn bộ ứng dụng
//		String dbUrl = config.get("db.url");
//		String dbUser = config.get("db.username");
//		String timeout = config.get("app.timeout_ms", "3000"); // Giá trị mặc định nếu không tồn tại
//
//		// In ra để kiểm tra
//		System.out.println("Database URL: " + dbUrl);
//		System.out.println("Database User: " + dbUser);
//		System.out.println("Timeout: " + timeout);
//
//		// Lấy tất cả config
//		Map<String, String> allConfigs = config.getAll();
//		allConfigs.forEach((k, v) -> System.out.println(k + ": " + v));
		SpringApplication.run(QuanlysancauApplication.class, args);
	}

}
