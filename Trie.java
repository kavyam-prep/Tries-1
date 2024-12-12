import java.util.HashMap;
import java.util.Map;

public class Trie{
    TrieNode root;
    class TrieNode{
        // TrieNode[] children; //can use hashmap 
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode(){
            // this.children = new TrieNode[26];
            this.children = new HashMap<>();
        }
    }

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) { //O(L) 
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            // if(curr.children[c - 'a'] == null){
            if(curr.children.get(c) == null){
                // curr.children[c - 'a'] = new TrieNode();
                curr.children.put(c, new TrieNode());
            }
            // curr = curr.children[c - 'a'];
            curr = curr.children.get(c);
        }

        curr.isEnd = true;
    }
    
    public boolean search(String word) { //O(L)
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            // if(curr.children[c - 'a'] == null){
            if(curr.children.get(c) == null){
                return false;
            }
            // curr = curr.children[c - 'a'];
            curr = curr.children.get(c);
        }

        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {  //O(L)
        TrieNode curr = root;
        for(char c: prefix.toCharArray()){
            // if(curr.children[c - 'a'] == null){
            if(curr.children.get(c) == null){
                return false;
            }
            // curr = curr.children[c - 'a'];
            curr = curr.children.get(c);
        }

        return true;
    }
}