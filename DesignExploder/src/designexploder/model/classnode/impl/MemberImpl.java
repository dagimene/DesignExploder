package designexploder.model.classnode.impl;

import java.util.Collections;
import java.util.List;

import designexploder.model.classnode.Member;
import designexploder.model.classnode.Modifier;

public class MemberImpl implements Member {
	
	private String name = "";
	private String symbol = "";
	private List<Modifier> modifiers = Collections.emptyList();
	private String type;
	private List<String> stereotypes = Collections.emptyList();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<Modifier> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(List<String> stereotypes) {
		this.stereotypes = stereotypes;
	} 
}
