package webtailor2.setup;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

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
			parseStructureXML(odpStructurePath);
			System.out.println("DONE.");
		}
		else{
			System.out.println("NO FILE PATH.");
		}
	}
	
	public static void parseStructureXML(String path){
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser structParser;
		StructXMLHandler structHandler;
		
		try{
			structParser = spf.newSAXParser();
			structParser.parse(path, new StructXMLHandler());
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}
