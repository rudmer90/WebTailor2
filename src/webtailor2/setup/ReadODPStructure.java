package webtailor2.setup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import webtailor2.setup.model.TopicModel;
import webtailor2.setup.xml.StructXMLHandler;

public class ReadODPStructure {
	static private StructXMLHandler structHandler;
	
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
			System.out.println("DONE PARSING.");
			
			System.out.println("WRITING TOPICS...");
			HashMap<String, TopicModel> topicsMap = structHandler.getTopicsManager().getTopicsMap();
			LinkedList<TopicModel> topicsList = structHandler.getTopicsManager().getTopicsList();
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/johande/workspace/output/topics.u8"), "UTF-8"));
				
				Collection<TopicModel> topics = topicsMap.values();
				for(TopicModel topic : topics){
					bw.write(topic.getTitle() + "(");
					
					for(Integer categoryID : topic.getCategoryIDs()){
						bw.write(categoryID + " ");
					}
					bw.write(")\n\n");					
				}				
				bw.close();
				
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/johande/workspace/output/topics_sorted.u8"), "UTF-8"));
				for(TopicModel topic : topicsList){
					bw.write(topic.getTitle() + "\n");
				}
				bw.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("DONE WRITING TOPICS.");
		}
		else{
			System.out.println("NO FILE PATH.");
		}
	}
	
	public static void parseStructureXML(String path){
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser structParser;
		structHandler = new StructXMLHandler();
		
		try{
			InputSource inputSource = new InputSource(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
			inputSource.setEncoding("UTF-8");
			
			structParser = spf.newSAXParser();
			structParser.parse(inputSource, structHandler);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
		System.out.println(structHandler.getTopicsManager().report());
	}
}
