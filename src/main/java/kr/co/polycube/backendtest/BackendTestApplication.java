package kr.co.polycube.backendtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 배치 스캐줄러를 사용하기 위해 사용
public class BackendTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            BackendTestApplication.class,
            args
        );
    }

}
