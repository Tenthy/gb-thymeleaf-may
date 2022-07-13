package ru.kmetha.gbthymeleafmay.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {

}
