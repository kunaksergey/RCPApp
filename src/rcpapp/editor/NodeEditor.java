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

public class NodeEditor extends EditorPart {
	public static final String ID = "rcpapp.view.NodeEditor";
	private NodeEditorInput input;

	public NodeEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		this.input = (NodeEditorInput) input;
		setSite(site);
		setInput(input);
		setPartName(this.input.getNode().fullPath()+"/"+this.input.getNode().getName());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText(input.getNode().getName());
		Text text = new Text(parent, SWT.BORDER);
		text.setText(input.getNode().getName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
