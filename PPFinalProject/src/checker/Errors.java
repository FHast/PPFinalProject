package checker;

/**
 * Error messages for typechecking
 * @author gereon
 *
 */
public abstract class Errors {
	public static final String VARIABLE_DECL_FAIL = "Variable '%s' already declared.";
	public static final String FUNCTION_DECL_FAIL = "Function '%s' already declared.";
	public static final String EXPECTED_CATCHABLE = "Unhandled Exception, expected catchable.";
	public static final String MISSING_GLOBAL = "Variables in global scope must be global.";
	public static final String VARIBALE_NOT_IN_SCOPE = "Variable '%s' not in scope.";
	public static final String EXPECTED_TYPE_BUT_FOUND = "Expected type '%s' but found '%s'";
	public static final String THREAD_NAME_TAKEN = "Thread name '%s' already taken.";
	public static final String CANNOT_JOIN_UNDEFINED =  "Cannot join undefined thread '%s'.";
	public static final String CANNOT_UNLOCK_UNLOCKED = "Cannot unlock unlocked Lock '%s'.";
	public static final String CANNOT_RETURN_IN_VOID = "Cannot return value in void scope.";
	public static final String EXPECTED_BUT_FOUND = "Expected '%s' but found '%s'";
	public static final String FUNCTION_NOT_DEFINED = "Function '%s' not defined.";
	public static final String EXPECTED_NUM_ARGUMENTS = "Expected %s arguments but found %s.";
	public static final String EXCEPTION_NOT_CAUGHT = "Exception is never caught.";
	public static final String MISSING_ASSIGN_OR_SIZE = "Missing assignment or size.";
}
