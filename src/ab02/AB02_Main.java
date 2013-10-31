package ab02;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;

public class AB02_Main {

	public static void main(String[] args) {
		ex01();
		ex02("src/ab02/lena_512x512.png", 2);
		ex02("src/ab02/lena_512x512.png", 4);
		ex02("src/ab02/lena_512x512.png", 8);
//		ex02("src/ab02/lena_512x512.png", 16);
//		ex02("src/ab02/lena_512x512.png", 32);
//		ex02("src/ab02/lena_512x512.png", 64);
//		ex02("src/ab02/lena_512x512.png", 128);
//		ex02("src/ab02/lena_512x512.png", 256);
//		ex02("src/ab02/lena_512x512.png", 512);
	}

	private static void ex02(String filename, int blocksize) {
		BufferedImage img = null;
		BufferedImage newImg = null;
		int pixel = 0;
		int blocksizeForName = blocksize;
		try {
			img = ImageIO.read(new File(filename));
			newImg = img;
			
			for (int y = 0; y < img.getHeight() - blocksize; y += blocksize) {
				for (int x = 0; x < img.getWidth() - blocksize; x += blocksize) {
					pixel = img.getRGB(x, y);
					newImg = calculateBlock(blocksize, newImg, pixel, y, x);
				}
			}

//			// Not working properly!!! But no time left anymore :(
//			blocksize = img.getHeight() % blocksize;
//
//			// Calc last column
//			for (int y = 1; y < img.getHeight() - blocksize; y += blocksize) {
//				pixel = img.getRGB(img.getWidth() - blocksize, y);
//				newImg = calculateBlock(blocksize, newImg, pixel, y, img.getWidth() - blocksize);
//			}
//
//			// calc last line
//			for (int x = 1; x < img.getWidth() - blocksize; x += blocksize) {
//				pixel = img.getRGB(x, img.getHeight() - blocksize);
//				newImg = calculateBlock(blocksize, newImg, pixel, img.getHeight() - blocksize, x);
//			}

			ImageIO.write(newImg, "png", new File("src/ab02/lena_512x512_" + blocksizeForName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage calculateBlock(int blocksize, BufferedImage newImg, int pixel, int y, int x) {
		for (int yBlock = 0; yBlock < blocksize; yBlock++) {
			for (int xBlock = 0; xBlock < blocksize; xBlock++) {
				newImg.setRGB(x + xBlock, y + yBlock, pixel);
			}
		}
		return newImg;
	}

	private static void ex01() {
		int[] seq = initSeqFromFile("src/ab02/sequence.csv");
		writeSequenceToFile("src/ab02/mySequence.csv", seq);
		printReconstructedValuesFromFile("src/ab02/mySequence.csv");
	}

	@SuppressWarnings("unused")
	private static void writeSequenceToFile(String string, int[] seq) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(string));
			int[] e = new int[seq.length];
			int[] eTilde = new int[seq.length];
			int[] fCeil = new int[seq.length];
			int[] fTilde = new int[seq.length];

			fTilde[0] = seq[0];
			fTilde[1] = seq[1];
			e[0] = e[1] = 0;
			eTilde[0] = eTilde[1] = 0;
			fCeil[0] = fCeil[1] = 0;
			String toFile = "";// "f~n-2, f~n-1, e~n >> debug >> fn, en, e~n, f~n, f^n\n";

			for (int i = 2; i < seq.length; i++) {
				fCeil[i] = (int) ((fTilde[i - 1] + fTilde[i - 2]) / 2);
				e[i] = seq[i] - fCeil[i];
				if (i == 2) {
					eTilde[i] = 0;
				} else {
					eTilde[i] = (16 * (int) ((255 + e[i]) / 16)) - 256 + 8;
				}
				fTilde[i] = fCeil[i] + eTilde[i];

				toFile += fTilde[i - 2] + "," + fTilde[i - 1] + "," + eTilde[i];

				if (false) {
					toFile += ", >> debug >> " + seq[i] + "," + e[i] + "," + eTilde[i] + "," + fTilde[i] + "," + fCeil[i];
				}

				if (i != seq.length - 1) {
					toFile += "\n";
				}
			}

			bw.write(toFile);
			// bw.write("f~n-1,f~n-2,e~n;");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printReconstructedValuesFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		String spliter = ",";
		String output = "";
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] aTmp = line.split(spliter);
				int fTildeMinus2 = Integer.parseInt(aTmp[0]);
				int fTildeMinus1 = Integer.parseInt(aTmp[1]);
				int eTilde = Integer.parseInt(aTmp[2]);
				int reconstruct = ((int) ((fTildeMinus1 + fTildeMinus2) / 2)) + eTilde;
				output += reconstruct + "\t";
			}
			br.close();
			System.out.print("\nOutput:\n" + output.subSequence(0, output.length() - 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static int[] initSeqFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		String spliter = ",";
		int[] aInt;

		try {
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			String[] aTmp = line.split(spliter);
			aInt = new int[aTmp.length + 2];

			aInt[0] = Integer.parseInt(aTmp[0]);
			aInt[1] = Integer.parseInt(aTmp[0]);

			for (int i = 2; i < aInt.length; i++) {
				aInt[i] = Integer.parseInt(aTmp[i - 2]);
			}
			if (true) {
				System.out.println("Input:");
				for (int i = 2; i < aInt.length; i++) {
					System.out.print(aInt[i] + "\t");
				}
			}
			br.close();
			return aInt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
