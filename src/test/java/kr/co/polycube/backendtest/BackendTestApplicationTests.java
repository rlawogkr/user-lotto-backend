package kr.co.polycube.backendtest;

import com.jayway.jsonpath.JsonPath;
import kr.co.polycube.backendtest.entity.Users;
import kr.co.polycube.backendtest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendTestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long testUserId;

    @BeforeEach
    public void setup() {
        // 테스트 시작 전에 사용자 생성
        Users testUser = new Users("재학");
        testUser = userRepository.save(testUser);
        testUserId = testUser.getId();
    }
    // TODO:사용자 생성 테스트
    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/users")
                        .content("{\"name\": \"abab\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // TODO:사용자 조회 테스트
    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/users/" + testUserId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("재학"));
    }

    // TODO:사용자 수정 테스트
    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(put("/users/" + testUserId)
                        .content("{\"name\": \"재학2\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/" + testUserId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("재학2"));
    }

    // TODO:/users/{id}?name=test!! api호출에 대한 테스트
    @Test
    public void testValidUrl() throws Exception {
        // 허용된 특수문자만 포함된 요청 (정상적인 요청)
        mockMvc.perform(get("/users/1?name=재학")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidUrl() throws Exception {
        // 허용되지 않은 특수문자가 포함된 요청 (잘못된 요청)
        mockMvc.perform(get("/users/1?name=test!!")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
