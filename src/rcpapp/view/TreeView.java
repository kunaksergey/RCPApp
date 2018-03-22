package rcpapp.view;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.services.ISourceProviderService;

import rcpapp.command.CommandStateProvider;
import rcpapp.controller.TreeController;
import rcpapp.model.IChangeModelListener;
import rcpapp.model.Node;

public class TreeView extends ViewPart implements IChangeModelListener {
	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.getTree().setHeaderVisible(true);
		//viewer.getTree().setLinesVisible(true);
	
		
		TreeViewerColumn mainColumn = new TreeViewerColumn(viewer, SWT.NONE);
		mainColumn.getColumn().setText("Node");
		mainColumn.getColumn().setWidth(300);
		mainColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new TreeLabelProvider()));
		TreeController treeController = TreeController.getInstance();
		viewer.setInput(treeController.listRoots());
		treeController.addChangeModelListener(this);
		
		
		// the viewer field is an already configured TreeViewer
		Tree tree =  viewer.getTree();
    
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				TreeItem item = (TreeItem) e.item;
//				Node node=(Node)item.getData();
//				
//				ISourceProviderService service =(ISourceProviderService) PlatformUI.getWorkbench().getService(ISourceProviderService.class);
//				CommandStateProvider sourceProvider = (CommandStateProvider)service.getSourceProvider("isFolder");
//	
//				if(node.isFolder()){
//					sourceProvider.setFolder();
//				}else{
//					sourceProvider.setFile();
//				}
			}
		});
		
	
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				
				// TODO Auto-generated method stub
//			       TreeViewer viewer = (TreeViewer) event.getViewer();
//			        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
//			        Object selectedNode = thisSelection.getFirstElement();
//			        viewer.setExpandedState(selectedNode,
//			                !viewer.getExpandedState(selectedNode));
				getSite().setSelectionProvider(viewer);
				 IHandlerService handlerService = getSite().getService(IHandlerService.class);
				 try {
	                    handlerService.executeCommand("rcapp.command.EditNode", null);
	                } catch (Exception ex) {
	                    throw new RuntimeException(ex.getMessage());
	             }
				 
					
				 
			}
			
          
        });
}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void changeModel() {
		System.out.println( TreeController.getInstance().listRoots());
		viewer.refresh();
	}

}
