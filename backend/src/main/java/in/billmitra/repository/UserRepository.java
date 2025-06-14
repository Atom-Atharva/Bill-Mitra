package in.billmitra.repository;

import in.billmitra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByEmail(String email);
}
