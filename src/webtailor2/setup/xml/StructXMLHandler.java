package webtailor2.setup.xml;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import webtailor2.setup.TopicsManager;

public class StructXMLHandler extends DefaultHandler {
	private StringBuilder currentValue;
	
	private TopicsManager topicsManager;
	private boolean inTopic;
	
	private int categoryID;
	private String title;
	private String topicIDStr;
	private String description;
	private LinkedList<String> childrenTitles;
	
	public StructXMLHandler(){
		super();
		
		topicsManager = new TopicsManager();
		inTopic = false;		
		initVars();
	}
	
	
	public StructXMLHandler(TopicsManager manager){
		super();
		
		if(manager != null){
			topicsManager = manager;
		}
		else{
			System.err.println("ERROR: StructXMLHandler cannot be created with a null TopicsManager.  Creating a default TopicsManager.");
			topicsManager = new TopicsManager();
		}
		
		inTopic = false;
		initVars();
	}
	
	private void initVars(){
		categoryID = -1;
		title = null;
		topicIDStr = null;
		description = null;
		childrenTitles = null;
		
		currentValue = new StringBuilder();
	}
	
	public TopicsManager getTopicsManager(){
		return topicsManager;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			if(inTopic){
				topicsManager.incrementNumMalformedTopics();
			}
			inTopic = true;
			
			//set up
			initVars();			
			topicsManager.incrementNumTopicStarts();
			
			//TODO: handle attributes 
			topicIDStr = attributes.getValue(XMLConstants.R_ID);
		}
		
		//TODO: handle other tags
		
		currentValue.setLength(0);
	}
	
	/** Called when tag closing ( ex:- <name>AndroidPeople</name> -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//TODO: handle other tags
		if(qName.equalsIgnoreCase(XMLConstants.TITLE)){
			title = currentValue.toString();
		}
		else if(qName.equalsIgnoreCase(XMLConstants.CATEGORY_ID)){
			categoryID = Integer.parseInt(currentValue.toString());
		}		
		else if(qName.equalsIgnoreCase(XMLConstants.TOPIC)){
			topicsManager.incrementNumTopicEnds();
			inTopic = false;
			
			topicsManager.addTopic(topicIDStr, categoryID, title, childrenTitles, description);
		}
		currentValue.setLength(0);
	}
	
	/** Called to get tag characters ( ex:- <name>AndroidPeople</name>
	* -- to get AndroidPeople Character ) */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
			currentValue.append(ch, start, length);
			if(currentValue.length() == 0){
				//System.out.println("CURRENT VALUE = " + currentValue);
			}
	}
}
