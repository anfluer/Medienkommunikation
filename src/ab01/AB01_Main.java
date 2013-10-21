package ab01;

import java.util.Vector;
import ab0104.HuffmanCoding;
import ab0104.HuffmanNode;

public class AB01_Main {

	//http://stackoverflow.com/questions/16658419/huffman-tree-in-java
	public static void main(String[] args) {
		HuffmanCoding hfc = new HuffmanCoding();
		Vector<HuffmanNode> nodes = hfc.readFile("src/alphabet.csv");
		
		for(HuffmanNode node: nodes){
			System.out.println(node.getAlphabetChar() + ";" + node.getFrequency());
		}
	}

}
