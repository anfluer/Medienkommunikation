package ab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class AB01_Main {
	public static float entropy = 0f;
	public static float avgLength = 0f;
	public static int index = 1;

	public static void main(String[] args) {
//		ArrayList<HuffmanTree> leafs = initLeafsFromFile("src/ab01/alphabetEX03.csv");
		ArrayList<HuffmanTree> leafs = initLeafsFromFile("src/ab01/alphabet.csv");
		HuffmanTree tree = generateTree(leafs);

		printCodeBook(tree, new StringBuffer()); //Ex04
		printMetaData(); //Ex05
	}
	
	@SuppressWarnings("resource")
	private static ArrayList<HuffmanTree> initLeafsFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		String spliter = ";";
		ArrayList<HuffmanTree> leafs = new ArrayList<HuffmanTree>();
		float freq = 0f;

		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] aTmp = line.split(spliter);
				freq = Float.parseFloat(aTmp[1]);
				
				entropy = entropy + (float) (-(freq * (Math.log(freq) / Math.log(2))));
				leafs.add(new HuffmanTree(aTmp[0], freq, null, null, true, (float) (-(freq * (Math.log(freq) / Math.log(2))))));
			}
			Collections.sort(leafs);
			return leafs;
		} catch (Exception e) {
			System.err.println("NÖ! So nicht! Probiers nochmal!");
			return null;
		}
	}
	
	private static HuffmanTree generateTree(ArrayList<HuffmanTree> trees) {
		while (trees.size() > 2) {
			HuffmanTree t1 = trees.remove(0);
			HuffmanTree t2 = trees.remove(1);

			HuffmanTree nht = new HuffmanTree(
					(t1.alphabetChar + t2.alphabetChar),
					(t1.frequency + t2.frequency),
					t1,
					t2,
					false,
					0f
			);
			
			trees.add(nht);
			Collections.sort(trees);
		}

		HuffmanTree tree = new HuffmanTree(
				trees.get(0).alphabetChar + trees.get(1).alphabetChar,
				trees.get(0).frequency + trees.get(1).frequency,
				trees.get(0),
				trees.get(1),
				false,
				0f
		);
		return tree;
	}
	
	private static void printCodeBook(HuffmanTree tree, StringBuffer code) {
		if (tree.isLeaf) {
			System.out.format("CodebookEntry %2d: ", index++);
//			System.out.println("[" + tree.alphabetChar + "][" + code + "] h:" + tree.entropy);
			System.out.println("[" + tree.alphabetChar + "][" + code + "]");
			
			tree.code = code.toString();
			tree.codeLength = tree.frequency * code.length();
			avgLength = avgLength + tree.codeLength;
		} else {
			code.append('0');
			printCodeBook(tree.zero, code);
			code.deleteCharAt(code.length() - 1);

			code.append('1');
			printCodeBook(tree.one, code);
			code.deleteCharAt(code.length() - 1);
		}

	}

	@SuppressWarnings("unused")
	private static void printMetaData() {
		System.out.println("\n \t\t Wert \t\t\t Formel");
		System.out.println("Entropy: \t " + entropy + " Bits \t -sum(pi * log2 pi)");
		System.out.println("AVG Länge: \t " + avgLength + " Bits \t\t sum(Länge des Codewortes * Frequenz)");
		System.out.println("Redundanz: \t " + (avgLength - entropy) + " Bits \t AVG Länge - Entropy");
	}
}