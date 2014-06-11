/**
 *
 * @author yuansun
 */
public class AccManagement {
    Configuration config = new Configuration();
    Client client;
    
    public boolean login (String email, String pw) {
        if(config.verifyPw(email,pw)){
                client = config.getClient();
                client.changeLoginStatus();
                return true;
        }
        return false;
    }
        
    public void logout () {
        client.changeLoginStatus();
    }
    
    public void register (String name, String pw, String email) {
        Client client = new Client(name, pw, email);
        config.saveClient();
    }
}