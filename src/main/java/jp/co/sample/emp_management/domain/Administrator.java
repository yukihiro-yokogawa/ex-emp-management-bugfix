package jp.co.sample.emp_management.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 管理者情報を表すドメイン.
 * 
 * @author igamasayuki
 * 
 */

public class Administrator implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id(主キー) */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

	/**
	 * 引数無しのコンストラクタ.
	 */
	public Administrator() {
	}

	/**
	 * 初期化用コンストラクタ.
	 * 
	 * @param id
	 *            id(主キー)
	 * @param name
	 *            名前
	 * @param mailAddress
	 *            メールアドレス
	 * @param password
	 *            パスワード
	 */
	public Administrator(Integer id, String name, String mailAddress, String password) {
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.mailAddress;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
