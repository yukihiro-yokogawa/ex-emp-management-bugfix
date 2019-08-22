package jp.co.sample.emp_management.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class InsertEmployeeForm {
	/** id */
	private String id;
	/** 従業員名 */
	@NotBlank(message = "従業員名は必須です")
	private String name;
	/** 性別 */
	@NotBlank(message = "性別は必須です")
	private String gender;
	/** 入社日 */
	@Pattern(message="入力形式が不正です",regexp="[1-2][0-9]{3}-[0-1][0-9]-[0-3][1-9]")
	private String hireDate;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が不正です")
	private String mailAddress;
	/** 郵便番号 */
	@Pattern(message="入力形式が不正です 入力例:111-1111",regexp="[0-9] {3}-[0-9] {3}")
	@NotBlank(message = "郵便番号は必須です")
	private String zipCode;
	/** 住所 */
	@NotBlank(message = "住所は必須です")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号は必須です")
	private String telephone;
	/** 給料 */
	@NotBlank(message = "給料は必須です")
	private String salary;
	/** 特性 */
	@NotBlank(message = "特性は必須です")
	private String characteristics;
	/** 扶養人数 */
	@NotBlank(message = "扶養人数は必須です")
	private String dependentsCount;

	@Override
	public String toString() {
		return "InsertEmployee [id=" + id + ", name=" + name + ", gender=" + gender + ", hireDate=" + hireDate
				+ ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
				+ telephone + ", salary=" + salary + ", characteristics=" + characteristics + ", dependentsCount="
				+ dependentsCount + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
