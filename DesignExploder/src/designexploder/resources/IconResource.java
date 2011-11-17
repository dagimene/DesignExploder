package designexploder.resources;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public enum IconResource {

	// classmodel icons --------------------------------------------------------------
	
	CLASS_ICON("images/classmodel/class.gif"),
	ABSTRACT_CLASS_ICON("images/classmodel/abstract_class.gif"),
	INTERFACE_ICON("images/classmodel/interface.gif"),
	ENUM_ICON("images/classmodel/enum.gif"),
	
	FIELD_PUBLIC_ICON("images/classmodel/field_public.png"),
	FIELD_PROTECTED_ICON("images/classmodel/field_protected.png"),
	FIELD_PACKAGE_ICON("images/classmodel/field_package.png"),
	FIELD_PRIVATE_ICON("images/classmodel/field_private.png"),

	METHOD_PUBLIC_ICON("images/classmodel/method_public.gif"),
	METHOD_PROTECTED_ICON("images/classmodel/method_protected.gif"),
	METHOD_PACKAGE_ICON("images/classmodel/method_package.gif"),
	METHOD_PRIVATE_ICON("images/classmodel/method_private.gif"),
	
	GETTER_PUBLIC_ICON("images/classmodel/getter_public.gif"),
	GETTER_PROTECTED_ICON("images/classmodel/getter_protected.gif"),
	GETTER_PACKAGE_ICON("images/classmodel/getter_package.gif"),
	GETTER_PRIVATE_ICON("images/classmodel/getter_private.gif"),
	
	SETTER_PUBLIC_ICON("images/classmodel/setter_public.gif"),
	SETTER_PROTECTED_ICON("images/classmodel/setter_protected.gif"),
	SETTER_PACKAGE_ICON("images/classmodel/setter_package.gif"),
	SETTER_PRIVATE_ICON("images/classmodel/setter_private.gif"),

	ACCESORS_PUBLIC_ICON("images/classmodel/accesors_public.gif"),
	ACCESORS_PROTECTED_ICON("images/classmodel/accesors_protected.gif"),
	ACCESORS_PACKAGE_ICON("images/classmodel/accesors_package.gif"),
	ACCESORS_PRIVATE_ICON("images/classmodel/accesors_private.gif"),

	// IoC icons ---------------------------------------------------------------------

	BEAN_ICON("images/IoC/bean.gif"),  
	CONTEXT_ICON("images/IoC/context.gif"),  
	
	// Beans 
	
	BEAN_FACADE_ICON("images/IoC/bean_facade.gif"),  
	BEAN_AUTO_ICON("images/IoC/bean_auto.gif"),  
	BEAN_STATELESS_ICON("images/IoC/bean_stateless.gif"),  
	BEAN_FACTORY_ICON("images/IoC/bean_factory.gif"),  

	// Bean fields

	BEAN_FIELD_PROXY_ICON("images/IoC/bean_field_proxy.gif"),  
	BEAN_FIELD_COLLECTION_ICON("images/IoC/bean_field_collection.gif"),  
	BEAN_FIELD_TREE_ICON("images/IoC/bean_field_tree.gif"),
	
	// IoC aware method

	BEAN_METHOD_FACTORY_ICON("images/IoC/bean_method_factory.gif"),  
	BEAN_METHOD_INIT_ICON("images/IoC/bean_method_init.gif"),  
	BEAN_METHOD_INSTANTIATE_ICON("images/IoC/bean_method_instantiate.gif"),  
    BEAN_METHOD_ACTIVATE_ICON("images/IoC/bean_method_activate.gif"),
    BEAN_METHOD_DESTROY_ICON("images/IoC/bean_method_destroy.gif"),

	// Injection

	INJECTION_METHOD_PUBLIC_ICON("images/IoC/injection_method_public.gif"),  
	INJECTION_METHOD_PROTECTED_ICON("images/IoC/injection_method_protected.gif"),  
	INJECTION_METHOD_PACKAGE_ICON("images/IoC/injection_method_package.gif"),  
	INJECTION_METHOD_PRIVATE_ICON("images/IoC/injection_method_private.gif"),  

	INJECTION_FIELD_PUBLIC_ICON("images/IoC/injection_field_public.gif"),  
	INJECTION_FIELD_PROTECTED_ICON("images/IoC/injection_field_protected.gif"),  
	INJECTION_FIELD_PACKAGE_ICON("images/IoC/injection_field_package.gif"),  
	INJECTION_FIELD_PRIVATE_ICON("images/IoC/injection_field_private.gif"),  

	INJECTION_ERROR_ICON("images/IoC/injection_error.gif"),  
	INJECTION_WARNING_ICON("images/IoC/injection_warning.gif"),
	
	// Others

	BEAN_CONTEXT_ICON("images/IoC/bean_context.gif"),  

	// common icons ------------------------------------------------------------------
	
	ERROR_ICON("images/common/error.gif"),
	WARNING_ICON("images/common/warning.gif"),
	GRAPH_ICON("images/common/graph.gif");
	
	private final String path;
	private Image image;
	
	IconResource(String path) {
		this.path = path;
	}
	
	public Image getImage() {
		return image != null ? image : createImage();
	}
	
	private Image createImage() {
		return image = new Image(Display.getCurrent(), IconResource.class.getResourceAsStream(path));
	}

	public String getPath() {
		return path;
	}
}
