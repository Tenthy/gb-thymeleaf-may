//package ru.kmetha.externalapi.security;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kmetha.externalapi.dao.security.AccountRoleDao;
//import ru.kmetha.externalapi.dao.security.AccountUserDao;
//import ru.kmetha.externalapi.entity.security.AccountRole;
//import ru.kmetha.externalapi.entity.security.AccountUser;
//import ru.kmetha.externalapi.entity.security.enums.AccountStatus;
//import ru.kmetha.externalapi.exception.UsernameAlreadyExistsException;
//import ru.kmetha.externalapi.mapper.UserMapper;
//import ru.kmetha.externalapi.service.UserService;
//import ru.kmetha.gbapimay.security.UserDto;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class JpaUserDetailsService implements UserDetailsService, UserService {
//
//    private final AccountUserDao accountUserDao;
//    private final AccountRoleDao accountRoleDao;
//    private final PasswordEncoder passwordEncoder;
//    private final UserMapper userMapper;
////    private JpaUserDetailsService userService;
////
////    @Autowired
////    public void setUserservice(JpaUserDetailsService jpaUserDetailsService) {
////        this.userService = jpaUserDetailsService;
////    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return accountUserDao.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("Username: " + username + " not found")
//        );
//    }
//
//    @Override
//    public UserDto register(UserDto userDto) {
//        if (accountUserDao.findByUsername(userDto.getUsername()).isPresent()) {
//            throw new UsernameAlreadyExistsException(String.format(
//                    "User with username %s already exists", userDto.getUsername()));
//        }
//        AccountUser accountUser = userMapper.toAccountUser(userDto);
//        AccountRole roleUser = accountRoleDao.findByName("ROLE_USER");
//        accountUser.setRoles(Set.of(roleUser));
//        accountUser.setStatus(AccountStatus.ACTIVE);
//        accountUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        AccountUser registeredAccountUser = accountUserDao.save(accountUser);
//        log.debug("User with username {} was registered successfully", registeredAccountUser.getUsername());
//        return userMapper.toUserDto(registeredAccountUser);
//    }
//
//    @Override
//    @Transactional
//    public UserDto update(UserDto userDto) {
//        AccountUser user = userMapper.toAccountUser(userDto);
//        if (user.getId() != null) {
//            accountUserDao.findById(userDto.getId()).ifPresent(
//                    (p) -> {
//                        user.setVersion(p.getVersion());
//                        user.setStatus(p.getStatus());
//                    }
//            );
//        }
//        return userMapper.toUserDto(accountUserDao.save(user));
//    }
//
//    @Override
//    public AccountUser findByUsername(String username) {
//        return accountUserDao.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("Username: " + username + " not found")
//        );
//    }
//
//    private AccountUser update(AccountUser accountUser) {
//        if (accountUser.getId() != null) {
//            accountUserDao.findById(accountUser.getId()).ifPresent(
//                    (p) -> accountUser.setVersion(p.getVersion())
//            );
//        }
//        return accountUserDao.save(accountUser);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDto findById(Long id) {
//        return userMapper.toUserDto(accountUserDao.findById(id).orElse(null));
//    }
//
//    @Override
//    public List<UserDto> findAll() {
//        return accountUserDao.findAll().stream()
//                .map(userMapper::toUserDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        final AccountUser accountUser = accountUserDao.findById(id).orElseThrow(
//                () -> new UsernameNotFoundException(
//                        String.format("User with id %s not found", id)
//                )
//        );
//        disable(accountUser);
//        update(accountUser);
//    }
//
//    private void enable(final AccountUser accountUser) {
//        accountUser.setStatus(AccountStatus.ACTIVE);
//        accountUser.setAccountNonLocked(true);
//        accountUser.setAccountNonExpired(true);
//        accountUser.setEnabled(true);
//        accountUser.setCredentialsNonExpired(true);
//    }
//
//    private void disable(final AccountUser accountUser) {
//        accountUser.setStatus(AccountStatus.DELETED);
//        accountUser.setAccountNonLocked(false);
//        accountUser.setAccountNonExpired(false);
//        accountUser.setEnabled(false);
//        accountUser.setCredentialsNonExpired(false);
//    }
//}
