package log4Mats;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class LogManager {

    private static Document createDocument(File xml) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xml);
    }

    public static Logger getLogger(File xml){
        Logger resultado= null;
        try{
            resultado = Logger.getInstance(createDocument(xml));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println("Problem retrieving a Logger instance");
        }
        return resultado;
    }

}
