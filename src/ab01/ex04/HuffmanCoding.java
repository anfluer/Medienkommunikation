package ab01.ex04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class HuffmanCoding {

	public HuffmanCoding() {
		super();
	}

	@SuppressWarnings("resource")
	public Vector<HuffmanNode> readFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		String spliter = ";";
		Vector<HuffmanNode> nodeList = new Vector<HuffmanNode>();
		HuffmanNode node = new HuffmanNode();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				String[] aTmp = line.split(spliter);
				
				node.setAlphabetChar(aTmp[0]);
				node.setFrequency(Double.parseDouble(aTmp[1]));
				
				nodeList.add(node);
				node = new HuffmanNode();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodeList;
	}
}
