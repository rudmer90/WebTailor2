package webtailor2.setup.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Encapsulates an ODP topic, with fields for catid, Title, and descriptions.  Also keeps a list of the titles of children topics.
 * @author johande
 *
 */
public class TopicModel {
	private int categoryID;
	private String title;
	private LinkedList<String> childrenTitles;
	private String description;
	
	private static final String EMPTY = "EMPTY";
	
	public TopicModel(){
		categoryID = -1;
		title = EMPTY;
		childrenTitles = new LinkedList<String>();
		description = EMPTY;
	}
	
	public TopicModel(int categoryID, String title, Collection<String> childrenTitles, String description){
		this.categoryID = categoryID;
		this.title = title;
		this.description = description;
		
		if(childrenTitles != null){
			this.childrenTitles = new LinkedList<String>(childrenTitles);
		}
		else{
			this.childrenTitles = null;
		}
	}
	
	//ACCESSORS
	public int getCategoryID(){
		return categoryID;
	}
	
	public String getTitle(){
		return title;
	}
	
	public LinkedList<String> getChildrenTitles(){
		return childrenTitles;
	}
	
	public boolean containsChild(String childTitle){
		return childrenTitles.contains(childTitle);
	}
	
	public String getDescription(){
		return description;
	}
	
	//MUTATORS
	public void setCategoryID(int categoryID){
		this.categoryID = categoryID;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * Adds a collection of Strings to the list of children topics' titles.
	 * 
	 * @param titles
	 * @return True if all Strings were added, false if at least one was already in the list.
	 */
	public boolean addChildrenTitles(Collection<String> titles){
		boolean allAdded = true;
		
		for(String title : titles){
			if(!addChildTitle(title)){
				allAdded = false;
			}
		}
		
		return allAdded;
	}
	
	/**
	 * Adds a String to the list of children topics' titles, if that String is not already in the list.
	 * 
	 * @param childTitle
	 * @return	True if the child's title String was added to the list, false if it was already in the list.
	 */
	public boolean addChildTitle(String childTitle){
		if(!childrenTitles.contains(childTitle)){
			childrenTitles.add(childTitle);
			return true;
		}
		return false;
	}
	
	public boolean removeChild(String childTitle){
		return childrenTitles.remove(childTitle);
	}
}
