
import java.util.*;

/**
 * 
 */
public interface GodCard {


    /**
     * @param BuilderModel 
     * @param CellModel 
     * @return
     */
    public boolean move(void BuilderModel, void CellModel);

    /**
     * @param BuilderModel 
     * @param CellModel 
     * @return
     */
    public boolean build(void BuilderModel, void CellModel);

    /**
     * @return
     */
    public boolean haswon();

    /**
     * @return
     */
    public boolean hasLost();

}