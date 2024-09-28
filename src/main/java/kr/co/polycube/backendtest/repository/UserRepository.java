package kr.co.polycube.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.polycube.backendtest.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
