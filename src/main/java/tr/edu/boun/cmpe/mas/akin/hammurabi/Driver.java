package tr.edu.boun.cmpe.mas.akin.hammurabi;

import java.io.FileNotFoundException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.SocialProtocolMonitor;

/**
 *
 * @author Akin Gunay
 */
public class Driver {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Incorrect call.");
        }
        try {
            SocialProtocolMonitor monitor = SocialProtocolMonitor.newSocialProtocolMonitor(args[0], args[1], Long.parseLong(args[2]));
            monitor.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException e) {
            e.printStackTrace();
            return;
        } catch (tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException e) {
            e.printStackTrace();
            return;
        }
    }
    
}
