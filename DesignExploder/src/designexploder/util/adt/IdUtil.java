package designexploder.util.adt;

import java.util.Iterator;

import designexploder.model.extension.classnode.Type;

public class IdUtil {

	public static final String CLASS_TYPE = "class";
	public static final String BEAN_TYPE = "bean";
	public static final String CONTEXT_TYPE = "ctx";
	public static final String CONNECTION_TYPE = "conn";

	public static ID parseId(String id) {
		String[] splitted = id.split("://", 2);
		if(splitted.length != 2) {
			return null;
		}
		int ix = splitted[1].indexOf('?'); 
		if(ix != -1) {
			String[] nameAndParams = splitted[1].split("\\?", 2);
			if(nameAndParams[1].indexOf("&") != -1) {
				// Parse connection: Optional ordered parameters are [N] [S] [T]
				if(splitted[0] != CONNECTION_TYPE) return null;
				String[] params = nameAndParams[1].split("&");
				Integer number = null;
				Integer source = null;
				Integer target = null;
				for(int i = 0; i < params.length; i++) {
					Integer value = getNumberFromParam("N", params[i]);
					if(value != null) {
						if(number != null) return null;
						number = value;
					} else {
						value = getNumberFromParam("S", params[i]);
						if(value != null) {
							if(source != null) return null;
							source = value; 
						} else {
							value = getNumberFromParam("T", params[i]);
							if(value == null || target != null) return null;
							target = value;
						}
					}
				}
				return new ConnectionID(splitted[0], nameAndParams[0], number != null ? number : -1, source != null ? source : -1, target != null ? target : -1);
			} else {
				// Parse node: Parameter is N
				Integer number = getNumberFromParam("N", nameAndParams[1]);
				return number != null ? new ID(splitted[0], nameAndParams[0], number) : null; 
			}
		}
		return new ID(splitted[0], splitted[1]);
	}
	
	private static Integer getNumberFromParam(String paramName, String string) {
		String prefix = paramName + "=";
		if(string.startsWith(prefix)) {
			return parseIntOrNull(string.substring(prefix.length()));
		}
		return null;
	}

	private static Integer parseIntOrNull(String string) {
		try {
			 return Integer.valueOf(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static ID createTypeId(Type type) {
		return IdUtil.createClassId(type.getName());
	}

	public static ID createClassId(String name) {
		return createId(CLASS_TYPE, name);
	}
	
	public static ID createClassId(String name, int number) {
		return createId(CLASS_TYPE, name, number);
	}

	public static ID createBeanId(String name) {
		return createId(BEAN_TYPE, name);
	}

	public static ID createBeanId(String name, int number) {
		return createId(BEAN_TYPE, name, number);
	}
	
	public static ID createContextId(String name) {
		return createId(CONTEXT_TYPE, name);
	}

	public static ConnectionID createConnectionId(String sourceId, String targetId) {
		return creteConnectionId(sourceId, targetId, -1);
	}

	public static ConnectionID creteConnectionId(String sourceId, String targetId, int number) {
		ID source = IdUtil.parseId(sourceId);
		ID target = IdUtil.parseId(targetId);
		if(source == null || target == null) {
			throw new IllegalArgumentException("Id "+ (source == null ? sourceId : targetId) + " is invalid.");
		}
		return new ConnectionID(CONNECTION_TYPE, createConnectionIdName(source, target), number, source.number, target.number);
	}

	private static ID createId(String type, String name, int number) {
		return new ID(type, name, number);
	}
	
	private static ID createId(String type, String name) {
		return new ID(type, name);
	}

	public static class ID {
		public final String type;
		public final String name;
		public final int number;
		
		private ID(String type, String name) {
			this(type, name, -1);
		}
		
		private ID(String type, String name, int number) {
			this.type = type.intern();
			this.name = name.intern();
			this.number = number;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			if(type != null) {
				builder.append(type);
				builder.append("://");
			}
			builder.append(name);
			appendParameters(builder);
			return builder.toString();
		}

		protected boolean appendParameters(StringBuilder builder) {
			if(number != -1) {
				builder.append("?N=");
				builder.append(String.valueOf(number));
				return true;
			}
			return false;
		}
	}
	
	public static class ConnectionID extends ID {
		
		public final int sourceNumber;
		public final int targetNumber;
		
		public ConnectionID(String type, String name, int number, int sourceNumber, int targetNumber) {
			super(type, name, number);
			this.sourceNumber = sourceNumber;
			this.targetNumber = targetNumber;
		}

		@Override
		protected boolean appendParameters(StringBuilder builder) {
			boolean appended = super.appendParameters(builder);
			if(sourceNumber != -1) {
				builder.append(appended ? "&" : "?");
				builder.append("S=");
				builder.append(String.valueOf(sourceNumber));
				appended = true;
			}
			if(targetNumber != -1) {
				builder.append(appended ? "&" : "?");
				builder.append("T=");
				builder.append(String.valueOf(targetNumber));
				appended = true;
			}
			return appended;
		}
		
	}
	
	public static String createConnectionIdName(ID source, ID target) {
		StringBuilder builder = new StringBuilder();
		builder.append(source.type);
		builder.append(':');
		builder.append(source.name);
		builder.append("::");
		builder.append(target.type);
		builder.append(":");
		builder.append(target.name);
		return builder.toString();
	}

	public static Iterator<String> getIdsIterator(String type, String name) {
		final String header = createId(type, name).toString();
		return new Iterator<String>() {
			private int number = -1;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public String next() {
				if(number != -1) {
					return header + "?N=" + String.valueOf(number++);
				} else {
					number++;
				}
				return header;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
}
