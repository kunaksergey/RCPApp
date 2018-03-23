package rcpapp.view;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.EditorInputTransfer;

import rcpapp.editor.NodeEditor;
import rcpapp.editor.NodeEditorInput;
import rcpapp.model.Node;

public class NodeDragListener implements DragSourceListener {

	private final TreeViewer viewer;

	public NodeDragListener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Start Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		System.out.println("dragSetData");
		if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)) { // if
																					// support
																					// EditorInputTransfer
			IStructuredSelection selection = viewer.getStructuredSelection();
			Node node = (Node) selection.getFirstElement();
			IEditorInput input = new NodeEditorInput(node);
			EditorInputTransfer.EditorInputData data = EditorInputTransfer.createEditorInputData(NodeEditor.ID, input);
			event.data = new EditorInputTransfer.EditorInputData[] { data };
			System.out.println();
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		if (event.detail == DND.DROP_MOVE) {
			System.out.println("Finished Drag");
		}
	}
}
