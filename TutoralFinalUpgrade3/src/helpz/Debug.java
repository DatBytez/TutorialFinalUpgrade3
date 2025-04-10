package helpz;

public class Debug {

	public static boolean ENABLED = true;

	private static final String PURPLE = "\u001B[36m";
	private static final String RESET = "\u001B[0m";

	public static void msg(String message) {
		if (!ENABLED)
			return;
		// Get the calling class name (skip this class in the stack trace)
		String callerClass = "UnknownClass";
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		if (stack.length > 2) {
			callerClass = stack[2].getClassName();

			// Optional: remove package name if you want only class name
			if (callerClass.contains(".")) {
				callerClass = callerClass.substring(callerClass.lastIndexOf(".") + 1);
			}
		}
		System.out.println(PURPLE + "[DEBUG] [" + callerClass + "] " + message + RESET);
	}

	public static void toggle() {
		ENABLED = !ENABLED;
		System.out.println(PURPLE + "[DEBUG] Debugging is now " + (ENABLED ? "ENABLED" : "DISABLED") + RESET);
	}

	public static void enable() {
		ENABLED = true;
		System.out.println(PURPLE + "[DEBUG] Debugging ENABLED" + RESET);
	}

	public static void disable() {
		ENABLED = false;
		System.out.println(PURPLE + "[DEBUG] Debugging DISABLED" + RESET);
	}

	public static boolean isEnabled() {
		return ENABLED;
	}
}