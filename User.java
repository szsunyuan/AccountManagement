/**
 *
 * @author yuansun
 */
public class User extends Client{
    private int accessLvl;
    //user is subclass of client with access value of 0
    public User(String name, String pw, String email) {
        super(name,pw,email);
        accessLvl = 0;
    }
}