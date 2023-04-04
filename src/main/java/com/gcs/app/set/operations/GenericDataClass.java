package com.gcs.app.set.operations;

public class GenericDataClass<T> extends Identity {

	T value;
	int id;
	static int idCount;
	
	GenericDataClass(T value){
		this.value = value;
		idCount++;
		id = idCount;
	}
	GenericDataClass(T value, int id){
		this.value = value;
		this.id = id;
		idCount++;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public String toString() {
		return value.toString()+"("+id+")";
	}
	
}
