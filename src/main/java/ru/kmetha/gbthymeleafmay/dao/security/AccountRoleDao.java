package ru.kmetha.gbthymeleafmay.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.security.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Long> {

}
