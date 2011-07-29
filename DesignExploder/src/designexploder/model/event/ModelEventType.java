package designexploder.model.event;

public interface ModelEventType { 
	public static class NamedModelEvent implements ModelEventType {
		private String name;
		public NamedModelEvent(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
}