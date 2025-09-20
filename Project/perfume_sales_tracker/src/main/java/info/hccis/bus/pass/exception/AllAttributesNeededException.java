package info.hccis.bus.pass.exception;

/**
 * Used if all attributes not provided or issues with some attributes.  
 * @author bjm
 * @since 6-Nov-2020
 */
public class AllAttributesNeededException extends Exception{
    public AllAttributesNeededException(String message){
        super(message);
    }
}
