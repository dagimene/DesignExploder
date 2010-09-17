package org.gems.test.deployment.deployment;

import org.eclipse.ui.plugin.*;
import org.osgi.framework.BundleContext;
import java.util.*;


public class DeploymentPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static DeploymentPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
  

	public DeploymentPlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("org.gems.test.deployment.deployment.DeploymentPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}


	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	public static DeploymentPlugin getDefault() {
		return plugin;
	}


	public static String getResourceString(String key) {
		ResourceBundle bundle = DeploymentPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
