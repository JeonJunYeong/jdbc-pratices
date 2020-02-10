package hr;

public class EmployeeVO {

	private Long no;
	private String birthDate;
	private String firstName;
	private String LastName;
	private String gender;
	private String hireDate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
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
	
	@Override
	public String toString() {
		return "EmployeeVO [no=" + no + ", birthDate=" + birthDate + ", firstName=" + firstName + ", LastName="
				+ LastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}
	
}
