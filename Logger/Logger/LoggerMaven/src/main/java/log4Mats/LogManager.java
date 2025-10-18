package log4Mats;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LogManager {

    private static Document createDocument(File xml) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xml);
    }

    /**
     *  Returns a Logger through Logger.getInstance() following Singleton design
     * @param xml to be DOM Parsed for configuration
     * @return
     */
    public static Logger getLogger(File xml){
        Logger logger = null;
        try{
            logger = Logger.getInstance(createDocument(xml));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println("Problem retrieving a Logger instance with XML");
        }
        return logger;
    }

    public static Logger getLoggerFromJson(File json){
        Logger logger = null;
        try{
            LogConfig config = loadFromJson(json);
            logger = Logger.getInstance(config);
        } catch (Exception e) {
            System.err.println("Problem retrieving a Logger instance with JSON");
        }
        return logger;
    }

    public static LogConfig loadFromJson(File json){

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, LogConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // or throw a RuntimeException
        }    }

}
