package webtailor2.setup;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class ReadODPStructure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String odpStructurePath = null;
		Model model = null;
		
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
			model = readODPStructureIntoModel(odpStructurePath);
			System.out.println("DONE.");
			System.out.print("MODEL = " + model.toString());
		}
		else{
			System.out.println("NO FILE PATH.");
		}
	}
	
	public static Model readODPStructureIntoModel(String path){
		Model model = ModelFactory.createDefaultModel();
		
		InputStream in = FileManager.get().open(path);
		if (in == null) {
		    throw new IllegalArgumentException("File: " + path + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);
		
		return model;
	}

}
