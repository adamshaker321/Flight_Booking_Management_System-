package flight_booking_management;

abstract public class User {

protected int userId;
protected String userName;
protected String name;
protected String email;
protected String password;
protected String contactInfo;

    public User() {
    }


    public User(int userId, String userName, String name, String email, String password, String contactInfo) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String isContactInfo() {
        return contactInfo;
    }



    public String toFileString(){
        return userId +","+userName +","+name+","+email+","+password+","+contactInfo+",";
    }




    public abstract void login(String email, String password);

    public abstract void logout(String email, String password);
}
