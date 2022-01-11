import java.util.Collection;

public interface AutoCompletionInteface {
	
	 boolean addWord(String str);
	 
	 boolean removeWord(String str);
	 
	 Collection<String> getCompletionOptions(String prefix);

}
