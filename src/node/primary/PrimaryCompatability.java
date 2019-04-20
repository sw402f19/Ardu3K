package node.primary;

// A class for checking which primary types are compatible
public class PrimaryCompatability {
    public static PrimaryCompatability thisInstance;

    // The class for checking compatibility
    // Returns the name of the class to cast to
    public static String CheckCompatabilty(String node1, String node2){
        try {
            switch (node1) {
                case "BoolNode":
                    return CompatibleBool(node2);
                case "IntegerNode":
                    return CompatibleInteger(node2);
                case "RealNode":
                    return CompatibleReal(node2);
                case "StringNode":
                    return CompatibleString(node2);
                case "StringValNode":
                    return CompatibleStringVal(node2);
                default:
                    throw new Exception("Invalid primitive type of first node!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /* === Check for each type === */

    private static String CompatibleBool(String node2){
        try {
            switch (node2) {
                case "BoolNode":
                    return "BoolNode";
                default:
                    throw new Exception("Invalid primitive type with bool!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private static String CompatibleInteger(String node2){
        try {
            switch (node2) {
                case "IntegerNode": case "StringValNode":
                    return "IntegerNode";
                case "RealNode":
                    return "RealNode";
                default:
                    throw new Exception("Invalid primitive type with integer!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private static String CompatibleReal(String node2){
        try {
            switch (node2) {
                case "RealNode": case "StringValNode":
                case "IntegerNode":
                    return "RealNode";
                default:
                    throw new Exception("Invalid primitive type with real!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private static String CompatibleString(String node2){
        try {
            switch (node2) {
                case "RealNode":    case "StringValNode":
                case "IntegerNode": case "StringNode":
                case "IdentifierNode":
                    return "StringNode";
                default:
                    throw new Exception("Invalid primitive type with string!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private static String CompatibleStringVal(String node2){
        try {
            switch (node2) {
                case "StringValNode":
                    return "StringValNode";
                case "IntegerNode":
                    return "IntegerNode";
                case "RealNode":
                    return "RealNode";
                default:
                    throw new Exception("Invalid primitive type with stringVal!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}
