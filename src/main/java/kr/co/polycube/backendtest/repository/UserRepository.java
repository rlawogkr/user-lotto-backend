package kr.co.polycube.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.co.polycube.backendtest.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
