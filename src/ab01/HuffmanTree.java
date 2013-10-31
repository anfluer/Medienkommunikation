package ab01;

import java.util.Comparator;

public class HuffmanTree implements Comparator<HuffmanTree>, Comparable<HuffmanTree> {
	public String alphabetChar;
	public float frequency;
	public HuffmanTree zero;
	public HuffmanTree one;
	
	
	public String code;
	public float codeLength;
	public float entropy;
	public boolean isLeaf;
		
	public HuffmanTree(String alphabetChar, float frequency, HuffmanTree zero, HuffmanTree one, boolean isLeaf, float entropy) {
		this.alphabetChar = alphabetChar;
		this.frequency = frequency;
		this.zero = zero;
		this.one = one;
		this.isLeaf = isLeaf;
		this.entropy = entropy;
	}
	
	@Override
	public int compareTo(HuffmanTree o) {
		if (this.frequency == o.frequency) return 0;
		if (this.frequency < o.frequency) return -1;
		else return 1;
	}

	@Override
	public boolean equals(Object obj) {
		HuffmanTree o = (HuffmanTree) obj;
		if (this.frequency == o.frequency) return true;
		else return false;
	}

	@Override
	public int compare(HuffmanTree e1, HuffmanTree e2) {
		if (e1.frequency == e2.frequency) return 0;
		if (e1.frequency < e2.frequency) return -1;
		else return 1;
	}
}