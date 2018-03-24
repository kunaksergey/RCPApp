package rcpapp.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import rcpapp.controller.TreeController;
import rcpapp.model.Node;

public class NodeEditor extends EditorPart {
	public static final String ID = "rcpapp.view.NodeEditor";
	private NodeEditorInput input;
	private Node node;
	Text text;

	public NodeEditor() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
		setInput(input);
		this.input = (NodeEditorInput) input;
		node = this.input.getNode();
		setPartName(node.fullPath());
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText(this.input.getNode().getName());
		text = new Text(parent, SWT.BORDER);
		text.setText(node.getName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

		// If node is NEW
		if (!node.getParent().hasChield(node)) {
			TreeController.getInstance().addAsChield(node);
		}
		node.setName(text.getText());
		TreeController.getInstance().changedModel();
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

}
