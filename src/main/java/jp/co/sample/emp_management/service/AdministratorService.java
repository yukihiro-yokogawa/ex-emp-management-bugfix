package jp.co.sample.emp_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	AdministratorRepository administratorRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator　管理者情報
	 */
	public void insert(Administrator administrator) {
		String encodePassword = passwordEncoder.encode(administrator.getPassword());
		administrator.setPassword(encodePassword);
		administratorRepository.insert(administrator);
	}
	
	/**
	 * 管理者のメールアドレスを検索します.
	 * 
	 * @param mailAddress
	 * @return administrator 管理者のメールアドレス
	 */
	public Administrator serchByMailAddress(String mailAddress) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		return administrator;
	}
	
	/**
	 * ログインをします.
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 管理者情報　存在しない場合はnullが返ります
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator hashAdministrator = administratorRepository.findByMailAddress(mailAddress);
		if(!(hashAdministrator==null)&&(passwordEncoder.matches(password, hashAdministrator.getPassword()))) {
			return hashAdministrator;
		}
		return null;
	}
}
