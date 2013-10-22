package ab01;

import java.util.Comparator;

public class HuffmanTree implements Comparator<HuffmanTree>, Comparable<HuffmanTree>{
	public String alphabetChar;
	public float frequency;
	public HuffmanTree one;
	public HuffmanTree zero;

	public HuffmanTree(String alphabetChar, float frequency, HuffmanTree one,
			HuffmanTree zero) {
		this.alphabetChar = alphabetChar;
		this.frequency = frequency;
		this.one = one;
		this.zero = zero;
	}

	public boolean isLeaf() {
		if (one == null && zero == null)
			return true;
		else
			return false;
	}

	@Override
	public int compare(HuffmanTree e1, HuffmanTree e2) {
		if (e1.frequency == e2.frequency) return 0;
		if (e1.frequency < e2.frequency) return -1;
		else return 1;
	}

	@Override
	public boolean equals(Object obj) {
		HuffmanTree o = (HuffmanTree) obj;
		if (this.frequency == o.frequency ) return true;
		else return false;
	}

	@Override
	public int compareTo(HuffmanTree o) {
		if (this.frequency == o.frequency) return 0;
		if (this.frequency < o.frequency) return -1;
		else return 1;
	}
	
}
