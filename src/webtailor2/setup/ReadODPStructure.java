package webtailor2.setup;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import webtailor2.setup.xml.StructXMLHandler;

public class ReadODPStructure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String odpStructurePath = null;
		
		//read args
		for(int i = 0; i < args.length; i++){
			System.out.println(args[i]);
			if(args[i].equals("-f")){
				i++;
				if(i < args.length){
					odpStructurePath = args[i];
				}
			}
		}		
		
		if(odpStructurePath != null){
			System.out.println("PARSING...");
			int numTopics = parseStructureXML(odpStructurePath);
			System.out.println("DONE. NUM_TOPICS = " + numTopics);
		}
		else{
			System.out.println("NO FILE PATH.");
		}
	}
	
	public static int parseStructureXML(String path){
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser structParser;
		StructXMLHandler structHandler = new StructXMLHandler();
		
		try{
			structParser = spf.newSAXParser();
			structParser.parse(path, structHandler);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
		if(structHandler != null)
			return structHandler.numTopics;
		
		return -1;
	}
}
