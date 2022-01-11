import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

public class AutoCompletionImpl implements AutoCompletionInteface {
	Collection<String> collection;
	// This ctor supports instance creation with desired type of collection
	public AutoCompletionImpl(Collection<String> collection) {
		this.collection = collection;
	}
	@Override
	public boolean addWord(String str) {
		// TODO - Done
		return collection.add(str);
	}

	@Override
	public boolean removeWord(String str) {
		// TODO - Done
		return collection.remove(str);
	}
	@Override
	public Collection<String> getCompletionOptions(String prefix) {
		// TODO Done
//		LinkedList<String> wanted = new LinkedList<String>(collection);		
		TreeSet<String> wanted = new TreeSet<String>(collection);		
		wanted.removeIf(str -> !str.startsWith(prefix));		
		return wanted;
	}
}
