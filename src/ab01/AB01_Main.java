package ab01;

import java.util.Vector;
import ab01.ex04.*;

public class AB01_Main {
    //TODO Learning Git!!
	//http://stackoverflow.com/questions/16658419/huffman-tree-in-java
	public static void main(String[] args) {
		runEx04();
//		runEx05();
		
	}

//	private static void runEx05() {
//	}

	private static void runEx04() {
		HuffmanCoding hfc = new HuffmanCoding();
		Vector<HuffmanNode> nodes = hfc.readFile("src/ab01/alphabet.csv");
		
		for(HuffmanNode node: nodes){
			System.out.println(node.getAlphabetChar() + ";" + node.getFrequency());
		}
	}
}
