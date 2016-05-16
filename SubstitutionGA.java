/** 
 * The program is an implementation of a genetic algorithm to solve 
 * Substitution cipher. The program tries to find the key which has as many
 * characters matching with the original as possible.
 * 
 * Version:   $Id: SubstitutionGA.java, v 1.0 2015/03/2 16:00:00 $
 * 
 * @author Harshad Paradkar
 * @author Pratiksha Mishra
 * 
 * Revisions: 
 *      
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SubstitutionGA {
	
	static LinkedList<Character> alphabets = new LinkedList<Character>();
	int decodedCharFreq[] = new int[26];
	static double singleCharFreq[] = new double[26];
	static String bigramCharFreq[][] = new String[30][2];
	static String trigramCharFreq[][] = new String[30][2];

	public void getDecodedCharFreq(String decodedText){
		
		/*
		 * calculate the frequencies of characters in the deciphered text
		 */
		int position;
		for(int i = 0; i < decodedText.length(); i++){
			// calculate for each character in plain text
			
			if(decodedText.charAt(i) == 'A'){
				position = 0;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'B'){
				position = 1;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'C'){
				position = 2;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'D'){
				position = 3;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'E'){
				position = 4;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'F'){
				position = 5;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'G'){
				position = 6;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'H'){
				position = 7;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'I'){
				position = 8;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'J'){
				position = 9;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'K'){
				position = 10;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'L'){
				position = 11;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'M'){
				position = 12;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'N'){
				position = 13;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'O'){
				position = 14;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'P'){
				position = 15;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'Q'){
				position = 16;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'R'){
				position = 17;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'S'){
				position = 18;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'T'){
				position = 19;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'U'){
				position = 20;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'V'){
				position = 21;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'W'){
				position = 22;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'X'){
				position = 23;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'Y'){
				position = 24;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}else if(decodedText.charAt(i) == 'Z'){
				position = 25;
				decodedCharFreq[position] = decodedCharFreq[position] + 1;
			}
		}

		// calculate the percentage of occurrence of each character
		for(int i = 0; i < 26; i++){
			decodedCharFreq[i] = (decodedCharFreq[i] / 26) * 100;
		}
	}

	/*
	 * calculate the error value caused by the unigrams in the deciphered text
	 */
	public double singleCharAnalysis(){
		double errorValue = 0;
		for(int i = 0; i < 26; i++){
			errorValue = errorValue + Math.abs(singleCharFreq[i] -
					decodedCharFreq[i]);
		}
		return errorValue;
	}
	
	/*
	 * calculate the error value caused by the bigrams in the deciphered text
	 */
	public double bigramCharAnalysis(String decryptedText){
		double errorValue = 0;
		double count = 0;
		
		/*
		 * using pattern matching calculate the number of times a particular
		 * bigram occurs in the deciphered text
		 */
		for(int i = 0; i < bigramCharFreq.length; i++){

			Pattern requiredPattern = Pattern.compile(bigramCharFreq[i][0]);
			Matcher matcherObject = requiredPattern.matcher(decryptedText);

			while (matcherObject.find()) {
				count++;
			}
			errorValue = errorValue + Math.abs(Double.
					parseDouble(bigramCharFreq[i][1]) - count);
			count  = 0;
		}
		return errorValue;
	}
	
	/*
	 * calculate the error value caused by the trigrams in the deciphered text
	 */
	public double trigramCharAnalysis(String decryptedText){
		double errorValue = 0;
		double count = 0;
		
		/*
		 * using pattern matching calculate the number of times a particular
		 * trigram occurs in the deciphered text		 
		 */
		for(int i = 0; i < trigramCharFreq.length; i++){

			Pattern requiredPattern = Pattern.compile(trigramCharFreq[i][0]);
			Matcher matcherObject = requiredPattern.matcher(decryptedText);

			while (matcherObject.find()) {
				count++;
			}
			errorValue = errorValue + Math.abs(Double.
					parseDouble(trigramCharFreq[i][1]) - count);
			count  = 0;
		}
		return errorValue;
	}


	public static void main(String ar[])throws Exception{

		if(ar.length != 3){
			System.err.println("Invalid commands.\nUsage: java cipher_text "
					+ "key no_of_generations");
			System.exit(0);
		}

		String cipherText = ar[0].toUpperCase(), plainText = "",
				originalKey = ar[1];

		LinkedList<LinkedList<String>> decodingInfo = null;
		LinkedList<LinkedList<String>> keySelectionInfo;
		LinkedList<LinkedList<String>> keysToRemove;

		int noOfGen = Integer.parseInt(ar[2]), noOfCrossoverPts = 15,
				populationSize = 200, noOfPairs = (populationSize - 4) / 2;
		
		double DIST_BTWN_PTRS = 0.5, randomNumber;

		LinkedList<LinkedList<Character>> keysCharacters = 
				new LinkedList<LinkedList<Character>>();
		
		LinkedList<String> keys = new LinkedList<String>();

		LinkedList<Character> temp = new LinkedList<Character>();

		Map<String, String> kee = new HashMap<String, String>();

		LinkedList<Double>  segmentLimit;
		boolean same = false;

		for(int i = 0; i < 26; i++){
			alphabets.add((char)('A' + i));
			temp.add((char)('A' + i));
		}

		// store the frequencies of all 26 characters in the English alphabet
		singleCharFreq[0] = 8.167; singleCharFreq[1] = 1.492; 
		singleCharFreq[2] = 2.782;
		singleCharFreq[3] = 4.253; singleCharFreq[4] = 12.702; 
		singleCharFreq[5] = 2.228;
		singleCharFreq[6] = 2.015; singleCharFreq[7] = 6.094; 
		singleCharFreq[8] = 6.996;
		singleCharFreq[9] = 0.153; singleCharFreq[10] = 0.772; 
		singleCharFreq[11] = 4.025;
		singleCharFreq[12] = 2.406; singleCharFreq[13] = 6.749; 
		singleCharFreq[14] = 7.507;
		singleCharFreq[15] = 1.929; singleCharFreq[16] = 0.095; 
		singleCharFreq[17] = 5.987;
		singleCharFreq[18] = 6.327; singleCharFreq[19] = 9.056; 
		singleCharFreq[20] = 2.758;
		singleCharFreq[21] = 0.978; singleCharFreq[22] = 2.360; 
		singleCharFreq[23] = 0.150;
		singleCharFreq[24] = 1.974; singleCharFreq[25] = 0.074;

		/*
		 * store frequently occurring bigrams in English language
		 */
		bigramCharFreq[0][0] = "TH";bigramCharFreq[1][0] = "HE";
		bigramCharFreq[2][0]= "IN";
		bigramCharFreq[3][0] = "ER";bigramCharFreq[4][0] = "AN";
		bigramCharFreq[5][0] = "RE";
		bigramCharFreq[6][0] = "ES";bigramCharFreq[7][0] = "ON";
		bigramCharFreq[8][0] = "ST";
		bigramCharFreq[9][0] = "NT";bigramCharFreq[10][0] = "EN";
		bigramCharFreq[11][0] = "AT";
		bigramCharFreq[12][0] = "ED";bigramCharFreq[13][0] = "ND";
		bigramCharFreq[14][0] = "TO";
		bigramCharFreq[15][0] = "OR";bigramCharFreq[16][0] = "EA";
		bigramCharFreq[17][0] = "TI";
		bigramCharFreq[18][0] = "AR";bigramCharFreq[19][0] = "TE";
		bigramCharFreq[20][0] = "NG";
		bigramCharFreq[21][0] = "AL";bigramCharFreq[22][0] = "IT";
		bigramCharFreq[23][0] = "AS";
		bigramCharFreq[24][0] = "IS";bigramCharFreq[25][0] = "HA";
		bigramCharFreq[26][0] = "ET";
		bigramCharFreq[27][0] = "SE";bigramCharFreq[28][0] = "OU";
		bigramCharFreq[29][0] = "OF";
		
		/*
		 * store the frequencies of the frequently occurring bigrams in English
		 * language 
		 */
		bigramCharFreq[0][1] = "2.71"; bigramCharFreq[1][1] = "2.33"; 
		bigramCharFreq[2][1] = "2.03";
		bigramCharFreq[3][1] = "1.78"; bigramCharFreq[4][1] = "1.61"; 
		bigramCharFreq[5][1] = "1.41";
		bigramCharFreq[6][1] = "1.32"; bigramCharFreq[7][1] = "1.32";
		bigramCharFreq[8][1] = "1.25";
		bigramCharFreq[9][1] = "1.17"; bigramCharFreq[10][1] = "1.13"; 
		bigramCharFreq[11][1] = "1.12";
		bigramCharFreq[12][1] = "1.08"; bigramCharFreq[13][1] = "1.07"; 
		bigramCharFreq[14][1] = "1.07";
		bigramCharFreq[15][1] = "1.06"; bigramCharFreq[16][1] = "1.00"; 
		bigramCharFreq[17][1] = "0.99";
		bigramCharFreq[18][1] = "0.98";bigramCharFreq[19][1] = "0.98";
		bigramCharFreq[20][1] = "0.89";
		bigramCharFreq[21][1] = "0.88";bigramCharFreq[22][1] = "0.88";
		bigramCharFreq[23][1] = "0.87";
		bigramCharFreq[24][1] = "0.86";bigramCharFreq[25][1] = "0.83";
		bigramCharFreq[26][1] = "0.76";
		bigramCharFreq[27][1] = "0.73";bigramCharFreq[28][1] = "0.72";
		bigramCharFreq[29][1] = "0.71";

		/*
		 * store frequently occurring trigrams in English language
		 */
		trigramCharFreq[0][0] = "THE";trigramCharFreq[1][0] = "AND";
		trigramCharFreq[2][0] = "ING";
		trigramCharFreq[3][0] = "ENT";trigramCharFreq[4][0] = "ION";
		trigramCharFreq[5][0] = "HER";
		trigramCharFreq[6][0] = "FOR";trigramCharFreq[7][0] = "THA";
		trigramCharFreq[8][0] = "NTH";
		trigramCharFreq[9][0] = "INT";trigramCharFreq[10][0] = "ERE";
		trigramCharFreq[11][0] = "TIO";
		trigramCharFreq[12][0] = "TER";trigramCharFreq[13][0] = "EST";
		trigramCharFreq[14][0] = "ERS";
		trigramCharFreq[15][0] = "ATI";trigramCharFreq[16][0] = "HAT";
		trigramCharFreq[17][0] = "ATE";
		trigramCharFreq[18][0] = "ALL";trigramCharFreq[19][0] = "ETH";
		trigramCharFreq[20][0] = "HES";
		trigramCharFreq[21][0] = "VER";trigramCharFreq[22][0] = "HIS";
		trigramCharFreq[23][0] = "OFT";
		trigramCharFreq[24][0] = "ITH";trigramCharFreq[25][0] = "FTH";
		trigramCharFreq[26][0] = "STH";
		trigramCharFreq[27][0] = "OTH";trigramCharFreq[28][0] = "RES";
		trigramCharFreq[29][0] = "ONT";

		/*
		 * store the frequencies of the frequently occurring trigrams in English
		 * language 
		 */
		trigramCharFreq[0][1] = "1.81"; trigramCharFreq[1][1] = "0.73"; 
		trigramCharFreq[2][1] = "0.72";
		trigramCharFreq[3][1] = "0.42"; trigramCharFreq[4][1] = "0.42"; 
		trigramCharFreq[5][1] = "0.36";
		trigramCharFreq[6][1] = "0.34"; trigramCharFreq[7][1] = "0.33";
		trigramCharFreq[8][1] = "0.33";
		trigramCharFreq[9][1] = "0.32"; trigramCharFreq[10][1] = "0.31"; 
		trigramCharFreq[11][1] = "0.31";
		trigramCharFreq[12][1] = "0.30"; trigramCharFreq[13][1] = "0.28";
		trigramCharFreq[14][1] = "0.28";
		trigramCharFreq[15][1] = "0.26"; trigramCharFreq[16][1] = "0.26"; 
		trigramCharFreq[17][1] = "0.25";
		trigramCharFreq[18][1] = "0.25";trigramCharFreq[19][1] = "0.24";
		trigramCharFreq[20][1] = "0.24";
		trigramCharFreq[21][1] = "0.24";trigramCharFreq[22][1] = "0.24";
		trigramCharFreq[23][1] = "0.22";
		trigramCharFreq[24][1] = "0.21";trigramCharFreq[25][1] = "0.21";
		trigramCharFreq[26][1] = "0.21";
		trigramCharFreq[27][1] = "0.21";trigramCharFreq[28][1] = "0.21";
		trigramCharFreq[29][1] = "0.20";


		// generate and store unique keys made out of 26 characters in English
		Collections.shuffle(temp);
		for(int i = 0; i < populationSize; i++){

			while(same){
				Collections.shuffle(temp);
				for(int j = 0; j < keysCharacters.size(); j++){
					
					if(keysCharacters.get(j).equals(temp)){
						/*
						 * if same key is generated again, break the loop
						 * and again shuffle the characters
						 */
						break;
					}else{
						same = false;
					}
				}
			}
			same = true;
			// store the key characters in the Linked List
			keysCharacters.addLast(new LinkedList<>(temp));
		}
		
		String temp1 = "";
		
		// concatenate the characters in the key to form a string
		for(int i = 0; i < populationSize; i++){
			for(int j = 0; j < 26; j++){
				temp1 = temp1 + keysCharacters.get(i).get(j);
			}
			// add the key string in the linked list
			keys.add(temp1);
			temp1 = "";
		}

		System.out.println("Best Keys found:\n");
		/*
		 * for M generations
		 */
		for(int i = 0; i < noOfGen; i++){

			decodingInfo = new LinkedList<LinkedList<String>>();

			for(int j = 0; j < keysCharacters.size(); j++){

				for(int k = 0; k < cipherText.length(); k++){

					/*
					 * a. Decrypt the cipher text by the 20 generated keys.
					 */
					plainText = plainText + keysCharacters.get(j).
							get(alphabets.indexOf(cipherText.charAt(k)));
				}
				decodingInfo.add(new LinkedList<String>(Arrays.
						asList(plainText, keys.get(j))));

				new SubstitutionGA().getDecodedCharFreq(plainText);

				/*
				 * b. Calculate the suitability of each key from every
				 *	  decrypted text using the formula of fitness.
				 */
				double error = new SubstitutionGA().singleCharAnalysis() +
						new SubstitutionGA().bigramCharAnalysis(plainText) +
						new SubstitutionGA().trigramCharAnalysis(plainText);
				
				decodingInfo.get(j).add(Double.toString(error));

				plainText = "";
			}
			
			/*
			 * c. Sort the keys based on the increased fitness values.
			 */
			Collections.sort(decodingInfo,
					new Comparator<LinkedList<String>>() {    
				@Override
				public int compare(LinkedList<String> o1, 
						LinkedList<String> o2) {
					return o1.get(2).compareTo(o2.get(2));
				}               
			});

			/*
			 * d. Keep 20% (2 pairs) of best fittest of p(0) for next
			 *    generation.
			 */
			keys.clear();
			keySelectionInfo = new LinkedList<LinkedList<String>>(decodingInfo);

			for(int j = 0; j < 4; j++){
				keys.add(keySelectionInfo.getFirst().get(1));
				keySelectionInfo.removeFirst();
			}
			
			Random randomizer = new Random();

			for(int j = 0; j < noOfPairs; j++){

				int nOfPointers = 0;
				keysToRemove = new LinkedList<LinkedList<String>>();
				double limit = 0.0, total = 0.0;
				segmentLimit = new LinkedList<Double>();

				// calculate total of all fitness values
				for(int k = 0; k < keySelectionInfo.size(); k++){
					total  = total + 
							Double.parseDouble(keySelectionInfo.get(k).get(2));
				}
				
				// calculate the segment limit for each key
				for(int k = keySelectionInfo.size() - 1; k >= 0; k--){
					limit  = limit + 
							(Double.parseDouble(keySelectionInfo.
									get(k).get(2)) / total);
					segmentLimit.addLast(limit);
				}

				// generate a random number between 0 and 0.5
				randomNumber = DIST_BTWN_PTRS * randomizer.nextDouble();

				// pick up the key which falls under the pointers
				for(int k = 0; k < segmentLimit.size(); k++){

					if(randomNumber <= segmentLimit.get(k)){
						// if the key falls under the pointer, select this key
						keysToRemove.add(keySelectionInfo.get(k));
						nOfPointers++;
						keys.add(keySelectionInfo.get(k).get(1));
						kee.put(keySelectionInfo.get(k).get(1), "");
						randomNumber = randomNumber + DIST_BTWN_PTRS;
						
						/*
						 * since we need two pairs, or two keys, break the loop
						 * since we have generated two pointers and got two keys
						 */
						if(nOfPointers == 2){
							break;
						}
					}
				}
				
				/*
				 * for next selection operation, remove the keys that have
				 * already been selected 
				 */
				for(int k = 0; k < keysToRemove.size(); k++){
					keySelectionInfo.remove(keysToRemove.get(k));
				}
			}

			/*************************************************************
			 * 															 *
			 * Crossover												 *
			 * 															 *	
			 *************************************************************/


			Map <Integer, Integer>uniqueCrPoint = new HashMap<Integer, 
					Integer>();
			Map <Character, Integer>crChars = new HashMap<Character, Integer>();
			char childOneCharacters[] = new char[26];
			char childTwoCharacters[] = new char[26];

			LinkedList<Integer> crossoverPoints;
			String childOne = "", childTwo = "";
			temp.clear();
			int secParCharPos;
			
			
			for(int j = 0; j < populationSize; j++){

				secParCharPos = 0;
				for(int k = 0; k < childOneCharacters.length; k++){
					childOneCharacters[k] = ' ';
					childTwoCharacters[k] = ' ';
				}

				// generate unique crossover points
				while(uniqueCrPoint.size() != noOfCrossoverPts){
					uniqueCrPoint.put(randomizer.nextInt(26), 0);
				}
				
				// get the crossover points stored in the key of Map
				crossoverPoints = 
						new LinkedList<Integer>(uniqueCrPoint.keySet());
				
				/*
				 * sort them so that its easy to fill them up in a for loop in
				 * sequence
				 */
				Collections.sort(crossoverPoints);

				// put the characters from first parent in the child
				for(int k = 0; k < crossoverPoints.size(); k++){
					childOneCharacters[crossoverPoints.get(k)] = keys.get(j).
							charAt(crossoverPoints.get(k));
					crChars.put(childOneCharacters[crossoverPoints.get(k)], 0);
				}
				
				// put rest of the remaining characters from parent 2
				for(int k = 0; k < 26; k++){

					if(childOneCharacters[k] == ' '){
						/*
						 * check until a character not already present is found
						 * from parent two
						 */
						while(true){
							if(!crChars.containsKey(keys.get(j + 1).
									charAt(secParCharPos))){
								childOneCharacters[k] = keys.get(j + 1).
										charAt(secParCharPos);
								secParCharPos++;
								break;
							}else{
								secParCharPos++;
							}
						}
					}
				}

				secParCharPos = 0;
				crChars.clear();
				uniqueCrPoint.clear();
				temp.clear();
				
				// generate unique crossover points for child two
				while(uniqueCrPoint.size() != noOfCrossoverPts){
					uniqueCrPoint.put(randomizer.nextInt(26), 0);
				}
				
				// get the crossover points stored in key section of Map
				crossoverPoints = 
						new LinkedList<Integer>(uniqueCrPoint.keySet());

				/*
				 * sort them so that its easy to fill them up in a for loop in
				 * sequence
				 */
				Collections.sort(crossoverPoints);

				// put the characters from second parent in the child
				for(int k = 0; k < crossoverPoints.size(); k++){
					childTwoCharacters[crossoverPoints.get(k)] = 
							keys.get(j + 1).charAt(crossoverPoints.get(k));
					crChars.put(childTwoCharacters[crossoverPoints.get(k)], 0);
				}
				
				// put rest of the remaining characters from parent 1
				for(int k = 0; k < 26; k++){

					if(childTwoCharacters[k] == ' '){
						/*
						 * check until a character not already present is found
						 * from parent one
						 */
						while(true){
							if(!crChars.containsKey(keys.
									get(j).charAt(secParCharPos))){
								childTwoCharacters[k] = 
										keys.get(j).charAt(secParCharPos);
								secParCharPos++;
								break;
							}else{
								secParCharPos++;
							}
						}
					}
				}
				
				crChars.clear();
				uniqueCrPoint.clear();

				/*************************************************************
				 * 															 *
				 * Mutation													 *
				 * 															 *	
				 *************************************************************/
				int mutatedGenePos;
				char swap;
				
				mutatedGenePos = randomizer.nextInt(25);
				
				// mutate only with 20% probability
				if(randomizer.nextDouble() < 0.2){
					swap = childOneCharacters[mutatedGenePos];
					childOneCharacters[mutatedGenePos] = 
							childOneCharacters[mutatedGenePos + 1];
					childOneCharacters[mutatedGenePos + 1] = swap;
				}
				
				mutatedGenePos = randomizer.nextInt(25);
				if(randomizer.nextDouble() < 0.2){
					swap = childTwoCharacters[mutatedGenePos];
					childTwoCharacters[mutatedGenePos] = 
							childTwoCharacters[mutatedGenePos + 1];
					childTwoCharacters[mutatedGenePos + 1] = swap;
				}
				
				for(int k = 0; k < childOneCharacters.length; k++){
					
					// generate Children from the characters crossovered
					childOne = childOne + childOneCharacters[k];
					childTwo = childTwo + childTwoCharacters[k];
				}
				
				// replace the parents with their children
				keys.set(j, childOne);
				keys.set(j + 1, childTwo);

				childOne = ""; childTwo = "";

				j = j + 1;
				temp.clear();
			}

			keysCharacters.clear();
			temp.clear();
			
			/*
			 * generate the characters from the key strings and store them
			 * for decrypting the cipher text in the next generation
			 */
			for(int j = 0; j < keys.size(); j++){
				temp = new LinkedList<Character>();
				for(int k = 0; k < 26; k++){
					temp.add(keys.get(j).charAt(k));
				}
				keysCharacters.add(temp);
				
			}
			
			int count = 0;
			String bestKey = " ";
			for(int j = 0; j < decodingInfo.size(); j++){
				temp1 = decodingInfo.get(j).get(1).toLowerCase();
				for(int k = 0; k < originalKey.length(); k++){
					
					if(originalKey.charAt(k) == 
							decodingInfo.get(j).get(1).charAt(k)){
						//System.out.print(decodingInfo.get(j).get(1).charAt(k));
						bestKey = bestKey + 
								decodingInfo.get(j).get(1).charAt(k) + " ";
						count++;
					}else{
						//System.out.print(temp1.charAt(k));
						bestKey = bestKey + temp1.charAt(k) + " ";
					}
				}
			
				/*
				 * if the number of matches with the original key is greater
				 * than 5, display the key with the number of matches
				 */
				if(count > 5){
					System.out.println(bestKey + " matches: " + count);
				}
				count = 0;
				bestKey = "";
			}
		}
		
		
		for(int j = 0; j < keysCharacters.size(); j++){

			for(int k = 0; k < cipherText.length(); k++){

				/*
				 * a. Decrypt the cipher text by the 20 generated keys.
				 */
				plainText = plainText + keysCharacters.get(j).
						get(alphabets.indexOf(cipherText.charAt(k)));
				
			}
			//System.out.println(plainText.toLowerCase());
			plainText = "";

			//System.out.println(decodingInfo.get(j).get(2));
		}
	}
}
