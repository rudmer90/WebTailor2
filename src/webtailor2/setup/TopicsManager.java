package webtailor2.setup;

import java.util.Collection;
import java.util.HashMap;

import webtailor2.setup.model.TopicModel;

public class TopicsManager {
	private HashMap<String, TopicModel> topicsMap;	
	private int numIncompleteTopics, numMalformedTopics, numTopicStarts, numTopicEnds, numDuplicateTopics;

	public TopicsManager(){
		topicsMap = new HashMap<String, TopicModel>();
		numIncompleteTopics =
				numMalformedTopics =
				numTopicStarts =
				numTopicEnds = 
				numDuplicateTopics = 0;
	}
	
	public void addTopic(int categoryID, String title, Collection<String> childrenTitles, String description){
		TopicModel topic;
		
		if(categoryID != -1 && title != null){
			topic = new TopicModel(categoryID, title, childrenTitles, description);
			
			if(topicsMap.containsKey(topic.getTitle())){
				incrementNumDuplicateTopics();
			}
			
			topicsMap.put(topic.getTitle(), topic);				
		}
		else{//TEST CODE
			System.out.println("ERROR: incomplete topic: ");
			incrementNumIncompleteTopics();
			
			if(categoryID == -1){
				System.out.println("\tcategoryID = " + categoryID);
			}
			
			if(title == null){
				System.out.println("\t* null title.");
			}				
		}
	}
	
	//ACCOUNTING METHODS
	public void incrementNumMalformedTopics(){
		numMalformedTopics++;
	}
	
	public void incrementNumTopicStarts(){
		numTopicStarts++;
	}
	
	public void incrementNumTopicEnds(){
		numTopicEnds++;
	}
	
	public void incrementNumDuplicateTopics(){
		numDuplicateTopics++;
	}
	
	public void incrementNumIncompleteTopics(){
		numIncompleteTopics++;
	}
	
	//ACCESSORS
	public int getNumTopics(){
		return topicsMap.size();
	}
	
	public HashMap<String, TopicModel> getTopicsMap(){
		return topicsMap;
	}
		
	public int getNumIncompleteTopics(){
		return numIncompleteTopics;
	}
	
	public int getNumMalformedTopics(){
		return numMalformedTopics;
	}
	
	public int getNumTopicStarts(){
		return numTopicStarts;
	}
	
	public int getNumTopicEnds(){
		return numTopicEnds;
	}
	
	public int getNumDuplicateTopics(){
		return numDuplicateTopics;
	}
	
	public String report(){
		String reportStr = "REPORT:\n\tTopics in map: " + topicsMap.size()
				+ "\n\tIncomplete topics: " + numIncompleteTopics 
				+ "\n\tMalformed topics: " + numMalformedTopics
				+ "\n\tDuplicate topics: " + numDuplicateTopics
				+ "\n\tTopic starts: " + numTopicStarts
				+ "\n\tTopic ends: " + numTopicEnds
				+ "\nEND REPORT.";
		
		return reportStr;
	}
	
	
}
