import java.util.*;
import java.io.*;
public class ReadWords {
    private static String WORD_FILE = "wordslower.txt";
    
    /**
     * @param list contains all valid words
     * @param letters is from Spelling Bee, 
     *         center letter is last character in letters
     */
    
    public boolean wordWorks(String word, String letters){
        char last = letters.charAt(letters.length()-1);
        // is center letter in the word?
        if (word.indexOf(last) < 0) return false;
        for(char ch : word.toCharArray()){
            if (letters.indexOf(ch) < 0) return false;
        }
        return true;
    }

    /**
     * It's known that word can be made from letters, e.g., wordWorks(word,letters) == true
     * @param word is a String
     * @param letters is letters that come from Spelling Bee, last char at center
     * @return true iff word is a pangram, e.g., contains every char in letters
     */
    private boolean isPangram(String word, String letters){
        for(char ch : letters.toCharArray()){
            if (word.indexOf(ch) < 0) return false;
        }
        return true;
    }

    /**
     * Find all words that can be made from letters, where letters
     * is from Spelling Bee and last char in letters is center char
     * @param list of valid words in English
     * @param letters from Spelling Bee (last char is center)
     */
    public void findWords(List<String> list, String letters){
        for(String word : list){
            if (wordWorks(word, letters)){
                System.out.printf("%s",word);
                if (isPangram(word,letters)){
                    System.out.println("   ****** PANGRAM");
                }
                else {
                    System.out.println();
                }
            }
        }
    }
                
    public List<String> readWords(String filename) throws FileNotFoundException{
        Scanner sc = new Scanner(
                         new BufferedReader(
                               new FileReader(filename)));

        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
        }
        return list;
    }
    public static void main(String[] args) {
        String spBeeLets = "xocniet";
        ReadWords rw = new ReadWords();
        List<String> list;
        try {
            list = rw.readWords(WORD_FILE);
            System.out.printf("read %d words\n", list.size());
            rw.findWords(list,spBeeLets);
        } catch (FileNotFoundException e) {
            System.out.printf("could not open %s\n",WORD_FILE);
        }
    }
}