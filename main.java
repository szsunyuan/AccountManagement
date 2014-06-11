/*
 * This main class is solely for testing purposes. JUnit test still need to be implemented to replace this one.
 * As of now, all the methods implemented are tested(except for the saveClient in config class).
 */

/**
 *
 * @author yuansun
 */
public class main {
    static Configuration config = new Configuration();
    static Client cl,cl1;
    
    public static void main(String[] args) {
        config.loadClient(0);
        cl = config.getClient();
        cl.setEmail("szsunyuan@gmail.com");
        config.setClient(cl);
        cl1 = config.getClient();
        System.out.println(cl1.getEmail());
    }
    
}
