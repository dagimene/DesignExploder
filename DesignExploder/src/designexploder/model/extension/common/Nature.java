package designexploder.model.extension.common;

import designexploder.model.extension.classnode.InmutableNamed;

public interface Nature extends InmutableNamed {

	public final static Nature NONE = new Nature() {
		@Override
		public String getName() {
			return "NONE";
		}
	};
	
}
