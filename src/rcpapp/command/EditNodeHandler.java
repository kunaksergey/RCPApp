package rcpapp.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import rcpapp.editor.NodeEditor;
import rcpapp.editor.NodeEditorInput;
import rcpapp.model.Node;

public class EditNodeHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// get the page
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		
		 // get the selection
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        
        if (selection != null && selection instanceof IStructuredSelection) {
        	IStructuredSelection structure=((IStructuredSelection) selection);
            Object obj = ((IStructuredSelection) selection).getFirstElement();
//            System.out.println(obj);
               // if we had a selection lets open the editor
            if (obj != null) {
                Node node = (Node) obj;
                IEditorInput input = new NodeEditorInput(node);
                             try {
                    page.openEditor(input, NodeEditor.ID);
                } catch (PartInitException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;

	}

}

