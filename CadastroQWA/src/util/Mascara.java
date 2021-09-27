 package util;

import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Mascara {
    
    public static DefaultFormatterFactory getDataMask(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
        } catch ( ParseException ex){
            return null;
        }
        return (new DefaultFormatterFactory(mask,mask));
    }
    
    
     public static DefaultFormatterFactory getCPFMask(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
        } catch ( ParseException ex){
            return null;
        }
        return (new DefaultFormatterFactory(mask,mask));
    }
    
}
