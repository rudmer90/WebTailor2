package webtailor2.setup;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StructXMLHandler extends DefaultHandler {
	boolean currentElement = false;
	String currentValue;
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//NOTE: Examine the attributes, they could be what you need
		currentElement = true;
		if(!localName.isEmpty()){
			System.out.println("STARTING " + localName.toString());//TEST CODE
		}
		
		
	}
	
	/** Called when tag closing ( ex:- <name>AndroidPeople</name>
	* -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		currentElement = false;
		if(localName.equalsIgnoreCase(XMLConstants.TOPIC)){
			System.out.println("ENDING " + localName.toString());//TEST CODE
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
