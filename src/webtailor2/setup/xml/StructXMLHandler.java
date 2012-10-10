package webtailor2.setup.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class StructXMLHandler extends DefaultHandler {
	private boolean currentElement = false;
	private String currentValue;
	private BufferedWriter bw;
	
	public int numTopics = 0;
	
	public StructXMLHandler(){
		super();
		
		try {
			bw = new BufferedWriter(new FileWriter("/Volumes/StorageHD/workspace/output/topics.txt"));
		} catch (IOException e) {
			System.out.println("ERROR: could not create output file.");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//NOTE: Examine the attributes, they could be what you need
		currentElement = true;
		if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			//System.out.println("STARTING TOPIC ");//TEST CODE
			numTopics++;
		}
		
		for(int i = 0; i < attributes.getLength(); i++){
			try {
				bw.write("\t" + i +") " + attributes.getQName(i) + " = " + attributes.getValue(i));
				bw.newLine();
			} catch (IOException e) {
				System.out.println("ERROR: could not write to file.");
				e.printStackTrace();
			}
		}
		
		try {
			bw.flush();
		} catch (IOException e) {
			System.out.println("ERROR: could not flush buffer.");
			e.printStackTrace();
		}
		
		
	}
	
	/** Called when tag closing ( ex:- <name>AndroidPeople</name>
	* -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		currentElement = false;
		if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			//System.out.println("ENDING TOPIC " + currentValue);//TEST CODE
		}
	}
	
	/** Called to get tag characters ( ex:- <name>AndroidPeople</name>
	* -- to get AndroidPeople Character ) */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		//if (currentElement) {
			currentValue = new String(ch, start, length);
			//System.out.println("VALUE = " + currentValue);//TEST CODE
			//currentElement = false;
		//}

	}
}
