package visitor;

import gen.Ardu3kParser;
import node.RootNode;
import node.expression.*;
import node.expression.DeclarationNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.primary.*;
import node.primary.IntegerNode;
import symbol.*;

public class TypeVisitor extends TopDeclVisitor{
    // This function checks if the types of Primary nodes in an expression is compatible
    // It will return the type for the expression
    // TODO: Shall this handle Identifier nodes?
    public RootNode expressionTypeVisitor(AbstractInfixExpressionNode exprNode) throws Exception {
        RootNode[] leftRight = new RootNode[2];
        String[] classNames = new String[2];

        // Set children
        leftRight[0] = exprNode.getLeft();
        leftRight[1] = exprNode.getRight();

        // Check if they are primary nodes
        for (int i = 0; i < 2; i++) {
            if (leftRight[i] != null) {
                if (leftRight[i] instanceof AbstractPrimaryNode) {
                    // Do nothing as they are what we want
                } else if (leftRight[i] instanceof AbstractInfixExpressionNode) {
                    // Recursively get their primary node type outcome of expression
                    leftRight[i] = expressionTypeVisitor((AbstractInfixExpressionNode)leftRight[i]);
                } else throw new Exception("Invalid children of expression");
            }
        }

        // Get the class names
        for (int i = 0; i < 2; i++){ classNames[i] = leftRight[i].getClass().getSimpleName(); }

        // Check if same classes // TODO: Take decision if we want to cast here also
        if (classNames[0].equals(classNames[1])) {
            return leftRight[0];
        } else { // Return the type to end up with
            switch (PrimaryCompatability.CheckCompatabilty(classNames[0], classNames[1])){
                case "BoolNode":
                    return new BoolNode(false);
                case "IntegerNode":
                    return new IntegerNode(1);
                case "RealNode":
                    return new RealNode(1);
                case "StringNode":
                    return new StringNode();
                case "StringValNode":
                    return new StringValNode("1");
                default:
                    throw new Exception("Unable to cast type of expression!");
            }
        }
    }




    // TODO: We need to figure out if we need 8.6.5, and in case we do, then how we implement it as it is...
    // TODO: ...different in our language
    // Check entire list towards first index

    // 3.6.6 struct and records do not exist in ARDU3k :)

    // TODO: Do we want to have enums?

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        return null;
    }
}
