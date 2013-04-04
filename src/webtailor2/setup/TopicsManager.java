package webtailor2.setup;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import webtailor2.setup.model.TopicModel;

public class TopicsManager {
	private HashMap<String, TopicModel> topicsMap;
	private LinkedList<TopicModel> topicsList;
	private int numIncompleteTopics, numMalformedTopics, numTopicStarts, numTopicEnds, numDuplicateTopics, numDuplicateTopicsWithDifferingCategoryIDs;

	public TopicsManager(){
		topicsMap = new HashMap<String, TopicModel>();
		topicsList = new LinkedList<TopicModel>();
		numIncompleteTopics =
				numMalformedTopics =
				numTopicStarts =
				numTopicEnds = 
				numDuplicateTopics = 
				numDuplicateTopicsWithDifferingCategoryIDs = 0;
	}
	
	public void addTopic(String topicIDStr, int categoryID, String title, Collection<String> childrenTitles, String description){
		TopicModel topic;
		
		if(topicIDStr != null && !topicIDStr.isEmpty() && categoryID != -1 && title != null  && !title.isEmpty()){
			if(topicsMap.containsKey(topicIDStr)){
				incrementNumDuplicateTopics();
				topic = topicsMap.get(topicIDStr);
				
				if(topic.containsCategoryID(categoryID)){
					incrementNumDuplicateTopicsWithDifferingCategoryIDs();
				}
				else{
					topic.addCategoryID(categoryID);
				}
			}
			else{
				topic = new TopicModel(topicIDStr, categoryID, title, childrenTitles, description);
				topicsMap.put(topic.getTopicIDStr(), topic);	
			}
			
			topicsList.add(topic);
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
	
	public void incrementNumDuplicateTopicsWithDifferingCategoryIDs(){
		numDuplicateTopicsWithDifferingCategoryIDs++;
	}
	
	//ACCESSORS
	public int getNumTopics(){
		return topicsMap.size();
	}
	
	public HashMap<String, TopicModel> getTopicsMap(){
		return topicsMap;
	}
	
	public LinkedList<TopicModel> getTopicsList(){
		return topicsList;
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
	
	public int getNumDuplicateTopicsWithDifferingCategoryIDs(){
		return numDuplicateTopicsWithDifferingCategoryIDs;
	}
	
	public String report(){
		String reportStr = "REPORT:\n\tTopics in map: " + topicsMap.size()
				+ "\n\tIncomplete topics: " + numIncompleteTopics 
				+ "\n\tMalformed topics: " + numMalformedTopics
				+ "\n\tDuplicate topics: " + numDuplicateTopics
				+ "\n\tDuplicate topics with differing category IDs: " + numDuplicateTopicsWithDifferingCategoryIDs
				+ "\n\tTopic starts: " + numTopicStarts
				+ "\n\tTopic ends: " + numTopicEnds
				+ "\nEND REPORT.";
		
		return reportStr;
	}
	
	
}
