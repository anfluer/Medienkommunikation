package ab01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AB01_Main {

	// http://stackoverflow.com/questions/16658419/huffman-tree-in-java
	public static void main(String[] args) {
		runEx04();
	}

	private static void runEx04() {
		ArrayList<HuffmanTree> trees = initTreesFromFile("src/ab01/alphabet.csv");

		while (trees.size() > 2) {
			HuffmanTree t1 = trees.remove(0);
			HuffmanTree t2 = trees.remove(1);

			HuffmanTree nht = new HuffmanTree(
					t1.alphabetChar + t2.alphabetChar, t1.frequency
							+ t2.frequency, t1, t2);
			trees.add(nht);
			Collections.sort(trees);

		}
		;
		HuffmanTree tree = new HuffmanTree(trees.get(0).alphabetChar
				+ trees.get(1).alphabetChar, trees.get(0).frequency
				+ trees.get(1).frequency, trees.get(0), trees.get(1));

		printCodeBook(tree, new StringBuffer());
	}

	private static void printCodeBook(HuffmanTree tree, StringBuffer prefix) {
		if (tree.isLeaf()) {
			System.out.println("CodebookEntry: [" + tree.alphabetChar + "]["
					+ prefix + "]");
		} else {
			prefix.append('0');
			printCodeBook(tree.zero, prefix);
			prefix.deleteCharAt(prefix.length() -1);
			
            prefix.append('1');
            printCodeBook(tree.one, prefix);
            prefix.deleteCharAt(prefix.length()-1);
		}

	}

	@SuppressWarnings("resource")
	private static ArrayList<HuffmanTree> initTreesFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		String spliter = ";";
		ArrayList<HuffmanTree> trees = new ArrayList<HuffmanTree>();

		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] aTmp = line.split(spliter);
				trees.add(new HuffmanTree(aTmp[0], Float.parseFloat(aTmp[1]),
						null, null));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(trees);
		return trees;
	}
}