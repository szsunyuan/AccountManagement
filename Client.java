import java.util.ArrayList;

/**
 *
 * @author yuansun
 */
public class Client {
    private String name,pw,email;
    private int accessLvl,id = 10000;
    private boolean loginStatus;
    private ArrayList<String> categories,locations;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    
    //this constructor is for registering new clients
    public Client(String name, String pw, String email) {
        this.name = name;
        this.pw = pw;
        this.email = email;
        id++;
        accessLvl = 0;
    }
    //this constructor is for recreating the existing client in xml file(database)
    public Client(String name, String pw, String email, int id, int accessLvl, ArrayList<String> categories, ArrayList<String> locations) {
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.id = id;
        this.accessLvl = accessLvl;
        this.categories = categories;
        this.locations = locations;
    }
     
    public void setName(String name) {
        if(checkName(name)) {
            this.name = name;
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setPw(String pw) {
        if(checkPw(pw))
            this.pw = pw;
    }
    
    public String getPw() {
        return pw;
    }
    
    public void setEmail(String email) {
        if(checkEmail(email))
            this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getId() {
        return id;
    }
    
    public void setCategory(ArrayList<String> categories) {
        this.categories = categories;
    }
    
    public ArrayList<String> getCategory() {
        return categories;
    }
    
    public void setLocation(ArrayList<String> locations) {
        this.locations = locations;
    }
    
    public ArrayList<String> getLocation() {
        return locations;
    }
    
    public void setAccessLvl(int accessLvl) {
        this.accessLvl = accessLvl;
    }
    
    public int getAccessLvl() {
        return accessLvl;
    }
    //toggling the login status
    public void changeLoginStatus() {
        if(loginStatus)
            loginStatus = false;
        else
            loginStatus = true;
    }
    //name check
    public boolean checkName(String name) {
        if (name.length() < 3 || name.length() > 20) {
            return false;
        }
        char[] namearray;
        namearray = name.toCharArray();
        for (char n : namearray) {
            if (!(Character.isLetter(n)) || !(Character.isDigit(n))) {
                return false;
            }
        }
        return true;
    }
    //password sanity check
    public boolean checkPw(String pw) {
        if (pw.length() < 6 || pw.length() > 18) {
            return false;
        }
        char[] pwarray;
        pwarray = pw.toCharArray();
        for (char c : pwarray) {
            if (!(Character.isLetter(c)) || !(Character.isDigit(c)) || !((SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0))) {
                return false;
            }
        }
        return true;
    }
    //email sanity check
    public boolean checkEmail(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!(email.matches(EMAIL_REGEX))) {
            return false;
        }
        return true;
    }
}