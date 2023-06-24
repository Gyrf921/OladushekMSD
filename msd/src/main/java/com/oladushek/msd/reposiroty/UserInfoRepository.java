package com.oladushek.msd.reposiroty;

import com.oladushek.msd.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByLogin(String login);

    void deleteByLogin(String login);


}
