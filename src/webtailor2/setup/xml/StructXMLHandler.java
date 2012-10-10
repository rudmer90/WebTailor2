package webtailor2.setup.xml;

import java.util.HashMap;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import webtailor2.setup.model.TopicModel;


public class StructXMLHandler extends DefaultHandler {
	private String currentValue;
	
	private HashMap<String, TopicModel> topicsMap;	
	private int numIncompleteTopics;
	
	private TopicModel topic;
	private int categoryID;
	private String title;
	private String description;
	private LinkedList<String> childrenTitles;
	
	public StructXMLHandler(){
		super();
		topicsMap = new HashMap<String, TopicModel>();
		numIncompleteTopics = 0;
		
		topic = null;
		categoryID = -1;
		title = null;
		description = null;
		childrenTitles = null;
	}
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			//set up
			topic = null;
			categoryID = -1;
			title = null;
			description = null;
			childrenTitles = null;
			
			//TODO: handle attributes 
		}
		
		//TODO: handle other tags
		
		
	}
	
	/** Called when tag closing ( ex:- <name>AndroidPeople</name>
	* -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//TODO: handle other tags
		if(qName.equalsIgnoreCase(XMLConstants.TITLE)){
			title = currentValue;
		}
		else if(qName.equalsIgnoreCase(XMLConstants.CATEGORY_ID)){
			categoryID = Integer.parseInt(currentValue);
		}		
		else if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			if(categoryID != -1 && title != null){
				topic = new TopicModel(categoryID, title, childrenTitles, description);
				topicsMap.put(topic.getTitle(), topic);
			}
			else{//TEST CODE
				System.out.println("ERROR: incomplete topic: ");
				
				if(categoryID == -1){
					System.out.println("\tcategoryID = " + categoryID);
				}
				
				if(title == null){
					System.out.println("\t* null title.");
				}				
			}
		}		
	}
	
	/** Called to get tag characters ( ex:- <name>AndroidPeople</name>
	* -- to get AndroidPeople Character ) */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
			currentValue = new String(ch, start, length);
	}
	
	public int getNumTopics(){
		return topicsMap.size();
	}
	
	public int getIncompleteTopics(){
		return numIncompleteTopics;
	}
	
	public HashMap<String, TopicModel> getTopicsMap(){
		return topicsMap;
	}
}
