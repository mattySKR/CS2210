public class Decompress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char c;
		boolean end = false;
		CompressedFile compressedFileName = new CompressedFile(args[0], "read");
		TextFile compressionCodesName = new TextFile(args[1], "read");
		String outputName = args[0].substring(0, args[0].length() - 3) + "dec";
		TextFile decompFile = new TextFile(outputName, "write");
		ArrayCode newList = new ArrayCode(26);

		while (!end && (c = compressionCodesName.readChar()) != (char) 0) {
			String code = compressionCodesName.readLine();
			CodePair newPair = new CodePair(c, code);
			newList.add(newPair);
		}

		compressionCodesName.close();

		System.out.println("Name of file to decompress: " + args[0]);
		System.out.println("Name of file storing compression codes: " + args[1]);

		end = false; // Setting the boolean
		String code = "";

		while (end != true) {

			char bit = compressedFileName.readBit();
			code = code + bit;

			int codePost = newList.findCode(code);

			if (codePost != -1) {
				char character = newList.getCharacter(codePost);
				decompFile.writeChar(character);
				code = "";

			}
			if (bit == (char) 0)
				end = true;

		}

		decompFile.close();

	}

}