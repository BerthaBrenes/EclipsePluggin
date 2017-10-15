package parseoast.handlers;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class MethodVisitor extends ASTVisitor {
	List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	
	@Override
	public boolean visit(MethodDeclaration node) {
		methods.add(node);
		return super.visit(node);
		//IMethodBinding binding = (IMethodBinding) node.getName().resolveBinding();
		//ICompilationUnit unit = (ICompilationUnit) binding.getJavaElement().getAncestor( IJavaElement.COMPILATION_UNIT );
		//if ( unit == null ) {
		   // not available, external declaration
		//}
	}
	public List<MethodDeclaration> getMethods(){
		return methods;
	}
	
}
