package businessLogic.javaClass;

public class User1 {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String firstname;
	private String lastname;
	private String address;
	private boolean emailVerification;
	private String creditCardType;
	private String creditCardNum;
	private int creditCardExpMonth;
	private int creditCardExpYear;
	private int creditCardCvv;
	
	public User1() {
		
	}
	
	
	public User1(int UserId) {
		this.userId = UserId;
		this.username = "";
		this.password = "";
		this.email = "";
		this.nickname = "";
		this.firstname = "";
		this.lastname = "";
		this.address = "";
		this.emailVerification = false;
		this.creditCardType = "";
		this.creditCardNum = "";
		this.creditCardExpMonth = -1;
		this.creditCardExpYear = -1;
		this.creditCardCvv = -123;
		
		
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int UserID) {
		this.userId = UserID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String Username) {
		this.username = Username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String Password) {
		this.password = Password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String Email) {
		this.email = Email;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String Nickname) {
		this.nickname = Nickname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String Firstname) {
		this.firstname = Firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String Lastname) {
		this.lastname = Lastname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String Address) {
		this.address = Address;
	}
	
	public Boolean getEmailVerification() {
		return emailVerification;
	}
	public void setEmailVerification(int EmailVerification) {
		if (EmailVerification == 0) this.emailVerification = false;
		else this.emailVerification = true;
	}
	
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String CreditCardType) {
		this.creditCardType = CreditCardType;
	}
	
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String CreditCardNum) {
		this.creditCardNum = CreditCardNum;
	}
	
	public int getCreditCardExpMonth() {
		return creditCardExpMonth;
	}
	public void setCreditCardExpMonth(int CreditCardExpMonth) {
		this.creditCardExpMonth = CreditCardExpMonth;
	}
	
	public int getCreditCardExpYear() {
		return creditCardExpYear;
	}
	public void setCreditCardExpYear(int CreditCardExpYear) {
		this.creditCardExpYear = CreditCardExpYear;
	}
	
	public int getCreditCardCvv() {
		return creditCardCvv;
	}
	public void setCreditCardCvv(int CreditCardCvv) {
		this.creditCardCvv = CreditCardCvv;
	}
}
