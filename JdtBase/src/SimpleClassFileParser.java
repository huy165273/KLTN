import cfg.ICFG;
import cfg.generation.CFGGeneration;
import cfg.generation.CFGGenerationSubCondition;
import cfg.generation.testpath.Testpath;
import cfg.generation.testpath.TestpathGeneration;
import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleClassFileParser extends Object implements IJdtParser {
	public static void main(String[] args) {
        String filePath = "JdtBase/src/Test.java";
		new SimpleClassFileParser(filePath);
	}

	public SimpleClassFileParser(String filePath) {
		try {
			String fileContent = Utils.readFileContent(filePath);
			parse(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parse(String fileContent) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(fileContent.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	
		cu.accept(new ASTVisitor() {

			// Thăm khai báo một phương thức

			public boolean visit(MethodDeclaration node) {
				System.out.println("method" );

				AST ast = node.getAST();
	 			// Lay body method
				CFGGeneration cfgGenerration = new CFGGeneration(node);
				System.out.println("check cfgGeneration");
				System.out.println(cfgGenerration.getFunctionNode());
				System.out.println("Get all node:");
				ArrayList<ICfgNode> iCfgNodes = cfgGenerration.generateCFG().getAllNodes();
				int index = 0;
				for (ICfgNode iCfgNode: iCfgNodes) {
					System.out.print("node:");
					System.out.println(index++);

					System.out.println(iCfgNode);
				}

				ICFG cfg = cfgGenerration.generateCFG();
				CFGGenerationSubCondition cfgGenerrationforsubcondition = new CFGGenerationSubCondition(cfg, 1);
				ICFG cfgsubcondition = cfgGenerrationforsubcondition.generateCFG();

				TestpathGeneration testpathGen = new TestpathGeneration(cfgsubcondition);
				testpathGen.generateTestpaths();
// day la doan tao them vao de tes
				//System.out.println("tesst: " + testpathGen.generateTestpaths());





// den day la het

//				FullTestpaths testPaths = testpathGen.getPossibleTestpaths(); // Lấy FullTestpaths
//				if (testPaths != null && !testPaths.isEmpty()) {
//					int pathIndex = 1;
//					for (ITestpath testPath : testPaths) { // FullTestpaths là một Iterable<ITestpath>
//						System.out.println("Test Path " + pathIndex++ + ": " + testPath.toString());
//					}
//				} else {
//					System.out.println("No test paths generated.");
//				}

				int startPosition = node.getStartPosition();
				int endPosition = startPosition + node.getLength();
				String methodString = fileContent.substring(startPosition,endPosition);
				Block body = node.getBody();

				// Lay ten method
				SimpleName name = node.getName();

				// Lay kieu tra ve cua phuong thuc: void, boolean
				Type returnValueType = node.getReturnType2();


				// Lấy danh sách tham số tryền vào method
				List<MethodDeclaration> parameters = node.parameters();

				// Lấy danh sách modifier: public/private/protected, @Override
				List<BodyDeclaration> modifiers = node.modifiers();

//				Method method = new Method(cu.getClass(),modifiers,returnValueType,name,parameters,body,startPosition,endPosition,methodString);

				// Lấy cha của node khai báo trong cây AST
				ASTNode parent = node.getParent();

				return true;
			}

			// Thăm khai báo biến toàn cục
			public boolean visit(FieldDeclaration node) {
				// Lấy danh sách khai báo biến toàn cục
				List<FieldDeclaration> fragements = node.fragments();
				return true;
			}

			// Thăm một class (có thể là nested class)
			public boolean visit(TypeDeclaration classDeclaration) {
				// Lấy danh sách implementation
				List<TypeDeclaration> interfaces = classDeclaration.superInterfaceTypes();

				// Lấy lớp được thừa kế
				Type extendClass = classDeclaration.getSuperclassType();
				return true;
			}

			// Thăm import
			public boolean visit(ImportDeclaration node) {
				print(node);

				return true;
			}

			// Thăm biểu thức ép kiểu
			public boolean visit(CastExpression node) {
				// VD1: (CompilationUnit)parser.createAST(null)
				// VD2: (String)o.toString()

				// Lấy kiểu ép
				Type castExpression = node.getType();

				// Lấy biểu thức bị ép kiểu
				Expression expression = node.getExpression();
				return true;
			}

			// Thăm khai báo biến cục bộ
			public boolean visit(VariableDeclarationStatement node) {
				return true;
			}

			// Thăm câu lệnh hoặc biểu thức có lời gọi hàm
			public boolean visit(MethodInvocation node) {
				// VD1: System.out.println("----\n" + (String)o.toString());
				// VD2: o.toString()
				return true;
			}
		});
	}

	/**
	 * Xem nội dung object để test
	 * 
	 * @param
	 */
	public void print(Object o) {
		if (o != null)
			System.out.println("----\n" + o.toString() + "\n------\n");
	}
}
