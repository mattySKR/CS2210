/**
 * This class reads 2 files, creates an array of code pairs, compresses and
 * writes the compressed text file into a new .zzz compressed file
 * 
 * @author Matvey Skripchenko
 */
public class Compress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char c;
		boolean end = false;
		TextFile input = new TextFile(args[0], "read");
		TextFile compressionCodesName = new TextFile(args[1], "read");
		String outputName = args[0].substring(0, args[0].length() - 3) + "zzz";
		CompressedFile outputFile = new CompressedFile(outputName, "write");
		ArrayCode newList = new ArrayCode(26);

		while (!end && (c = compressionCodesName.readChar()) != (char) 0) {
			String code = compressionCodesName.readLine();
			CodePair newPair = new CodePair(c, code);
			newList.add(newPair);
		}

		compressionCodesName.close();

		System.out.println("Name of file to compress: " + args[0]);
		System.out.println("Name of file storing compression codes: " + args[1]);

		boolean endOfFile = false; // Setting the boolean

		while (!endOfFile && (c = input.readChar()) != (char) 0) {
			String code = newList.getCode(newList.findCharacter(c));
			char[] ch = code.toCharArray(); // creating an array of chars after converting a String into sequence of
											// characters
			for (int i = 0; i < ch.length; i++) {
				outputFile.writeBit(ch[i]); // writing the compression code into the file
			}

		}

		input.close();
		outputFile.close();

	}

}
