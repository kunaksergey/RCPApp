package rcpapp.controller;

import java.util.ArrayList;
import java.util.List;

import rcpapp.model.IChangeModelListener;
import rcpapp.model.Node;
import rcpapp.service.ITreeService;
import rcpapp.service.LocalTreeService;

public class TreeController {
	private static TreeController treeController;
	private ITreeService service = new LocalTreeService();
	private List<IChangeModelListener> listeners=new ArrayList<>();

	private TreeController() {

	}

	public static TreeController getInstance() {
		if (treeController == null) {
			treeController = new TreeController();
		}
		return treeController;
	}

	public void setService(ITreeService service) {
		this.service = service;
	}

	public List<Node> listRoots() {
		return service.getListRoots();
	}

	public void remove(Node node) {
		service.remove(node);
		changeModel();
	}

	private void changeModel() {
		listeners.forEach(l -> l.changeModel());
	}
	
	public void addChangeModelListener(IChangeModelListener listener){
		listeners.add(listener);
	}
}
