import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWords {
    class Trie{
        TrieNode root; 

        public Trie(){
            this.root = new TrieNode();
        }

        class TrieNode{
            Map<Character, TrieNode> children;
            boolean isEnd;

            public TrieNode(){
                this.children = new HashMap<>();
            }
        }

        public void insert(String word){
            TrieNode curr = root;
            for(char c: word.toCharArray()){
                if(curr.children.get(c) == null){
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isEnd = true;
        }

        public String getShortestVersion(String str){
            TrieNode curr = root;
            StringBuilder result = new StringBuilder();
            for(char c: str.toCharArray()){
                if(curr.children.get(c) == null || curr.isEnd){
                    //reason we have curr.isEnd is cause we need to stop if we found a 
                    //smaller word and return it
                    break;
                }
                curr = curr.children.get(c);
                result.append(c);
            }
            if(curr.isEnd){
                return result.toString();
            }
            else{
                return str;
            }
        }
        
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for(String s: dictionary){
            trie.insert(s);
        }

        StringBuilder result = new StringBuilder();
        String[] splitStr = sentence.split(" ");
        for(int i =0; i < splitStr.length; i++){
            result.append(trie.getShortestVersion(splitStr[i]));
            result.append(" ");
        }

        return result.toString().trim();
    }
}
