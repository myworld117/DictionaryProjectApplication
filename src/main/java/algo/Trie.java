package algo;

public class Trie {
    private static final int alphabetSize = 30;
    public static TrieNode root = new TrieNode();

    public void add(String key) {
        key = key.toLowerCase();
        TrieNode trieNode = root;
        for (int i = 0; i < key.length(); i++) {
            char x = key.charAt(i);
            int index = 0;
            if (x >= 'a' && x <= 'z') {
                index = x - 'a';
            } else if (x >= 'A' && x <= 'Z') {
                index = x - 'A';
            } else if (x >= '0' && x <= '9') {
                index = x - '0';
            } else if (x == ' ') {
                index = 26; // Giả sử rằng bạn đã dành một vị trí trong mảng con cho ký tự cách
            } else if (x == '-') {
                index = 27;
            } else if (x == '.') {
                index = 28;
            } else if (x== ',') {
                index = 29;
            }
            if (trieNode.getChildren().get(index) == null) {
                trieNode.getChildren().set(index, new TrieNode());
            }
            trieNode = trieNode.getChildren().get(index);
        }
        trieNode.setEndOfWord(true);
        trieNode.setWord(key);
    }


    public TrieNode search(String key) {
        key = key.toLowerCase();
        TrieNode trieNode = root;
        for (int i = 0; i < key.length(); i++) {
            char x = key.charAt(i);
            int index = 0;
            if (x >= 'a' && x <= 'z') {
                index = x - 'a';
            } else if (x >= 'A' && x <= 'Z') {
                index = x - 'A';
            } else if (x >= '0' && x <= '9') {
                index = x - '0';
            } else if (x == ' ') {
                index = 26; // Giả sử rằng bạn đã dành một vị trí trong mảng con cho ký tự cách
            } else if (x == '-') {
                index = 27;
            } else if (x == '.') {
                index = 28;
            } else if (x== ',') {
                index = 29;
            }
            if (trieNode.getChildren().get(index) == null) {
                return null;
            }
            trieNode = trieNode.getChildren().get(index);
        }
        return trieNode;
    }


    public String find(TrieNode keyNode) {
        String ans = "";
        if (keyNode == null) {
            return "";
        }
        if (keyNode.isEndOfWord()) {
            ans = keyNode.getWord() + "\n";
        }
        for (int i = 0; i < alphabetSize; i++) {
            if (keyNode.getChildren().get(i) != null) {
                ans = ans.concat(find(keyNode.getChildren().get(i)));
            }
        }
        return ans;
    }
    public String findAllWord(String key) {
        String ans = "";
        try {
            key = key.toLowerCase();
            TrieNode trieNode = search(key);
            if (trieNode != null) {
                String[] words = find(trieNode).split("\n");
                for (String word : words) {
                    ans = ans.concat(word + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while finding all words: " + e.getMessage());
        }
        return ans;
    }

    static boolean isEmpty(TrieNode trieNode) {
        for (int i = 0; i < alphabetSize; i++) {
            if (trieNode.getChildren().get(i) != null) {
                return false;
            }
        }
        return true;
    }

    public void delete(String key) {
        key = key.toLowerCase();
        backTrackDelete(root, key, 0);
    }

    public TrieNode backTrackDelete(TrieNode trieNode, String key, int depth) {
        key = key.toLowerCase();
        if (trieNode == null) {
            return null;
        }
        if (depth == key.length()) {
            if (trieNode.isEndOfWord()) {
                trieNode.setEndOfWord(false);
            }
            if (isEmpty(trieNode)) {
                trieNode = null;
            }
            return trieNode;
        }

        char x = key.charAt(depth);
        int index = 0;
        if (x >= 'a' && x <= 'z') {
            index = x - 'a';
        } else if (x >= 'A' && x <= 'Z') {
            index = x - 'A';
        } else if (x >= '0' && x <= '9') {
            index = x - '0';
        }

        trieNode.getChildren().set(index, backTrackDelete(trieNode.getChildren().get(index), key, depth + 1));
        if (isEmpty(trieNode) && !trieNode.isEndOfWord()) {
            trieNode = null;
        }
        return trieNode;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("ans");
        trie.add("ant");
        trie.add("anxious");
        trie.add("hello");
        trie.add("hell");
        trie.delete("hell");
        System.out.println(trie.findAllWord("an"));
    }
}
