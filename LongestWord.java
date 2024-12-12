import java.util.HashMap;
import java.util.Map;

public class LongestWord {
    String result;
    class Trie{
        TrieNode root;
        public Trie(){
            this.root = new TrieNode('.');
        }

        class TrieNode{
            Map<Character, TrieNode> children;
            char value;
            boolean isEnd;
            public TrieNode(char value){
                this.children = new HashMap<>();
                this.isEnd = false;
                this.value = value;
            }
        }

        public void buildTrie(String word){
            TrieNode curr = root; 
            for(char c: word.toCharArray()){
                if(curr.children.get(c) == null){
                    //not there 
                    curr.children.put(c, new TrieNode(c));
                }
                curr = curr.children.get(c);
            }
            curr.isEnd = true;
        }

        public void dfs(StringBuilder path, TrieNode node){

            if(result.length() == path.length()){
                int diff = result.compareTo(path.toString());
                if(diff > 0) result = path.toString();
            }
            if(result.length() < path.length()){
                result = path.toString();
            }

            //logic 
            for(TrieNode child: node.children.values()){
                if(child.isEnd){
                    int le = path.length();
                    path.append(child.value);
                    dfs(path, child);
                    path.setLength(le);
                }
            }
        }

    }

    public String longestWord(String[] words) {
        this.result = "";
        Trie trie = new Trie();
        for(String word: words){
            trie.buildTrie(word);
        }
        trie.dfs(new StringBuilder(), trie.root);
        return result;
    }
}
