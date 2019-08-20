package jp.co.sample.emp_management.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前は必須です")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が不正です")
	private String mailAddress;
	/** パスワード */
	@Size(min = 1, max = 16, message = "パスワードは1文字以上16文字以内で記載してください")
	private String password;
	/** 確認用パスワード */
	private String passwordConfirm;
	
	/**
	 * 常にtrueでなければならないバリデーション
	 * 確認用パスワードがパスワード
	 * 
	 * @return true
	 */
	@AssertTrue(message = "パスワードが一致していません")
	public boolean isPasswordConfirmed() {
		if ((password == null || password.isEmpty())&&(passwordConfirm == null || passwordConfirm.isEmpty())) {
			return true;
		}
		return password.equals(passwordConfirm);
	}

	public String getName() {
		return name;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", passwordConfirm=" + passwordConfirm + "]";
	}

}
