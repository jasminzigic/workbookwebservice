package com.jobs.workbook.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jobs.workbook.entites.user.User;
import com.jobs.workbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findOneByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));
    }

    return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), "Admin");
  }

}


