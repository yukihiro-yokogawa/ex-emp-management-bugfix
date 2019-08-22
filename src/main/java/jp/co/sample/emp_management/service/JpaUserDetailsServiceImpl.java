package jp.co.sample.emp_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

@Component
public class JpaUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Override
	public UserDetails loadUserByUsername(String mailAddress)
		throws UsernameNotFoundException {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		if(administrator == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		return administrator;
	}
}
