
import java.util.*;

/**
 * 
 */
public class GodFactory {

    /**
     * Default constructor
     */
    public GodFactory() {
    }


    /**
     * @param String 
     * @return
     */
    public static GodCard getGod(String cardType) {
   	if(cardType== null) return null;
	if(cardType.equalsIgnoreCase("Apollo")) return new Apollo();
	if(cardType.equalsIgnoreCase("Artemis")) return new Arthemis();
	if(cardType.equalsIgnoreCase("Athena")) return new Athena();
	if(cardType.equalsIgnoreCase("Atlas")) return new Atlas();
	if(cardType.equalsIgnoreCase("Demeter")) return new Demeter();
	if(cardType.equalsIgnoreCase("Hephaestus")) return new Hephaestus();
	if(cardType.equalsIgnoreCase("Minotaur")) return new Minotaur();
	if(cardType.equalsIgnoreCase("Pan")) return new Pan();
	if(cardType.equalsIgnoreCase("Prometheus")) return new Prometheus();
        return null;
    }

}