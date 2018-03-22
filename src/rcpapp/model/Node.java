package rcpapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.jface.viewers.ISelection;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;

public class Node{
	private String name;
	private Node parent;
	private boolean isFolder;
	private List<Node> chields=new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parrent) {
		this.parent = parrent;
	}
	public boolean isFolder() {
		return isFolder;
	}
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	public List<Node> getChields() {
		return chields;
	}
	public void setChields(List<Node> chields) {
		this.chields = chields;
	}
	public void addChield(Node chield) {
		chield.setParent(this);
		chields.add(chield);
	}
	
	public String fullPath(){
		StringBuilder path=new StringBuilder("");
		List<String> listPath=new ArrayList<>();
		Node currentNode=this;
		
		while(currentNode.getParent()!=null){
			listPath.add(currentNode.getParent().getName());
			currentNode=currentNode.getParent();
		}
		
		ListIterator<String> itr=listPath.listIterator(listPath.size());
		while(itr.hasPrevious()){
			String previous=itr.previous();
			System.out.println(previous);
			if(!previous.equals("/")){
			path.append("/").append(previous);
			}else{
				path.append(previous);
			}
			System.out.println(path);
		}
		
		return path.toString();
	}
	@Override
	public String toString() {
		return "Node ["+parent.getName()+": name=" + name +"]";
	}
	
	
}
