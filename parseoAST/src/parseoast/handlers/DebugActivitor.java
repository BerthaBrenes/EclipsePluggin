package parseoast.handlers;

import java.util.Hashtable;

import org.eclipse.osgi.service.debug.DebugOptions;
import org.eclipse.osgi.service.debug.DebugOptionsListener;
import org.eclipse.osgi.service.debug.DebugTrace;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DebugActivitor implements BundleActivator, DebugOptionsListener {
	public static boolean DEBUG = false;
	public static DebugTrace trace;

	@Override
	public void start(BundleContext context) throws Exception {
		Hashtable props = new Hashtable(4);
		props.put(DebugOptions.LISTENER_SYMBOLICNAME,"com.GetInfo.mybundle");
		context.registerService(DebugOptionsListener.class.getName(), this, props);
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void optionsChanged(DebugOptions options) {
		if(trace == null) 
			trace = options.newDebugTrace("com.GetInfo.mybundle");
			DEBUG = options.getBooleanOption("com.GetInfo.mybundle/debug", false);
		
		
	}
	public void prueba() {
		if(DEBUG)
			trace.trace(null, "debugger actividado");
	}

}
