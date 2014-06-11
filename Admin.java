/**
 *
 * @author yuansun
 */
public class Admin extends Client{
    private int accessLvl;
    //admin is subclass of client with access of value 1
    public Admin(String name, String pw, String email) {
        super(name,pw,email);
        accessLvl = 1;
    }
    //more features are to be implemented, like announcements etc.
}