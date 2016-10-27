package process;

import java.io.File;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		File folder = new File("src/profiler");
		File[] xmlFiles = folder.listFiles();
		for (File xml : xmlFiles){
			if(xml.isFile()){
				XMLParser p = new XMLParser();
				p.parse(xml);
				//reduce recursive path
				//calculate minCPD
				//calculate height
				//calculate induced cost
				//store result


			}
		}
	}
}
